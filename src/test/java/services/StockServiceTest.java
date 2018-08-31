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
import static services.StockService.ERROR_AMOUNT;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    private static final Amount CLOSING_PRICE = of(5.0);

    @Mock
    private StockParser stockParser;
    @Mock
    private StockRetriever stockRetriever;

    private StockService stockService;

    @Before
    public void setUp() {
        given(this.stockRetriever.getStockInfo(STOCK)).willReturn(null);
        given(this.stockParser.getClosingPrice(null)).willReturn(CLOSING_PRICE);
        this.stockService = new StockService(this.stockParser, this.stockRetriever);
        this.stockService.millisecondsBetweenTries = 1;
    }

    @Test
    public void shouldReturnClosingPrice() {
        assertThat(this.stockService.getLastClosingPrice(STOCK)).isEqualTo(CLOSING_PRICE);
    }

    @Test
    public void shouldReturnErrorAmount() {
        given(this.stockRetriever.getStockInfo(STOCK)).willThrow(new RuntimeException());
        assertThat(this.stockService.getLastClosingPrice(STOCK)).isEqualTo(ERROR_AMOUNT);
    }
}