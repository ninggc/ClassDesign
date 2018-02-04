package com.ning.DAO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ning on 2017/7/11.
 */
@Entity
@Table(name = "classroom")
public class ClassroomEntity implements IEntity {
    private int id;
    private String number;
    private int capacity;
    private String extra;

//    private transient Set<TeacherEntity> teachers = new HashSet<>();
    private transient Set<HireEntity> hiresForClassroom = new HashSet<>();

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "extra")
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

//    @ManyToMany(mappedBy="classrooms",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    public Set<TeacherEntity> getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(Set<TeacherEntity> teachers) {
//        this.teachers = teachers;
//    }


    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<HireEntity> getHiresForClassroom() {
        return hiresForClassroom;
    }

    public void setHiresForClassroom(Set<HireEntity> hires) {
        this.hiresForClassroom = hires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassroomEntity that = (ClassroomEntity) o;

        if (id != that.id) return false;
        if (capacity != that.capacity) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }
}
