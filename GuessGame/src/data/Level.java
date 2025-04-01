package data;

import java.util.EnumSet;
import java.util.List;

public enum Level {
    GUESSMAN(0), BEGINNER(5), MIDLEVEL(10), EXPERT(20), GURU(40);

    private String url;
    private int levelScore;

    Level(int i) {
        this.levelScore = i;
        url = name().toLowerCase();
    }

    public int getLevel() {
        return levelScore;
    }

    public static Level findLevel(float score) {

        List<Level> a = EnumSet.allOf(Level.class)
                .stream()
                .filter(e -> e.getLevel() <= (score))
                .toList();

        return a.getLast();
    }

    public String getUrl() {
        return url;
    }

}
