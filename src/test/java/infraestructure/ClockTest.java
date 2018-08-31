package infraestructure;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;


public class ClockTest {
    private static final LocalDate FRIDAY = of(2018, 8, 24);
    private static final LocalDate SATURDAY = of(2018, 8, 25);
    private static final LocalDate SUNDAY = of(2018, 8, 26);
    private final Clock clock = new Clock();

    @Test
    public void shouldReturnFridayOnSaturday() {
        assertThat(this.clock.getLastWorkingDate(SATURDAY)).isEqualTo(FRIDAY);
    }

    @Test
    public void shouldReturnFridayOnSunday() {
        assertThat(this.clock.getLastWorkingDate(SUNDAY)).isEqualTo(FRIDAY);
    }

    @Test
    public void shouldReturnTodayWhenNoWeekend() {
        assertThat(this.clock.getLastWorkingDate(FRIDAY)).isEqualTo(FRIDAY);
    }
}