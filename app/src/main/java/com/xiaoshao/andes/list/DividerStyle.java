package com.xiaoshao.andes.list;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * Author: liuxiao
 * Date: 2019/3/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DividerStyle {

    enum Style {

        NONE(0),
        SIMPLE(1 << 1),
        LARGE(1 << 2);

        private int mPriority;

        Style(int priority) {
            mPriority = priority;
        }

        public int priority() {
            return mPriority;
        }
    }

    Style top() default Style.SIMPLE;

    Style bottom() default Style.SIMPLE;

}
