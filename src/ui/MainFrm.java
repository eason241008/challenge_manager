package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.TeamManager;
import model.*;
import util.*;
public class MainFrm extends JFrame {

    private JPanel contentPane;
    private TeacherPanel teacherPanel;
    private StudentPanel studentPanel;
    private ChallengePanel challengePanel;
    private Challenge_ArrangementPanel challenge_ArrangementPanel;
    private showHelp showHelp;
    private TeamAddPanel teamPanel;
    private TeamDeletePanel teamDeletePanel;
    private TeamChangeInfoPanel teamChangeInfoPanel;
    private JTable table;
    private DefaultTableModel tableModel;

    private TeamManager teamManager;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrm frame = new MainFrm();
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
    public MainFrm() {
        setTitle("竞赛获奖管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        teamDeletePanel = new TeamDeletePanel();
        studentPanel = new StudentPanel();
        teacherPanel = new TeacherPanel();
        challengePanel = new ChallengePanel();
        teamPanel = new TeamAddPanel();
        teamChangeInfoPanel = new TeamChangeInfoPanel();
        challenge_ArrangementPanel = new Challenge_ArrangementPanel();

        JMenuItem mntmNewMenuItem = new JMenuItem("学生管理");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentactionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("教师管理");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacheractionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("竞赛管理");
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                challengeactionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("赛事管理");
        mntmNewMenuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                competitionactionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_7);

        JMenu mnNewMenu_6 = new JMenu("队伍管理");
        menuBar.add(mnNewMenu_6);

        JMenuItem mntmNewMenuItem_17 = new JMenuItem("添加队伍");
        mntmNewMenuItem_17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamaddactrionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_17);

