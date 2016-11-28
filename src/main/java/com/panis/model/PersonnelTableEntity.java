package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Entity
@Table(name = "personnel_table", schema = "committee_admin", catalog = "")
public class PersonnelTableEntity {
    private int uId;
    private String duty;

    @Id
    @Column(name = "u_id", nullable = false)
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "duty", nullable = false, length = 50)
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonnelTableEntity that = (PersonnelTableEntity) o;

        if (uId != that.uId) return false;
        if (duty != null ? !duty.equals(that.duty) : that.duty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId;
        result = 31 * result + (duty != null ? duty.hashCode() : 0);
        return result;
    }
}
