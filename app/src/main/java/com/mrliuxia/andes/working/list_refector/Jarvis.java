package com.mrliuxia.andes.working.list_refector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * Author: liuxia
 * Data: 1/11/21
 *
 * @blame: liuxia
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Jarvis {

    JarvisCommand command();

    Class<?> type() default Void.class;

    Class<?> listItemType() default Void.class;

}
