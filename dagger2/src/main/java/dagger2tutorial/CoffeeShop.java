package dagger2tutorial;

import dagger.Component;
import dagger2tutorial.modules.DripCoffeeModule;

@Component(modules = DripCoffeeModule.class)
interface CoffeeShop {

    CoffeeMaker maker();

}
