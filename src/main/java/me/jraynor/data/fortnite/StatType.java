package me.jraynor.data.fortnite;

import java.util.Arrays;

public enum StatType {
    KILLS("kills"), WIN_PERCENT("win%", "winratio"), KILL_DEATH_RATIO("k/d", "kd"),
    WINS("wins", "top1"), GAMES_PLAYED("matches played", "matches"), TOP_5("top 5s", "top5"),
    TOP_10("top 10", "top10"), TOP_12("top 12s", "top12"), TOP_25("top 25s", "top25"),
    SCORE("score"), UNKNOWN("unknown");
    private String[] value;

    StatType(String... value) {
        this.value = value;
    }

    /**
     * Checks the inputted string against the possible values of the stat id
     * for some reason the backend has different names for the same values,
     * so we have to cleanse that and convert them into a uniform boolean
     *
     * @param input the raw stat string
     * @return the converted and stripped StatType
     */
    public static StatType getStatType(String input) {
        for (StatType type : StatType.values()) {
            for (String key : type.value)
                if (key.equalsIgnoreCase(input))
                    return type;
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
