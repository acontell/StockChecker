package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Amount implements Comparable<Amount> {
    private static final int ONE_HUNDRED = 100;
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    public static final Amount ZERO = of(BigDecimal.ZERO);

    private final BigDecimal value;

    private Amount(final BigDecimal value) {
        this.value = value;
    }

    private Amount(final double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public static Amount of(final BigDecimal value) {
        return new Amount(value);
    }

    public static Amount of(final double value) {
        return new Amount(value);
    }

    public Amount add(final Amount a) {
        return of(this.value.add(a.value));
    }

    public Amount divide(final Amount a) {
        return of(this.value.divide(a.value, SCALE, RoundingMode.CEILING));
    }

    public Amount percentage(final Amount a) {
        return this.divide(a).multiply(of(ONE_HUNDRED));
    }

    public Amount multiply(final Amount a) {
        return of(this.value.multiply(a.value));
    }

    public Amount subtract(final Amount a) {
        return of(this.value.subtract(a.value));
    }

    @Override
    public String toString() {
        return this.getValueRounded().toString();
    }

    private BigDecimal getValueRounded() {
        return this.value.setScale(SCALE, ROUNDING_MODE);
    }

    @Override
    public int compareTo(final Amount o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Amount amount = (Amount) o;
        return Objects.equals(this.getValueRounded(), amount.getValueRounded());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getValueRounded());
    }
}
