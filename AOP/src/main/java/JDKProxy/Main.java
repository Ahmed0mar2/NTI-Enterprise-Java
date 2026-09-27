package JDKProxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationServiceImpl();
        NotificationService proxy = (NotificationService) Proxy.newProxyInstance(NotificationService.class.getClassLoader(),
                new Class[]{NotificationService.class},
                new LoggingHandler(notificationService));

        proxy.sendEmail("ahmed", "idk");
    }
}
