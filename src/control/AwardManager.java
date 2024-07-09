package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Beanstudent;
import util.DBUtil;

public class AwardManager {
	public Object[][] getAwardsStatistics(String startDate, String endDate, String major) {
        String sql = "SELECT competition_name, award_level, COUNT(*) as quantity " +
                     "FROM AwardDetails " +
                     "WHERE major = ? AND held_time BETWEEN ? AND ? " +
                     "GROUP BY competition_name, award_level";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, major);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();

                Object[][] results = new Object[rowCount][3];
                int i = 0;
                while (rs.next()) {
                    results[i][0] = rs.getString("competition_name");
                    results[i][1] = rs.getString("award_level");
                    results[i][2] = rs.getInt("quantity");
                    i++;
                }
                return results;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0][0];
    }
	public Object[][] getClassAwardsStatistics(String startDate, String endDate, String className) {
        String sql = "SELECT competition_name, award_level, COUNT(*) as quantity " +
                     "FROM AwardDetails " +
                     "WHERE class = ? AND held_time BETWEEN ? AND ? " +
                     "GROUP BY competition_name, award_level";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, className);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.last();
                int rowCount = rs.getRow();
                rs.beforeFirst();

                Object[][] results = new Object[rowCount][3];
                int i = 0;
                while (rs.next()) {
                    results[i][0] = rs.getString("competition_name");
                    results[i][1] = rs.getString("award_level");
                    results[i][2] = rs.getInt("quantity");
                    i++;
                }
                return results;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0][0];
    }
	 public Object[][] getAwardsStatistics(String startDate, String endDate, String competitionName, String challengeName) {
	        String sql = "SELECT competition_name,challenge_name, award_level, COUNT(*) as quantity " +
	                     "FROM awardchallenge " +
	                     "WHERE (held_time BETWEEN ? AND ? " +
	                     "AND competition_name = ? ) " +
	                     "or (held_time BETWEEN ? AND ? "+" AND challenge_name = ? )" +
	                     "GROUP BY competition_name, award_level";
	        try (Connection con = DBUtil.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setString(1, startDate);
	            pstmt.setString(2, endDate);
	            pstmt.setString(3, competitionName);
	            pstmt.setString(6, challengeName);
	            pstmt.setString(4, startDate);
	            pstmt.setString(5, endDate);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                rs.last();
	                int rowCount = rs.getRow();
	                rs.beforeFirst();

	                Object[][] results = new Object[rowCount][3];
	                int i = 0;
	                while (rs.next()) {
	                    results[i][0] = rs.getString("competition_name");
	                    results[i][1] = rs.getString("award_level");
	                    results[i][2] = rs.getInt("quantity");
	                    i++;
	                }
	                return results;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return new Object[0][0];
	    }

	 public List<Object[]> getAwardstudent(String startDate, String endDate, String studentId, String competitionName, String challengeName) {
	        List<Object[]> awardsList = new ArrayList<>();

	        String query = "SELECT student_id, student_name, class, major, competition_name, challenge_name, award_level, competition_type " +
	                       "FROM AwardView " +
	                       "WHERE held_time BETWEEN ? AND ? " +
	                       (studentId != null && !studentId.isEmpty() ? "AND student_id = ? " : "") +
	                       (competitionName != null && !competitionName.isEmpty() ? "AND competition_name LIKE ? " : "") +
	                       (challengeName != null && !challengeName.isEmpty() ? "AND challenge_name LIKE ? " : "");

	        try (Connection connection =DBUtil.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {

	            int paramIndex = 1;
	            statement.setString(paramIndex++, startDate);
	            statement.setString(paramIndex++, endDate);
	            
	            if (studentId != null && !studentId.isEmpty()) {
	                statement.setString(paramIndex++, studentId);
	            }
	            if (competitionName != null && !competitionName.isEmpty()) {
	                statement.setString(paramIndex++, "%" + competitionName + "%");
	            }
	            if (challengeName != null && !challengeName.isEmpty()) {
	                statement.setString(paramIndex++, "%" + challengeName + "%");
	            }

	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                Object[] row = new Object[8];
	                row[0] = resultSet.getString("student_name");
	                row[1] = resultSet.getString("class");
	                row[2] = resultSet.getString("major");
	                row[3] = resultSet.getString("competition_name");
	                row[4] = resultSet.getString("challenge_name");
	                row[5] = resultSet.getString("award_level");
	                row[6] = resultSet.getString("competition_type");
	                awardsList.add(row);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return awardsList;
	    }
}
