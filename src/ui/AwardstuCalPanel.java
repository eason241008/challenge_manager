package ui;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import control.AwardManager;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AwardstuCalPanel extends JPanel {
    private JComboBox<String> startDateComboBox;
    private JComboBox<String> endDateComboBox;
    private JTextField studentIdField;
    private JTextField studentNameField;  // ���ѧ�������ֶ�
    private JTable table;
    private DefaultTableModel tableModel;
    private AwardManager awardManager;

    public AwardstuCalPanel() {
        awardManager = new AwardManager();

        JLabel lblStartDate = new JLabel("��ʼ����:");
        lblStartDate.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblEndDate = new JLabel("��������:");
        lblEndDate.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel lblStudentId = new JLabel("ѧ��:");
        lblStudentId.setFont(new Font("SansSerif", Font.BOLD, 11));
        
        JLabel lblStudentName = new JLabel("����:");  // ���ѧ��������ǩ
        lblStudentName.setFont(new Font("SansSerif", Font.BOLD, 11));

        startDateComboBox = new JComboBox<>();
        endDateComboBox = new JComboBox<>();
        studentIdField = new JTextField();
        studentNameField = new JTextField();  // ���ѧ�������ı���

        for (int year = 2010; year <= 2030; year++) {
            startDateComboBox.addItem(String.valueOf(year));
            endDateComboBox.addItem(String.valueOf(year));
        }

        JButton searchButton = new JButton("��ѯ");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchActionPerformed(e);
            }
        });

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"����", "�༶", "רҵ", "��������", "��������", "�񽱵ȼ�", "��ʱ��"}  // �޸�������
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
                        .addComponent(lblStudentName))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(startDateComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(endDateComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(studentIdField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(204, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(265, Short.MAX_VALUE)
                    .addComponent(searchButton)
                    .addGap(130))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addContainerGap())
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
                        .addComponent(lblStudentName)
                        .addComponent(studentNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addComponent(searchButton)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setLayout(layout);
    }

    private void searchActionPerformed(ActionEvent e) {
        String startDate = (String) startDateComboBox.getSelectedItem();
        String endDate = (String) endDateComboBox.getSelectedItem();
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();  // ��ȡѧ������

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // ��ձ��

        List<Object[]> results = awardManager.getAwardstudentCal(startDate + "-01-01", endDate + "-12-31", studentId, studentName);
        for (Object[] result : results) {
            model.addRow(result);
        }
    }
}
