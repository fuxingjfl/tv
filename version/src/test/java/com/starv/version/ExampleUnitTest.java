package com.starv.version;

import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        URI uri=URI.create("http://47.92.53.144:8080");
        System.out.print("host="+uri.getHost()+"---"+uri.getPort());
    }
}