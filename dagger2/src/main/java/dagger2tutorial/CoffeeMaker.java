package dagger2tutorial;

import javax.inject.Inject;

import dagger.Lazy;
import dagger2tutorial.parts.Heater;
import dagger2tutorial.parts.Pump;

public class CoffeeMaker {

    private final Lazy<Heater> heater; // Create a possibly costly heater only when we use it.
    private final Pump pump;


    @Inject
    public CoffeeMaker(Lazy<Heater> heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.get().on();
        pump.pump();
        System.out.println(" [_]P coffee! [_]P ");
        heater.get().off();
    }
}