package model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*
 * Created by Ben Radock on 11/15/2016.
 */
public class SetWorkerReportConditionColumnTest {

    WorkerReport report = new WorkerReport("", "", WaterPurityCondition.SAFE, 0, 0);

    @Test
    public void passInSameCondition() {
        report.setConditionColumn("Safe");
        assertTrue(report.getCondition() == WaterPurityCondition.SAFE);
        assertTrue(report.getConditionColumn().equals("Safe"));
    }

    @Test
    public void changeConditionToTreatable() {
        report.setConditionColumn("Treatable");
        assertTrue(report.getCondition() == WaterPurityCondition.TREATABLE);
        assertTrue(report.getConditionColumn().equals("Treatable"));
    }

    @Test
    public void changeConditionToUnsafe() {
        report.setConditionColumn("Unsafe");
        assertTrue(report.getCondition() == WaterPurityCondition.UNSAFE);
        assertTrue(report.getConditionColumn().equals("Unsafe"));
    }

    @Test
    public void passIn_invalidOption() {
        try {
            report.setConditionColumn("asdf");
            assertTrue(false); //this line should not be reached
        } catch (RuntimeException e) {
            //this catch block should be reached
            assertTrue(e.getMessage().equals("Water purity condition not found"));
        }
    }

    @Test
    public void passIn_null() {
        try {
            report.setConditionColumn(null);
            assertTrue(false); //this line should not be reached
        } catch (NullPointerException e) {
            //this catch block should be reached
            assertTrue(true);
        }
    }
}
