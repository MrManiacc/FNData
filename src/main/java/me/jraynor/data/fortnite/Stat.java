package me.jraynor.data.fortnite;

public class Stat {
    private String label;
    private Category category;
    private String field;
    private StatType statType;
    private String value;
    private float floatVal;

    public Stat() {
        this.label = "Error";
        this.category = Category.UNKNOWN;
        this.field = "Error";
        this.value = "-9999";
    }

    public Stat(String label, String category, String field, String value) {
        this.label = label;
        this.category = Category.getCategory(category);
        this.field = field.toLowerCase();
        this.value = value;
        parseValue();
    }

    public Stat(String label, String value) {
        this.label = label;
        this.category = Category.LIFE_TIME;
        this.field = label.toLowerCase();
        this.value = value.replace(",", "");
        parseValue();
    }

    /**
     * Simply strips and converts the data into a float. This is okay because all of the processed
     * data is either a percent (containing %) or just a simple number value
     */
    private void parseValue() {
        this.statType = StatType.getStatType(field);
        String fixedVal = value.replace("%", "").replace(",", "");
        this.floatVal = Float.parseFloat(fixedVal);
    }

    public StatType getStatType() {
        return statType;
    }

    public String getValue() {
        return value;
    }

    public float getFloatVal() {
        return floatVal;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "label='" + label + '\'' +
                ", category=" + category +
                ", field='" + field + '\'' +
                ", value=" + value +
                '}';
    }

}
