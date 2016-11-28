package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 2016/11/10.
 */
@Entity
@Table(name = "rule_break_table", schema = "committee_admin", catalog = "")
public class RuleBreakTableEntity {
    private int breakLogId;
    private String decribe;
    private UserTableEntity userTableByAdminUId;
    private UserTableEntity userTableByBreakUId;

    @Id
    @Column(name = "break_log_id", nullable = false)
    public int getBreakLogId() {
        return breakLogId;
    }

    public void setBreakLogId(int breakLogId) {
        this.breakLogId = breakLogId;
    }

    @Basic
    @Column(name = "decribe", nullable = true, length = 400)
    public String getDecribe() {
        return decribe;
    }

    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleBreakTableEntity that = (RuleBreakTableEntity) o;

        if (breakLogId != that.breakLogId) return false;
        if (decribe != null ? !decribe.equals(that.decribe) : that.decribe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = breakLogId;
        result = 31 * result + (decribe != null ? decribe.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "admin_u_id", referencedColumnName = "u_id", nullable = false)
    public UserTableEntity getUserTableByAdminUId() {
        return userTableByAdminUId;
    }

    public void setUserTableByAdminUId(UserTableEntity userTableByAdminUId) {
        this.userTableByAdminUId = userTableByAdminUId;
    }

    @ManyToOne
    @JoinColumn(name = "break_u_id", referencedColumnName = "u_id", nullable = false)
    public UserTableEntity getUserTableByBreakUId() {
        return userTableByBreakUId;
    }

    public void setUserTableByBreakUId(UserTableEntity userTableByBreakUId) {
        this.userTableByBreakUId = userTableByBreakUId;
    }
}
