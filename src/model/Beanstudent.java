package model;
import  java.util.*;

public class Beanstudent {
    private int studentId;
    private String studentName;
    private String enrollmentYear;
    private String className;
    private String grade;
    private String major;
    private String mobilePhone;
    private String email;
    private String qq;
    private String password;

	public Beanstudent(int studentId, String studentName, String enrollmentYear, String className, String grade, String major, String mobilePhone, String email, String qq) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrollmentYear = enrollmentYear;
        this.className = className;
        this.grade = grade;
        this.major = major;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.qq = qq;
    }
    public Beanstudent(int studentId, String studentName, String enrollmentYear, String className, String grade, String major, String mobilePhone, String email, String qq,String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrollmentYear = enrollmentYear;
        this.className = className;
        this.grade = grade;
        this.major = major;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.qq = qq;
        this.password=password;
    }
    public Beanstudent(int studentId,String password) {
        this.studentId = studentId;
        this.password=password;
    }
    public int getStudentId() {
        return studentId;
    }
    public  Beanstudent() {
		
	}
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
