package com.panis.model;

import javax.persistence.*;

/**
 * Created by fuyipeng on 28/11/2016.
 */
@Entity
@Table(name = "property_table", schema = "committee_admin", catalog = "")
public class PropertyTableEntity {
    private int propertyId;
    private String pName;
    private int pValue;
    private int count;

    @Id
    @Column(name = "property_id", nullable = false)
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    @Basic
    @Column(name = "p_name", nullable = false, length = 50)
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Basic
    @Column(name = "p_value", nullable = false)
    public int getpValue() {
        return pValue;
    }

    public void setpValue(int pValue) {
        this.pValue = pValue;
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

        PropertyTableEntity that = (PropertyTableEntity) o;

        if (propertyId != that.propertyId) return false;
        if (pValue != that.pValue) return false;
        if (count != that.count) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propertyId;
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + pValue;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "propertyId:"+propertyId+"\npName:"+pName+"\npValue:"+pValue+"\ncount:"+count;
    }
}

