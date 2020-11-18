package playground.guice.billing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCard {
    private int balance;
    private final int limit;

    // not thread safe :-)
    public boolean charge(int amount) {
        if (balance + amount > limit) {
            return false;
        }

        balance += amount;
        return true;
    }
}
