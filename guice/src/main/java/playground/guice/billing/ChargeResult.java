package playground.guice.billing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class ChargeResult {
    private final boolean wasSuccessful;
    private final String declineMessage;

    public boolean wasSuccessful() {
        return wasSuccessful;
    }

    public String getDeclineMessage() {
        return declineMessage;
    }
}
