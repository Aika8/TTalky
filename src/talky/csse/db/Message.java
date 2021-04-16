package talky.csse.db;

import java.sql.Timestamp;

public class Message {

    private Long id;
    private Long chatId;
    private Long userId;
    private Long senderId;
    private String message_text;
    private Boolean IsRead;
    private Timestamp sent_date;

    public Message(Long id, Long chatId, Long userId, Long senderId, String message_text, Boolean isRead, Timestamp sent_date) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.senderId = senderId;
        this.message_text = message_text;
        IsRead = isRead;
        this.sent_date = sent_date;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Boolean getRead() {
        return IsRead;
    }

    public void setRead(Boolean read) {
        IsRead = read;
    }

    public Timestamp getSent_date() {
        return sent_date;
    }

    public void setSent_date(Timestamp sent_date) {
        this.sent_date = sent_date;
    }
}
