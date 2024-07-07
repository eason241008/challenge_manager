package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.*;
import java.awt.Font;

import control.TeacherManager;
import model.BeanTeacher;

public class TeacherPanel extends JPanel {
    private JTextField teacherIdField;
    private JTextField teacherNameField;
    private JTextField departmentField;
    private JTextField mobilePhoneField;
    private JTextField emailField;
    private TeacherManager teacherManager;

    public TeacherPanel() {
        teacherManager = new TeacherManager();
        setLayout(new BorderLayout(10, 10)); // �����������������߾�

        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50); // �����������ҵı߽�
        setBorder(padding); // ���߽�Ӧ��������TeacherPanel

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 40));
        teacherIdField = createTextField();
        teacherNameField = createTextField();
        departmentField = createTextField();
        mobilePhoneField = createTextField();
        emailField = createTextField();

        addTextFieldWithLabel(inputPanel, "��ʦID:", teacherIdField);
        addTextFieldWithLabel(inputPanel, "��ʦ����:", teacherNameField);
        addTextFieldWithLabel(inputPanel, "����:", departmentField);
        addTextFieldWithLabel(inputPanel, "�ֻ���:", mobilePhoneField);
        addTextFieldWithLabel(inputPanel, "����:", emailField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton("��ӽ�ʦ", e -> addTeacher(), buttonPanel);
        addButton("ɾ����ʦ", e -> deleteTeacher(), buttonPanel);
        addButton("�޸Ľ�ʦ��Ϣ", e -> updateTeacher(), buttonPanel);
//        addButton("���ҽ�ʦ", e -> findTeacher(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // ϸ���߿��Լ��ٿռ�
        return textField;
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));  // ���ñ�ǩ�ı߽�
        panel.add(label);
        panel.add(textField);
    }

    private void addButton(String buttonText, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addTeacher() {
        if (teacherIdField.getText().isEmpty() ||
            teacherNameField.getText().isEmpty() ||
            departmentField.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "�����ֶα�����д����������ӽ�ʦ��Ϣ��");
            return;
        }

        BeanTeacher teacher = new BeanTeacher();
        teacher.setTeacherId(Integer.parseInt(teacherIdField.getText()));
        teacher.setTeacherName(teacherNameField.getText());
        teacher.setDepartment(departmentField.getText());
        teacher.setMobilePhone(mobilePhoneField.getText());
        teacher.setEmail(emailField.getText());

        boolean success = teacherManager.addTeacher(teacher);
        if (success) {
            JOptionPane.showMessageDialog(this, "��ӳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "���ʧ��!");
        }
    }


    private void deleteTeacher() {
        if (teacherIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "�������ʦID�Խ���ɾ��������");
            return;
        }

        int teacherId = Integer.parseInt(teacherIdField.getText());
        boolean success = teacherManager.deleteTeacher(teacherId);
        if (success) {
            JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "ɾ��ʧ��!");
        }
    }


    private void updateTeacher() {
        if (teacherIdField.getText().isEmpty() ||
            teacherNameField.getText().isEmpty() ||
            departmentField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "��ʦID�������Ͳ��Ų���Ϊ��!");
            return;
        }

        int teacherId = Integer.parseInt(teacherIdField.getText());
        BeanTeacher teacher = teacherManager.findTeacher(teacherId);
        if (teacher == null) {
            JOptionPane.showMessageDialog(this, "��ʦ������!");
            return;
        }

        teacher.setTeacherName(teacherNameField.getText());
        teacher.setDepartment(departmentField.getText());
        teacher.setMobilePhone(mobilePhoneField.getText());
        teacher.setEmail(emailField.getText());

        boolean success = teacherManager.updateTeacher(teacher);
        if (success) {
            JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
        }
    }

    private void findTeacher() {
        if (teacherIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "�������ʦID�Խ��в��ң�");
            return;
        }

        int teacherId = Integer.parseInt(teacherIdField.getText());
        BeanTeacher teacher = teacherManager.findTeacher(teacherId);
        if (teacher != null) {
            teacherNameField.setText(teacher.getTeacherName());
            departmentField.setText(teacher.getDepartment());
            mobilePhoneField.setText(teacher.getMobilePhone());
            emailField.setText(teacher.getEmail());
        } else {
            JOptionPane.showMessageDialog(this, "��ʦ������!");
        }
    }

}
