package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import model.BeanTeam;
import model.BeanTeamMember;

public class TeamManager {
    // 获取队伍基本信息
    public BeanTeam getTeam(int teamId) {
        String sql = "SELECT * FROM TeamInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BeanTeam team = new BeanTeam();
                    team.setTeamId(rs.getInt("team_id"));
                    team.setCompetitionId(rs.getInt("competition_id"));
                    team.setTeamName(rs.getString("team_name"));
                    team.setTeamNameEng(rs.getString("team_name_eng"));
                    team.setCreateTime(rs.getString("create_time"));
                    team.setRemark(rs.getString("remark"));
                    team.setMemberCounts(rs.getInt("member_counts"));
                    return team;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取队伍成员信息
    public List<BeanTeamMember> getTeamMembers(int teamId) {
        List<BeanTeamMember> teamMembers = new ArrayList<>();
        String sql = "SELECT * FROM TeamMemberInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BeanTeamMember teamMember = new BeanTeamMember();
                    teamMember.setStudentId(rs.getInt("student_id"));
                    teamMember.setTeamId(rs.getInt("team_id"));
                    teamMember.setLeader(rs.getBoolean("isLeader"));
                    teamMembers.add(teamMember);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamMembers;
    }

    public boolean addTeam(BeanTeam team) {
        String sql = "INSERT INTO TeamInfo (team_id, competition_id, team_name, team_name_eng, create_time, remark, member_counts) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, team.getTeamId());
            pstmt.setInt(2, team.getCompetitionId());
            pstmt.setString(3, team.getTeamName());
            pstmt.setString(4, team.getTeamNameEng());
            pstmt.setString(5, team.getCreateTime());
            pstmt.setString(6, team.getRemark());
            pstmt.setInt(7, team.getMemberCounts());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTeam(BeanTeam team) {
        String sql = "UPDATE TeamInfo SET team_name = ?, team_name_eng = ? WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, team.getTeamName());
            pstmt.setString(2, team.getTeamNameEng());
            pstmt.setInt(3, team.getTeamId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTeamLeader(int teamId, int leaderId) {
        String resetSql = "UPDATE TeamMemberInfo SET isLeader = 0 WHERE team_id = ?";
        String setLeaderSql = "UPDATE TeamMemberInfo SET isLeader = 1 WHERE team_id = ? AND student_id = ?";
        try (Connection con = DBUtil.getConnection()) {
            try (PreparedStatement resetPstmt = con.prepareStatement(resetSql);
                 PreparedStatement setLeaderPstmt = con.prepareStatement(setLeaderSql)) {
                con.setAutoCommit(false); // Start transaction
                resetPstmt.setInt(1, teamId);
                resetPstmt.executeUpdate();
                setLeaderPstmt.setInt(1, teamId);
                setLeaderPstmt.setInt(2, leaderId);
                setLeaderPstmt.executeUpdate();
                con.commit(); // Commit transaction
                return true;
            } catch (SQLException e) {
                con.rollback(); // Rollback transaction on error
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTeamMember(BeanTeamMember teamMember) {
        String sql = "INSERT INTO TeamMemberInfo (student_id, team_id, isLeader) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamMember.getStudentId());
            pstmt.setInt(2, teamMember.getTeamId());
            pstmt.setBoolean(3, teamMember.isLeader());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeamMember(BeanTeamMember teamMember) {
        String sql = "DELETE FROM TeamMemberInfo WHERE student_id = ? AND team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamMember.getStudentId());
            pstmt.setInt(2, teamMember.getTeamId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean studentExists(int studentId) {
        String sql = "SELECT COUNT(*) FROM StudentInfo WHERE student_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean teamExists(int teamId) {
        String sql = "SELECT COUNT(*) FROM TeamInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean teamHasAwards(int teamId) {
        String sql = "SELECT COUNT(*) FROM TeamTeacher_AwardInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeam(int teamId) {
        String sql = "DELETE FROM TeamInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTeamMemberByTeamId(int teamId) {
        String sql = "DELETE FROM TeamMemberInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    
    public boolean deleteTeamMember(int teamId) {
        String sql = "DELETE FROM TeamMemberInfo WHERE team_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
