package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import control.*;
import util.*;

public class MainFrm extends JFrame {

    private JPanel contentPane;
    private TeacherPanel teacherPanel;
    private StudentPanel studentPanel;
    private ChallengePanel challengePanel;
    private Challenge_ArrangementPanel challenge_ArrangementPanel;
    private TeamAddPanel teamPanel;
    private TeamDeletePanel teamDeletePanel;
    private TeamChangeInfoPanel teamChangeInfoPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private AwardMajorPanel awardMajorPanel;
    private TeamManager teamManager;
    private AwardClassPanel awardClassPanel;
    private AwardsearchPanel awardsearchPanel;
    private AwardstuPanel awardstuPanel;
    private AwardstuCalPanel awardstuCalPanel;
    private AwardstuLoadPanel awardstuLoadPanel;
    private ResetstuPanel resetstuPanel;
    private UserInfoPanel userInfoPanel;
    private JTextArea textArea;
    private Map<String, JPanel> panelMap;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrm frame = new MainFrm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

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
        awardMajorPanel = new AwardMajorPanel();
        awardClassPanel = new AwardClassPanel();
        awardsearchPanel = new AwardsearchPanel();
        awardstuPanel = new AwardstuPanel();
        awardstuCalPanel = new AwardstuCalPanel();
        awardstuLoadPanel = new AwardstuLoadPanel();
        userInfoPanel = new UserInfoPanel();
        resetstuPanel = new ResetstuPanel();

        panelMap = new HashMap<>();
        panelMap.put("Student", studentPanel);
        panelMap.put("Teacher", teacherPanel);
        panelMap.put("Challenge", challengePanel);
        panelMap.put("Competition", challenge_ArrangementPanel);
        panelMap.put("Team", teamChangeInfoPanel);

        JMenuItem mntmNewMenuItem = new JMenuItem("学生管理");
        mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem.addActionListener(this::studentactionPerformed);

