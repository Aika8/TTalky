package talky.csse.db;

import java.sql.Timestamp;

public class Post {

    private Long id;
    private User user;
    private String title;
    private String short_content;
    private String content;
    private Timestamp postDate;

    public Post() {
    }

    public Post(Long id, User user, String title, String short_content, String content, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.postDate = postDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
