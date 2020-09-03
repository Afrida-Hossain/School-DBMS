package Controllers;

import Classes.Course;
import Classes.Student;
import DAO.CourseDAO;
import DAO.StudentDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {

    @RequestMapping("/add")
    public ModelAndView addStudent(@RequestParam("name") String name, @RequestParam("roll") String roll){

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent(name,Integer.parseInt(roll));

        ModelAndView mv = new ModelAndView();
        mv.addObject("Placeholder","Student");
        mv.addObject("name",name);
        mv.addObject("roll",roll);
        mv.setViewName("addedStudent");
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteStudent(@RequestParam("roll" )int roll){

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudent(roll);

        ModelAndView mv = new ModelAndView();
        mv.addObject("Placeholder","Student");
        mv.setViewName("deletedStudent");
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView editStudent(@RequestParam("roll") int roll, @RequestParam("name") String name){

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.editStudent(roll,name);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editedStudent");
        mv.addObject("Placeholder","Student");
        mv.addObject("name",name);
        mv.addObject("roll",roll);
        return mv;
    }

    @RequestMapping("/viewStudent")
    public ModelAndView viewStudent(@RequestParam("roll") int roll){

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.viewStudent(roll);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("viewStudent");
        mv.addObject("name",student.getName());
        mv.addObject("roll",roll);
        mv.addObject("courseNo",student.getCourse().size());
        mv.addObject("Courses",student.getCourse());
        return mv;
    }

    @RequestMapping("/viewAllStudents")
    public ModelAndView viewAllStudent(){

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students= studentDAO.getStudentList();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/allStudents");
        mv.addObject("Placeholder","Students");
        mv.addObject("AllStudents",students);
        return mv;
    }

    @RequestMapping("/loadStudentPage")
    public ModelAndView loadStudentPage(){

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCourseList();
        StudentDAO studentDAO = new StudentDAO();

        List<Student> students = studentDAO.getStudentList();
        ModelAndView mv = new ModelAndView("studentPage");
        mv.addObject("StudentList",students);
        mv.addObject("CourseName",courses);
        return mv;
    }


}
