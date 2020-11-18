package playground.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.java.Log;
import playground.guice.billing.BillingModule;
import playground.guice.billing.BillingService;
import playground.guice.billing.CreditCard;
import playground.guice.billing.Receipt;

import java.util.stream.IntStream;

@Log
public class GuiceMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BillingModule());

        PizzaOrder order = PizzaOrder.builder().amount(1337).build();
        CreditCard creditCard = CreditCard.builder().limit(5000).build();

        BillingService billingService = injector.getInstance(BillingService.class);

        IntStream.range(0, 4).forEach(i -> {
            Receipt receipt = billingService.chargeOrder(order, creditCard);
            log.info("receipt: " + receipt.toString());
            log.info("credit card: " + creditCard.toString());
        });
    }
}
