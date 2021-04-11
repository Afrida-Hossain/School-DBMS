package DAO;

import Classes.Student;
import Classes.Teacher;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Nested
@DisplayName("Testing Teacher CRUDs")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeacherDAOTest {

    Teacher actual;
    TeacherDAO teacherDAO;
    String name;
    int id;

    @BeforeEach
    public void beforeEachInit(){
        actual = new Teacher();
        teacherDAO = new TeacherDAO();
        name = "Abc";
        id = 100;
    }

    @Test
    @Order(1)
    @DisplayName("Test adding teacher")
    void addTeacher() {

        try (Session session = teacherDAO.connect()) {
            /*Add teacher Test*/
            teacherDAO.addTeacher(name, id);
            actual = session.get(Teacher.class, id);
            assertEquals(name, actual.getName(), "The names should match");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Test editing teacher")
    void editTeacher() {

        try (Session session = teacherDAO.connect()) {
            /*Edit Student Test*/
            String editedName = "edit";

            teacherDAO.editTeacher(id,editedName);
            actual = session.get(Teacher.class,id);
            assertEquals(editedName,actual.getName(),"The edited names should match");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Test deleting teacher")
    void deleteTeacher() {

        try (Session session = teacherDAO.connect()) {
            /*Delete Student Test*/
            teacherDAO.deleteTeacher(id);
            actual = session.get(Teacher.class,id);
            assertThrows(NullPointerException.class, () ->actual.getName(),"Should throw NullPointerException");

        }
    }

}