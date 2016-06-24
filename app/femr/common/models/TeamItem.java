package femr.common.models;

public class TeamItem {
    private String name;
    private String description;
    private String location;
    private String teamAcronym;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamAcronym(){return teamAcronym; }

    public void setTeamAcronym(String teamAcronym) {this.teamAcronym = teamAcronym; }
}
