package com.gyf.toolutils4a;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int i = 0;
        while (i < 10000){
            i++;
            System.out.println(StringUtil.randomFor6());
        }

        //assertEquals(4, 2 + 2);
    }
}