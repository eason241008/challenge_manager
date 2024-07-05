package model;
public class BeanTeamTeacherAward {
    private int teacherId;
    private int teamId;
    private int competitionId;
    private String awardLevel;

    public BeanTeamTeacherAward(int teacherId, int teamId, int competitionId, String awardLevel) {
        this.teacherId = teacherId;
        this.teamId = teamId;
        this.competitionId = competitionId;
        this.awardLevel = awardLevel;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
