package ui;
import javax.swing.*;

import util.DBUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetstuPanel extends JPanel {
    private JTextField studentIdText;
    private JButton resetButton;
    private DBUtil dbUtil = new DBUtil(); 

    public ResetstuPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentIdLabel = new JLabel("ѧ��ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(studentIdLabel, gbc);

        studentIdText = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(studentIdText, gbc);

        resetButton = new JButton("��������");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(resetButton, gbc);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
        });

        add(inputPanel, BorderLayout.CENTER);
    }

    private void resetPassword() {
        String studentId = studentIdText.getText().trim();

        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "������ѧ��ID", "����", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        try {
            conn = dbUtil.getConnection();
            String sql = "UPDATE StudentInfo SET password = ? WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            pstmt.setString(2, studentId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "�������óɹ�", "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "δ�ҵ�ѧ��ID", "����", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "���ݿ����: " + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
