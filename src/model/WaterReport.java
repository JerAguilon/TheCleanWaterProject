package model;

/**
 * Created by jeremy on 10/6/16.
 */
public class WaterReport extends Report {
    private WaterSourceType type;
    private WaterSourceCondition condition;

    public WaterReport(String reporterName, String location, WaterSourceType type, WaterSourceCondition condition) {
        super(reporterName, location);
        this.type = type;
        this.condition = condition;

    }

    public WaterSourceCondition getCondition() {
        return condition;
    }

    public void setCondition(WaterSourceCondition condition) {
        this.condition = condition;
    }

    public WaterSourceType getType() {
        return type;
    }

    public void setType(WaterSourceType type) {
        this.type = type;
    }
}
