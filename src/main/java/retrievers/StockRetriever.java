package retrievers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import infraestructure.Input;
import domain.models.Stock;

@Singleton
public class StockRetriever {
    private final Input input;

    @Inject
    public StockRetriever(final Input input) {
        this.input = input;
    }

    public JsonObject getStockInfo(final Stock stock) {
        return new JsonParser()
                .parse(this.input.retrieveStock(stock.getId()))
                .getAsJsonObject();
    }
}
