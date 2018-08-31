package deserializers;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;
import domain.models.Stock;

import static domain.Amount.ZERO;
import static domain.Amount.of;

@Singleton
public class StockDeserializer {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String N_STOCKS = "nStocks";
    private static final String STOCK_BUYING_PRICE = "stockBuyingPrice";
    private static final String PRICE_PAID_AFTER_TAXES = "pricePaidAfterTaxes";
    private static final String EXPECTED_PRICE = "expectedPrice";

    private static final JsonDeserializer<Stock> STOCK_DESERIALIZER = (jsonElement, type, jsonDeserializationContext) ->
            getStock(jsonElement.getAsJsonObject());

    private static Stock getStock(final JsonObject jsonObject) {
        return new Stock(
                jsonObject.get(ID).getAsString(),
                ZERO,
                jsonObject.get(NAME).getAsString(),
                of(jsonObject.get(N_STOCKS).getAsInt()),
                of(jsonObject.get(STOCK_BUYING_PRICE).getAsBigDecimal()),
                of(jsonObject.get(PRICE_PAID_AFTER_TAXES).getAsBigDecimal()),
                of(jsonObject.get(EXPECTED_PRICE).getAsBigDecimal())
        );
    }

    public JsonDeserializer<Stock> getStockDeserializer() {
        return STOCK_DESERIALIZER;
    }
}
