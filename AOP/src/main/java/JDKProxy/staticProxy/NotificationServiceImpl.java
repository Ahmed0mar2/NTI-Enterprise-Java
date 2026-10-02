package JDKProxy.staticProxy;

public class NotificationServiceImpl implements NotificationService{
    @Override
    public void sendEmail(String to, String message) {
        System.out.println("Sending " + message + "to: " + to + " through email...");
    }

    @Override
    public void sendSms(String to, String message) {
        System.out.println("Sending " + message + "to: " + to + " through SMS...");
    }
}
