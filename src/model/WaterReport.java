package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by jeremy on 10/6/16.
 */
public class WaterReport extends Report {

    private WaterSourceType type;
    private WaterSourceCondition condition;

    private final SimpleStringProperty typeColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty conditionColumnProperty = new SimpleStringProperty();

    public WaterReport(String reporterName, String location, WaterSourceType type, WaterSourceCondition condition) {
        super(reporterName, location);
        this.type = type;
        this.condition = condition;

        this.typeColumnProperty.set(type.toString());
        this.conditionColumnProperty.set(condition.toString());

    }

    public String getTypeColumn() {
        return typeColumnProperty.get();
    }

    public void setTypeColumn(String value) {
        WaterSourceType newType = WaterSourceType.match(value);
        if (newType == null) throw new RuntimeException("Water source type not found");
        type = newType;

        typeColumnProperty.set(type.toString());
    }

    public String getConditionColumn() {
        return conditionColumnProperty.get();
    }

    public void setConditionColumn(String value) {
        WaterSourceCondition newCondition = WaterSourceCondition.match(value);
        if (newCondition == null) throw new RuntimeException("Water source type not found");
        this.condition = newCondition;

        conditionColumnProperty.set(condition.toString());
    }


}