        JMenuItem mntmNewMenuItem_8 = new JMenuItem("\u8FD4\u56DE");
        mntmNewMenuItem_8.setBackground(Color.LIGHT_GRAY);
        mntmNewMenuItem_8.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_8.addActionListener(e -> showMainPanel());
        menuBar.add(mntmNewMenuItem_8);
        menuBar.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("教师管理");
        mntmNewMenuItem_5.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_5.addActionListener(this::teacheractionPerformed);
        menuBar.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("竞赛管理");
        mntmNewMenuItem_6.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_6.addActionListener(this::challengeactionPerformed);
        menuBar.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("赛事管理");
        mntmNewMenuItem_7.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_7.addActionListener(this::competitionactionPerformed);
        menuBar.add(mntmNewMenuItem_7);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u7ADE\u8D5B\u4FE1\u606F\u5F55\u5165");
        mntmNewMenuItem_1.addActionListener(e -> showstuloadAwardhangePanel());
        mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_6 = new JMenu("队伍管理");
        mnNewMenu_6.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu_6);

        JMenuItem mntmNewMenuItem_17 = new JMenuItem("添加队伍");
        mntmNewMenuItem_17.addActionListener(this::teamaddactrionPerformed);
        mnNewMenu_6.add(mntmNewMenuItem_17);

        JMenuItem mntmNewMenuItem_20 = new JMenuItem("队伍信息修改");
        mntmNewMenuItem_20.addActionListener(this::teamchangeinfoactionPerformed);
        mnNewMenu_6.add(mntmNewMenuItem_20);

        JMenu mnNewMenu = new JMenu("竞赛信息查询");
        mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_12 = new JMenuItem("学生获奖查询");
        mntmNewMenuItem_12.addActionListener(this::awardstuactionPerformed);
        mnNewMenu.add(mntmNewMenuItem_12);

        JMenuItem mntmNewMenuItem_21 = new JMenuItem("学生获奖统计");
        mntmNewMenuItem_21.addActionListener(this::awardstuCalactionPerformed);
        mnNewMenu.add(mntmNewMenuItem_21);

        JMenuItem mntmNewMenuItem_24 = new JMenuItem("赛事获奖统计");
        mntmNewMenuItem_24.addActionListener(this::showchallengeawardactionPerformed);
        mnNewMenu.add(mntmNewMenuItem_24);

        JMenuItem mntmNewMenuItem_22 = new JMenuItem("班级获奖统计");
        mntmNewMenuItem_22.addActionListener(this::showClassAwardhangePanel);
        mnNewMenu.add(mntmNewMenuItem_22);

        JMenuItem mntmNewMenuItem_23 = new JMenuItem("专业获奖统计");
        mntmNewMenuItem_23.addActionListener(this::awardmajoractionPerformed);
        mnNewMenu.add(mntmNewMenuItem_23);

        JMenu mnNewMenu_2 = new JMenu("用户管理");
        mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("重置学生密码");
        mntmNewMenuItem_3.addActionListener(this::resetstuactionPerformed);
        mnNewMenu_2.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("个人信息管理");
        mntmNewMenuItem_4.addActionListener(this::userinfoactionPerformed);
        mnNewMenu_2.add(mntmNewMenuItem_4);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        showMainPanel();

        teamManager = new TeamManager();
    }

    protected void userinfoactionPerformed(ActionEvent e) {
        showuserinfoPanel();
    }

    protected void resetstuactionPerformed(ActionEvent e) {
        showstuResetPanel();
    }

    protected void awardstuCalactionPerformed(ActionEvent e) {
        showstuCalAwardhangePanel();
    }

    protected void awardstuactionPerformed(ActionEvent e) {
        showstuAwardhangePanel();
    }

    protected void showchallengeawardactionPerformed(ActionEvent e) {
        showchallengeAwardhangePanel();
    }

    protected void showClassAwardhangePanel(ActionEvent e) {
        showClassAwardhangePanel();
    }

    protected void awardmajoractionPerformed(ActionEvent e) {
        showMajorAwardhangePanel();
    }

    protected void teamchangeinfoactionPerformed(ActionEvent e) {
        showTeamchangePanel();
    }

    protected void teamaddactrionPerformed(ActionEvent e) {
        showTeamaddPanel();
    }

    private void showuserinfoPanel() {
        contentPane.removeAll();
        contentPane.add(userInfoPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuResetPanel() {
        contentPane.removeAll();
        contentPane.add(resetstuPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuloadAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuLoadPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuCalAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuCalPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamchangePanel() {
        contentPane.removeAll();
        contentPane.add(teamChangeInfoPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showchallengeAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardsearchPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showMajorAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardMajorPanel, BorderLayout.CENTER);
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

    private void showClassAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardClassPanel, BorderLayout.CENTER);
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

    private void showSearchPanel(String label, String[] searchFields, String baseQuery, String queryAll, String[] columnNames) {
        contentPane.removeAll();

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        contentPane.add(searchPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Map<String, JComponent> inputFields = new HashMap<>();

        for (String field : searchFields) {
            JLabel fieldLabel = new JLabel(field + ":");
            if (field.equals("Held Time") || field.equals("Enrollment Year") || field.equals("Create Time")) {
                JComboBox<String> comboBox = new JComboBox<>();
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                for (int year = currentYear; year >= 2000; year--) {
                    comboBox.addItem(String.valueOf(year));
                }
                inputFields.put(field, comboBox);
                inputPanel.add(fieldLabel);
                inputPanel.add(comboBox);
            } else {
                JTextField fieldInput = new JTextField(10);
                inputFields.put(field, fieldInput);
                inputPanel.add(fieldLabel);
                inputPanel.add(fieldInput);
            }
        }

        JButton searchButton = new JButton("查找");
        inputPanel.add(searchButton);
        searchPanel.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        searchButton.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            StringBuilder queryBuilder = new StringBuilder(baseQuery);
            List<String> conditions = new ArrayList<>();
            List<Object> parameters = new ArrayList<>();

            for (Map.Entry<String, JComponent> entry : inputFields.entrySet()) {
                String field = entry.getKey();
                JComponent component = entry.getValue();
                String value = "";
                if (component instanceof JTextField) {
                    value = ((JTextField) component).getText();
                } else if (component instanceof JComboBox) {
                    value = (String) ((JComboBox<?>) component).getSelectedItem();
                }
                if (!value.isEmpty()) {
                    if (field.equals("Held Time") || field.equals("Enrollment Year") || field.equals("Create Time")) {
                        conditions.add(field.replace(" ", "_").toLowerCase() + " BETWEEN ? AND ?");
                        parameters.add(value + "-01-01");
                        parameters.add(value + "-12-31");
                    } else {
                        conditions.add(field.replace(" ", "_").toLowerCase() + " = ?");
                        parameters.add(value);
                    }
                }
            }

            if (conditions.isEmpty()) {
                queryBuilder = new StringBuilder(queryAll);
            } else {
                queryBuilder.append(String.join(" AND ", conditions));
            }

            try (Connection con = DBUtil.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(queryBuilder.toString())) {
                for (int i = 0; i < parameters.size(); i++) {
                    pstmt.setObject(i + 1, parameters.get(i));
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Object[] row = new Object[columnNames.length];
                        for (int i = 0; i < columnNames.length; i++) {
                            row[i] = rs.getObject(columnNames[i].replace(" ", "_").toLowerCase());
                        }
                        model.addRow(row);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String entityType = label;
                        int entityId = (Integer) table.getValueAt(row, 0); // Assuming the ID is an Integer
                        handleDoubleClick(entityType, entityId);
                    }
                }
            }
        });

        showAllData(queryAll, columnNames);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private boolean checkRelatedAwards(String label, String entityId) {
        String query = "";
        switch (label) {
            case "Competition":
                query = "SELECT * FROM AwardDetails WHERE competition_name = (SELECT competition_name FROM Challenge_ArrangementInfo WHERE competition_id = ?)";
                break;
            case "Challenge":
                query = "SELECT * FROM AwardDetails WHERE competition_name IN (SELECT competition_name FROM Challenge_ArrangementInfo WHERE challenge_id = ?)";
                break;
            case "Student":
                query = "SELECT * FROM AwardDetails WHERE student_id = ?";
                break;
            case "Teacher":
                query = "SELECT * FROM teamteacher_awardinfo WHERE teacher_id = ? ";
                break;
            case "Team":
                query = "SELECT * FROM AwardDetails WHERE student_id IN (SELECT student_id FROM TeamMemberInfo WHERE team_id = ?)";
                break;
        }

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, entityId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
    }

    private void deleteEntity(String label, String entityId) {
        String tableName = "";
        switch (label) {
            case "Competition":
                tableName = "Challenge_ArrangementInfo";
                break;
            case "Challenge":
                tableName = "ChallengeInfo";
                break;
            case "Student":
                tableName = "StudentInfo";
                break;
            case "Teacher":
                tableName = "TeacherInfo";
                break;
            case "Team":
                tableName = "TeamInfo";
                break;
        }

        String query = "DELETE FROM " + tableName + " WHERE " + label.toLowerCase().replace(" ", "_") + "_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, entityId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void searchCompetitionActionPerformed(ActionEvent e) {
        showSearchPanel("Competition", new String[]{"Competition ID", "Challenge ID", "Held Time"},
                "SELECT * FROM Challenge_ArrangementInfo WHERE ",
                "SELECT * FROM Challenge_ArrangementInfo",
                new String[]{"Competition ID", "Challenge ID", "Competition Name", "Held Time", "Held Address"});
    }

    private void searchChallengeActionPerformed(ActionEvent e) {
        showSearchPanel("Challenge", new String[]{"Challenge ID", "Host", "Whether Zudui"},
                "SELECT * FROM ChallengeInfo WHERE ",
                "SELECT * FROM ChallengeInfo",
                new String[]{"Challenge ID", "Challenge Name", "Host", "Organizer", "Whether Zudui", "Description"});
    }

    private void searchStudentActionPerformed(ActionEvent e) {
        showSearchPanel("Student", new String[]{"Student ID", "Major", "Class", "Enrollment Year"},
                "SELECT * FROM StudentInfo WHERE ",
                "SELECT * FROM StudentInfo",
                new String[]{"Student ID", "Student Name", "Enrollment Year", "Class", "Grade", "Major", "MobilePhone", "Email", "QQ"});
    }

    private void searchTeacherActionPerformed(ActionEvent e) {
        showSearchPanel("Teacher", new String[]{"Teacher ID", "Department"},
                "SELECT * FROM TeacherInfo WHERE ",
                "SELECT * FROM TeacherInfo",
                new String[]{"Teacher ID", "Teacher Name", "MobilePhone", "Department", "Email"});
    }

    private void searchTeamActionPerformed(ActionEvent e) {
        showSearchPanel("Team", new String[]{"Team ID", "Competition ID", "Create Time"},
                "SELECT * FROM TeamInfo WHERE ",
                "SELECT * FROM TeamInfo",
                new String[]{"Team ID", "Competition ID", "Team Name", "Team Name Eng", "Create Time", "Remark", "Member Counts"});
    }

    private void showAllData(String query, String[] columnNames) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getObject(columnNames[i].replace(" ", "_").toLowerCase());
                }
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showMainPanel() {
        contentPane.removeAll();

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

        JButton competitionSearchButton = new JButton("赛事查找");
        competitionSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(competitionSearchButton);

        JButton challengeSearchButton = new JButton("竞赛查找");
        challengeSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(challengeSearchButton);

        JButton teamSearchButton = new JButton("队伍查找");
        teamSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(teamSearchButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{});
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("宋体", Font.PLAIN, 22));
        textArea.setForeground(Color.RED);
        textArea.setText("欢迎使用竞赛获奖管理系统！\n请选择对应的功能进行操作。");
        textArea.setOpaque(false);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setBorder(null);

        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setOpaque(false);
        textPanel.add(textArea, new GridBagConstraints());

        scrollPane.setViewportView(textPanel);

        this.setLocationRelativeTo(null);

        studentSearchButton.addActionListener(this::searchStudentActionPerformed);
        teacherSearchButton.addActionListener(this::searchTeacherActionPerformed);
        competitionSearchButton.addActionListener(this::searchCompetitionActionPerformed);
        challengeSearchButton.addActionListener(this::searchChallengeActionPerformed);
        teamSearchButton.addActionListener(this::searchTeamActionPerformed);

        contentPane.revalidate();
        contentPane.repaint();
    }

    private void handleDoubleClick(String entityType, int entityId) {
        int response = JOptionPane.showConfirmDialog(this, "是否要修改" + entityType + "信息？", "确认修改", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            showEntityPanel(entityType, entityId);
        }
    }

    private void showEntityPanel(String entityType, int entityId) {
        JPanel panel = panelMap.get(entityType);
        if (panel == null) {
            JOptionPane.showMessageDialog(this, "Panel not found for entity type: " + entityType);
            return;
        }

        if (panel instanceof StudentPanel) {
            ((StudentPanel) panel).loadStudentData(entityId);
        } else if (panel instanceof TeacherPanel) {
            ((TeacherPanel) panel).loadTeacherData(entityId);
        } else if (panel instanceof ChallengePanel) {
            ((ChallengePanel) panel).loadChallengeData(entityId);
        } else if (panel instanceof Challenge_ArrangementPanel) {
            ((Challenge_ArrangementPanel) panel).loadChallengeArrangementData(entityId);
        }else if (panel instanceof TeamChangeInfoPanel) {
        	((TeamChangeInfoPanel) panel).loadTeamData(entityId);
		}

        contentPane.removeAll();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }
}
