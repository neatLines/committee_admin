package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 03/12/2016.
 */
@Entity
@Table(name = "park_table", schema = "committee_admin", catalog = "")
public class ParkTableEntity {
    private int parkId;
    private String place;
    private Integer uId;

    @Id
    @Column(name = "park_id", nullable = false)
    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    @Basic
    @Column(name = "place", nullable = false, length = 100)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "u_id", nullable = true)
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkTableEntity that = (ParkTableEntity) o;

        if (parkId != that.parkId) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (uId != null ? !uId.equals(that.uId) : that.uId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parkId;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "parkId:"+parkId+"\nplace:"+place+"\nuId:"+uId;
    }
}
