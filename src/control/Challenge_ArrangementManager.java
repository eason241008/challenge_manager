
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BeanChallengeArrangement;
import util.DBUtil;
/**
 * 赛事管理
 * @author 13020
 *
 */
public class Challenge_ArrangementManager {


    public boolean addArrangement(BeanChallengeArrangement arrangement) {
        String sql = "INSERT INTO Challenge_ArrangementInfo (competition_id,challenge_id, competition_name, held_time, held_address) VALUES (? , ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, arrangement.getCompetitionId());
            pstmt.setInt(2, arrangement.getChallengeId());
            pstmt.setString(3, arrangement.getCompetitionName());
            pstmt.setString(4, arrangement.getHeldTime());
            pstmt.setString(5, arrangement.getHeldAddress());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteArrangement(int competitionId) {
        String sql = "DELETE FROM Challenge_ArrangementInfo WHERE competition_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, competitionId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateArrangement(BeanChallengeArrangement arrangement) {
        String sql = "UPDATE Challenge_ArrangementInfo SET challenge_id = ?, competition_name = ?, held_time = ?, held_address = ? WHERE competition_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, arrangement.getChallengeId());
            pstmt.setString(2, arrangement.getCompetitionName());
            pstmt.setString(3, arrangement.getHeldTime());
            pstmt.setString(4, arrangement.getHeldAddress());
            pstmt.setInt(5, arrangement.getCompetitionId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BeanChallengeArrangement findArrangement(int competitionId) {
        String sql = "SELECT * FROM Challenge_ArrangementInfo WHERE competition_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, competitionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	BeanChallengeArrangement arrangement = new BeanChallengeArrangement();
                    arrangement.setCompetitionId(rs.getInt("competition_id"));
                    arrangement.setChallengeId(rs.getInt("challenge_id"));
                    arrangement.setCompetitionName(rs.getString("competition_name"));
                    arrangement.setHeldTime(rs.getString("held_time"));
                    arrangement.setHeldAddress(rs.getString("held_address"));
                    return arrangement;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean challengeExists(int challengeId) {
        String sql = "SELECT COUNT(*) FROM ChallengeInfo WHERE challenge_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, challengeId);
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
}
