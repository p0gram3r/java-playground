package playground.guice.billing;

import playground.guice.UnreachableException;

public interface CreditCardProcessor {
    ChargeResult charge(CreditCard creditCard, int amount) throws UnreachableException;
}
