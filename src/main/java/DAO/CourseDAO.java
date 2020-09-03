package DAO;

import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Map;

import javax.persistence.Query;
import java.util.List;

public class CourseDAO {

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class).addAnnotatedClass(Student.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public void addCourseTeacher(String name, int teacherID){

        Session session = connect();

        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class,name);
        Teacher teacher = session.get(Teacher.class,teacherID);
        teacher.getCourse().add(course);
        course.setTeacher(teacher);
        session.update(teacher);
        session.update(course);

        tx.commit();
    }

    public void addCourseStudent(String name, int studentID){
        Session session = connect();
/*      get the student's info from student table
        add the course into the student object
        add the new student object to the table (update)*/
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class,studentID);
        Course c = session.get(Course.class,name);
        s.getCourse().add(c);
        session.update(s);
        tx.commit();
    }

    public void addCourse(String name){

        Course course = new Course();
        course.setCourseName(name);

        Session session = connect();
        Transaction tx = session.beginTransaction();
        session.save(course);
        tx.commit();

    }

    public void deleteCourse(String name){

        Session session = connect();
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class,name);
        Teacher teacher = course.getTeacher();
        teacher.getCourse().remove(course);
        session.update(teacher);
        session.delete(course);
        tx.commit();

    }

    public void removeTeacher(String name){
        Session session = connect();
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class,name);
        Teacher teacher = course.getTeacher();
        course.setTeacher(null);
        teacher.getCourse().remove(course);
        session.update(course);
        session.update(teacher);
        tx.commit();
    }

    public List<Course> getCourseList(){

        Session session = connect();
        Query q = session.createQuery("from Course ");
        List<Course> courses = q.getResultList();
        return courses;
    }

    public List<Course> getCourseWithTeacherList(){

        Session session = connect();
        Query q = session.createQuery("from Course where teacher!=null");
        List<Course> courses = q.getResultList();
        return courses;
    }

}
