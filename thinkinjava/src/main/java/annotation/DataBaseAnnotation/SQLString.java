package annotation.DataBaseAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/7 15:09
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    int value() default 0;//当使用这个注解时，如果只需要给value赋值可以不写value=
    String name() default "";
    Constraints constraints() default @Constraints;
}
