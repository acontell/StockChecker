package services;

import domain.Amount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import parsers.StockParser;
import retrievers.StockRetriever;

import static domain.Amount.of;
import static fixtures.PortFolioFixture.STOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static services.PriceService.ERROR_AMOUNT;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    private static final Amount CLOSING_PRICE = of(5.0);

    @Mock
    private StockParser stockParser;
    @Mock
    private StockRetriever stockRetriever;

    private PriceService priceService;

    @Before
    public void setUp() {
        given(this.stockRetriever.getStockInfo(STOCK)).willReturn(null);
        given(this.stockParser.getClosingPrice(null)).willReturn(CLOSING_PRICE);
        this.priceService = new PriceService(this.stockParser, this.stockRetriever);
        this.priceService.millisecondsBetweenTries = 1;
    }

    @Test
    public void shouldReturnClosingPrice() {
        assertThat(this.priceService.getLastClosingPrice(STOCK)).isEqualTo(CLOSING_PRICE);
    }

    @Test
    public void shouldReturnErrorAmount() {
        given(this.stockRetriever.getStockInfo(STOCK)).willThrow(new RuntimeException());
        assertThat(this.priceService.getLastClosingPrice(STOCK)).isEqualTo(ERROR_AMOUNT);
    }
}