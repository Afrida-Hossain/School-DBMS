package DAO;

import Classes.Course;
import Classes.Student;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testing course CRUDs")
class CourseDAOTest {

    Course actual;
    CourseDAO courseDAO;
    String name;
    int teacherID;
    int studentRoll;

    @BeforeEach
    public void beforeEachInit(){
        actual = new Course();
        courseDAO = new CourseDAO();
        name = "Abc";
        teacherID = 1;
        studentRoll = 1;
    }

    @Test
    @Order(1)
    @DisplayName("Test adding new course")
    void addCourse() {

        try (Session session = courseDAO.connect()) {
            courseDAO.addCourse(name);
            actual = session.get(Course.class, name);
            assertEquals(name, actual.getCourseName(), "The names should match");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Test adding course teacher")
    void addCourseTeacher() {

        try (Session session = courseDAO.connect()) {
            courseDAO.addCourseTeacher(name, teacherID);
            actual = session.get(Course.class, name);
            assertEquals(teacherID, actual.getTeacher().getId(), "The names should match");
        }
    }


    @Test
    @Order(3)
    @DisplayName("Test removing course teacher")
    void removeTeacher() {

        try (Session session = courseDAO.connect()) {
            courseDAO.removeTeacher(name);
            actual = session.get(Course.class, name);
            assertEquals(null, actual.getTeacher(), "The teacher ID should be NULL");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Test adding a course for a student")
    void addCourseStudent() {

        try (Session session = courseDAO.connect()) {
            courseDAO.addCourseStudent(name,studentRoll);
            Student student = session.get(Student.class,studentRoll);
            actual = session.get(Course.class, name);
            assertEquals(true, student.getCourse().contains(actual),"The student should have the course");
        }
    }

    @Test
    @Order(4)
    @DisplayName("Test deleting a course")
    void deleteCourse() {

        try (Session session = courseDAO.connect()) {
            courseDAO.deleteCourse(name);
            actual = session.get(Course.class, name);
            assertThrows(NullPointerException.class,() -> actual.getCourseName(),"The name should be NULL");
        }
    }

}