package fixtures;

import domain.Amount;
import domain.models.PortFolio;
import domain.models.Stock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterFixture {
    private static final Amount PRICE_PAID_AFTER_TAXES = Amount.of(100);
    private static final String STOCK_ID = "SAN.MC";
    private static final String STOCK_NAME = "Santander";
    private static final Amount N_STOCKS = Amount.of(60);
    private static final Amount STOCK_BUYING_PRICE = Amount.of(114.50);
    private static final Amount EXPECTED_PRICE = Amount.of(116);
    private static final String PORTFOLIO_OWNER = "Sample";
    private static final int SECOND = 1;
    private static final int NUMBER_OF_STOCKS = 2;
    protected static final String STOCK_JSON =
            "{" +
                    "\"id\": \"" + STOCK_ID + "\",\n" +
                    "  \"name\": \"" + STOCK_NAME + "\",\n" +
                    "  \"nStocks\": " + N_STOCKS + ",\n" +
                    "  \"stockBuyingPrice\": " + STOCK_BUYING_PRICE + ",\n" +
                    "  \"expectedPrice\": " + EXPECTED_PRICE + ",\n" +
                    "  \"pricePaidAfterTaxes\": " + PRICE_PAID_AFTER_TAXES +
                    "}";
    protected static final String PORTFOLIO_JSON = "{\n" +
            "    \"owner\": \"" + PORTFOLIO_OWNER + "\",\n" +
            "    \"stocks\": [\n" +
            "        {\n" +
            "            \"id\": \"BKIA.MC\",\n" +
            "            \"name\": \"Bankia\",\n" +
            "            \"nStocks\": 1500,\n" +
            "            \"stockBuyingPrice\": 1.4,\n" +
            "            \"expectedPrice\": 2.4,\n" +
            "            \"pricePaidAfterTaxes\": 500\n" +
            "        },\n"
            + STOCK_JSON +
            "    ]\n" +
            "}";


    protected void shouldConvertPortfolioWell(final PortFolio portFolio) {
        assertThat(portFolio.getOwner()).isEqualTo(PORTFOLIO_OWNER);
        shouldConvertStocksWell(portFolio.getStocks());
    }

    private void shouldConvertStocksWell(final List<Stock> stocks) {
        assertThat(stocks.size()).isEqualTo(NUMBER_OF_STOCKS);
        shouldConvertStockWell(stocks.get(SECOND));
    }

    protected void shouldConvertStockWell(final Stock stock) {
        assertThat(stock.getId()).isEqualTo(STOCK_ID);
        assertThat(stock.getName()).isEqualTo(STOCK_NAME);
        assertThat(stock.getNStocks()).isEqualTo(N_STOCKS);
        assertThat(stock.getStockBuyingPrice()).isEqualTo(STOCK_BUYING_PRICE);
        assertThat(stock.getExpectedPrice()).isEqualTo(EXPECTED_PRICE);
        assertThat(stock.getPrice()).isEqualTo(Amount.ZERO);
        assertThat(stock.getPricePaidAfterTaxes()).isEqualTo(PRICE_PAID_AFTER_TAXES);
    }
}
