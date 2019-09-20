package com.xiaoshao.andes.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoshao.andes.R;
import com.xiaoshao.andes.base.BaseApplication;
import com.xiaoshao.andes.base.BaseFragment;

/**
 * Author: liuxiao
 * Created: 2019/1/12 18:29
 * Description:
 */
public class MainHomeTabFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.main_home_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        String leftStr = "【LEFT】1983年小巷，12月晴朗【end】";
//        String rightStr = ;
//        TextView leftTv = view.findViewById(R.id.tv_left);
//        TextView rightTv = view.findViewById(R.id.tv_right);
//
//        final int lastFixSize = "【end】".length();
//        String remainStr = rightStr.split()

        TextView tv = view.findViewById(R.id.tv_right);
        TextView ltv = view.findViewById(R.id.tv_left);
        if (true) {
            tv.setText("【RIGHT】为你弹奏肖邦的夜曲【end】");
            ltv.setText("【LEFT】1983年小巷，12月晴朗【end】");
            return;
        }
//        String content = "【RIGHT】为你弹奏肖邦的夜曲，纪念我死去的爱情，而我为你隐姓埋名，在月光下弹琴【END】";
//        String fixEndStr = "【END】";
//        String ellipsisStr = "...";
//        String remainStr = content.split(fixEndStr)[0];
//        int screenWidth = getScreenWidth();
//        int endIndex = content.length() - fixEndStr.length();
//        String s = remainStr;
//        for (int i = endIndex; i >= 0 ; i--) {
//            s = remainStr.substring(0, i) + ellipsisStr;
//            if (getPaintWidth(s + fixEndStr, tv) < screenWidth) {
//                break;
//            }
//        }
//        tv.setText(s + fixEndStr);
//
//        processStr(content, 5);
        SpannableStringBuilder content = new SpannableStringBuilder();
//        content.append("【RIGHT】为你弹奏肖邦的夜曲，纪念我死去的爱情，而我为你隐姓埋名，在月光下弹琴【END】");
        content.append("【RIGHT】为你弹奏肖邦的夜曲，纪念我");

//        ForegroundColorSpan highlightSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.blue));
//        ForegroundColorSpan highlightSpan1 = new ForegroundColorSpan(getContext().getResources().getColor(R.color.red));
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getContext().getResources().getColor(R.color.blue));
            }
        };
        content.clearSpans();
        content.setSpan(clickSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        content.setSpan(highlightSpan, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        content.setSpan(highlightSpan1, 1, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(content);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(Color.TRANSPARENT);

//        tv.setText(processStr(content, 5));

    }

    private String processStr(String s, int fixLength) {
        if (TextUtils.isEmpty(s) || fixLength == 0) {
            return s;
        }
        TextView tv = getView().findViewById(R.id.tv_right);
        String end = s.substring(s.length() - fixLength);
        String remain = s.substring(0, s.length() - fixLength);
        int maxWidth = getScreenWidth();
        float ratio = maxWidth / getPaintWidth(s, tv);
        String pref = remain.substring(0, (int) (remain.length() * ratio));
        boolean b = canDisplay(getResultStr(pref, end), tv, maxWidth);
        while (!b) {
            pref = pref.substring(0, pref.length() - 1);
            b = canDisplay(getResultStr(pref, end), tv, maxWidth);
        }
        return getResultStr(pref, end);
    }

    private String getResultStr(String remain, String end) {
        return remain + "..." + end;
    }

    private boolean canDisplay(String s, TextView tv, int maxWidth) {
        return getPaintWidth(s, tv) < maxWidth;
    }

    private float getPaintWidth(String s, TextView tv) {
        TextPaint textPaint = new TextPaint();
//        float textSize = getResources().getDisplayMetrics().scaledDensity * 15;
        float textSize = tv.getTextSize();
        textPaint.setTextSize(textSize);
        return textPaint.measureText(s);
    }

    private int getScreenWidth() {
        return getActivity().getWindowManager().getDefaultDisplay().getWidth();
    }

    private int getMeasureWidth(String s, TextView tv) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv.measure(spec, spec);
        return tv.getMeasuredWidth();
    }


}
