package model;
import java.util.*;
public class BeanChallengeArrangement {
    private int competitionId;
    private int challengeId;
    private String competitionName;
    private String heldTime;
    private String heldAddress;

    public BeanChallengeArrangement(int competitionId, int challengeId, String competitionName, String heldTime, String heldAddress) {
        this.competitionId = competitionId;
        this.challengeId = challengeId;
        this.competitionName = competitionName;
        this.heldTime = heldTime;
        this.heldAddress = heldAddress;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getHeldTime() {
        return heldTime;
    }

    public void setHeldTime(String heldTime) {
        this.heldTime = heldTime;
    }

    public String getHeldAddress() {
        return heldAddress;
    }

    public void setHeldAddress(String heldAddress) {
        this.heldAddress = heldAddress;
    }
}
