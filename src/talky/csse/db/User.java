package talky.csse.db;

public class User {

    private Long id;
    private String email;
    private String password;
    private String full_name;
    private String birthday;
    private String pictureUrl;

    public User() {
    }

    public User(Long id, String email, String password, String full_name, String birthday, String pictureUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.birthday = birthday;
        this.pictureUrl = pictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
