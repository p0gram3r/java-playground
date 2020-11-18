package playground.guice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PizzaOrder {
    private final int amount;
}
