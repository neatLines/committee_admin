package com.panis.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by fuyipeng on 2016/11/10.
 */
@Entity
@Table(name = "user_table", schema = "res_committee_admin", catalog = "")
public class UserTableEntity {
    private int uId;
    private String uName;
    private int uAge;
    private byte uSex;
    private String phoneNumber;
    private String userName;
    private String password;
    private Byte power;
    private Collection<HouseTableEntity> houseTablesByUId;
    private Collection<ParkTableEntity> parkTablesByUId;
    private PersonnelTableEntity personnelTableByUId;
    private Collection<PropertyLogTableEntity> propertyLogTablesByUId;
    private Collection<PublicityTableEntity> publicityTablesByUId;
    private Collection<RuleBreakTableEntity> ruleBreakTablesByUId;
    private Collection<RuleBreakTableEntity> ruleBreakTablesByUId_0;

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
    @Column(name = "u_sex", nullable = false)
    public byte getuSex() {
        return uSex;
    }

    public void setuSex(byte uSex) {
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
    public Byte getPower() {
        return power;
    }

    public void setPower(Byte power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTableEntity that = (UserTableEntity) o;

        if (uId != that.uId) return false;
        if (uAge != that.uAge) return false;
        if (uSex != that.uSex) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
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
        result = 31 * result + (int) uSex;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userTableByUId")
    public Collection<HouseTableEntity> getHouseTablesByUId() {
        return houseTablesByUId;
    }

    public void setHouseTablesByUId(Collection<HouseTableEntity> houseTablesByUId) {
        this.houseTablesByUId = houseTablesByUId;
    }

    @OneToMany(mappedBy = "userTableByUId")
    public Collection<ParkTableEntity> getParkTablesByUId() {
        return parkTablesByUId;
    }

    public void setParkTablesByUId(Collection<ParkTableEntity> parkTablesByUId) {
        this.parkTablesByUId = parkTablesByUId;
    }

    @OneToOne(mappedBy = "userTableByUId")
    public PersonnelTableEntity getPersonnelTableByUId() {
        return personnelTableByUId;
    }

    public void setPersonnelTableByUId(PersonnelTableEntity personnelTableByUId) {
        this.personnelTableByUId = personnelTableByUId;
    }

    @OneToMany(mappedBy = "userTableByChangeUId")
    public Collection<PropertyLogTableEntity> getPropertyLogTablesByUId() {
        return propertyLogTablesByUId;
    }

    public void setPropertyLogTablesByUId(Collection<PropertyLogTableEntity> propertyLogTablesByUId) {
        this.propertyLogTablesByUId = propertyLogTablesByUId;
    }

    @OneToMany(mappedBy = "userTableByWriterId")
    public Collection<PublicityTableEntity> getPublicityTablesByUId() {
        return publicityTablesByUId;
    }

    public void setPublicityTablesByUId(Collection<PublicityTableEntity> publicityTablesByUId) {
        this.publicityTablesByUId = publicityTablesByUId;
    }

    @OneToMany(mappedBy = "userTableByAdminUId")
    public Collection<RuleBreakTableEntity> getRuleBreakTablesByUId() {
        return ruleBreakTablesByUId;
    }

    public void setRuleBreakTablesByUId(Collection<RuleBreakTableEntity> ruleBreakTablesByUId) {
        this.ruleBreakTablesByUId = ruleBreakTablesByUId;
    }

    @OneToMany(mappedBy = "userTableByBreakUId")
    public Collection<RuleBreakTableEntity> getRuleBreakTablesByUId_0() {
        return ruleBreakTablesByUId_0;
    }

    public void setRuleBreakTablesByUId_0(Collection<RuleBreakTableEntity> ruleBreakTablesByUId_0) {
        this.ruleBreakTablesByUId_0 = ruleBreakTablesByUId_0;
    }
}
