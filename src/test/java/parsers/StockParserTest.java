package parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.Amount;
import infraestructure.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class StockParserTest {
    private static final LocalDate DAY = of(2018, 8, 24);
    private static final Amount CLOSING_PRICE = Amount.of(28.54);
    private static final String JSON_DATA = "{\n" +
            "    \"Time Series (Daily)\": {\n" +
            "        \"2018-08-24\": {\n" +
            "            \"1. open\": \"28.3300\",\n" +
            "            \"2. high\": \"28.6200\",\n" +
            "            \"3. low\": \"28.3300\",\n" +
            "            \"4. close\": \"" + CLOSING_PRICE + "\",\n" +
            "            \"5. volume\": \"1682263\"\n" +
            "        }" +
            "   }" +
            "}";
    private static final JsonObject JSON_OBJECT = new JsonParser()
            .parse(JSON_DATA)
            .getAsJsonObject();

    @Mock
    private Clock clock;

    private StockParser stockParser;

    @Before
    public void setUp() {
        given(this.clock.getLastWorkingDate(any(LocalDate.class))).willReturn(DAY);
        this.stockParser = new StockParser(this.clock);
    }

    @Test
    public void shouldReturnClosingPrice() {
        assertThat(this.stockParser.getClosingPrice(JSON_OBJECT)).isEqualTo(CLOSING_PRICE);
    }
}