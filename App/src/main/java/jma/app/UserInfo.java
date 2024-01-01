package jma.app;

public class UserInfo {
    private static UserInfo instance = new UserInfo();

    private String userName;
    private String fullName;

    private UserInfo() {}

    public static UserInfo getInstance() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
