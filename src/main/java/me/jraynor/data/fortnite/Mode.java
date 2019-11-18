package me.jraynor.data.fortnite;

public enum Mode {
    SOLO, DUO, SQUAD, SOLO_CURRENT, DUO_CURRENT, SQUAD_CURRENT, LIFE_TIME, UNKNOWN;

    /**
     * Converts the weird data used to represent the specific modes on the backend
     * into a nicely formatted Enum type
     *
     * @param mode the ugly input string from the backend
     * @return the nicely formatted mode enum type
     */
    public static Mode getType(String mode) {
        switch (mode) {
            case "p2":
            case "solo":
                return Mode.SOLO;
            case "p10":
            case "duo":
                return Mode.DUO;
            case "p9":
            case "squad":
                return Mode.SQUAD;
            case "curr_p2":
            case "currsolo":
                return Mode.SOLO_CURRENT;
            case "curr_p10":
            case "currduo":
                return Mode.DUO_CURRENT;
            case "curr_p9":
            case "currsquad":
                return Mode.SQUAD_CURRENT;
            case "lifeTimeStats":
            case "life":
                return Mode.LIFE_TIME;
        }
        return Mode.UNKNOWN;
    }
}