        JMenuItem mntmNewMenuItem_19 = new JMenuItem("删除队伍");
        mntmNewMenuItem_19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamdeteactionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_19);

        JMenuItem mntmNewMenuItem_20 = new JMenuItem("队伍信息修改");
        mntmNewMenuItem_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamchangeinfoactionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_20);

        JMenu mnNewMenu = new JMenu("竞赛信息查询");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_12 = new JMenuItem("学生获奖查询");
        mnNewMenu.add(mntmNewMenuItem_12);

        JMenuItem mntmNewMenuItem_21 = new JMenuItem("学生获奖统计");
        mnNewMenu.add(mntmNewMenuItem_21);

        JMenuItem mntmNewMenuItem_24 = new JMenuItem("赛事获奖统计");
        mnNewMenu.add(mntmNewMenuItem_24);

        JMenuItem mntmNewMenuItem_22 = new JMenuItem("班级获奖统计");
        mnNewMenu.add(mntmNewMenuItem_22);

        JMenuItem mntmNewMenuItem_23 = new JMenuItem("专业获奖统计");
        mnNewMenu.add(mntmNewMenuItem_23);

        JMenu mnNewMenu_1 = new JMenu("竞赛信息录入");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("学生奖项录入");
        mnNewMenu_1.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("教师奖项录入");
        mnNewMenu_1.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_2 = new JMenu("用户管理");
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("重置学生密码");
        mnNewMenu_2.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("个人信息管理");
        mnNewMenu_2.add(mntmNewMenuItem_4);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel leftPanel = new JPanel();
        contentPane.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10));

        JButton studentSearchButton = new JButton("学生查找");
        studentSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(studentSearchButton);

        JButton teacherSearchButton = new JButton("教师查找");
        teacherSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(teacherSearchButton);

        JButton competitionSearchButton = new JButton("竞赛查找");
        competitionSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(competitionSearchButton);

        JButton challengeSearchButton = new JButton("赛事查找");
        challengeSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(challengeSearchButton);

        JButton teamSearchButton = new JButton("队伍查找");
        teamSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(teamSearchButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {}
        );

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        this.setLocationRelativeTo(null);

        // Action listeners for the search buttons
        studentSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentActionPerformed(e);
            }
        });

        teacherSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTeacherActionPerformed(e);
            }
        });

        competitionSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCompetitionActionPerformed(e);
            }
        });

        challengeSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchChallengeActionPerformed(e);
            }
        });

        teamSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTeamActionPerformed(e);
            }
        });

        teamManager = new TeamManager();
    }

    protected void teamchangeinfoactionPerformed(ActionEvent e) {
        showTeamchangePanel();
    }

    protected void teamdeteactionPerformed(ActionEvent e) {
        showTeamdeletePanel();
    }

    protected void teamaddactrionPerformed(ActionEvent e) {
        showTeamaddPanel();
    }

    private void showTeamchangePanel() {
        contentPane.removeAll();
        contentPane.add(teamChangeInfoPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamdeletePanel() {
        contentPane.removeAll();
        contentPane.add(teamDeletePanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamaddPanel() {
        contentPane.removeAll();
        contentPane.add(teamPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    protected void competitionactionPerformed(ActionEvent e) {
        showCompetitionPanel();
    }

    protected void challengeactionPerformed(ActionEvent e) {
        showChallengePanel();
    }

    protected void studentactionPerformed(ActionEvent e) {
        showStudentPanel();
    }

    protected void teacheractionPerformed(ActionEvent e) {
        showTeacherPanel();
    }

    private void showChallengePanel() {
        contentPane.removeAll();
        contentPane.add(challengePanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showCompetitionPanel() {
        contentPane.removeAll();
        contentPane.add(challenge_ArrangementPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeacherPanel() {
        contentPane.removeAll();
        contentPane.add(teacherPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showStudentPanel() {
        contentPane.removeAll();
        contentPane.add(studentPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void searchChallengeActionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        model.setColumnIdentifiers(new String[] {"Challenge ID", "Challenge Name", "Host", "Organizer", "Whether Zudui", "Description"});

        String sql = "SELECT * FROM ChallengeInfo";
        try (Connection con = DBUtil.getConnection();
             java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("challenge_id"),
                    rs.getString("challenge_name"),
                    rs.getString("host"),
                    rs.getString("organizer"),
                    rs.getBoolean("whether_zudui"),
                    rs.getString("des")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchTeacherActionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        model.setColumnIdentifiers(new String[] {"Teacher ID", "Teacher Name", "Department", "Mobile Phone", "Email"});

        String sql = "SELECT * FROM TeacherInfo";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("teacher_id"),
                    rs.getString("teacher_name"),
                    rs.getString("department"),
                    rs.getString("mobilephone"),
                    rs.getString("email")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchCompetitionActionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        model.setColumnIdentifiers(new String[] {"Competition ID", "Competition Name", "Held Time", "Held Address"});

        String sql = "SELECT * FROM Challenge_ArrangementInfo";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("competition_id"),
                    rs.getString("competition_name"),
                    rs.getString("held_time"),
                    rs.getString("held_address")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchStudentActionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        model.setColumnIdentifiers(new String[] {"Student ID", "Student Name", "Enrollment Year", "Class", "Grade", "Major", "Mobile Phone", "Email", "QQ"});

        String sql = "SELECT * FROM StudentInfo";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("Enrollment_year"),
                    rs.getString("class"),
                    rs.getString("grade"),
                    rs.getString("major"),
                    rs.getString("mobilephone"),
                    rs.getString("email"),
                    rs.getString("qq")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchTeamActionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        model.setColumnIdentifiers(new String[] {"Team ID", "Competition ID", "Team Name", "Team Name Eng", "Create Time", "Remark", "Member Counts"});

        String sql = "SELECT * FROM TeamInfo";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("team_id"),
                    rs.getInt("competition_id"),
                    rs.getString("team_name"),
                    rs.getString("team_name_eng"),
                    rs.getString("create_time"),
                    rs.getString("remark"),
                    rs.getInt("member_counts")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
