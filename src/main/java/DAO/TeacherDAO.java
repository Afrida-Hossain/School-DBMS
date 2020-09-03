package DAO;

import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class TeacherDAO {

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class).addAnnotatedClass(Student.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public void addTeacher(String name, int roll){

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setId(roll);

        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Teacher.class).addAnnotatedClass(Course.class).addAnnotatedClass(Student.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.save(teacher);
        tx.commit();

    }

    public void deleteTeacher(int roll){

        Session session = connect();
        Transaction tx = session.beginTransaction();

        Teacher teacher = session.get(Teacher.class,roll);
        List<Course> courses = teacher.getCourse();
        CourseDAO courseDAO = new CourseDAO();
        for(Course course:courses)
        {
            courseDAO.removeTeacher(course.getCourseName());
        }
        session.delete(teacher);
        tx.commit();
    }

    public void editTeacher(int roll, String name){

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setId(roll);
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Teacher.class).addAnnotatedClass(Course.class).addAnnotatedClass(Student.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        session.update(teacher);
        tx.commit();
    }

    public List<Teacher> getTeacherList(){

        Session session = connect();
        Query q = session.createQuery("from Teacher ");
        List<Teacher> teachers = q.getResultList();
        return teachers;
    }

}
