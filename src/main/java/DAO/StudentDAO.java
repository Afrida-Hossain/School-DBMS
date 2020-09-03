package DAO;


import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAO {

    public Session connect(){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        return session;
    }

    public void addStudent(String name, int roll){

        Student student = new Student();
        student.setName(name);
        student.setRoll(roll);
        Session session = connect();
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();

    }

    public void deleteStudent(int roll){

        Session session = connect();

        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class,roll);
        session.delete(student);
        tx.commit();
    }

    public void editStudent(int roll, String name){

        Student student = new Student();
        student.setRoll(roll);
        student.setName(name);
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.update(student);
        tx.commit();
    }

    public Student viewStudent(int r){
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Course.class).addAnnotatedClass(Teacher.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Student s where s.roll = :r");
        q.setParameter("r",r);
        //Transaction tx = session.beginTransaction();
        //Student s = session.get(Student.class,roll);
        //tx.commit();
        Student s = (Student) q.setMaxResults(1).uniqueResult();
        return s;
    }

    public List<Student> getStudentList()
    {
        Session session = connect();
        Query q = session.createQuery("from Student ");
        List<Student> students = q.getResultList();
        return students;

    }

}
