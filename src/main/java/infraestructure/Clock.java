package infraestructure;

import com.google.inject.Singleton;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Singleton
public class Clock {

    public LocalDate getLastWorkingDate(final LocalDate today) {
        return isWeekend(today.getDayOfWeek())
                ? getPreviousWorkingDay(today)
                : today;
    }

    private boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private LocalDate getPreviousWorkingDay(final LocalDate today) {
        return today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
    }
}
