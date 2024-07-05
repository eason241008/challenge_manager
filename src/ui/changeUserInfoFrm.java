package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import util.*;
import control.*;
import model.Beanstudent;
import java.sql.*;
public class changeUserInfoFrm extends JFrame {

	private JPanel contentPane;
	private JTextField studentidText;
	private JTextField studentclassText;
	private JTextField password_Text;
	private JTextField studentnameText;
	private JTextField majorText;
	private JTextField mobilephoneText;
	private AdminManager Stu_dao=new AdminManager();
    private DBUtil dbUtil=new DBUtil();
    private JTextField studentemailText;
    private JTextField studentqqText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeUserInfoFrm frame = new changeUserInfoFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//public studentFrm() {
//		setTitle("\u5B66\u751F\u7ADE\u8D5B\u83B7\u5956\u67E5\u8BE2");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//	}
	public changeUserInfoFrm(int userId) {
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel studentid = new JLabel("\u5B66\u53F7:");
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D:");
		
		JLabel lblNewLabel_2 = new JLabel("\u73ED\u7EA7:");
		
		JLabel lblNewLabel_3 = new JLabel("\u4E13\u4E1A:");
		
		JLabel lblNewLabel_4 = new JLabel("\u5BC6\u7801:");
		
		JLabel lblNewLabel_5 = new JLabel("\u624B\u673A\u53F7:");
		
		studentidText = new JTextField();
		studentidText.setEditable(false);
		studentidText.setColumns(10);
		
		studentclassText = new JTextField();
		studentclassText.setEditable(false);
		studentclassText.setColumns(10);
		
		password_Text = new JTextField();
		password_Text.setColumns(10);
		
		studentnameText = new JTextField();
		studentnameText.setEditable(false);
		studentnameText.setColumns(10);
		
		majorText = new JTextField();
		majorText.setEditable(false);
		majorText.setColumns(10);
		
		mobilephoneText = new JTextField();
		mobilephoneText.setColumns(10);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeActionPerformed(e);
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("\u90AE\u7BB1:");
		
		studentemailText = new JTextField();
		studentemailText.setColumns(10);
		
		studentqqText = new JTextField();
		studentqqText.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("QQ\u53F7:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(569, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(40))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(studentemailText))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(studentid)
								.addComponent(lblNewLabel_4))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(password_Text)
								.addComponent(studentidText, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(studentclassText)))
					.addGap(160)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(studentnameText))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addComponent(majorText))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(studentqqText))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(mobilephoneText, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentid)
						.addComponent(lblNewLabel_1)
						.addComponent(studentidText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentnameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentclassText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(majorText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1)
						.addComponent(studentemailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1)
						.addComponent(studentqqText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblNewLabel_4)
						.addComponent(password_Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(mobilephoneText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addComponent(btnNewButton)
					.addGap(42))
		);
		contentPane.setLayout(gl_contentPane);
		fillPane();
		
	}
	public changeUserInfoFrm() {
		// TODO Auto-generated constructor stub
	}

	protected void changeActionPerformed(ActionEvent e) {
	    String password = password_Text.getText();  
	    String studentId=studentidText.getText();
	    String studentemail = studentemailText.getText(); 
        String studentqq=studentqqText.getText();
        String studentmobilephone =mobilephoneText.getText();
	    if (StringUtil.isEmpty(password)) {
	    	JOptionPane.showMessageDialog(null, "密码不能为空");
	        return;
	    }

	    Connection con = null;
	    try {
	        con = dbUtil.getConnection();
	        String sql = "UPDATE studentinfo SET password = ?, mobilephone = ? ,qq = ? ,email = ? WHERE student_id = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);

	        pstmt.setString(1, password);
	        pstmt.setString(2, studentmobilephone);
	        pstmt.setString(3, studentqq);
	        pstmt.setString(4, studentemail);
	        pstmt.setInt(5, Integer.parseInt(studentId));

	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            JOptionPane.showMessageDialog(null, "更新成功");
	        } else {
	            JOptionPane.showMessageDialog(null, "更新失败");
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "数据库错误: " + ex.getMessage());
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}


	private void fillPane() {
	    Beanstudent student = new Beanstudent();
	    student.setStudentId(loadFrm.userid);
	    Connection con = null;
	    try {
	        con = dbUtil.getConnection();
	        java.sql.ResultSet rs = AdminManager.list(con, student);
	        if (rs.next()) {
	            studentidText.setText(String.valueOf(rs.getInt("student_id")));
	            studentnameText.setText(rs.getString("student_name"));
	            studentclassText.setText(rs.getString("class"));
	            majorText.setText(rs.getString("major"));
	            password_Text.setText(rs.getString("password"));
	            if (!StringUtil.isEmpty(rs.getString("mobilephone"))) {
	            	mobilephoneText.setText(rs.getString("mobilephone"));
	            } else {
	            	mobilephoneText.setText("");
	            }
	            if (!StringUtil.isEmpty(rs.getString("email"))) {
	            	studentemailText.setText(rs.getString("email"));
	            } else {
	            	studentemailText.setText("");
	            }
	            if (!StringUtil.isEmpty(rs.getString("qq"))) {
	            	studentqqText.setText(rs.getString("qq"));
	            } else {
	            	studentqqText.setText("");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            con.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	}
}
