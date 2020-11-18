package playground.guice.billing;

import playground.guice.UnreachableException;

public class PaypalCreditCardProcessor implements CreditCardProcessor {
    @Override
    public ChargeResult charge(CreditCard creditCard, int amount) throws UnreachableException {
        final ChargeResult.ChargeResultBuilder builder = ChargeResult.builder();

        final boolean success = creditCard.charge(amount);
        if (!success) {
            builder.declineMessage("our of money :-(");
        }

        builder.wasSuccessful(success);
        return builder.build();
    }
}
