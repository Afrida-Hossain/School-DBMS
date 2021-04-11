package DAO;

import Classes.Student;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testing student CRUDs")
class StudentDAOTest {

    Student actual;
    StudentDAO studentDAO;
    String name;
    int roll;

    @BeforeEach
    public void beforeEachInit(){
        actual = new Student();
        studentDAO = new StudentDAO();
        name = "Abc";
        roll = 100;
    }


    @Test
    @Order(1)
    @DisplayName("Test adding student")
    public void testStudentAdd() {

        try (Session session = studentDAO.connect()) {
            /*Add Student Test*/
            studentDAO.addStudent(name, roll);
            actual = session.get(Student.class, roll);
            assertEquals(name, actual.getName(), "The names should match");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Test viewing student")
    public void testStudentView() {

            actual = studentDAO.viewStudent(roll);
            assertEquals(name,actual.getName(),"Fetched student name should match");
    }

    @Test
    @Order(3)
    @DisplayName("Test editing student")
    public void testStudentEdit() {

        try (Session session = studentDAO.connect()) {
            /*Edit Student Test*/
            String editedName = "edit";

            studentDAO.editStudent(roll,editedName);
            actual = session.get(Student.class,roll);
            assertEquals(editedName,actual.getName(),"The edited names should match");
        }
    }

    @Test
    @Order(4)
    @DisplayName("Test deleting student")
    public void testStudentDelete() {

        try (Session session = studentDAO.connect()) {
            /*Delete Student Test*/
            studentDAO.deleteStudent(roll);
            actual = session.get(Student.class,roll);
            assertThrows(NullPointerException.class, () ->actual.getName(),"Should throw NullPointerException");

        }
    }

}