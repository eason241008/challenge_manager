package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import util.DBException;

public class TeamManager {

    public void assignStudentToTeam(int studentIdentifier, int teamId) throws DBException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT student_id FROM studentinfo WHERE student_id = ? OR student_name = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, studentIdentifier);
            pst.setInt(2, studentIdentifier);
            ResultSet rs = pst.executeQuery();
            
            if (!rs.next()) {
                throw new DBException("û���ҵ�ƥ���ѧ����Ϣ");
            }
            int studentId = rs.getInt("student_id");
            rs.close();
            pst.close();
            
            sql = "UPDATE TeamMemberInfo SET team_id = ? WHERE student_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, teamId);
            pst.setInt(2, studentId);
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new DBException("�����Ŷӳ�Ա��Ϣʧ��");
            }
            System.out.println("ѧ���ѳɹ����䵽�Ŷ�");
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("���ݿ�����쳣", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
