package playground.guice.billing;

import playground.guice.UnreachableException;

public interface TransactionLog {
    void logChargeResult(ChargeResult result);

    void logConnectException(UnreachableException e);
}
