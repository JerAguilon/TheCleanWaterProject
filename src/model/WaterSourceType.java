package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterSourceType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");

    private String sourceType;

    private WaterSourceType(String sourceTye) {
        this.sourceType = sourceTye;
    }

    public String toString() {
        return  sourceType;
    }

    public static WaterSourceType match(String value) {
        if (value.equals("Bottled")) return BOTTLED;
        if (value.equals("Well")) return WELL;
        if(value.equals("Lake")) return LAKE;
        if(value.equals("Other")) return OTHER;

        return null;
    }
}
