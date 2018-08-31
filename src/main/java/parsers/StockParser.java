package parsers;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.Amount;
import infraestructure.Clock;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import static java.time.LocalDate.now;

@Singleton
public class StockParser {
    private static final String TIME_SERIES_FIELD = "Time Series (Daily)";
    private static final String CLOSING_PRICE_FIELD = "4. close";

    private final Clock clock;

    @Inject
    public StockParser(final Clock clock) {
        this.clock = clock;
    }

    public Amount getClosingPrice(final JsonObject stockInfo) {
        return getTimeSeriesFieldFnc()
                .andThen(this::getTodayField)
                .andThen(this::getClosingPriceField)
                .apply(stockInfo);
    }

    private Function<JsonObject, JsonObject> getTimeSeriesFieldFnc() {
        return this::getTimeSeriesField;
    }

    private JsonObject getTimeSeriesField(final JsonObject stockInfo) {
        return stockInfo
                .get(TIME_SERIES_FIELD)
                .getAsJsonObject();
    }

    private JsonObject getTodayField(final JsonObject stockInfo) {
        return stockInfo
                .get(this.clock.getLastWorkingDate(now()).format(DateTimeFormatter.ISO_DATE))
                .getAsJsonObject();
    }

    private Amount getClosingPriceField(final JsonObject stockInfo) {
        return Amount.of(stockInfo
                .get(CLOSING_PRICE_FIELD)
                .getAsBigDecimal());
    }
}
