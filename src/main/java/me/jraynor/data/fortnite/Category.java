package me.jraynor.data.fortnite;

public enum Category {
    GENERAL("general"), TOPS("tops"), LIFE_TIME("lifeTime"), UNKNOWN("unknown");
    String value;

    Category(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Converts a string category into the enum version of the category
     *
     * @param category
     * @return
     */
    public static Category getCategory(String category) {
        switch (category) {
            case "General":
                return Category.GENERAL;
            case "Tops":
                return Category.TOPS;
            case "life":
                return Category.LIFE_TIME;
        }
        return Category.UNKNOWN;
    }
}
