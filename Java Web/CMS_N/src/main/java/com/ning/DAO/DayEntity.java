package com.ning.DAO;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ning on 2017/7/11.
 */
@Entity
@Table(name = "day")
@Deprecated
public class DayEntity implements IEntity {
    private int id;
    private Date day;

//    private HireEntity hire = new  HashSet<>();
//    private Set<TimeEntity> times = new HashSet<>();

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "day")
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "hire_id")
//    public HireEntity getHire() {
//        return hire;
//    }
//
//    public void setHire(HireEntity hire) {
//        this.hire = hire;
//    }

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "day")
//    public Set<TimeEntity> getTimes() {
//        return times;
//    }
//
//    public void setTimes(Set<TimeEntity> times) {
//        this.times = times;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayEntity dayEntity = (DayEntity) o;

        if (id != dayEntity.id) return false;
        if (day != null ? !day.equals(dayEntity.day) : dayEntity.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }
}
