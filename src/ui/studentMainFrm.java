package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Font;

import control.AdminManager;
import model.Beanstudent;
import util.DBUtil;
import util.StringUtil;

public class studentMainFrm extends JFrame {

    private JPanel contentPane;
    private JPanel currentPanel;
    private JComboBox<String> startYearComboBox;
    private JComboBox<String> endYearComboBox;
    private JTextArea resultArea;
    private DBUtil dbUtil = new DBUtil();
    private int studentId;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    studentMainFrm frame = new studentMainFrm();
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
    public studentMainFrm(int userId) {
    	studentId=userId;
        setTitle("学生竞赛信息查询系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem mntmCompetitionInfo = new JMenuItem("竞赛信息查询");
        mntmCompetitionInfo.setBorder(new LineBorder(Color.BLACK)); // 添加边框
        mntmCompetitionInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCompetitionInfoPanel();
            }
        });
        menuBar.add(mntmCompetitionInfo);

        JMenuItem mntmUserInfoChange = new JMenuItem("用户信息修改");
        mntmUserInfoChange.setBorder(new LineBorder(Color.BLACK)); // 添加边框
        mntmUserInfoChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserInfoChangePanel();
            }
        });
        menuBar.add(mntmUserInfoChange);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        currentPanel = new JPanel(); // 初始化为默认面板
        contentPane.add(currentPanel, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    public studentMainFrm() {
		// TODO Auto-generated constructor stub
	}

	private void showCompetitionInfoPanel() {
        if (!(currentPanel instanceof CompetitionInfoPanel)) {
            contentPane.remove(currentPanel);
            currentPanel = new CompetitionInfoPanel();
            contentPane.add(currentPanel, BorderLayout.CENTER);
            contentPane.validate();
            contentPane.repaint();
        }
    }

    private void showUserInfoChangePanel() {
        if (!(currentPanel instanceof UserInfoChangePanel)) {
            contentPane.remove(currentPanel);
            currentPanel = new UserInfoChangePanel();
            contentPane.add(currentPanel, BorderLayout.CENTER);
            contentPane.validate();
            contentPane.repaint();
        }
    }
    public class CompetitionInfoPanel extends JPanel {
        public CompetitionInfoPanel() {
            setLayout(new BorderLayout());

            JLabel startYearLabel = new JLabel("开始年份:");
            startYearComboBox = new JComboBox<>(getYearRange());
            JLabel endYearLabel = new JLabel("结束年份:");
            endYearComboBox = new JComboBox<>(getYearRange());
            JButton searchButton = new JButton("查询");
            resultArea = new JTextArea();
            resultArea.setEditable(false);
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchCompetitionInfo();
                }
            });

            JPanel controlPanel = new JPanel();
            GroupLayout layout = new GroupLayout(controlPanel);
            controlPanel.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startYearLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(startYearComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(endYearLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(endYearComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(searchButton)
                        .addContainerGap(220, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(startYearLabel)
                            .addComponent(startYearComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(endYearLabel)
                            .addComponent(endYearComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            add(controlPanel, BorderLayout.NORTH);
            add(resultArea, BorderLayout.CENTER);
            
        }

        private String[] getYearRange() {
            ArrayList<String> years = new ArrayList<>();
            for (int i = 2010; i <= 2023; i++) {
                years.add(String.valueOf(i));
            }
            return years.toArray(new String[0]);
        }

        private void searchCompetitionInfo() {
            String startYear = (String) startYearComboBox.getSelectedItem();
            String endYear = (String) endYearComboBox.getSelectedItem();

            StringBuilder result = new StringBuilder();
            result.append("获奖情况:\n");

            try (Connection con = dbUtil.getConnection()) {
                // 查询单人获奖
                String individualQuery = "SELECT c.competition_name, c.held_time, i.award_level "
                        + "FROM individualteacher_awardinfo i "
                        + "JOIN challenge_arrangementinfo c ON i.competition_id = c.competition_id "
                        + "WHERE i.student_id =  ?  AND c.held_time BETWEEN ? AND ?";
               System.out.print(startYear);
               System.out.print(endYear);
               System.out.print(studentId);
                try (PreparedStatement pstmt = con.prepareStatement(individualQuery)) {
                    pstmt.setInt(1, studentId);
                    pstmt.setString(2, startYear);
                    pstmt.setString(3, endYear);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            result.append(String.format("竞赛名称: %s, 举办时间: %s, 奖项: %s\n",
                                    rs.getString("competition_name"),
                                    rs.getString("held_time"),
                                    rs.getString("award_level")));
                        }
                    }
                }

                // 查询团队获奖
                String teamQuery = "SELECT c.competition_name, c.held_time, t.award_level "
                        + "FROM teamTeacher_awardInfo t "
                        + "JOIN challenge_arrangementinfo c ON t.competition_id = c.competition_id "
                        + "JOIN teamMemberInfo m ON t.team_id = m.team_id "
                        + "WHERE m.student_id = ? AND c.held_time BETWEEN ? AND ?";
                try (PreparedStatement pstmt = con.prepareStatement(teamQuery)) {
                    pstmt.setInt(1, studentId);
                    pstmt.setString(2, startYear);
                    pstmt.setString(3, endYear);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            result.append(String.format("竞赛名称: %s, 举办时间: %s, 奖项: %s (团队)\n",
                                    rs.getString("competition_name"),
                                    rs.getString("held_time"),
                                    rs.getString("award_level")));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "查询失败: " + e.getMessage());
            }

            resultArea.setText(result.toString());
        }



   
    }
    public class UserInfoChangePanel extends JPanel {
        private JTextField studentidText;
        private JTextField studentclassText;
        private JTextField password_Text;
        private JTextField studentnameText;
        private JTextField majorText;
        private JTextField mobilephoneText;
        private AdminManager Stu_dao = new AdminManager();
        private DBUtil dbUtil = new DBUtil();
        private JTextField studentemailText;
        private JTextField studentqqText;

        public UserInfoChangePanel() {
            setLayout(new BorderLayout());
            JPanel contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setLayout(new BorderLayout(0, 0));
            add(contentPane, BorderLayout.CENTER);

            JLabel studentid = new JLabel("学号:");

            JLabel lblNewLabel_1 = new JLabel("姓名:");

            JLabel lblNewLabel_2 = new JLabel("班级:");

            JLabel lblNewLabel_3 = new JLabel("专业:");

            JLabel lblNewLabel_4 = new JLabel("密码:");

            JLabel lblNewLabel_5 = new JLabel("手机号:");

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

            JButton btnNewButton = new JButton("修改");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changeActionPerformed(e);
                }
            });

            JLabel lblNewLabel_2_1 = new JLabel("邮箱:");

            studentemailText = new JTextField();
            studentemailText.setColumns(10);

            studentqqText = new JTextField();
            studentqqText.setColumns(10);

            JLabel lblNewLabel_3_1 = new JLabel("QQ号:");
            GroupLayout gl_contentPane = new GroupLayout(contentPane);
            gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
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
                                .addComponent(lblNewLabel_3)
                                .addGap(18)
                                .addComponent(majorText))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(studentqqText))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblNewLabel_5)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(mobilephoneText, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
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
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblNewLabel_4)
                            .addComponent(password_Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNewLabel_5)
                            .addComponent(mobilephoneText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(73)
                        .addComponent(btnNewButton)
                        .addContainerGap(42, Short.MAX_VALUE))
            );
            contentPane.setLayout(gl_contentPane);
            fillPane();
        }

        protected void changeActionPerformed(ActionEvent e) {
            String password = password_Text.getText();
            String studentId = studentidText.getText();
            String studentemail = studentemailText.getText();
            String studentqq = studentqqText.getText();
            String studentmobilephone = mobilephoneText.getText();
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
                ResultSet rs = AdminManager.list(con, student);
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
    }
    

