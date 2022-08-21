package kr.irang.baccarat;

public class Card {
    private String pattern;
    private int denomination;

    public Card(String pattern, int denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public String getSpecialCharacter(String pattern) {
        switch (pattern) {
            case "spade":
                return "♠";
            case "heart":
                return "♥";
            case "diamond":
                return "◆";
            default:
                return "♣";
        }
    }
}
