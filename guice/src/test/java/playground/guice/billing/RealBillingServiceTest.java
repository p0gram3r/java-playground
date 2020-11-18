package playground.guice.billing;

import org.junit.Before;
import org.junit.Test;
import playground.guice.PizzaOrder;
import playground.guice.UnreachableException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RealBillingServiceTest {

    private CreditCardProcessor mockedCreditCardProcessor;
    private TransactionLog mockedTransactionLog;
    private RealBillingService billingService;

    @Before
    public void before() {
        mockedCreditCardProcessor = mock(CreditCardProcessor.class);
        mockedTransactionLog = mock(TransactionLog.class);

        this.billingService = new RealBillingService(mockedCreditCardProcessor, mockedTransactionLog);
    }

    @Test
    public void chargeOrderSuccessful() throws UnreachableException {
        PizzaOrder order = PizzaOrder.builder()
                .amount(1000)
                .build();
        CreditCard creditCard = CreditCard.builder()
                .balance(0)
                .limit(1000)
                .build();

        ChargeResult chargeResult = ChargeResult.builder()
                .wasSuccessful(true)
                .build();

        when(mockedCreditCardProcessor.charge(creditCard, order.getAmount()))
                .thenReturn(chargeResult);

        Receipt receipt = billingService.chargeOrder(order, creditCard);
        assertThat(receipt.getAmount(), is(order.getAmount()));
        assertThat(receipt.getMessage(), is(nullValue()));

        verify(mockedTransactionLog).logChargeResult(chargeResult);
    }

    @Test
    public void chargeOrderNotSuccessfulWhenLimitExceeded() throws UnreachableException {
        PizzaOrder order = PizzaOrder.builder()
                .amount(1000)
                .build();
        CreditCard creditCard = CreditCard.builder()
                .balance(0)
                .limit(500)
                .build();

        ChargeResult chargeResult = ChargeResult.builder()
                .wasSuccessful(false)
                .declineMessage("out of money")
                .build();

        when(mockedCreditCardProcessor.charge(creditCard, order.getAmount()))
                .thenReturn(chargeResult);

        Receipt receipt = billingService.chargeOrder(order, creditCard);
        assertThat(receipt.getAmount(), is(0));
        assertThat(receipt.getMessage(), is(chargeResult.getDeclineMessage()));

        verify(mockedTransactionLog).logChargeResult(chargeResult);
    }
}
