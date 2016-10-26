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

    /**
     * Constructor for userReport class
     * @param time the time the report was made
     * @param reporterName the name of the person making the report
     * @param location the location at which the report was made
     * @param type the WaterSourceType
     * @param condition the WaterSourceCondition
     * @param id the id of the person making the report
     */
    public UserReport(String time, String reporterName,
                      String location, WaterSourceType type, WaterSourceCondition condition, String id) {
        super(time, reporterName, location, id);

        this.type = type;
        this.condition = condition;

        this.typeColumnProperty.set(type.toString());
        this.conditionColumnProperty.set(condition.toString());
    }

    /**
     * Get method for WaterSourceCondition
     * @return WaterSourceCondition in the report
     */
    public WaterSourceCondition getCondition() {
        return this.condition;
    }

    /**
     *Get method for WaterSourceType
     * @return WaterSourceType in the report
     */
    public WaterSourceType getType() {
        return this.type;
    }

    /**
     * creates a UserReport with the reporterName, location, type, and condition
     * @param reporterName the name of the person making the report
     * @param location the location at which the report was made
     * @param type the WaterSourceType
     * @param condition the WaterSourceCondition
     */
    public UserReport(String reporterName, String location, WaterSourceType type, WaterSourceCondition condition) {
        super(reporterName, location);
        this.type = type;
        this.condition = condition;

        this.typeColumnProperty.set(type.toString());
        this.conditionColumnProperty.set(condition.toString());

    }

    /**
     * Get method for the TypeColumn
     * @return String that is what the user has inputted in as the water source type
     */
    public String getTypeColumn() {
        return typeColumnProperty.get();
    }

    /**
     * Set method for the TypeColumn
     * @param value the type of water source entered in by the user as a string
     */
    public void setTypeColumn(String value) {
        WaterSourceType newType = WaterSourceType.match(value);
        if (newType == null) throw new RuntimeException("Water source type not found");
        type = newType;

        typeColumnProperty.set(type.toString());
    }

    /**
     * Get method for the ConditionColumn
     * @return String that is what the user has inputted in as the water source condition
     */
    public String getConditionColumn() {
        return conditionColumnProperty.get();
    }

    /**
     * Set method for the ConditionColumn
     * @param value the condition of water source entered in by the user as a string
     */
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
