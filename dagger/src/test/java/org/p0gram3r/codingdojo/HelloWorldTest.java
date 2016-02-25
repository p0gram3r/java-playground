package org.p0gram3r.codingdojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.p0gram3r.codingdojo.HelloWorld;

public class HelloWorldTest {

    @Test
    public void test() {
        HelloWorld thingy = new HelloWorld();
        assertThat(thingy.getGreeting(), is("Hello World!"));
    }

}
