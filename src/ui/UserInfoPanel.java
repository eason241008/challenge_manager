package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.AdminManager;
import model.BeanAdministrators;
import model.Beanstudent;
import util.DBUtil;
import util.StringUtil;

public class UserInfoPanel extends JPanel {
    private JTextField studentidText;
    private JTextField password_Text;
    private AdminManager Stu_dao = new AdminManager();
    private DBUtil dbUtil = new DBUtil();

    public UserInfoPanel() {
        setLayout(new BorderLayout());
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        add(contentPane, BorderLayout.CENTER);

        JLabel studentid = new JLabel("管理员ID:");
        JLabel lblNewLabel_4 = new JLabel("密码:");

        studentidText = new JTextField();
        studentidText.setEditable(false);
        studentidText.setColumns(10);

        password_Text = new JTextField();
        password_Text.setColumns(10);

        JButton btnNewButton = new JButton("修改");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeActionPerformed(e);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(62)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(studentid)
                            .addGap(18)
                            .addComponent(studentidText, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(lblNewLabel_4)
                            .addGap(18)
                            .addComponent(password_Text)))
                    .addContainerGap(159, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                    .addContainerGap(569, Short.MAX_VALUE)
                    .addComponent(btnNewButton)
                    .addGap(40))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(82)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(studentid)
                        .addComponent(studentidText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(45)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_4)
                        .addComponent(password_Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(73)
                    .addComponent(btnNewButton)
                    .addContainerGap(42, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        fillPane();
    }

    protected void changeActionPerformed(ActionEvent e) {
        String password = password_Text.getText();
        String adminid = studentidText.getText();
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }

        Connection con = null;
        try {
            con = dbUtil.getConnection();
            String sql = "UPDATE administrators SET password = ? WHERE administrator_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, password);
            pstmt.setString(2, adminid);

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
        BeanAdministrators admin = new BeanAdministrators();
        admin.setAdministratorId(loadFrm.userid);
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            ResultSet rs = AdminManager.list(con, admin);
            if (rs.next()) {
                studentidText.setText(String.valueOf(rs.getInt("administrator_id")));
                password_Text.setText(rs.getString("password"));
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
