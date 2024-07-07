package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BeanChallenge;
import util.DBUtil;
/**
 * ¾ºÈü¹ÜÀí
 * @author 13020
 *
 */
public class ChallengeManager {


    public boolean addChallenge(BeanChallenge challenge) {
        String sql = "INSERT INTO ChallengeInfo (challenge_id, challenge_name, host, organizer, whether_zudui, des) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, challenge.getChallengeId());
            pstmt.setString(2, challenge.getChallengeName());
            pstmt.setString(3, challenge.getHost());
            pstmt.setString(4, challenge.getOrganizer());
            pstmt.setBoolean(5, challenge.isWhetherZudui());
            pstmt.setString(6, challenge.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteChallenge(int challengeId) {

        String checkSql = "SELECT COUNT(*) FROM Challenge_ArrangementInfo WHERE challenge_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement checkPstmt = con.prepareStatement(checkSql)) {
            checkPstmt.setInt(1, challengeId);
            try (ResultSet rs = checkPstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "DELETE FROM ChallengeInfo WHERE challenge_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, challengeId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateChallenge(BeanChallenge challenge) {
        String sql = "UPDATE ChallengeInfo SET challenge_name = ?, host = ?, organizer = ?, whether_zudui = ?, des = ? WHERE challenge_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, challenge.getChallengeName());
            pstmt.setString(2, challenge.getHost());
            pstmt.setString(3, challenge.getOrganizer());
            pstmt.setBoolean(4, challenge.isWhetherZudui());
            pstmt.setString(5, challenge.getDescription());
            pstmt.setInt(6, challenge.getChallengeId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BeanChallenge findChallenge(int challengeId) {
        String sql = "SELECT * FROM ChallengeInfo WHERE challenge_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, challengeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BeanChallenge challenge = new BeanChallenge();
                    challenge.setChallengeId(rs.getInt("challenge_id"));
                    challenge.setChallengeName(rs.getString("challenge_name"));
                    challenge.setHost(rs.getString("host"));
                    challenge.setOrganizer(rs.getString("organizer"));
                    challenge.setWhetherZudui(rs.getBoolean("whether_zudui"));
                    challenge.setDescription(rs.getString("des"));
                    return challenge;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
