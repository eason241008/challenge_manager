package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import java.awt.Font;

import control.ChallengeManager;
import model.BeanChallenge;

public class ChallengePanel extends JPanel {
    private JTextField challengeIdField;
    private JTextField challengeNameField;
    private JTextField hostField;
    private JTextField organizerField;
    private JCheckBox whetherZuduiCheckBox;
    private JTextField desField;
    private ChallengeManager challengeManager;

    public ChallengePanel() {
        challengeManager = new ChallengeManager();
        setLayout(new BorderLayout(10, 10));

        Border padding = BorderFactory.createEmptyBorder(10,50,10,50);
        setBorder(padding);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 40));
        challengeIdField = createTextField();
        challengeNameField = createTextField();
        hostField = createTextField();
        organizerField = createTextField();
        whetherZuduiCheckBox = new JCheckBox();
        desField = createTextField();

        addTextFieldWithLabel(inputPanel, "����ID:", challengeIdField);
        addTextFieldWithLabel(inputPanel, "��������:", challengeNameField);
        addTextFieldWithLabel(inputPanel, "���췽:", hostField);
        addTextFieldWithLabel(inputPanel, "�а췽:", organizerField);
        addTextFieldWithLabel(inputPanel, "�Ƿ����:", whetherZuduiCheckBox);
        addTextFieldWithLabel(inputPanel, "����:", desField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton("��Ӿ���", e -> addChallenge(), buttonPanel);
        addButton("ɾ������", e -> deleteChallenge(), buttonPanel);
        addButton("�޸ľ�����Ϣ", e -> updateChallenge(), buttonPanel);
//        addButton("���Ҿ���", e -> findChallenge(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10)); 
        return textField;
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));  
        panel.add(label);
        panel.add(textField);
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JCheckBox checkBox) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));  
        panel.add(label);
        panel.add(checkBox);
    }

    private void addButton(String buttonText, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addChallenge() {
        String challengeName = challengeNameField.getText();
        String host = hostField.getText();
        String organizer = organizerField.getText();

        if (challengeName.isEmpty() || host.isEmpty() || organizer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "�������ơ����췽�ͳа췽����Ϊ��!");
            return;
        }

        BeanChallenge challenge = new BeanChallenge();
        challenge.setChallengeId(Integer.parseInt(challengeIdField.getText()));
        challenge.setChallengeName(challengeName);
        challenge.setHost(host);
        challenge.setOrganizer(organizer);
        challenge.setWhetherZudui(whetherZuduiCheckBox.isSelected());
        challenge.setDescription(desField.getText());

        boolean success = challengeManager.addChallenge(challenge);
        if (success) {
            JOptionPane.showMessageDialog(this, "��ӳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "���ʧ��!");
        }
    }

    private void deleteChallenge() {
        int challengeId = Integer.parseInt(challengeIdField.getText());

        boolean success = challengeManager.deleteChallenge(challengeId);
        if (success) {
            JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "ɾ��ʧ�ܣ�������������´���!");
        }
    }

    private void updateChallenge() {
        int challengeId = Integer.parseInt(challengeIdField.getText());
        BeanChallenge challenge = challengeManager.findChallenge(challengeId);
        if (challenge == null) {
            JOptionPane.showMessageDialog(this, "����������!");
            return;
        }

        challenge.setChallengeName(challengeNameField.getText());
        challenge.setHost(hostField.getText());
        challenge.setOrganizer(organizerField.getText());
        challenge.setWhetherZudui(whetherZuduiCheckBox.isSelected());
        challenge.setDescription(desField.getText());

        boolean success = challengeManager.updateChallenge(challenge);
        if (success) {
            JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
        }
    }

    private void findChallenge() {
        int challengeId = Integer.parseInt(challengeIdField.getText());
        BeanChallenge challenge = challengeManager.findChallenge(challengeId);
        if (challenge != null) {
            challengeNameField.setText(challenge.getChallengeName());
            hostField.setText(challenge.getHost());
            organizerField.setText(challenge.getOrganizer());
            whetherZuduiCheckBox.setSelected(challenge.isWhetherZudui());
            desField.setText(challenge.getDescription());
        } else {
            JOptionPane.showMessageDialog(this, "����������!");
        }
    }
}
