package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

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
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentPanel() {
        studentManager = new StudentManager();
        setLayout(new BorderLayout(10, 10)); // 设置布局间隔与TeacherPanel一致
        Border padding = BorderFactory.createEmptyBorder(10, 50, 10, 50); // 设置上下左右的边界
        setBorder(padding);

        // 设置inputPanel的布局
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10)); // 适应9个字段
        inputPanel.add(new JLabel("学生ID:"));
        studentIdField = createTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("学生姓名:"));
        studentNameField = createTextField();
        inputPanel.add(studentNameField);

        inputPanel.add(new JLabel("入学年份 (yyyy-MM-dd):"));
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
        try {
            String studentIdText = studentIdField.getText();
            String studentName = studentNameField.getText();
            LocalDate enrollmentYear = LocalDate.parse(enrollmentYearField.getText(), DATE_FORMATTER);
            String className = classField.getText();
            String grade = gradeField.getText();
            String major = majorField.getText();
            String mobilePhone = mobilePhoneField.getText();
            String email = emailField.getText();
            String qq = qqField.getText();

            if (studentName.isEmpty() || enrollmentYear == null || className.isEmpty() || grade.isEmpty() || major.isEmpty()) {
                JOptionPane.showMessageDialog(this, "学生姓名、入学年份、班级、年级和专业不能为空!");
                return;
            }

            Beanstudent student = new Beanstudent();
            student.setStudentId(Integer.parseInt(studentIdText));
            student.setStudentName(studentName);
            student.setEnrollmentYear(Date.from(enrollmentYear.atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "输入格式错误，请检查日期格式 (yyyy-MM-dd) 以及其他字段是否正确!");
        }
    }

    private void deleteStudent() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            boolean success = studentManager.deleteStudent(studentId);
            if (success) {
                JOptionPane.showMessageDialog(this, "删除成功!");
            } else {
                JOptionPane.showMessageDialog(this, "删除失败!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的学生ID!");
        }
    }

    private void updateStudent() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            Beanstudent student = studentManager.findStudent(studentId);
            if (student == null) {
                JOptionPane.showMessageDialog(this, "学生不存在!");
                return;
            }

            student.setStudentName(studentNameField.getText());
            student.setEnrollmentYear(Date.from(LocalDate.parse(enrollmentYearField.getText(), DATE_FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "输入格式错误，请检查日期格式 (yyyy-MM-dd) 以及其他字段是否正确!");
        }
    }

    private void findStudent() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            Beanstudent student = studentManager.findStudent(studentId);
            if (student != null) {
                studentNameField.setText(student.getStudentName());
                enrollmentYearField.setText(student.getEnrollmentYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DATE_FORMATTER));
                classField.setText(student.getClassName());
                gradeField.setText(student.getGrade());
                majorField.setText(student.getMajor());
                mobilePhoneField.setText(student.getMobilePhone());
                emailField.setText(student.getEmail());
                qqField.setText(student.getQq());
            } else {
                JOptionPane.showMessageDialog(this, "学生不存在!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的学生ID!");
        }
    }
}
