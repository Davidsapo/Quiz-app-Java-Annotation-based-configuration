package quiz.app.utils;

import quiz.app.configuration.AppContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext {

    private static AnnotationConfigApplicationContext applicationContext;

    public static void create() {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext(AppContextConfig.class);
        }
        System.out.println("Application context opened.");
    }

    public static void close() {
        applicationContext.close();
        System.out.println("Application context closed.");
    }

    public static <T> T getBean(String id, Class<T> T) {
        return applicationContext.getBean(id, T);
    }
}
