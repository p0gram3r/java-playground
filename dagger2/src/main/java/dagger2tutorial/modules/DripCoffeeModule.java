package dagger2tutorial.modules;

import dagger.Module;
import dagger.Provides;
import dagger2tutorial.parts.ElectricHeater;
import dagger2tutorial.parts.Heater;
import dagger2tutorial.parts.Pump;
import dagger2tutorial.parts.Thermosiphon;

/*
 * All @Provides methods must belong to a module. These are just classes that have an @Module annotation.
 */
@Module
public class DripCoffeeModule {

    /*
     * For these cases where @Inject is insufficient or awkward, use an @Provides-annotated method to satisfy a dependency. The method’s return
     * type defines which dependency it satisfies.
     */
    @Provides
    public static Heater provideHeater() {
        return new ElectricHeater();
    }

    @Provides
    public static Pump providePump(Thermosiphon pump) {
        return pump;
    }

}
