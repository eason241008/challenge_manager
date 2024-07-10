package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import util.DBUtil;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AwardstuLoadPanel extends JPanel {

    private JTextField teacherIdField;
    private JTextField teamIdField;
    private JTextField studentIdField;
    private JTextField competitionIdField;
    private JTextField competitionNameField;
    private JComboBox<String> awardLevelComboBox;
    private JButton submitTeamAwardButton;
    private JButton submitIndividualAwardButton;

    public AwardstuLoadPanel() {
        // 设置边界
        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50);
        setBorder(padding);

        // 创建输入面板
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 40));
        
        // 创建并添加组件到输入面板
        inputPanel.add(new JLabel("教师ID:"));
        teacherIdField = createTextField();
        inputPanel.add(teacherIdField);

        inputPanel.add(new JLabel("队伍ID (组队赛录入):"));
        teamIdField = createTextField();
        inputPanel.add(teamIdField);

        inputPanel.add(new JLabel("学生ID (个人赛录入):"));
        studentIdField = createTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("赛事ID:"));
        competitionIdField = createTextField();
        inputPanel.add(competitionIdField);


        inputPanel.add(new JLabel("奖项类别:"));
        awardLevelComboBox = new JComboBox<>(new String[]{"特等奖", "一等奖", "二等奖", "三等奖", "优胜奖"});
        awardLevelComboBox.setFont(new Font("SansSerif", Font.PLAIN, 11));
        inputPanel.add(awardLevelComboBox);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        submitTeamAwardButton = new JButton("提交组队赛奖项");
        submitIndividualAwardButton = new JButton("提交个人赛奖项");
        
        // 设置按钮大小和字体
        submitTeamAwardButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
        submitTeamAwardButton.setPreferredSize(new Dimension(150, 25));
        submitIndividualAwardButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
        submitIndividualAwardButton.setPreferredSize(new Dimension(150, 25));
        
        // 添加按钮到按钮面板
        buttonPanel.add(submitTeamAwardButton);
        buttonPanel.add(submitIndividualAwardButton);

        // 设置布局并添加输入面板和按钮面板到主面板
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 添加按钮监听器
        submitTeamAwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTeamAward();
            }
        });

        submitIndividualAwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitIndividualAward();
            }
        });
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // 细调边框以减少空间
        return textField;
    }

    private void submitTeamAward() {
        int teacherId = Integer.parseInt(teacherIdField.getText());
        int teamId = Integer.parseInt(teamIdField.getText());
        int competitionId = Integer.parseInt(competitionIdField.getText());
        String awardLevel = (String) awardLevelComboBox.getSelectedItem();

        try (Connection conn = DBUtil.getConnection()) {
            // Insert team award information
            String insert = "INSERT INTO TeamTeacher_AwardInfo (teacher_id, team_id, competition_id, award_level) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insert)) {
                insertStmt.setInt(1, teacherId);
                insertStmt.setInt(2, teamId);
                insertStmt.setInt(3, competitionId);
                insertStmt.setString(4, awardLevel);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Team award added successfully");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void submitIndividualAward() {
        int teacherId = Integer.parseInt(teacherIdField.getText());
        int studentId = Integer.parseInt(studentIdField.getText());
        int competitionId = Integer.parseInt(competitionIdField.getText());
        String awardLevel = (String) awardLevelComboBox.getSelectedItem();

        try (Connection conn = DBUtil.getConnection()) {
            // Insert individual award information
            String insert = "INSERT INTO IndividualTeacher_AwardInfo (teacher_id, student_id, competition_id, award_level) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insert)) {
                insertStmt.setInt(1, teacherId);
                insertStmt.setInt(2, studentId);
                insertStmt.setInt(3, competitionId);
                insertStmt.setString(4, awardLevel);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Individual award added successfully");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }


}
