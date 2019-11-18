package me.jraynor.data.fortnite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class League {
    private Mode mode;
    private Map<StatType, Float> stats = new HashMap<>();

    public League(String mode, List<Stat> stats) {
        this.mode = Mode.getType(mode);
        for (Stat stat : stats)
            this.stats.put(stat.getStatType(), stat.getFloatVal());
    }

    /**
     * Gets the specified stat based on the input stat type.
     *
     * @param type the type of stat to look for see
     * @return the float value of the stat
     */
    public Float getStat(StatType type) {
        return stats.get(type);
    }

    /**
     * The mode that the current league is for
     *
     * @return current mode
     */
    Mode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mode.toString() + " : ");
        for (StatType key : stats.keySet())
            sb.append("\n\t").append(key + " : ").append(stats.get(key));
        return sb.toString();
    }
}
