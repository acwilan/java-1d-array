package com.xoom.codechallenge.challenge190308;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SolutionTest {

    private static final String SUCCESS = "YES";
    private static final String FAIL = "NO";

    @Test
    public void smallTest1() {
        assertTrue("Should have won", Solution.canWin(3, new int[] {0, 0, 0, 0, 0}));
    }

    @Test
    public void smallTest2() {
        assertTrue("Should have won", Solution.canWin(5, new int[] {0, 0, 0, 1, 1, 1}));
    }

    @Test
    public void smallTest3() {
        assertFalse("Should have lost", Solution.canWin(3, new int[] {0, 0, 1, 1, 1, 0}));
    }

    @Test
    public void smallTest4() {
        assertFalse("Should have won", Solution.canWin(1, new int[] {0, 1, 0}));
    }

    @Test
    public void sampleTestCases0() throws Exception {
        final String[] output = getStrings("/src/main/resources/input00.txt");

        assertTrue(output.length >= 4);
        assertThat(output[0], is(SUCCESS));
        assertThat(output[1], is(SUCCESS));
        assertThat(output[2], is(FAIL));
        assertThat(output[3], is(FAIL));
    }

    @Test
    public void sampleTestCases6() throws Exception {
        final String[] output = getStrings("/src/main/resources/input06.txt");

        assertTrue(output.length >= 5);
        assertThat(output[0], is(SUCCESS));
        assertThat(output[1], is(SUCCESS));
        assertThat(output[2], is(FAIL));
        assertThat(output[3], is(SUCCESS));
        assertThat(output[4], is(FAIL));
    }

    private String[] getStrings(final String s) throws FileNotFoundException {
        final InputStream sysIn = System.in;
        final PrintStream sysOut = System.out;
        final FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + s);
        System.setIn(fileInputStream);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        Solution.main(new String[] {});
        System.setIn(sysIn);
        System.setOut(sysOut);
        return byteArrayOutputStream.toString().split(System.lineSeparator());
    }
}
