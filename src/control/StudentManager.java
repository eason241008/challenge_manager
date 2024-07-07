package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Beanstudent;
import util.DBUtil;
/**
 * 学生管理
 * @author 13020
 *
 */
public class StudentManager {

    // 添加学生
    public boolean addStudent(Beanstudent student) {
        String sql = "INSERT INTO StudentInfo (student_id, student_name, Enrollment_year, class, grade, major, mobilephone, email, qq,password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , "+"123456)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
        	int id=student.getStudentId();
            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getStudentName());
            pstmt.setString(3, student.getEnrollmentYear());
            pstmt.setString(4, student.getClassName());
            pstmt.setString(5, student.getGrade());
            pstmt.setString(6, student.getMajor());
            pstmt.setString(7, student.getMobilePhone());
            pstmt.setString(8, student.getEmail());
            pstmt.setString(9, student.getQq());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除学生
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM StudentInfo WHERE student_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新学生信息
    public boolean updateStudent(Beanstudent student) {
        String sql = "UPDATE StudentInfo SET student_name = ?, Enrollment_year = ?, class = ?, grade = ?, major = ?, mobilephone = ?, email = ?, qq = ? WHERE student_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentName());
            pstmt.setString(2, student.getEnrollmentYear());
            pstmt.setString(3, student.getClassName());
            pstmt.setString(4, student.getGrade());
            pstmt.setString(5, student.getMajor());
            pstmt.setString(6, student.getMobilePhone());
            pstmt.setString(7, student.getEmail());
            pstmt.setString(8, student.getQq());
            pstmt.setInt(9, student.getStudentId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查找学生
    public Beanstudent findStudent(int studentId) {
        String sql = "SELECT * FROM StudentInfo WHERE student_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	Beanstudent student = new Beanstudent();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentName(rs.getString("student_name"));
                    student.setEnrollmentYear(rs.getString("Enrollment_year"));
                    student.setClassName(rs.getString("class"));
                    student.setGrade(rs.getString("grade"));
                    student.setMajor(rs.getString("major"));
                    student.setMobilePhone(rs.getString("mobilephone"));
                    student.setEmail(rs.getString("email"));
                    student.setQq(rs.getString("qq"));
                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
