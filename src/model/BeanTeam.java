package model;
import  java.util.*;
public class BeanTeam {
    private int teamId;
    private int competitionId;
    private String teamName;
    private String teamNameEng;
    private String createTime;
    private int memberCounts;
    private String remark;

    public BeanTeam(int teamId, int competitionId, String teamName, String teamNameEng, String createTime, int memberCounts, String remark) {
        this.teamId = teamId;
        this.competitionId = competitionId;
        this.teamName = teamName;
        this.teamNameEng = teamNameEng;
        this.createTime = createTime;
        this.memberCounts = memberCounts;
        this.remark = remark;
    }
    public BeanTeam() {
    	
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNameEng() {
        return teamNameEng;
    }

    public void setTeamNameEng(String teamNameEng) {
        this.teamNameEng = teamNameEng;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getMemberCounts() {
        return memberCounts;
    }

    public void setMemberCounts(int memberCounts) {
        this.memberCounts = memberCounts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
