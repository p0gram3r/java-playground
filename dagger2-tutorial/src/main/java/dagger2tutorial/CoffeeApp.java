package dagger2tutorial;

public class CoffeeApp {

    public static void main(String[] args) {

        // If all dependencies can be constructed without the user creating a dependency instance, then the generated implementation will also have
        // a create() method that can be used to get a new instance without having to deal with the builder.
        CoffeeShop coffeeShop = DaggerCoffeeShop.create();

        // regular way to wire everything: create modules manually and pass them to the Component builder
        //CoffeeShop coffeeShop = DaggerCoffeeShop.builder()
        //    .dripCoffeeModule(new DripCoffeeModule())
        //    .build();

         
        coffeeShop.maker().brew();

        // this app actually contains a bug: since the module provides new Heater instances all the time, the coffeeShop uses two different heaters
        // . As a result the "pumping" output is never shown
    }

}
