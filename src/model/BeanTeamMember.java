package model;

public class BeanTeamMember {
    private int studentId;
    private int teamId;
    private boolean isLeader;

    public BeanTeamMember(int studentId, int teamId, boolean isLeader) {
        this.studentId = studentId;
        this.teamId = teamId;
        this.isLeader = isLeader;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }
}
