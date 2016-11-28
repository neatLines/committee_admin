package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 2016/11/10.
 */
@Entity
@Table(name = "park_table", schema = "committee_admin", catalog = "")
public class ParkTableEntity {
    private int parkId;
    private String place;
    private UserTableEntity userTableByUId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkTableEntity that = (ParkTableEntity) o;

        if (parkId != that.parkId) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parkId;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "u_id", referencedColumnName = "u_id")
    public UserTableEntity getUserTableByUId() {
        return userTableByUId;
    }

    public void setUserTableByUId(UserTableEntity userTableByUId) {
        this.userTableByUId = userTableByUId;
    }
}
