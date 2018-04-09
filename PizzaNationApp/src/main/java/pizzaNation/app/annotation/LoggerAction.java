package pizzaNation.app.annotation;

import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by George-Lenovo on 09/04/2018.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAction {

    Action action();

    TableEnum table();
}
