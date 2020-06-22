package com.szb.jpa;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @ClassName AbstractDocument
 * @Description TODO
 * @Author szb
 * @Date 2020/2/19 10:01
 * @Version 1.0
 **/
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {
    private static final long serialVersionUID = 1L;

    public AbstractEntity() {
    }

    public abstract ID getId();

    public abstract void setId(ID var1);

    public boolean isNew() {
        return null == this.getId();
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!this.getClass().equals(obj.getClass())) {
            return false;
        } else {
            AbstractEntity<?> that = (AbstractEntity)obj;
            return null == this.getId() ? false : this.getId().equals(that.getId());
        }
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode + (null == this.getId() ? 0 : this.getId().hashCode() * 31);
        return hashCode;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

