package com.example.widetech;


import com.example.widetech.utilities.StringUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class StringUtilsTest {

    @Before
    public void prepareDataForTest() {
        MockitoAnnotations.initMocks(StringUtilsTest.this);
    }

    @Test
    public void methodForValidateIfStringIsEmpty() {
        String stringEmpty = "";
        boolean stringIsEmpty = StringUtils.isEmpty(stringEmpty);

        Assert.assertTrue(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfStringIsNull() {
        String stringEmpty = null;
        boolean stringIsEmpty = StringUtils.isEmpty(stringEmpty);

        Assert.assertTrue(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfStringShouldReturnFalse() {
        String stringEmpty = "Hola";
        boolean stringIsEmpty = StringUtils.isEmpty(stringEmpty);

        Assert.assertFalse(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfEmailIsCorrectWithEmailEmptyShouldReturnFalse() {
        String stringEmpty = "";
        boolean stringIsEmpty = StringUtils.isValidEmail(stringEmpty);

        Assert.assertFalse(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfEmailIsCorrectWithErrorEmailShouldReturnFalse() {
        String stringEmpty = "yollamo.com";
        boolean stringIsEmpty = StringUtils.isValidEmail(stringEmpty);

        Assert.assertFalse(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfEmailIsCorrectWithErrorEmailShouldReturnFalse1() {
        String stringEmpty = "yollamo@com";
        boolean stringIsEmpty = StringUtils.isValidEmail(stringEmpty);

        Assert.assertFalse(stringIsEmpty);
    }

    @Test
    public void methodForValidateIfEmailCorrectShouldReturnTrue() {
        String stringEmpty = "yollamo@gmail.com";
        boolean stringIsEmpty = StringUtils.isValidEmail(stringEmpty);

        Assert.assertTrue(stringIsEmpty);
    }

}
