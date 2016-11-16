package model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ragavi on 11/16/2016.
 */
public class WaterSourceConditionTest {

    @Test
    public void validWaterSourceCondition_waste() {
            WaterSourceCondition wsc;
            wsc = WaterSourceCondition.match("Waste");
            assertTrue(wsc == WaterSourceCondition.WASTE);
    }

    @Test
    public void validWaterSourceCondition_clearTreatable() {
        WaterSourceCondition wsc;
        wsc = WaterSourceCondition.match("Clear (Treatable)");
        assertTrue(wsc == WaterSourceCondition.TREATABLE_CLEAR);
    }

    @Test
    public void validWaterSourceCondition_muddyTreatable() {
        WaterSourceCondition wsc;
        wsc = WaterSourceCondition.match("Muddy (Treatable)");
        assertTrue(wsc == WaterSourceCondition.TREATABLE_MUDDY);
    }

    @Test
    public void validWaterSourceCondition_potable() {
        WaterSourceCondition wsc;
        wsc = WaterSourceCondition.match("Potable");
        assertTrue(wsc == WaterSourceCondition.POTABLE);
    }

    @Test
    public void invalidWaterSourceCondition() {
        WaterSourceCondition wsc;
        wsc = WaterSourceCondition.match("INVALID STRING");
        assertTrue(wsc == null);
    }
}
