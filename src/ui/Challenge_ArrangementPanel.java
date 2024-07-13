package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;

import control.Challenge_ArrangementManager;
import model.BeanChallengeArrangement;

public class Challenge_ArrangementPanel extends JPanel {
    private JTextField competitionId;
    private JTextField challengeId;
    private JTextField competitionName;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> dayComboBox;
    private JTextField heldAddress;
    private Challenge_ArrangementManager Challenge_ArrangementManager;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Challenge_ArrangementPanel() {
        Challenge_ArrangementManager = new Challenge_ArrangementManager();
        setLayout(new BorderLayout(10, 10));

        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50);
        setBorder(padding);
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 40));
        competitionId = createTextField();
        challengeId = createTextField();
        competitionName = createTextField();
        heldAddress = createTextField();
        createComboBoxes();

        addTextFieldWithLabel(inputPanel, "����ID:", competitionId);
        addTextFieldWithLabel(inputPanel, "����ID:", challengeId);
        addTextFieldWithLabel(inputPanel, "������:", competitionName);
        addDateComboBoxesWithLabel(inputPanel, "�ٰ�ʱ��:");
        addTextFieldWithLabel(inputPanel, "�ٰ�ص�:", heldAddress);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton("�������", e -> addCompetition(), buttonPanel);
        addButton("�޸�������Ϣ", e -> updateCompetition(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));
        return textField;
    }

    private void createComboBoxes() {
        yearComboBox = new JComboBox<>();
        for (int year = 2010; year <= 2023; year++) {
            yearComboBox.addItem(year);
        }

        monthComboBox = new JComboBox<>();
        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(month);
        }

        dayComboBox = new JComboBox<>();
        for (int day = 1; day <= 31; day++) {
            dayComboBox.addItem(day);
        }
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));
        panel.add(label);
        panel.add(textField);
    }

    private void addDateComboBoxesWithLabel(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));
        panel.add(label);

        JPanel datePanel = new JPanel(new GridLayout(1, 3));
        datePanel.add(yearComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        panel.add(datePanel);
    }

    private void addButton(String buttonText, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addCompetition() {
        if (competitionId.getText().isEmpty() ||
            challengeId.getText().isEmpty() ||
            competitionName.getText().isEmpty() ||
            heldAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "�����ֶα�����д�����������������Ϣ��");
            return;
        }

        int challengeIdValue = Integer.parseInt(challengeId.getText());

        // ��� challengeId �Ƿ����
        boolean challengeExists = Challenge_ArrangementManager.challengeExists(challengeIdValue);
        if (!challengeExists) {
            JOptionPane.showMessageDialog(this, "��Ӧ�ľ���ID�����ڣ��������룡");
            return;
        }

        LocalDate heldTimeValue = getSelectedDate();

        BeanChallengeArrangement competition = new BeanChallengeArrangement();
        competition.setCompetitionId(Integer.parseInt(competitionId.getText()));
        competition.setChallengeId(challengeIdValue);
        competition.setCompetitionName(competitionName.getText());
        competition.setHeldTime(Date.valueOf(heldTimeValue));
        competition.setHeldAddress(heldAddress.getText());

        boolean success = Challenge_ArrangementManager.addArrangement(competition);
        if (success) {
            JOptionPane.showMessageDialog(this, "��ӳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "���ʧ��!");
        }
    }

    private void deleteCompetition() {
        if (competitionId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "����������ID�Խ���ɾ��������");
            return;
        }

        int competitionid = Integer.parseInt(competitionId.getText());
        boolean success = Challenge_ArrangementManager.deleteArrangement(competitionid);
        if (success) {
            JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "ɾ��ʧ��!");
        }
    }

    private void updateCompetition() {
        if (competitionId.getText().isEmpty() ||
            challengeId.getText().isEmpty() ||
            competitionName.getText().isEmpty() ||
            heldAddress.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "�����ֶα�����д���������޸�������Ϣ��");
            return;
        }

        int competitionid = Integer.parseInt(competitionId.getText());
        BeanChallengeArrangement challengeArrangement = Challenge_ArrangementManager.findArrangement(competitionid);
        if (challengeArrangement == null) {
            JOptionPane.showMessageDialog(this, "���²�����!");
            return;
        }

        LocalDate heldTimeValue = getSelectedDate();

        challengeArrangement.setCompetitionId(Integer.parseInt(competitionId.getText()));
        challengeArrangement.setChallengeId(Integer.parseInt(challengeId.getText()));
        challengeArrangement.setCompetitionName(competitionName.getText());
        challengeArrangement.setHeldTime(Date.valueOf(heldTimeValue));
        challengeArrangement.setHeldAddress(heldAddress.getText());

        boolean success = Challenge_ArrangementManager.updateArrangement(challengeArrangement);
        if (success) {
            JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
        }
    }

    public void loadChallengeArrangementData(int competitionId) {
        BeanChallengeArrangement challengeArrangement = Challenge_ArrangementManager.findArrangement(competitionId);
        if (challengeArrangement != null) {
            this.competitionId.setText(String.valueOf(challengeArrangement.getCompetitionId()));
            challengeId.setText(String.valueOf(challengeArrangement.getChallengeId()));
            competitionName.setText(challengeArrangement.getCompetitionName());
            LocalDate heldTimeValue = ((Date)challengeArrangement.getHeldTime()).toLocalDate();
            yearComboBox.setSelectedItem(heldTimeValue.getYear());
            monthComboBox.setSelectedItem(heldTimeValue.getMonthValue());
            dayComboBox.setSelectedItem(heldTimeValue.getDayOfMonth());
            heldAddress.setText(challengeArrangement.getHeldAddress());
        } else {
            JOptionPane.showMessageDialog(this, "���²�����!");
        }
    }

    private LocalDate getSelectedDate() {
        int year = (int) yearComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        int day = (int) dayComboBox.getSelectedItem();
        return LocalDate.of(year, month, day);
    }
}
