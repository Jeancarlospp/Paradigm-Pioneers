
package ec.edu.espe.EDICOMPUCMS.model;

public class Notification {
    private int notificationID;
    private String type;
    private String message;
    private String content;

    public Notification(int notificationID, String type, String message, String content) {
        this.notificationID = notificationID;
        this.type = type;
        this.message = message;
        this.content = content;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{" + "notificationID=" + notificationID + ", type=" + type + ", message=" + message + ", content=" + content + '}';
    }
}
