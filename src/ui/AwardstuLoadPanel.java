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
        // ���ñ߽�
        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50);
        setBorder(padding);

        // �����������
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 40));
        
        // ���������������������
        inputPanel.add(new JLabel("��ʦID:"));
        teacherIdField = createTextField();
        inputPanel.add(teacherIdField);

        inputPanel.add(new JLabel("����ID (�����¼��):"));
        teamIdField = createTextField();
        inputPanel.add(teamIdField);

        inputPanel.add(new JLabel("ѧ��ID (������¼��):"));
        studentIdField = createTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("����ID:"));
        competitionIdField = createTextField();
        inputPanel.add(competitionIdField);


        inputPanel.add(new JLabel("�������:"));
        awardLevelComboBox = new JComboBox<>(new String[]{"�صȽ�", "һ�Ƚ�", "���Ƚ�", "���Ƚ�", "��ʤ��"});
        awardLevelComboBox.setFont(new Font("SansSerif", Font.PLAIN, 11));
        inputPanel.add(awardLevelComboBox);

        // ������ť���
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        submitTeamAwardButton = new JButton("�ύ���������");
        submitIndividualAwardButton = new JButton("�ύ����������");
        
        // ���ð�ť��С������
        submitTeamAwardButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
        submitTeamAwardButton.setPreferredSize(new Dimension(150, 25));
        submitIndividualAwardButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
        submitIndividualAwardButton.setPreferredSize(new Dimension(150, 25));
        
        // ��Ӱ�ť����ť���
        buttonPanel.add(submitTeamAwardButton);
        buttonPanel.add(submitIndividualAwardButton);

        // ���ò��ֲ�����������Ͱ�ť��嵽�����
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ��Ӱ�ť������
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
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // ϸ���߿��Լ��ٿռ�
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
