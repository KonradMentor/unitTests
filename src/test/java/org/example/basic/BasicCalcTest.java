package org.example.basic;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BasicCalcTest {
    private BasicCalc basicCalc;

    @BeforeEach
    void setup(){
        basicCalc = new BasicCalc();
    }

    @Test
    void add_shouldAddProperly_whenTwoNegativeIntegerNumbers() {
        //this test will always run
        Assumptions.assumeTrue(true);
        assertEquals(-10, basicCalc.add(-5,-5));
    }

    @Test
    void add_shouldAddProperly_whenOneNegativeIntegerNumber() {
        //this test will always be skipped/aborted
        Assumptions.assumeTrue(false);
        assertEquals(-10, basicCalc.add(-5,0));
    }

    @Test
    void add_shouldAddProperly_whenTwoIntegerNumbers() {
        assertEquals(5, basicCalc.add(1,4));
    }

    @Test
    void add_shouldThrowNullPointerException_whenFirstArgumentIsNull() {
        assertThrows(NullPointerException.class, () -> basicCalc.add(null, 4));

    }

    @Test
    void add_shouldThrowNullPointerException_whenSecondArgumentIsNull() {
        // This is the old way to assert throws
        try{
            basicCalc.add(1,null);
        } catch (Exception e) {
            assertInstanceOf(NullPointerException.class, e);
        }
    }

}