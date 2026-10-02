package JDKProxy.dynamic;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendEmail(String to, String message) {
        System.out.println(message);
    }

    @Override
    public void sendSms(String to, String message) {
        System.out.println(message);
    }
}
