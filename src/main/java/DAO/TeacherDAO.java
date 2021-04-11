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
public class TeacherDAO {

    private Teacher teacher;

    public TeacherDAO(){
        teacher = new Teacher();
    }

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class).addAnnotatedClass(Student.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public boolean addTeacher(String name, int roll){

//        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setId(roll);

        try(Session session = connect())
        {
            Transaction tx = session.beginTransaction();
            session.save(teacher);
            tx.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteTeacher(int roll){

        Session session = connect();
        Transaction tx = session.beginTransaction();

//        Teacher teacher = session.get(Teacher.class,roll);
        teacher = session.get(Teacher.class,roll);
        List<Course> courses = teacher.getCourse();
        CourseDAO courseDAO = new CourseDAO();
        try{
            for(Course course:courses)
            {
                courseDAO.removeTeacher(course.getCourseName());
            }
            session.delete(teacher);
            tx.commit();
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean editTeacher(int roll, String name){

        teacher.setName(name);
        teacher.setId(roll);

        try{
            Session session = connect();
            Transaction tx = session.beginTransaction();
            session.update(teacher);
            tx.commit();
            return true;
        } catch (Exception e){
            return false;
        }


    }

    public List<Teacher> getTeacherList(){

        Session session = connect();
        Query q = session.createQuery("from Teacher order by id");
        List<Teacher> teachers = q.getResultList();
        return teachers;
    }

}
