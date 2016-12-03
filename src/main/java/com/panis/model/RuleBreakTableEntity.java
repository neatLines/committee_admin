package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 03/12/2016.
 */
@Entity
@Table(name = "rule_break_table", schema = "committee_admin", catalog = "")
public class RuleBreakTableEntity {
    private int breakLogId;
    private int adminUId;
    private int breakUId;
    private String decribe;
    private boolean flag;

    @Id
    @Column(name = "break_log_id", nullable = false)
    public int getBreakLogId() {
        return breakLogId;
    }

    public void setBreakLogId(int breakLogId) {
        this.breakLogId = breakLogId;
    }

    @Basic
    @Column(name = "admin_u_id", nullable = false)
    public int getAdminUId() {
        return adminUId;
    }

    public void setAdminUId(int adminUId) {
        this.adminUId = adminUId;
    }

    @Basic
    @Column(name = "break_u_id", nullable = false)
    public int getBreakUId() {
        return breakUId;
    }

    public void setBreakUId(int breakUId) {
        this.breakUId = breakUId;
    }

    @Basic
    @Column(name = "decribe", nullable = true, length = 400)
    public String getDecribe() {
        return decribe;
    }

    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }

    @Basic
    @Column(name = "flag", nullable = false)
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleBreakTableEntity that = (RuleBreakTableEntity) o;

        if (breakLogId != that.breakLogId) return false;
        if (adminUId != that.adminUId) return false;
        if (breakUId != that.breakUId) return false;
        if (flag != that.flag) return false;
        if (decribe != null ? !decribe.equals(that.decribe) : that.decribe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = breakLogId;
        result = 31 * result + adminUId;
        result = 31 * result + breakUId;
        result = 31 * result + (decribe != null ? decribe.hashCode() : 0);
        result = 31 * result + (flag ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BreakLogId:"+breakLogId+"\nadminUId:"+adminUId+"\nbreakUId:"+breakUId+"\ndecribe:"+decribe+"\nflag:"+flag;
    }
}
