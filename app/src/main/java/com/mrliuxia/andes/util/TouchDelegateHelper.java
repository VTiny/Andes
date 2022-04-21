package com.mrliuxia.andes.util;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;

import com.mrliuxia.andes.R;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/4
 */
public class TouchDelegateHelper {

    public static void expandViewClickArea(final View view, final int left, final int top, final int right, final int bottom) {
        if (view == null || !(view.getParent() instanceof View)) {
            return;
        }
        final View parent = (View) view.getParent();
        parent.post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.left -= left;
                bounds.top -= top;
                bounds.right += right;
                bounds.bottom += bottom;
                parent.setTouchDelegate(new TouchDelegate(bounds, view));

            }
        });
    }
//    public static void expandViewClickArea(final View view, final int left, final int top, final int right, final int bottom) {
//        if (view == null || !(view.getParent() instanceof View)) {
//            return;
//        }
//        View parent = (View) view.getParent();
//        expandViewClickArea(view, parent, left, top, right, bottom);
//    }

    public static void expandViewClickArea(final View view, final View parent,
                                           final int left, final int top, final int right, final int bottom) {
        if (view == null || parent == null) {
            return;
        }
        parent.post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                Log.i("lxlx", "expand top=" + top + ",left=" + left + ",bottom=" + bottom + ",right=" + right);
                Log.i("lxlx", "view height=" + view.getHeight() + ",width=" + view.getWidth());
                Log.i("lxlx", "rect top=" + bounds.top + ",left=" + bounds.left + ",bottom=" + bounds.bottom + ",right=" + bounds.right);

                int expandLeft, remainingLeft, expandTop, remainingTop, expandRight, remainingRight, expandBottom, remainingBottom;
                if (left < bounds.left) {
                    expandLeft = left;
                    remainingLeft = 0;
                } else {
                    expandLeft = bounds.left;
                    remainingLeft = left - bounds.left;
                }
                if (top < bounds.top) {
                    expandTop = top;
                    remainingTop = 0;
                } else {
                    expandTop = bounds.top;
                    remainingTop = top - bounds.top;
                }
                if (right < bounds.right - view.getWidth()) {
                    expandRight = right;
                    remainingRight = 0;
                } else {
                    expandRight = bounds.right - view.getWidth();
                    remainingRight = right - (bounds.right - view.getWidth());
                }
                if (bottom < bounds.bottom - view.getHeight()) {
                    expandBottom = bottom;
                    remainingBottom = 0;
                } else {
                    expandBottom = bounds.bottom - view.getHeight();
                    remainingBottom = bottom - (bounds.bottom - view.getHeight());
                }

                bounds.left -= expandLeft;
                bounds.top -= expandTop;
                bounds.right += expandRight;
                bounds.bottom += expandBottom;
                parent.setTouchDelegate(new TouchDelegate(bounds, view));
                if (remainingLeft > 0 || remainingTop > 0 || remainingRight > 0 || remainingBottom > 0) {
                    expandViewClickArea(parent, ((View) parent.getParent()), remainingLeft, remainingTop, remainingRight, remainingBottom);
                }

            }
        });

    }

    public static void expandClickAreaByAddForkView(final View view, final int left, final int top, final int right, final int bottom) {
        ViewGroup parent = (ViewGroup) view.getParent();
        View forkView = new View(view.getContext());
        forkView.setBackgroundColor(view.getResources().getColor(R.color.red));
        parent.addView(forkView, view.getLayoutParams());
//        forkView.bringToFront();
//        parent.requestLayout();
        forkView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                view.onTouchEvent(event);
                return true;
            }
        });
//        forkView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                view.performClick();
//            }
//        });
        expandViewClickArea(forkView, parent, left, top, right, bottom);

    }

}
