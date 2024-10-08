package ui;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import control.AwardManager;

public class AwardstuPanel extends JPanel {
    private JComboBox<String> startDateComboBox;
    private JComboBox<String> endDateComboBox;
    private JTextField challengeNameField;
    private JTextField competitionNameField;
    private JTextField studentIdField;
    private JTable table;
    private DefaultTableModel tableModel;
    private AwardManager awardManager;

    public AwardstuPanel() {
        awardManager = new AwardManager();

        JLabel lblStartDate = new JLabel("开始日期:");
        lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblEndDate = new JLabel("结束日期:");
        lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblCompetitionName = new JLabel("竞赛名称:");
        lblCompetitionName.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblChallengeName = new JLabel("赛事名称:");
        lblChallengeName.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblStudentId = new JLabel("学号:");
        lblStudentId.setFont(new Font("SansSerif", Font.BOLD, 11));

        startDateComboBox = new JComboBox<>();
        endDateComboBox = new JComboBox<>();
        challengeNameField = new JTextField();
        competitionNameField = new JTextField();
        studentIdField = new JTextField();

        for (int year = 2010; year <= 2030; year++) {
            startDateComboBox.addItem(String.valueOf(year));
            endDateComboBox.addItem(String.valueOf(year));
        }

        JButton searchButton = new JButton("查询");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchActionPerformed(e);
            }
        });

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"姓名", "班级", "专业", "竞赛名称", "赛事名称", "获奖等级", "赛事类型"}
        );

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        GroupLayout layout = new GroupLayout(this);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblStartDate)
                        .addComponent(lblEndDate)
                        .addComponent(lblStudentId)
                        .addComponent(lblCompetitionName)
                        .addComponent(lblChallengeName))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(startDateComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(endDateComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(studentIdField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(challengeNameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(competitionNameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(searchButton)
                    .addContainerGap(30, Short.MAX_VALUE))
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblStartDate)
                        .addComponent(startDateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEndDate)
                        .addComponent(endDateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblStudentId)
                        .addComponent(studentIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCompetitionName)
                        .addComponent(challengeNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblChallengeName)
                        .addComponent(competitionNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton))
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE))
        );
        setLayout(layout);
    }

    private void searchActionPerformed(ActionEvent e) {
        String startDate = (String) startDateComboBox.getSelectedItem();
        String endDate = (String) endDateComboBox.getSelectedItem();
        String studentId = studentIdField.getText();
        String challengeName = challengeNameField.getText();
        String competitionName = competitionNameField.getText();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // 清空表格

        List<Object[]> results = awardManager.getAwardstudent(startDate + "-01-01", endDate + "-12-31", studentId, competitionName, challengeName);
        for (Object[] result : results) {
            model.addRow(result);
        }
    }
}
