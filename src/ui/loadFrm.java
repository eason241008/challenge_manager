package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.MainFrm;
import ui.changeUserInfoFrm;

import control.AdminManager;
import  model.BeanAdministrators;
import model.Beanstudent;
import  util.DBUtil;
import  util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class loadFrm extends JFrame {
    public static int userid;
	private JPanel contentPane;
	private JTextField name_text;
	private JTextField pwd_text;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
    private DBUtil dbUtil=new DBUtil();
    private JComboBox userTypeJcb;
    /**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadFrm frame = new loadFrm();
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
	public loadFrm() {
		setTitle("\u7ADE\u8D5B\u83B7\u5956\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		
		name_text = new JTextField();
		name_text.setHorizontalAlignment(SwingConstants.LEFT);
		name_text.setColumns(10);
		
		pwd_text = new JTextField();
		pwd_text.setHorizontalAlignment(SwingConstants.LEFT);
		pwd_text.setColumns(10);
		
		lblNewLabel_2 = new JLabel("\u7ADE\u8D5B\u83B7\u5956\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\program eason\\Java\\eclipse\\shrot_term\\src\\image\\learn.png"));
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 56));
		
		lblNewLabel_1 = new JLabel("\u5BC6\u7801:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 21));
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valuesetactionPerformed(e);
			}
		});
		btnReset.setFont(new Font("宋体", Font.PLAIN, 21));
		
		userTypeJcb = new JComboBox();
		userTypeJcb.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u7BA1\u7406\u5458"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(227)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(name_text))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(pwd_text, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnNewButton)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
											.addGap(14))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(176)
							.addComponent(userTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(name_text, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwd_text, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(userTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(64))
		);
		this.setLocationRelativeTo(null);
		
		contentPane.setLayout(gl_contentPane);
	}

	private void loginActionPerformed(ActionEvent arg0) {

	    String name = this.name_text.getText();
	    String password = this.pwd_text.getText();
	    
	    if (StringUtil.isEmpty(name)) {
	        JOptionPane.showMessageDialog(null, "名字不能为空");
	        return;
	    }
	    
	    if (StringUtil.isEmpty(password)) {
	        JOptionPane.showMessageDialog(null, "密码不能为空");
	        return;
	    }
	    int userId=Integer.valueOf(name);
	    String userType = userTypeJcb.getSelectedItem().toString();
	    if (userType.equals("学生")) {
            Beanstudent student = new Beanstudent(userId, password);
	    	
	        Connection con = null;
	        try {
	            con = dbUtil.getConnection();
	            Beanstudent currentStudent = AdminManager.stulogin(con, student);
	            if (currentStudent != null) {
	                userId = userId;  // This line seems redundant
	                dispose();
	                new studentMainFrm(userId).setVisible(true);
	            } else {
	                JOptionPane.showMessageDialog(null, "用户名或密码错误或类别错误");
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
	    else {
	    	BeanAdministrators admin = new BeanAdministrators(userId, password);
	        Connection con = null;
	        try {
	            con = dbUtil.getConnection();
	            BeanAdministrators currentAdmin = AdminManager.adminlogin(con, admin);
	            if (currentAdmin != null) {
	                userId = userId;  // This line seems redundant
	                dispose();
	                new MainFrm().setVisible(true);
	            } else {
	                JOptionPane.showMessageDialog(null, "用户名或密码错误或类别错误");
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
	private void valuesetactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.name_text.setText("");
	    this.pwd_text.setText("");
	    
	}
}