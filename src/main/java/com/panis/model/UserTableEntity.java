package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 03/12/2016.
 */
@Entity
@Table(name = "user_table", schema = "committee_admin", catalog = "")
public class UserTableEntity {
    private int uId;
    private String uName;
    private int uAge;
    private String uSex;
    private String phoneNumber;
    private String userName;
    private String password;
    private Integer power;

    @Id
    @Column(name = "u_id", nullable = false)
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_name", nullable = false, length = 40)
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_age", nullable = false)
    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    @Basic
    @Column(name = "u_sex", nullable = false, length = 11)
    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, length = 15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 40)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "power", nullable = true)
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTableEntity that = (UserTableEntity) o;

        if (uId != that.uId) return false;
        if (uAge != that.uAge) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uSex != null ? !uSex.equals(that.uSex) : that.uSex != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (power != null ? !power.equals(that.power) : that.power != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + uAge;
        result = 31 * result + (uSex != null ? uSex.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "uId:"+uId+"\nuName:"+uName+"\nuAge:"+uAge+"\nuSex:"+uSex+"\nphoneNumber:"+phoneNumber+"\npassword:"+password+"\npower:"+power;
    }
}
