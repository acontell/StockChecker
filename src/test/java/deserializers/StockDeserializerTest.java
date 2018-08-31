package deserializers;

import com.google.gson.GsonBuilder;
import domain.models.Stock;
import fixtures.ConverterFixture;
import org.junit.Test;

public class StockDeserializerTest extends ConverterFixture {
    private final StockDeserializer stockDeserializer = new StockDeserializer();

    @Test
    public void shouldDeserializeStockWell() {
        shouldConvertStockWell(toStock());
    }

    private Stock toStock() {
        return new GsonBuilder()
                .registerTypeAdapter(Stock.class, this.stockDeserializer.getStockDeserializer())
                .create()
                .fromJson(STOCK_JSON, Stock.class);
    }
}
