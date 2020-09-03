package Controllers;

import Classes.Course;
import Classes.Student;
import Classes.Teacher;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeacherController {

    @RequestMapping("/teacherAdd")
    public ModelAndView addTeacher(@RequestParam("name") String name, @RequestParam("roll") String roll){

        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.addTeacher(name,Integer.parseInt(roll));

        ModelAndView mv = new ModelAndView();
        mv.addObject("Placeholder","Teacher");
        mv.addObject("name",name);
        mv.addObject("roll",roll);
        mv.setViewName("addedStudent");
        return mv;
    }

    @RequestMapping("/teacherDelete")
    public ModelAndView deleteTeacher(@RequestParam("teacherID" )int roll){

        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.deleteTeacher(roll);

        ModelAndView mv = new ModelAndView();
        mv.addObject("Placeholder","Teacher");
        mv.setViewName("deletedStudent");
        return mv;
    }

    @RequestMapping("/teacherEdit")
    public ModelAndView editTeacher(@RequestParam("teacherID") int roll, @RequestParam("name") String name){

        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.editTeacher(roll,name);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editedStudent");
        mv.addObject("Placeholder","Teacher");
        mv.addObject("name",name);
        mv.addObject("roll",roll);
        return mv;
    }

    @RequestMapping("/viewAllTeachers")
    public ModelAndView viewAllTeachers(){

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers= teacherDAO.getTeacherList();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("allTeachers");
        mv.addObject("Placeholder","Teachers");
        mv.addObject("AllTeachers",teachers);
        return mv;
    }

    @RequestMapping("/loadTeacherPage")
    public ModelAndView loadTeacherPage(){

        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.getTeacherList();
        ModelAndView mv = new ModelAndView("teacherPage");
        mv.addObject("Teachers",teachers);
        return mv;
    }
}
