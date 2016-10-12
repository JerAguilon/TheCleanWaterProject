package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterPurityCondition {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private String condition;

    private WaterPurityCondition(String condition) {
        this.condition = condition;
    }

    public static WaterPurityCondition match(String value) {
        if (value.equals("Safe")) return SAFE;
        if (value.equals("Treatable")) return TREATABLE;
        if (value.equals("Unsafe")) return UNSAFE;

        return null;
    }

    public String toString() {
        return this.condition;
    }
}
