package pizzaNation.app.annotation;

import pizzaNation.app.enums.Action;
import pizzaNation.app.enums.TableEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerAction {

    Action action();

    TableEnum table();
}
