package com.panis.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by fuyipeng on 2016/11/10.
 */
@Entity
@Table(name = "publicity_table", schema = "committee_admin", catalog = "")
public class PublicityTableEntity {
    private int pId;
    private String title;
    private String detail;
    private Date date;
    private UserTableEntity userTableByWriterId;

    @Id
    @Column(name = "p_id", nullable = false)
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "detail", nullable = false, length = 1000)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicityTableEntity that = (PublicityTableEntity) o;

        if (pId != that.pId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "u_id", nullable = false)
    public UserTableEntity getUserTableByWriterId() {
        return userTableByWriterId;
    }

    public void setUserTableByWriterId(UserTableEntity userTableByWriterId) {
        this.userTableByWriterId = userTableByWriterId;
    }
}
