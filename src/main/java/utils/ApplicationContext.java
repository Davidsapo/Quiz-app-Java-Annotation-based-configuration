package utils;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContext {

    private static FileSystemXmlApplicationContext applicationContext;

    public static void create() {
        if (applicationContext == null) {
            applicationContext = new FileSystemXmlApplicationContext("classpath:config/ApplicationContext.xml");
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
