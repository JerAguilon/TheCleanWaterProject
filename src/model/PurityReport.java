package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by jeremy on 10/6/16.
 */
public class PurityReport extends Report {

    public PurityReport(String reporterName, String location) {
        super(reporterName, location);
    }
    private WaterPurityCondition condition;

    private final SimpleStringProperty conditionColumnProperty = new SimpleStringProperty();

    public PurityReport(String time, String reporterName,
                      String location, WaterPurityCondition condition, long id) {
        super(time, reporterName, location, id);

        this.condition = condition;

        this.conditionColumnProperty.set(condition.toString());
    }

    public WaterPurityCondition getCondition() {
        return this.condition;
    }

    public PurityReport(String reporterName, String location, WaterPurityCondition condition) {
        super(reporterName, location);

        this.condition = condition;

        this.conditionColumnProperty.set(condition.toString());

    }

    public String getConditionColumn() {
        return conditionColumnProperty.get();
    }

    public void setConditionColumn(String value) {
        WaterPurityCondition newCondition = WaterPurityCondition.match(value);
        if (newCondition == null) throw new RuntimeException("Water purity condition not found");
        this.condition = newCondition;

        conditionColumnProperty.set(condition.toString());
    }

    @Override
    public List<String> toLogList() {
        List<String> output = super.toLogList();
        output.add(String.format("\tCondition: %s", getConditionColumn()));
        return output;

    }
}
