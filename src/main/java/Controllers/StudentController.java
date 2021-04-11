package Controllers;

import Classes.Course;
import Classes.Student;
import DAO.CourseDAO;
import DAO.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private ModelAndView mv;

    private StudentDAO studentDAO;
    private boolean successful;

    public StudentController(){
        studentDAO = new StudentDAO();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){

/*        if(bindingResult.hasErrors()){
            return loadStudentPage(student);
        }*/
            successful = studentDAO.addStudent(student.getName(),student.getRoll());

            if(!successful){
                throw new PersistenceException();

            }
        else
            {
                mv.addObject("Placeholder","Student");
                mv.addObject("Object",student);
                mv.setViewName("addedStudent");
            }

        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteStudent(@RequestParam("roll" )int roll){

        successful = studentDAO.deleteStudent(roll);

        if(successful){
            mv.addObject("Placeholder","Student");
            mv.setViewName("deletedStudent");

        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","Roll");
        }
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView editStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){

       successful =  studentDAO.editStudent(student.getRoll(),student.getName());
       if(successful){
           mv.setViewName("editedStudent");
           mv.addObject("Placeholder","Student");
           mv.addObject("name",student.getRoll());
           mv.addObject("roll",student.getName());
       }
       else {
           mv.setViewName("operationFailed");
           mv.addObject("primaryKey","Roll");
       }
        return mv;
    }

    @RequestMapping("/viewStudent")
    public ModelAndView viewStudent(@RequestParam("roll") int roll){

        Student student = studentDAO.viewStudent(roll);

        mv.setViewName("viewStudent");
        mv.addObject("name",student.getName());
        mv.addObject("roll",roll);
        mv.addObject("courseNo",student.getCourse().size());
        mv.addObject("Courses",student.getCourse());
        return mv;
    }

    @RequestMapping("/viewAllStudents")
    public ModelAndView viewAllStudent(){

        List<Student> students= studentDAO.getStudentList();

        mv.setViewName("/allStudents");
        mv.addObject("Placeholder","Students");
        mv.addObject("AllStudents",students);
        return mv;
    }

    @RequestMapping("/loadStudentPage")
    public ModelAndView loadStudentPage(Student student){

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCourseList();

        List<Student> students = studentDAO.getStudentList();

        mv.setViewName("studentPage");
        mv.addObject("student",student);
        mv.addObject("StudentList",students);
        mv.addObject("CourseName",courses);
        return mv;
    }


}
