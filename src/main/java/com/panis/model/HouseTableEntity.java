package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Entity
@Table(name = "house_table", schema = "committee_admin", catalog = "")
public class HouseTableEntity {
    private int houseId;
    private String pannant;
    private String apHouse;
    private String state;

    @Id
    @Column(name = "house_id", nullable = false)
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "pannant", nullable = false, length = 20)
    public String getPannant() {
        return pannant;
    }

    public void setPannant(String pannant) {
        this.pannant = pannant;
    }

    @Basic
    @Column(name = "ap_house", nullable = false, length = 10)
    public String getApHouse() {
        return apHouse;
    }

    public void setApHouse(String apHouse) {
        this.apHouse = apHouse;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 100)
    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseTableEntity that = (HouseTableEntity) o;

        if (houseId != that.houseId) return false;
        if (pannant != null ? !pannant.equals(that.pannant) : that.pannant != null) return false;
        if (apHouse != null ? !apHouse.equals(that.apHouse) : that.apHouse != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = houseId;
        result = 31 * result + (pannant != null ? pannant.hashCode() : 0);
        result = 31 * result + (apHouse != null ? apHouse.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }    private Integer uId;

    @Basic
    @Column(name = "u_id", nullable = true)
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
}
