package util;

public class NotificationThread extends Thread {

    private String message;

    public NotificationThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {

        System.out.println(
                "\n[NOTIFICATION] " + message
        );
    }
}