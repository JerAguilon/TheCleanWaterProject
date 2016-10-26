package model;

/**
 * Created by jeremy on 10/6/16.
 */
public enum WaterSourceType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");

    private String sourceType;

    /**
     * Constructor method for the WaterSourceType enum class
     * @param sourceTye
     */
    private WaterSourceType(String sourceTye) {
        this.sourceType = sourceTye;
    }

    /**
     * toString method for the sourceType
     * @return the sourceType as a String
     */
    public String toString() {
        return  sourceType;
    }

    /**
     * Matches the String typed in to an appropriate enum value
     * @param value the String that entered in by the user
     * @return the WaterSourceType enum value that matches the String
     */
    public static WaterSourceType match(String value) {
        if (value.equals("Bottled")) return BOTTLED;
        if (value.equals("Well")) return WELL;
        if(value.equals("Lake")) return LAKE;
        if(value.equals("Other")) return OTHER;

        return null;
    }
}
