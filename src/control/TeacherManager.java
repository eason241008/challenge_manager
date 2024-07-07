package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BeanTeacher;
import model.BeanTeacher;
import util.DBUtil;

/**
 * 教师管理
 * @author 13020
 *
 */
public class TeacherManager {

    // 添加教师
    public boolean addTeacher(BeanTeacher teacher) {
        String sql = "INSERT INTO TeacherInfo (teacher_id, teacher_name, department, mobilephone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teacher.getTeacherId());
            pstmt.setString(2, teacher.getTeacherName());
            pstmt.setString(3, teacher.getDepartment());
            pstmt.setString(4, teacher.getMobilePhone());
            pstmt.setString(5, teacher.getEmail());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除教师
    public boolean deleteTeacher(int teacherId) {
        String sql = "DELETE FROM TeacherInfo WHERE teacher_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新教师信息
    public boolean updateTeacher(BeanTeacher teacher) {
        String sql = "UPDATE TeacherInfo SET teacher_name = ?, department = ?, mobilephone = ?, email = ? WHERE teacher_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getTeacherName());
            pstmt.setString(2, teacher.getDepartment());
            pstmt.setString(3, teacher.getMobilePhone());
            pstmt.setString(4, teacher.getEmail());
            pstmt.setInt(5, teacher.getTeacherId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查找教师
    public BeanTeacher findTeacher(int teacherId) {
        String sql = "SELECT * FROM TeacherInfo WHERE teacher_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	BeanTeacher teacher = new BeanTeacher();
                    teacher.setTeacherId(rs.getInt("teacher_id"));
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    teacher.setDepartment(rs.getString("department"));
                    teacher.setMobilePhone(rs.getString("mobilephone"));
                    teacher.setEmail(rs.getString("email"));
                    return teacher;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
