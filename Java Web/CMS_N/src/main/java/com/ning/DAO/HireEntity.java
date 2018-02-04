package com.ning.DAO;

import javax.persistence.*;

/**
 * Created by ning on 2017/7/11.
 */
@Entity
@Table(name = "hire")
public class HireEntity implements IEntity {
    private int id;
    private transient TimeEntity time;
//    private Set<DayEntity> days = new HashSet<>();

    private transient ClassroomEntity classroom;
    private transient TeacherEntity teacher;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne()
    @JoinColumn(name = "time_id")
    public TimeEntity getTime() {
        return time;
    }

    public void setTime(TimeEntity timeEntity) {
        this.time = timeEntity;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name="hiresForClassroom", referencedColumnName="id",insertable=false,updatable=false)
    @JoinColumn(name = "classroom_id")
    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "teacher_id")
    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HireEntity that = (HireEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }
}
