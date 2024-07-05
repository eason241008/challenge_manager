package model;

public class BeanIndividualTeacherAward {
    private int teacherId;
    private int studentId;
    private int competitionId;
    private String awardLevel;

    public BeanIndividualTeacherAward(int teacherId, int studentId, int competitionId, String awardLevel) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.competitionId = competitionId;
        this.awardLevel = awardLevel;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel;
    }
}
