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

        studentDAO = new DAOImpl<>(Student.class, connection);
        teacherDAO = new DAOImpl<>(Teacher.class, connection);
        markDAO = new DAOImpl<>(Mark.class, connection);
    }

    @Test
    public void getValidStudentList() throws SQLException {
        List<Student> students = studentDAO.getEntityList();

        assertEquals(2, students.size());
        assertEquals("user1", students.get(0).getName());
        assertEquals("user2", students.get(1).getName());
    }

    @Test
    public void getValidTeacherList() throws SQLException {
        List<Teacher> teachers = teacherDAO.getEntityList();

        assertEquals(2, teachers.size());
        assertEquals("test1", teachers.get(0).getTeachingExperience());
        assertEquals("test2", teachers.get(1).getTeachingExperience());
    }

    @Test
    public void getValidMarkList() throws SQLException {
        List<Mark> marks = markDAO.getEntityList();
        Long studentId = new Long(1);

        assertEquals(1, marks.size());
        assertEquals(studentId, marks.get(0).getStudentId());
    }

    @Test
    public void getValidStudentById() throws SQLException {
        Long id = new Long(1);
        Student student = studentDAO.getEntity(id);

        assertNotNull(student);
        assertEquals(id, student.getId());
        assertEquals("user1", student.getName());
        assertEquals("spec1", student.getSpecialization());
    }

    @Test
    public void wontFindNonExistingStudentInDB() throws SQLException {
        Long id = new Long(3);
        Student student = studentDAO.getEntity(id);

        assertNull(student);
    }

    @Test
    public void getValidTeacherById() throws SQLException {
        Long id = new Long(1);
        Teacher teacher = teacherDAO.getEntity(id);

        assertNotNull(teacher);
        assertEquals(id, teacher.getId());
        assertEquals("test1", teacher.getSubject());
    }

    @Test
    public void getValidMarkById() throws SQLException {
        Long id = new Long(3);
        Mark mark = markDAO.getEntity(id);

        assertNotNull(mark);
        assertEquals(id, mark.getId());
    }
}
