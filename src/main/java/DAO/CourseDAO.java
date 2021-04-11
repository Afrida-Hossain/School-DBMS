package DAO;

import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class CourseDAO {

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class).addAnnotatedClass(Student.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public boolean addCourseTeacher(String name, int teacherID){

        try(Session session = connect()) {
            Transaction tx = session.beginTransaction();
            Course course = session.get(Course.class,name);
            Teacher teacher = session.get(Teacher.class,teacherID);
            teacher.getCourse().add(course);
            course.setTeacher(teacher);
            session.update(teacher);
            session.update(course);
            tx.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addCourseStudent(String name, int studentID){

        try(Session session = connect()){
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class,studentID);
            Course course = session.get(Course.class,name);
            student.getCourse().add(course);
            session.update(student);
            tx.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean addCourse(String name){

        try(Session session = connect()){
            Course course = new Course();
            course.setCourseName(name);
            Transaction tx = session.beginTransaction();
            session.save(course);
            tx.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteCourse(String name){

        try(Session session = connect()){
            Transaction tx = session.beginTransaction();
            Course course = session.get(Course.class,name);
            Teacher teacher = course.getTeacher();
            List<Student> students = course.getStudent();
            if(teacher != null)
            {
                teacher.getCourse().remove(course);
                session.update(teacher);
            }
            if(!students.isEmpty()){
                for(Student student : students){
                    student.getCourse().remove(course);
                }
            }
            session.delete(course);
            tx.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean removeTeacher(String name){
        try(Session session = connect()){
            Transaction tx = session.beginTransaction();
            Course course = session.get(Course.class,name);
            Teacher teacher = course.getTeacher();
            course.setTeacher(null);
            teacher.getCourse().remove(course);
            session.update(course);
            session.update(teacher);
            tx.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Course> getCourseList(){

        Session session = connect();
        Query q = session.createQuery("from Course order by courseName");
        List<Course> courses = q.getResultList();
        return courses;
    }

    public List<Course> getCourseWithTeacherList(){

        Session session = connect();
        Query q = session.createQuery("from Course where teacher!=null order by courseName");
        List<Course> courses = q.getResultList();
        return courses;
    }

}
