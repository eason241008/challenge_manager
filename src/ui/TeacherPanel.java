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
        setLayout(new BorderLayout(10, 10)); // 可以在这里调整整体边距

        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50); // 设置上下左右的边界
        setBorder(padding); // 将边界应用于整个TeacherPanel

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 40));
        teacherIdField = createTextField();
        teacherNameField = createTextField();
        departmentField = createTextField();
        mobilePhoneField = createTextField();
        emailField = createTextField();

        addTextFieldWithLabel(inputPanel, "教师ID:", teacherIdField);
        addTextFieldWithLabel(inputPanel, "教师姓名:", teacherNameField);
        addTextFieldWithLabel(inputPanel, "部门:", departmentField);
        addTextFieldWithLabel(inputPanel, "手机号:", mobilePhoneField);
        addTextFieldWithLabel(inputPanel, "邮箱:", emailField);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton("添加教师", e -> addTeacher(), buttonPanel);
        addButton("删除教师", e -> deleteTeacher(), buttonPanel);
        addButton("修改教师信息", e -> updateTeacher(), buttonPanel);
//        addButton("查找教师", e -> findTeacher(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // 细调边框以减少空间
        return textField;
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));  // 设置标签的边界
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
            JOptionPane.showMessageDialog(this, "所有字段必须填写完整才能添加教师信息！");
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
            JOptionPane.showMessageDialog(this, "添加成功!");
        } else {
            JOptionPane.showMessageDialog(this, "添加失败!");
        }
    }


    private void deleteTeacher() {
        if (teacherIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入教师ID以进行删除操作！");
            return;
        }

        int teacherId = Integer.parseInt(teacherIdField.getText());
        boolean success = teacherManager.deleteTeacher(teacherId);
        if (success) {
            JOptionPane.showMessageDialog(this, "删除成功!");
        } else {
            JOptionPane.showMessageDialog(this, "删除失败!");
        }
    }


    private void updateTeacher() {
        if (teacherIdField.getText().isEmpty() ||
            teacherNameField.getText().isEmpty() ||
            departmentField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "教师ID、姓名和部门不能为空!");
            return;
        }

        int teacherId = Integer.parseInt(teacherIdField.getText());
        BeanTeacher teacher = teacherManager.findTeacher(teacherId);
        if (teacher == null) {
            JOptionPane.showMessageDialog(this, "教师不存在!");
            return;
        }

        teacher.setTeacherName(teacherNameField.getText());
        teacher.setDepartment(departmentField.getText());
        teacher.setMobilePhone(mobilePhoneField.getText());
        teacher.setEmail(emailField.getText());

        boolean success = teacherManager.updateTeacher(teacher);
        if (success) {
            JOptionPane.showMessageDialog(this, "修改成功!");
        } else {
            JOptionPane.showMessageDialog(this, "修改失败!");
        }
    }

    private void findTeacher() {
        if (teacherIdField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入教师ID以进行查找！");
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
            JOptionPane.showMessageDialog(this, "教师不存在!");
        }
    }

}
