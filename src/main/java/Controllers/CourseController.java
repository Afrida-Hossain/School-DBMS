package Controllers;

import Classes.Course;
import Classes.Teacher;
import DAO.CourseDAO;
import DAO.TeacherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseController {

    @RequestMapping("/addCourseTeacher")
    public ModelAndView addCourseTeacher(@RequestParam("name") String name, @RequestParam("teacherID") int teacherID){

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.addCourseTeacher(name, teacherID);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("addedStudent");
        mv.addObject("Placeholder","Course");
        mv.addObject("name",name);
        mv.addObject("teacherID",teacherID);

        return mv;
    }

    @RequestMapping("/addCourseStudent")
    public ModelAndView addCourseStudent(@RequestParam("name") String name, @RequestParam("studentID") int studentID){

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.addCourseStudent(name, studentID);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("addedStudent");
        mv.addObject("Placeholder","Course");
        mv.addObject("name",name);
        mv.addObject("teacherID",studentID);

        return mv;
    }

    @RequestMapping("/addCourse")
    public ModelAndView addCourse(@RequestParam("courseName") String name){

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.addCourse(name);

        ModelAndView mv = new ModelAndView("addedStudent");
        mv.addObject("Placeholder","Course");
        return mv;
    }

    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse(@RequestParam("courseName") String name){

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.deleteCourse(name);

        ModelAndView mv = new ModelAndView("deletedStudent");
        mv.addObject("Placeholder","Course");
        return mv;
    }

    @RequestMapping("/removeCourseTeacher")
    public ModelAndView removeCourseTeacher(@RequestParam("name") String courseName){

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.removeTeacher(courseName);

        ModelAndView mv = new ModelAndView("deletedStudent");
        mv.addObject("Placeholder","Teacher for course");
        return mv;
    }

    @RequestMapping("/viewAllCourses")
    public ModelAndView viewAllCourses(){

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses= courseDAO.getCourseList();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("allCourses");
        mv.addObject("Placeholder","Courses");
        mv.addObject("AllCourses",courses);
        return mv;
    }

    @RequestMapping("/loadCoursePage")
    public ModelAndView loadCoursePage(){

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getCourseList();
        List<Course> courseWithTeacher = courseDAO.getCourseWithTeacherList();

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getTeacherList();

        ModelAndView mv = new ModelAndView("coursePage");
        mv.addObject("CourseWithTeacher",courseWithTeacher);
        mv.addObject("CourseName",courses);
        mv.addObject("Teachers",teachers);
        return mv;
    }

}
