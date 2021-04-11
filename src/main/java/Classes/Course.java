package Classes;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Course {
/*    @Id
    private int courseId;*/

    @Id
    private String courseName;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Student> student =new ArrayList<>();

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
