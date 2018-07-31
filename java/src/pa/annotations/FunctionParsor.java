package pa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FunctionParsor {

    String createdBy() default "Unknown";

    String[] apiRoutes() default "none";

    String lastModified();

    String description() default "";

}