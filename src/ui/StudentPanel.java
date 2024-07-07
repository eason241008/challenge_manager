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
        setLayout(new BorderLayout(10, 10)); // ���ò��ּ����TeacherPanelһ��
        Border padding = BorderFactory.createEmptyBorder(10, 50, 10, 50); // �����������ҵı߽�
        setBorder(padding);

//        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ����inputPanel�Ĳ���
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10)); // ��Ӧ9���ֶ�
        inputPanel.add(new JLabel("ѧ��ID:"));
        studentIdField = createTextField();
        inputPanel.add(studentIdField);
        
        inputPanel.add(new JLabel("ѧ������:"));
        studentNameField = createTextField();
        inputPanel.add(studentNameField);
        
        inputPanel.add(new JLabel("��ѧ���:"));
        enrollmentYearField = createTextField();
        inputPanel.add(enrollmentYearField);
        
        inputPanel.add(new JLabel("�༶:"));
        classField = createTextField();
        inputPanel.add(classField);
        
        inputPanel.add(new JLabel("�꼶:"));
        gradeField = createTextField();
        inputPanel.add(gradeField);
        
        inputPanel.add(new JLabel("רҵ:"));
        majorField = createTextField();
        inputPanel.add(majorField);
        
        inputPanel.add(new JLabel("�ֻ���:"));
        mobilePhoneField = createTextField();
        inputPanel.add(mobilePhoneField);
        
        inputPanel.add(new JLabel("����:"));
        emailField = createTextField();
        inputPanel.add(emailField);
        
        inputPanel.add(new JLabel("QQ:"));
        qqField = createTextField();
        inputPanel.add(qqField);

        add(inputPanel, BorderLayout.CENTER);

        // ���ð�ť���
        JPanel buttonPanel = new JPanel();
        addButton("���ѧ��", e -> addStudent(), buttonPanel);
        addButton("ɾ��ѧ��", e -> deleteStudent(), buttonPanel);
        addButton("�޸�ѧ����Ϣ", e -> updateStudent(), buttonPanel);
//        addButton("����ѧ��", e -> findStudent(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));  // ϸ���߿��Լ��ٿռ�
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
            JOptionPane.showMessageDialog(this, "ѧ����������ѧ��ݡ��༶���꼶��רҵ����Ϊ��!");
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
            JOptionPane.showMessageDialog(this, "��ӳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "���ʧ��!");
        }
    }


    private void deleteStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        boolean success = studentManager.deleteStudent(studentId);
        if (success) {
            JOptionPane.showMessageDialog(this, "ɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "ɾ��ʧ��!");
        }
    }

    private void updateStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        Beanstudent student = studentManager.findStudent(studentId);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "ѧ��������!");
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
            JOptionPane.showMessageDialog(this, "�޸ĳɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "�޸�ʧ��!");
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
            JOptionPane.showMessageDialog(this, "ѧ��������!");
        }
    }
}