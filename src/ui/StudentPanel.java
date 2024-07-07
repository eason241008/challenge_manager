package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import control.StudentManager;
import model.Beanstudent;

public class StudentPanel extends JPanel {
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField enrollmentYearField;
    private JTextField classField;
    private JTextField gradeField;
    private JTextField majorField;
    private JTextField mobilePhoneField;
    private JTextField emailField;
    private JTextField qqField;
    private StudentManager studentManager;

        public StudentPanel() {
        studentManager = new StudentManager();
        setLayout(new BorderLayout(10, 10)); // 设置布局间隔与TeacherPanel一致
        Border padding = BorderFactory.createEmptyBorder(10, 50, 10, 50); // 设置上下左右的边界
        setBorder(padding);

//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 设置inputPanel的布局
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10)); // 适应9个字段
        inputPanel.add(new JLabel("学生ID:"));
        studentIdField = createTextField();
        inputPanel.add(studentIdField);
        
        inputPanel.add(new JLabel("学生姓名:"));
        studentNameField = createTextField();
        inputPanel.add(studentNameField);
        
        inputPanel.add(new JLabel("入学年份:"));
        enrollmentYearField = createTextField();
        inputPanel.add(enrollmentYearField);
        
        inputPanel.add(new JLabel("班级:"));
        classField = createTextField();
        inputPanel.add(classField);
        
        inputPanel.add(new JLabel("年级:"));
        gradeField = createTextField();
        inputPanel.add(gradeField);
        
        inputPanel.add(new JLabel("专业:"));
        majorField = createTextField();
        inputPanel.add(majorField);
        
        inputPanel.add(new JLabel("手机号:"));
        mobilePhoneField = createTextField();
        inputPanel.add(mobilePhoneField);
        
        inputPanel.add(new JLabel("邮箱:"));
        emailField = createTextField();
        inputPanel.add(emailField);
        
        inputPanel.add(new JLabel("QQ:"));
        qqField = createTextField();
        inputPanel.add(qqField);

        add(inputPanel, BorderLayout.CENTER);

        // 设置按钮面板
        JPanel buttonPanel = new JPanel();
        addButton("添加学生", e -> addStudent(), buttonPanel);
        addButton("删除学生", e -> deleteStudent(), buttonPanel);
        addButton("修改学生信息", e -> updateStudent(), buttonPanel);
//        addButton("查找学生", e -> findStudent(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // 细调边框以减少空间
        return textField;
    }

    private void addButton(String buttonText, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }
    private void addStudent() {
        String studentIdText = studentIdField.getText();
        String studentName = studentNameField.getText();
        String enrollmentYear = enrollmentYearField.getText();
        String className = classField.getText();
        String grade = gradeField.getText();
        String major = majorField.getText();
        String mobilePhone = mobilePhoneField.getText();
        String email = emailField.getText();
        String qq = qqField.getText();

        if (studentName.isEmpty() || enrollmentYear.isEmpty() || className.isEmpty() || grade.isEmpty() || major.isEmpty()) {
            JOptionPane.showMessageDialog(this, "学生姓名、入学年份、班级、年级和专业不能为空!");
            return;
        }

        Beanstudent student = new Beanstudent();
        student.setStudentId(Integer.parseInt(studentIdText));
        student.setStudentName(studentName);
        student.setEnrollmentYear(enrollmentYear);
        student.setClassName(className);
        student.setGrade(grade);
        student.setMajor(major);
        student.setMobilePhone(mobilePhone);
        student.setEmail(email);
        student.setQq(qq);

        boolean success = studentManager.addStudent(student);
        if (success) {
            JOptionPane.showMessageDialog(this, "添加成功!");
        } else {
            JOptionPane.showMessageDialog(this, "添加失败!");
        }
    }


    private void deleteStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        boolean success = studentManager.deleteStudent(studentId);
        if (success) {
            JOptionPane.showMessageDialog(this, "删除成功!");
        } else {
            JOptionPane.showMessageDialog(this, "删除失败!");
        }
    }

    private void updateStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        Beanstudent student = studentManager.findStudent(studentId);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "学生不存在!");
            return;
        }

        student.setStudentName(studentNameField.getText());
        student.setEnrollmentYear(enrollmentYearField.getText());
        student.setClassName(classField.getText());
        student.setGrade(gradeField.getText());
        student.setMajor(majorField.getText());
        student.setMobilePhone(mobilePhoneField.getText());
        student.setEmail(emailField.getText());
        student.setQq(qqField.getText());

        boolean success = studentManager.updateStudent(student);
        if (success) {
            JOptionPane.showMessageDialog(this, "修改成功!");
        } else {
            JOptionPane.showMessageDialog(this, "修改失败!");
        }
    }

    private void findStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        Beanstudent student = studentManager.findStudent(studentId);
        if (student != null) {
            studentNameField.setText(student.getStudentName());
            enrollmentYearField.setText(student.getEnrollmentYear());
            classField.setText(student.getClassName());
            gradeField.setText(student.getGrade());
            majorField.setText(student.getMajor());
            mobilePhoneField.setText(student.getMobilePhone());
            emailField.setText(student.getEmail());
            qqField.setText(student.getQq());
        } else {
            JOptionPane.showMessageDialog(this, "学生不存在!");
        }
    }
}