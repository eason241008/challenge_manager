package model;
public class BeanTeacher {
    private int teacherId;
    private String teacherName;
    private String mobilePhone;
    private String department;
    private String email;

    public BeanTeacher(int teacherId, String teacherName, String mobilePhone, String department, String email) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.mobilePhone = mobilePhone;
        this.department = department;
        this.email = email;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
