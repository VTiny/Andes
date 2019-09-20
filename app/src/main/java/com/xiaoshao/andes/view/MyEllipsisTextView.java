package com.xiaoshao.andes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.xiaoshao.andes.util.StringUtil;

import java.util.List;

/**
 * Description:
 * Author: liuxiao
 * Data: 2019-09-08
 *
 * @blame: liuxiao
 */
@SuppressLint("AppCompatCustomView")
public class MyEllipsisTextView extends TextView {

    public MyEllipsisTextView(Context context) {
        this(context, null);
    }

    public MyEllipsisTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEllipsisTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("lxlx", "onMeasure: width = " + MeasureSpec.getSize(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("lxlx", "onLayout: left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Log.i("lxlx", "setText: " + text);
        super.setText(text, type);
    }

    public void setAutoEllipsisText(CharSequence text) {
        if (text == null || text.length() == 0) {
            return;
        }
        String originText = text.toString();
        List<String> labelValues = StringUtil.getLabelValues(originText, "bre");


    }

    private float getPaintWidth(String s) {
        return getPaintWidth(s, 0, s.length());
    }

    private float getPaintWidth(String s, int startIndex, int endIndex) {
        if (s == null || s.length() == 0 || getPaint() == null
                || (startIndex | endIndex | (endIndex - startIndex) | (s.length() - endIndex)) < 0) {
            return 0;
        }
        return getPaint().measureText(s, startIndex, endIndex);
    }
}
