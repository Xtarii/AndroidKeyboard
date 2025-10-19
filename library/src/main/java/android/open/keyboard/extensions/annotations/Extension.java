package android.open.keyboard.extensions.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Keyboard Extension
 * <p/>
 * This is used to extend the <code>keyboard</code>
 * with extra features.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {
    /**
     * Extension ID
     */
    String ID();

    /**
     * Extension description
     */
    String description() default "My Custom Extension";

    /**
     * Extension version
     */
    String version() default "v1.0";
}
