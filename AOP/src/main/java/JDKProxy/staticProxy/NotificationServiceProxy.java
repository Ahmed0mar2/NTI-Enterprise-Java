package JDKProxy.staticProxy;

public class NotificationServiceProxy implements NotificationService{

    private NotificationServiceImpl real;

    public NotificationServiceProxy(NotificationServiceImpl real) {
        this.real = real;
    }

    @Override
    public void sendEmail(String to, String message) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("LOGGING BEFORE: " + methodName);
        real.sendEmail(to, message);
        System.out.println("LOGGING AFTER: " + methodName);
    }

    @Override
    public void sendSms(String to, String message) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("LOGGING BEFORE: " + methodName);
        real.sendSms(to, message);
        System.out.println("LOGGING AFTER: " + methodName);
    }
}
