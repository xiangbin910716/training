package entity;

import java.util.Date;

public class User {

    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 密码明文
     */
    private String pwdPlain;

    /**
     * 密码密文
     */
    private String pwdCipher;

    /**
     * 注册时间
     */
    private Date regDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPwdPlain() {
        return pwdPlain;
    }

    public void setPwdPlain(String pwdPlain) {
        this.pwdPlain = pwdPlain == null ? null : pwdPlain.trim();
    }

    public String getPwdCipher() {
        return pwdCipher;
    }

    public void setPwdCipher(String pwdCipher) {
        this.pwdCipher = pwdCipher == null ? null : pwdCipher.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}