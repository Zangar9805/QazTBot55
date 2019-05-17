package kz.zangpro.models;

import javax.persistence.*;


@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "userName")
    private String userName;
    @Column (name = "Fullname")
    private String fullName;
    @Column (name = "chatId")
    private String chatId;
    @Column (name = "regDate")
    private String regdate;
    @Column (name = "cityName")
    private String cityName;
    @Column (name = "menuState")
    private String menuState;

    public User() {
    }

    public User(String name, String userName, String fullName, String chatId, String regdate, String cityName, String menuState) {
        this.name = name;
        this.userName = userName;
        this.fullName = fullName;
        this.chatId = chatId;
        this.regdate = regdate;
        this.cityName = cityName;
        this.menuState = menuState;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getChatId() {
        return chatId;
    }

    public String getRegdate() {
        return regdate;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", chatId='" + chatId + '\'' +
                ", regdate='" + regdate + '\'' +
                ", cityName='" + cityName + '\'' +
                ", menuState='" + menuState + '\'' +
                '}';
    }
}
