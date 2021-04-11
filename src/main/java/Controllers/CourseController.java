package Controllers;

import Classes.Course;
import Classes.Teacher;
import DAO.CourseDAO;
import DAO.TeacherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private ModelAndView mv;
    private CourseDAO courseDAO;
    private boolean successful;

    public CourseController(){
        courseDAO = new CourseDAO();
    }

    @RequestMapping("/addCourseTeacher")
    public ModelAndView addCourseTeacher(@RequestParam("name") String name, @RequestParam("teacherID") int teacherID){

        successful = courseDAO.addCourseTeacher(name, teacherID);

        if(successful){
            mv.setViewName("addedCourse");
            mv.addObject("Placeholder","Course teacher added");
            mv.addObject("name",name);
            mv.addObject("id",teacherID);
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","CourseName or TeacherID");
        }

        return mv;
    }

    @RequestMapping("/addCourseStudent")
    public ModelAndView addCourseStudent(@RequestParam("name") String name, @RequestParam("studentID") int studentID){

        successful = courseDAO.addCourseStudent(name, studentID);

        if(successful){
            mv.setViewName("addedCourse");
            mv.addObject("Placeholder","Course student added");
            mv.addObject("name",name);
            mv.addObject("id",studentID);
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","CourseName or Roll");
        }

        return mv;
    }

    @RequestMapping("/addCourse")
    public ModelAndView addCourse(@RequestParam("courseName") String name){

       successful = courseDAO.addCourse(name);

        if(successful){
            mv.setViewName("addedCourse");
            mv.addObject("Placeholder","Course added");
            mv.addObject("name",name);
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","CourseName");
        }

        return mv;
    }

    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse(@RequestParam("courseName") String name){

        successful = courseDAO.deleteCourse(name);

        if(successful){
            mv.setViewName("addedCourse");
            mv.addObject("Placeholder","Course Deleted");
            mv.addObject("name",name);
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","CourseName");
        }

        return mv;
    }

    @RequestMapping("/removeCourseTeacher")
    public ModelAndView removeCourseTeacher(@RequestParam("name") String courseName){

        successful = courseDAO.removeTeacher(courseName);

        if(successful){
            mv.setViewName("addedCourse");
            mv.addObject("Placeholder","Removed teacher for course");
            mv.addObject("name",courseName);
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","TeacherID");
        }

        return mv;
    }

    @RequestMapping("/viewAllCourses")
    public ModelAndView viewAllCourses(){

        List<Course> courses= courseDAO.getCourseList();

        mv.setViewName("allCourses");
        mv.addObject("Placeholder","Courses");
        mv.addObject("AllCourses",courses);
        return mv;
    }

    @RequestMapping("/loadCoursePage")
    public ModelAndView loadCoursePage(){

        List<Course> courses = courseDAO.getCourseList();
        List<Course> courseWithTeacher = courseDAO.getCourseWithTeacherList();

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getTeacherList();

        mv.setViewName("coursePage");
        mv.addObject("CourseWithTeacher",courseWithTeacher);
        mv.addObject("CourseName",courses);
        mv.addObject("Teachers",teachers);
        return mv;
    }

}
