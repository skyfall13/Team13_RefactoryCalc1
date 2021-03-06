package com.riotgames.sample;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit test for simple CalcApp.
 */
public class CalcAppTest {

    @Test
    public void testCalcAppDoesCorrectAddOperation() {
        String[] args = new String[] { "1", "+", "2" };

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(3.0));
    }

    @Test
    public void 곱셈_연산_테스트(){
        String[] args = new String[] {"1", "*" , "2"};

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(2.0));
    }

    @Test
    public void 뺄셈_연산_테스트(){
        String[] args = new String[] {"10", "-" , "3"};

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(7.0));
    }

    @Test
    public void 나눗셈_연산_테스트(){
        String[] args = new String[] {"1", "/" , "2"};

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(0.5));
    }

    @Test
    public void 우선순위_연산_테스트(){
        String[] args = new String[] {"8","+","(","1", "*" , "2",")"};

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(10.0));
    }

    @Test
    public void 우선순위_연산_테스트_2(){
        String[] args = new String[] {"2","*","4", "/", "(", "1", "+",  "3.0", ")" ,"-", "3"};

        CalcApp app = new CalcApp();
        double result = app.calc(args);
        assertThat(result, is(-1.0));
    }

}
