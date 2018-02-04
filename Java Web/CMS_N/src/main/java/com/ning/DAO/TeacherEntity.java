package com.ning.DAO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ning on 2017/7/11.
 */
@Entity
@Table(name = "teacher")
public class TeacherEntity implements IEntity {
    private int id;
    private String name;
    private String course;
    private String careerTitle;
    private String password;

//    private transient Set<ClassroomEntity> classrooms = new HashSet<>();
    private transient Set<HireEntity> hiresForTeacher = new HashSet<>();

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "course")
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "career_title")
    public String getCareerTitle() {
        return careerTitle;
    }

    public void setCareerTitle(String careerTitle) {
        this.careerTitle = careerTitle;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name="hire",
//            joinColumns=@JoinColumn(name="teacher_id"),
//            inverseJoinColumns=@JoinColumn(name="classroom_id")
//    )    public Set<ClassroomEntity> getClassrooms() {
//        return classrooms;
//    }
//
//    public void setClassrooms(Set<ClassroomEntity> classrooms) {
//        this.classrooms = classrooms;
//    }


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<HireEntity> getHiresForTeacher() {
        return hiresForTeacher;
    }

    public void setHiresForTeacher(Set<HireEntity> hires) {
        this.hiresForTeacher = hires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        if (careerTitle != null ? !careerTitle.equals(that.careerTitle) : that.careerTitle != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (careerTitle != null ? careerTitle.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }
}
