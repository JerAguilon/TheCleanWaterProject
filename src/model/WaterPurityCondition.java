package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterPurityCondition {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private String condition;

    /**
     * Constructor method for the WaterPurityCondition enum class
     * @param condition
     */
    private WaterPurityCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Matches the String typed in to an appropriate enum value
     * @param value the String that entered in by the user
     * @return the WaterPurityCondition enum value that matches the String
     */
    public static WaterPurityCondition match(String value) {
        if (value.equals("Safe")) return SAFE;
        if (value.equals("Treatable")) return TREATABLE;
        if (value.equals("Unsafe")) return UNSAFE;

        return null;
    }

    /**
     * toString method for the condition
     * @return the condition as a String
     */
    public String toString() {
        return this.condition;
    }
}
