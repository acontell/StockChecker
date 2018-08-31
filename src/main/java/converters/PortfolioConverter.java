package converters;

import java.util.List;

import static java.util.stream.Collectors.toList;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.models.PortFolio;
import domain.models.Stock;
import deserializers.StockDeserializer;

@Singleton
public class PortfolioConverter {
    private final JsonDeserializer<Stock> jsonDeserializer;

    @Inject
    public PortfolioConverter(final StockDeserializer stockDeserializer) {
        this.jsonDeserializer = stockDeserializer.getStockDeserializer();
    }

    public List<PortFolio> convert(final List<String> portfolios) {
        return portfolios.stream()
                .map(this::toPortfolio)
                .collect(toList());
    }

    private PortFolio toPortfolio(final String json) {
        return new GsonBuilder()
                .registerTypeAdapter(Stock.class, this.jsonDeserializer)
                .create()
                .fromJson(json, PortFolio.class);
    }
}
