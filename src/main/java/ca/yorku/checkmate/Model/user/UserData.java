package ca.yorku.checkmate.Model.user;

/**
 * UserData model that represents data for a user
 */
public class UserData {
    public String id;
    public String language;
    public String theme;
    int wins;
    int loses;
    int gamesPlayed;

    public UserData() {}

    public UserData setId(String id) {
        this.id = id;
        return this;
    }

    public UserData setLanguage(String language) {
        this.language = language;
        return this;
    }

    public UserData setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public UserData setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public UserData setLoses(int loses) {
        this.loses = loses;
        return this;
    }

    public UserData setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        return this;
    }
}