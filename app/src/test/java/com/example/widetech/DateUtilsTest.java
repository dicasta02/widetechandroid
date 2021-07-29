package com.example.widetech;


import com.example.widetech.utilities.Constants;
import com.example.widetech.utilities.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtilsTest {

    @Before
    public void prepareDataForTest() {
        MockitoAnnotations.initMocks(DateUtilsTest.this);

    }

    @Test
    public void methodForGetCurrentDateShortFormatWithTwoDatesEquals() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_SHORT);
        String currentDate = format.format(calendar.getTime());
        String dateUtils = DateUtils.getCurrentDateShortFormat();

        Assert.assertEquals(dateUtils, currentDate);
    }

    @Test
    public void methodForGetCurrentDateShortFormatWithTwoDatesNotEquals() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT_SHORT);
        String currentDate = format.format(calendar.getTime());
        String dateUtils = DateUtils.getCurrentDateShortFormat();

        Assert.assertNotEquals(dateUtils, currentDate);
    }
}
