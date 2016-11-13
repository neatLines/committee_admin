package com.panis.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by fuyipeng on 2016/11/10.
 */
@Entity
@Table(name = "commonality_table", schema = "res_committee_admin", catalog = "")
public class CommonalityTableEntity {
    private int conId;
    private String conName;
    private Date conDate;
    private int count;

    @Id
    @Column(name = "con_id", nullable = false)
    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    @Basic
    @Column(name = "con_name", nullable = false, length = 40)
    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    @Basic
    @Column(name = "con_date", nullable = false)
    public Date getConDate() {
        return conDate;
    }

    public void setConDate(Date conDate) {
        this.conDate = conDate;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonalityTableEntity that = (CommonalityTableEntity) o;

        if (conId != that.conId) return false;
        if (count != that.count) return false;
        if (conName != null ? !conName.equals(that.conName) : that.conName != null) return false;
        if (conDate != null ? !conDate.equals(that.conDate) : that.conDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conId;
        result = 31 * result + (conName != null ? conName.hashCode() : 0);
        result = 31 * result + (conDate != null ? conDate.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
