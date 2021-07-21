package top.dreamcenter.todo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TodoB {
    int progress() default 0; // progress of the method realization
    Degree degree() default Degree.TIP; // how to deal if not realized
}
