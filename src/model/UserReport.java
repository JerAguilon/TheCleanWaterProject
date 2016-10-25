package model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jeremy on 10/6/16.
 */
public class UserReport extends Report {

    private WaterSourceType type;
    private WaterSourceCondition condition;

    private final SimpleStringProperty typeColumnProperty = new SimpleStringProperty();
    private final SimpleStringProperty conditionColumnProperty = new SimpleStringProperty();

    public UserReport(String time, String reporterName,
                      String location, WaterSourceType type, WaterSourceCondition condition, String id) {
        super(time, reporterName, location, id);

        this.type = type;
        this.condition = condition;

        this.typeColumnProperty.set(type.toString());
        this.conditionColumnProperty.set(condition.toString());
    }

    public WaterSourceCondition getCondition() {
        return this.condition;
    }

    public WaterSourceType getType() {
        return this.type;
    }

    public UserReport(String reporterName, String location, WaterSourceType type, WaterSourceCondition condition) {
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

    @Override
    public List<String> toLogList() {
        List<String> output = super.toLogList();
        output.add(String.format("\tType: %s", getTypeColumn()));
        output.add(String.format("\tCondition: %s", getConditionColumn()));
        return output;

    }


}
