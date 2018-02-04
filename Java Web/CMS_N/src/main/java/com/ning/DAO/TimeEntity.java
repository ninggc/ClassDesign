package com.ning.DAO;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ning on 2017/7/11.
 */
@Entity
@Table(name = "time")
public class TimeEntity implements IEntity {
    private int id;
    private Date day;
    private String time;
//    private int duration;
//    private transient HireEntity hire;

//    private DayEntity day;

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
    @Temporal(TemporalType.DATE)
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    @Basic
//    @Column(name = "duration")
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }

//    @OneToOne(mappedBy = "time")
//    public HireEntity getHire() {
//        return hire;
//    }
//
//    public void setHire(HireEntity hireEntity) {
//        this.hire = hireEntity;
//    }

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "day_id")
//    public DayEntity getDay() {
//        return day;
//    }
//
//    public void setDay(DayEntity day) {
//        this.day = day;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntity that = (TimeEntity) o;

        if (id != that.id) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }
}
