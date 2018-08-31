package domain;

import org.junit.Test;

import static domain.Amount.of;
import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {
    private static final Amount TEN = of(10);
    private static final String TEN_STRING = "10.00";
    private static final int TEN_HASHCODE = 31033;
    private static final Amount FIVE = of(5);
    private static final Amount TWO = of(2);
    private static final Amount TWENTY = of(20);
    private static final Amount FIFTY = of(50);
    private static final Amount FIFTEEN = of(15);

    @Test
    public void shouldAdd() {
        assertThat(TEN.add(FIVE)).isEqualTo(FIFTEEN);
    }

    @Test
    public void shouldMultiply() {
        assertThat(TEN.multiply(FIVE)).isEqualTo(FIFTY);
    }

    @Test
    public void shouldDivide() {
        assertThat(TEN.divide(FIVE)).isEqualTo(TWO);
    }

    @Test
    public void shouldSubtract() {
        assertThat(TEN.subtract(FIVE)).isEqualTo(FIVE);
    }

    @Test
    public void shouldPercentage() {
        assertThat(TEN.percentage(FIFTY)).isEqualTo(TWENTY);
    }

    @Test
    public void shouldToString() {
        assertThat(TEN.toString()).isEqualTo(TEN_STRING);
    }

    @Test
    public void shouldCompareTo() {
        assertThat(TEN.compareTo(TWO)).isEqualTo(1);
        assertThat(TEN.compareTo(of(10))).isEqualTo(0);
        assertThat(TEN.compareTo(TWENTY)).isEqualTo(-1);
    }

    @Test
    public void shouldEqual() {
        assertThat(TEN.equals(TEN_STRING)).isFalse();
        assertThat(TEN.equals(TWENTY)).isFalse();
        assertThat(TEN.equals(TEN)).isTrue();
        assertThat(TEN.equals(of(10))).isTrue();
    }

    @Test
    public void shouldHashCode() {
        assertThat(TEN.hashCode()).isEqualTo(TEN_HASHCODE);
    }
}