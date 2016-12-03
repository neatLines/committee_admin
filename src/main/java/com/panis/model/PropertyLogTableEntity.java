package com.panis.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Entity
@Table(name = "property_log_table", schema = "committee_admin", catalog = "")
public class PropertyLogTableEntity {
    private int proLogId;
    private Timestamp date;
    private int changeUId;
    private int changedPrId;
    private byte changeWay;
    private Boolean effective;

    @Id
    @Column(name = "pro_log_id", nullable = false)
    public int getProLogId() {
        return proLogId;
    }

    public void setProLogId(int proLogId) {
        this.proLogId = proLogId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "change_u_id", nullable = false)
    public int getChangeUId() {
        return changeUId;
    }

    public void setChangeUId(int changeUId) {
        this.changeUId = changeUId;
    }

    @Basic
    @Column(name = "changed_pr_id", nullable = false)
    public int getChangedPrId() {
        return changedPrId;
    }

    public void setChangedPrId(int changedPrId) {
        this.changedPrId = changedPrId;
    }

    @Basic
    @Column(name = "change_way", nullable = false)
    public byte getChangeWay() {
        return changeWay;
    }

    public void setChangeWay(byte changeWay) {
        this.changeWay = changeWay;
    }

    @Basic
    @Column(name = "effective", nullable = true)
    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyLogTableEntity that = (PropertyLogTableEntity) o;

        if (proLogId != that.proLogId) return false;
        if (changeUId != that.changeUId) return false;
        if (changedPrId != that.changedPrId) return false;
        if (changeWay != that.changeWay) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (effective != null ? !effective.equals(that.effective) : that.effective != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = proLogId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + changeUId;
        result = 31 * result + changedPrId;
        result = 31 * result + (int) changeWay;
        result = 31 * result + (effective != null ? effective.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "proLogId:"+proLogId+"\ndate:"+date+"\nchangeUId:"+changeUId+"\nchangedPrId:"+changedPrId+"\nchangeWay:"+changeWay+"\neffective:"+effective;
    }
}
