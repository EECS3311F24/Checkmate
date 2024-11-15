package ca.yorku.checkmate.Model.user;

/**
 * UserData model that represents data for a user
 */
public class UserData {
    public String id;
    public Language language = Language.ENGLISH;
    public Theme theme = Theme.LIGHT;
    public int wins;
    public int loses;
    public int gamesPlayed;

    public UserData() {}

    public UserData setId(String id) {
        this.id = id;
        return this;
    }

    public UserData setLanguage(Language language) {
        this.language = language == null ? Language.ENGLISH : language;
        return this;
    }

    public UserData setTheme(Theme theme) {
        this.theme = theme == null ? Theme.LIGHT : theme;
        return this;
    }

    public UserData setWins(int wins) {
        this.wins = Math.max(wins, 0);
        return this;
    }

    public UserData setLoses(int loses) {
        this.loses = Math.max(loses, 0);
        return this;
    }

    public UserData setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = Math.max(gamesPlayed, 0);
        return this;
    }

    public enum Language {
        ENGLISH,
        FRENCH,
        SPANISH;

        @Override
        public String toString(){
            return switch (this) {
                case ENGLISH -> "English";
                case FRENCH -> "French";
                case SPANISH -> "Spanish";
            };
        }
    }

    public enum Theme {
        LIGHT,
        DARK;

        @Override
        public String toString(){
            return switch (this) {
                case LIGHT -> "Light";
                case DARK -> "Dark";
            };
        }
    }
}