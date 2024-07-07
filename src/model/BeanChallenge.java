package  model;
public class BeanChallenge {
    private int challengeId;
    private String challengeName;
    private String host;
    private String organizer;
    private boolean whetherZudui;
    private String description;

    public BeanChallenge(int challengeId, String challengeName, String host, String organizer, boolean whetherZudui, String description) {
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.host = host;
        this.organizer = organizer;
        this.whetherZudui = whetherZudui;
        this.description = description;
    }
    public BeanChallenge() {

    }
    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public boolean isWhetherZudui() {
        return whetherZudui;
    }

    public void setWhetherZudui(boolean whetherZudui) {
        this.whetherZudui = whetherZudui;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
