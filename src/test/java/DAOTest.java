import com.lab2.dao.DAO;
import com.lab2.dao.DAOImpl;
import com.lab2.model.Student;
import com.lab2.model.Teacher;
import com.lab2.model.Mark;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.*;

public class DAOTest {

    Connection connection;
    DAOImpl<Student> studentDAO;
    DAOImpl<Teacher> teacherDAO;
    DAOImpl<Mark> markDAO;

    @Before
    public void init() throws IOException {
        MockFileDatabase db = new MockFileDatabase(new File(System.getProperty("user.dir") + "/db.test"));
        connection = new MockConnection(db);

        studentDAO = new DAOImpl<Student>(Student.class, connection);
        teacherDAO = new DAOImpl<Teacher>(Teacher.class, connection);
        markDAO = new DAOImpl<Mark>(Mark.class, connection);
    }

    @Test
    public void getValidStudentList() throws SQLException {
        List<Student> students = studentDAO.getEntityList();

        assertEquals(2, students.size());
        assertEquals("student_1", students.get(0).getName());
        assertEquals("student_2", students.get(1).getName());
    }

    @Test
    public void getValidTeacherList() throws SQLException {
        List<Teacher> teachers = teacherDAO.getEntityList();

        assertEquals(2, teachers.size());
        assertEquals("degree_1", teachers.get(0).getAcademicDegree());
        assertEquals("degree_2", teachers.get(1).getAcademicDegree());
    }

    @Test
    public void getValidMarkList() throws SQLException {
        List<Mark> marks = markDAO.getEntityList();
        Long studentId = 1L;

        assertEquals(1, marks.size());
        assertEquals(studentId, marks.get(0).getStudentId());
    }

    @Test
    public void getValidStudentById() throws SQLException {
        Long id = 1L;
        Student student = studentDAO.getEntity(id);

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals("student_1", student.getName());
        assertEquals("spec_1", student.getSpecialization());
    }

    @Test
    public void getNonExistingStudent() throws SQLException {
        Long id = 3L;
        Student student = studentDAO.getEntity(id);

        assertNull(student);
    }

    @Test
    public void getValidTeacherById() throws SQLException {
        Long id = 1L;
        Teacher teacher = teacherDAO.getEntity(id);

        assertNotNull(teacher);
        assertEquals(id, teacher.getId());
        assertEquals("subject_1", teacher.getSubject());
    }

    @Test
    public void getNonExistingTeacher() throws SQLException {
        Long id = 3L;
        Teacher teacher = teacherDAO.getEntity(id);

        assertNull(teacher);
    }

    @Test
    public void getValidMarkById() throws SQLException {
        Long id = 3L;
        Mark mark = markDAO.getEntity(id);

        assertNotNull(mark);
        assertEquals(id, mark.getId());
    }

    @Test
    public void getNonExistingMark() throws SQLException {
        Long id = 3L;
        Mark mark = markDAO.getEntity(id);

        assertNull(mark);
    }
}
