package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/*
 * Created by jeremy on 10/6/16.
 */
public class WorkerReport extends Report {

    private WaterPurityCondition condition;
    private String virusPPM;
    private String contaminantPPM;
    private String location;

    private final SimpleStringProperty conditionColumnProperty = new SimpleStringProperty();

    /**
     * Constructor for WorkerReport class
     * @param time the time the report was made
     * @param reporterName the name of the person making the report
     * @param location the location at which the report was made
     * @param condition the WaterPurityCondition
     * @param id the id of the person making the report
     */
    public WorkerReport(String time, String reporterName,
                        String location, WaterPurityCondition condition, String id) {
        super(time, reporterName, location, id);

        this.condition = condition;
        this.conditionColumnProperty.set(condition.toString());
    }

    /**
     * Get method for the water purity condition
     * @return the WaterPurityCondition
     */
    public WaterPurityCondition getCondition() {
        return this.condition;
    }

    /**
     * creates a WorkerReport with the reporterName, location, and condition
     * @param reporterName the name of the person making the report
     * @param location the location at which the report was made
     * @param condition the WaterPurityCondition
     */
    public WorkerReport(String reporterName, String location, WaterPurityCondition condition, String virusPPM,
                        String contaminantPPM) {
        super(reporterName, location);

        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.conditionColumnProperty.set(condition.toString());

    }

    /**
     * Get method for the ConditionColumn
     * @return String that is what the user has inputted in as the water purity condition
     */
    private String getConditionColumn() {
        return conditionColumnProperty.get();
    }

    /**
     * Set method for the ConditionColumn
     * @param value the condition of water purity entered in by the user as a string
     */
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

    public String getVirusPPM() {
        return virusPPM;
    }

    public String getContaminantPPM() {
        return contaminantPPM;
    }
}
