package control;
import java.sql.*;


import model.BeanAdministrators;
import model.Beanstudent;
import util.*;
/**
 * 用户管理
 * @author 13020
 *
 */
public class AdminManager {
	public static BeanAdministrators adminlogin(Connection con, BeanAdministrators Administrator) throws Exception {
    	BeanAdministrators resultAdmin = null;
        String sql = "SELECT * FROM administrators WHERE admin_id = ? AND password = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, Administrator.getAdministratorId());
        pst.setString(2, Administrator.getPassword());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            resultAdmin = new BeanAdministrators();
            resultAdmin.setAdministratorId(rs.getInt("admin_id"));
            resultAdmin.setName(rs.getString("admin_name"));
            resultAdmin.setPassword(rs.getString("password"));

        }
        return resultAdmin;
    }
	public static Beanstudent stulogin(Connection con, Beanstudent student) throws Exception {
		Beanstudent resultStudent = null;
        String sql = "SELECT * FROM studentinfo WHERE student_id = ? AND password = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, student.getStudentId());
        pst.setString(2, student.getPassword());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            resultStudent = new Beanstudent();
            resultStudent.setStudentId(rs.getInt("student_id"));
            resultStudent.setStudentName(rs.getString("student_name"));
            resultStudent.setPassword(rs.getString("password"));
            resultStudent.setClassName(rs.getString("class"));
            resultStudent.setGrade(rs.getString("grade"));
            resultStudent.setClassName(rs.getString("major"));
            resultStudent.setMobilePhone(rs.getString("mobilephone"));
        }
        return resultStudent;
    }
	public static ResultSet list(Connection con, Beanstudent student) throws Exception {
	    StringBuffer sb = new StringBuffer("select * from studentinfo");
	    if (student.getStudentId() != 0) {
	        sb.append(" and student_id = " + student.getStudentId());
	    }
	    if (!StringUtil.isEmpty(student.getStudentName())) {
	        sb.append(" and student_name = '" + student.getStudentName() + "'");
	    }
	    if (!StringUtil.isEmpty(student.getGrade())) {
	        sb.append(" and grade ='" + student.getGrade() + "'");
	    }
	    if (!StringUtil.isEmpty(student.getClassName())) {
	        sb.append(" and class = '" + student.getClassName() + "'");
	    }
	    if (!StringUtil.isEmpty(student.getClassName())) {
	        sb.append(" and major = '" + student.getMajor() + "'");
	    }
	    PreparedStatement pst = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
	    ResultSet rs = pst.executeQuery();
	    return rs;
	}
}
