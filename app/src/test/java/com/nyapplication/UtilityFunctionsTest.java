package com.nyapplication;

import com.nyapplication.Utility.Util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Sino K D
 * @since 8/8/18.
 */
public class UtilityFunctionsTest {

    @Test
    public void shouldConvertUTTime() {
        assertEquals(true, !Util.convertUTI("2018-08-06").isEmpty());
    }

    @Test
    public void shouldConvertUITimeEmptyString() {
        assertEquals("", Util.convertUTI(""));
    }

    @Test
    public void shouldConvertUITimeNullString() {
        assertEquals("", Util.convertUTI(null));
    }
}
