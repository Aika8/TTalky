package talky.csse.db;

import java.sql.Timestamp;

public class Chat {


    private Long id;
    private User user;
    private User opponent;
    private Timestamp createdDate;
    private String latestMessage;
    private Timestamp latestMessageTime;

    public Chat(Long id, User user, User opponent, Timestamp createdDate, String latestMessage, Timestamp latestMessageTime) {
        this.id = id;
        this.user = user;
        this.opponent = opponent;
        this.createdDate = createdDate;
        this.latestMessage = latestMessage;
        this.latestMessageTime = latestMessageTime;
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public Timestamp getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(Timestamp latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
    }
}
