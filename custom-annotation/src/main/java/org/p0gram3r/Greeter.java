package org.p0gram3r;

public class Greeter {

    private final String name;

    public Greeter(String name) {
        this.name = name;
    }

    @CustomAnnotation(prefix = "### ")
    public void greet(String message) {
        System.out.println(name + " says: " + message);
    }
}
