package Controllers;

import Classes.Teacher;
import DAO.TeacherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TeacherController {

    @Autowired
    private ModelAndView mv;

    private boolean successful;

    private TeacherDAO teacherDAO;

    public TeacherController(){
        teacherDAO = new TeacherDAO();
    }

    @RequestMapping("/teacherAdd")
    public ModelAndView addTeacher(@RequestParam("name") String name, @RequestParam("roll") String roll){

        successful = teacherDAO.addTeacher(name,Integer.parseInt(roll));

        if(successful){
            mv.addObject("Placeholder","Teacher added");
            mv.addObject("name",name);
            mv.addObject("roll",roll);
            mv.setViewName("addedTeacher");
        }
        else {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","ID");
        }

        return mv;
    }

    @RequestMapping("/teacherDelete")
    public ModelAndView deleteTeacher(@RequestParam("teacherID" )int roll){

        successful = teacherDAO.deleteTeacher(roll);

        if(successful){
            //        ModelAndView mv = new ModelAndView();
            mv.addObject("Placeholder","Teacher");
            mv.setViewName("deletedStudent");
        }
        else
        {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","ID");
        }
        return mv;
    }

    @RequestMapping("/teacherEdit")
    public ModelAndView editTeacher(@RequestParam("teacherID") int roll, @RequestParam("name") String name){

        successful = teacherDAO.editTeacher(roll,name);

        if(successful){
            mv.setViewName("addedTeacher");
            mv.addObject("Placeholder","Teacher edited");
            mv.addObject("name",name);
            mv.addObject("roll",roll);
        }
        else
        {
            mv.setViewName("operationFailed");
            mv.addObject("primaryKey","Teacher ID");
        }

        return mv;
    }

    @RequestMapping("/viewAllTeachers")
    public ModelAndView viewAllTeachers(){

        List<Teacher> teachers= teacherDAO.getTeacherList();

        mv.setViewName("allTeachers");
        mv.addObject("Placeholder","Teachers");
        mv.addObject("AllTeachers",teachers);
        return mv;
    }

    @RequestMapping("/loadTeacherPage")
    public ModelAndView loadTeacherPage(){

        List<Teacher> teachers = teacherDAO.getTeacherList();
        mv.setViewName("teacherPage");
        mv.addObject("Teachers",teachers);
        return mv;
    }
}
