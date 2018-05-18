package com.github.dmtest.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParamsTests {

    @Parameter
    public int operand1;

    @Parameter(1)
    public int operand2;

    @Parameter(2)
    public int expectedResult;

    @Parameters(name = "operand1 = {0} | operand2 = {1} | expectedResult = {2}")
    public static Collection<Integer[]> dataProvider() {
        return Arrays.asList(new Integer[][]{
                {1, 2, 3},
                {2, 4, 6},
                {5, 6, 11},
                {7, 5, 12},
                {2, 4, 5},
                {4, 1, 5},
                {8, 2, 11}
        });
    }

    @Test
    public void checkSum() {
        Assert.assertTrue("Сумма слагаемых не соответствует ожидаемому значению", operand1 + operand2 == expectedResult);
    }
}
