package swing;

import java.io.Serializable;
import java.time.LocalDate;

public class Message implements Serializable {
    private String userFrom;

    private String userTo;
    private String text;

    private LocalDate date;

    public Message(String userFrom, String userTo, String text) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
        this.date = LocalDate.now();
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
