package playground.guice.billing;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Receipt {
    private final int amount;
    private final String message;

    public static Receipt forSuccessfulCharge(int amount) {
        return Receipt.builder().amount(amount).build();
    }

    public static Receipt forDeclinedCharge(String message) {
        return Receipt.builder().message(message).build();
    }

    public static Receipt forSystemFailure(String message) {
        return Receipt.builder().message(message).build();
    }
}
