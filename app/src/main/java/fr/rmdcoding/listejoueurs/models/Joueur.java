package fr.rmdcoding.listejoueurs.models;

public class Joueur {
    private int id;
    private String name;
    private double speed_score;
    private double stamina_score;
    private double dribbling_score;
    private double defending_score;
    private double passing_score;
    private double shooting_score;
    private double score;
    private int rank;
    private int old_rank;
    private String city;
    private String country;
    private String player_detail_url;
    private int dynamic_rank;
    private String profile_pic;

    public Joueur() {
    }

    public Joueur(int id, String name, double speed_score, double stamina_score, double dribbling_score, double defending_score, double passing_score, double shooting_score, double score, int rank, int old_rank, String city, String country, String player_detail_url, int dynamic_rank, String profile_pic) {
        this.id = id;
        this.name = name;
        this.speed_score = speed_score;
        this.stamina_score = stamina_score;
        this.dribbling_score = dribbling_score;
        this.defending_score = defending_score;
        this.passing_score = passing_score;
        this.shooting_score = shooting_score;
        this.score = score;
        this.rank = rank;
        this.old_rank = old_rank;
        this.city = city;
        this.country = country;
        this.player_detail_url = player_detail_url;
        this.dynamic_rank = dynamic_rank;
        this.profile_pic = profile_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed_score() {
        return speed_score;
    }

    public void setSpeed_score(double speed_score) {
        this.speed_score = speed_score;
    }

    public double getStamina_score() {
        return stamina_score;
    }

    public void setStamina_score(double stamina_score) {
        this.stamina_score = stamina_score;
    }

    public double getDribbling_score() {
        return dribbling_score;
    }

    public void setDribbling_score(double dribbling_score) {
        this.dribbling_score = dribbling_score;
    }

    public double getDefending_score() {
        return defending_score;
    }

    public void setDefending_score(double defending_score) {
        this.defending_score = defending_score;
    }

    public double getPassing_score() {
        return passing_score;
    }

    public void setPassing_score(double passing_score) {
        this.passing_score = passing_score;
    }

    public double getShooting_score() {
        return shooting_score;
    }

    public void setShooting_score(double shooting_score) {
        this.shooting_score = shooting_score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getOld_rank() {
        return old_rank;
    }

    public void setOld_rank(int old_rank) {
        this.old_rank = old_rank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlayer_detail_url() {
        return player_detail_url;
    }

    public void setPlayer_detail_url(String player_detail_url) {
        this.player_detail_url = player_detail_url;
    }

    public int getDynamic_rank() {
        return dynamic_rank;
    }

    public void setDynamic_rank(int dynamic_rank) {
        this.dynamic_rank = dynamic_rank;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speed_score=" + speed_score +
                ", stamina_score=" + stamina_score +
                ", dribbling_score=" + dribbling_score +
                ", defending_score=" + defending_score +
                ", passing_score=" + passing_score +
                ", shooting_score=" + shooting_score +
                ", score=" + score +
                ", rank=" + rank +
                ", old_rank=" + old_rank +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", player_detail_url='" + player_detail_url + '\'' +
                ", dynamic_rank=" + dynamic_rank +
                ", profile_pic='" + profile_pic + '\'' +
                '}';
    }
}
