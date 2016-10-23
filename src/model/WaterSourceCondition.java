package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterSourceCondition {
    WASTE("Waste"), TREATABLE_CLEAR("Clear (Treatable"), TREATABLE_MUDDY("Muddy (Treatable"), POTABLE("Potable");

    private String condition;

    private WaterSourceCondition(String condition) {
        this.condition = condition;
    }

    public String toString() {
        return condition;
    }

    public static WaterSourceCondition match(String value) {
        if (value.equals("Waste")) return WASTE;
        if (value.equals("Clear (Treatable)")) return TREATABLE_CLEAR;
        if (value.equals("Muddy (Treatable)")) return TREATABLE_MUDDY;
        if (value.equals("Potable")) return POTABLE;

        return null;
    }
}
