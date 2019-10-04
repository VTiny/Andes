package com.xiaoshao.andes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.xiaoshao.andes.MyTextView;
import com.xiaoshao.andes.R;
import com.xiaoshao.andes.util.DataUtil;
import com.xiaoshao.andes.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Data: 2019-10-04
 *
 * @blame: liuxiao
 */
public class AutoParseLabelTextView extends MyTextView {

    public static final String LABEL_NAME_LINK = "em";
    public static final String LABEL_NAME_ELLIPSIS = "bre";
    public static final String SPLITTER_LINK = "\\|";
    public static final String ELLIPSIS_POINTS = "...";

    private int mLinkTextColor;
    private int mLinkTextNightColor;
    private boolean mEnableClick;
    private boolean mHasEllipsisLabel;
    private int mMaxWidth;

    public AutoParseLabelTextView(Context context) {
        this(context, null);
    }

    public AutoParseLabelTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoParseLabelTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoParseLabelTextView);
        mLinkTextColor = typedArray.getColor(R.styleable.AutoParseLabelTextView_link_text_color, ContextCompat.getColor(context, R.color.red));
        mLinkTextNightColor = typedArray.getColor(R.styleable.AutoParseLabelTextView_link_text_night_color, ContextCompat.getColor(context, R.color.red));
        mEnableClick = typedArray.getBoolean(R.styleable.AutoParseLabelTextView_link_enable_click, Boolean.TRUE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (getPaint().measureText(getText() + " ") > mMaxWidth && mHasEllipsisLabel) {
            processCustomEllipsis(getText());
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

//    @Override
//    public void setText(CharSequence text, BufferType type) {
//        if (text != null) {
//
//        }
//        super.setText(text, type);
//    }

    public void loadText(CharSequence text) {
        loadText(text, BufferType.NORMAL);
    }

    public void loadText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            markIfNeedEllipsis(text);
            text = processLinkLabel(text);
        }
        setText(text, type);
    }

    private CharSequence processLinkLabel(@NonNull CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        SpannableStringBuilder result = new SpannableStringBuilder();
        List<Pair<CharSequence, Boolean>> dataList = parseLabel(text, LABEL_NAME_LINK);
        if (dataList == null) {
            return text;
        }
        for (Pair<CharSequence, Boolean> data : dataList) {
            if (TextUtils.isEmpty(data.first)) {
                continue;
            }
            if (data.second) {
                String[] split = data.first.toString().split(SPLITTER_LINK);
                String show = split[0];
                final String link = split.length > 1 ? split[1] : "";
                result.append(show);
                Object span;
                if (mEnableClick && !TextUtils.isEmpty(link)) {
                    span = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            if (mEnableClick && !TextUtils.isEmpty(link) && getContext() != null) {
                                Toast.makeText(widget.getContext(), "click", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setUnderlineText(false);
                            ds.setColor(mLinkTextColor);
                        }
                    };
                    setMovementMethod(LinkMovementMethod.getInstance()); //todo lx
                } else {
                    span = new ForegroundColorSpan(mLinkTextColor);
                }
                result.setSpan(span, result.length() - show.length(), result.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            } else {
                result.append(data.first);
            }
        }
        return result;
    }

    private void markIfNeedEllipsis(@NonNull CharSequence text) {
        mHasEllipsisLabel = DataUtil.isValid(StringUtil.getLabelValues(text.toString(), LABEL_NAME_ELLIPSIS));
    }

    private void processCustomEllipsis(@NonNull CharSequence text) {
        if (text.length() == 0) {
            return;
        }
        List<Pair<CharSequence, Boolean>> dataList = parseLabel(text, LABEL_NAME_ELLIPSIS);
        CharSequence result;
        for (int i = 0, n = dataList.size(); i < n; i++) {
            Pair<CharSequence, Boolean> data = dataList.get(i);
            if (TextUtils.isEmpty(data.first) || !data.second) {
                continue;
            }
            CharSequence shrinkingStr = data.first;
            while (!TextUtils.isEmpty(shrinkingStr)) {
                shrinkingStr = shrinkingStr.subSequence(0, shrinkingStr.length() - 1);
                result = generateShrunkText(dataList, i, shrinkingStr);
                if (mMaxWidth > 0 && getPaint() != null && getPaint().measureText(result.toString()) < mMaxWidth) {
                    setText(result);
                    return;
                }
            }
            dataList.set(i, Pair.create(shrinkingStr, true));
        }
    }

    private CharSequence generateShrunkText(List<Pair<CharSequence, Boolean>> dataList, int shrinkingIndex, CharSequence shrinkingStr) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        for (int i = 0, n = dataList.size(); i < n; i++) {
            if (i == shrinkingIndex) {
                sb.append(shrinkingStr).append(ELLIPSIS_POINTS);
            } else {
                sb.append(dataList.get(i).first);
            }
        }
        return sb.append(" ");
    }

    private List<Pair<CharSequence, Boolean>> parseLabel(CharSequence s, String labelName) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        List<Pair<CharSequence, Boolean>> resultList = new ArrayList<>();
        List<CharSequence> labelValues = StringUtil.getLabelValues(s, labelName);
        int index = 0;
        if (labelValues != null) {
            for (CharSequence labelValue : labelValues) {
                if (TextUtils.isEmpty(labelValue)) {
                    continue;
                }
                String wrapped = StringUtil.wrapLabel(labelName, labelValue.toString());
                int startIndex = s.toString().indexOf(wrapped);
                if (startIndex > index) {
                    resultList.add(Pair.create(s.subSequence(index, startIndex), false));
                }
                resultList.add(Pair.create(labelValue, true));
                index = startIndex + wrapped.length();
            }
        }
        if (index < s.length()) {
            resultList.add(Pair.create(s.subSequence(index, s.length()), false));
        }
        return resultList;
    }


}
