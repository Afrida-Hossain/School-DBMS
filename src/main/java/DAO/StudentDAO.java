package DAO;


import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StudentDAO {

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public boolean addStudent(String name, int roll){

        try(Session session = connect()){
            Student student = new Student();
            student.setName(name);
            student.setRoll(roll);
            Transaction tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteStudent(int roll){

       try(Session session = connect()){
           Student student = new Student();
           Transaction tx = session.beginTransaction();
           student = session.get(Student.class,roll);
           session.delete(student);
           tx.commit();
           return true;
       }catch (Exception e){
           return false;
       }
    }

    public boolean editStudent(int roll, String name){

        try(Session session = connect()){
            Student student = new Student();
            student.setRoll(roll);
            student.setName(name);
            Transaction tx = session.beginTransaction();
            session.update(student);
            tx.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Student viewStudent(int r){
        Student student = new Student();
        try(Session session = connect()){
            Query q = session.createQuery("from Student s where s.roll = :r");
            q.setParameter("r",r);
            student = (Student) q.setMaxResults(1).uniqueResult();
            return student;
        }catch(Exception e){
            return student;
        }

/*        Student student;
        Session session = connect();
        Query q = session.createQuery("from Student s where s.roll = :r");
        q.setParameter("r",r);
        student = (Student) q.setMaxResults(1).uniqueResult();
        return student;*/
    }

    public List<Student> getStudentList()
    {
        Session session = connect();
        Query q = session.createQuery("from Student order by id");
        List<Student> students = q.getResultList();
        return students;

    }

}
