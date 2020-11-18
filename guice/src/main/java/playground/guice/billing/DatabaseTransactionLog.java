package playground.guice.billing;

import lombok.extern.java.Log;
import playground.guice.UnreachableException;

@Log
public class DatabaseTransactionLog implements TransactionLog {

    @Override
    public void logChargeResult(ChargeResult result) {
        log.info(result.toString());
    }

    @Override
    public void logConnectException(UnreachableException e) {
        log.warning(e.getMessage());
    }
}
