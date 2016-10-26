package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterSourceCondition {
    WASTE("Waste"), TREATABLE_CLEAR("Clear (Treatable)"), TREATABLE_MUDDY("Muddy (Treatable)"), POTABLE("Potable");

    private String condition;
    /**
     * Constructor method for the WaterSourceCondition enum class
     * @param condition
     */
    private WaterSourceCondition(String condition) {
        this.condition = condition;
    }

    /**
     * toString method for the condition
     * @return the condition as a String
     */
    public String toString() {
        return condition;
    }

    /**
     * Matches the String typed in to an appropriate enum value
     * @param value the String that entered in by the user
     * @return the WaterSourceCondition enum value that matches the String
     */
    public static WaterSourceCondition match(String value) {
        if (value.equals("Waste")) return WASTE;
        if (value.equals("Clear (Treatable)")) return TREATABLE_CLEAR;
        if (value.equals("Muddy (Treatable)")) return TREATABLE_MUDDY;
        if (value.equals("Potable")) return POTABLE;

        return null;
    }
}
