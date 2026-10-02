package JDKProxy.staticProxy;

public class Main {
    public static void main(String[] args) {
        NotificationServiceImpl real = new NotificationServiceImpl();
        NotificationServiceProxy proxy = new NotificationServiceProxy(real);

        proxy.sendEmail("Eng.EZZ","Random Message");
        proxy.sendSms("Eng.EZZ","Random Message");
    }
}
