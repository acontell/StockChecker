package services;

import converters.PortfolioConverter;
import domain.Amount;
import domain.models.Stock;
import infraestructure.Input;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static domain.Amount.of;
import static fixtures.PortFolioFixture.PORT_FOLIO;
import static fixtures.PortFolioFixture.STOCK;
import static fixtures.PortFolioFixture.STOCK_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceTest {

    private static final Amount CLOSING_PRICE = of(15.4);
    private static final Amount CLOSING_PRICE_1 = of(12.3);
    @Mock
    private StockService stockService;
    @Mock
    private PortfolioConverter portfolioConverter;
    @Mock
    private Input input;

    private PortfolioService portfolioService;

    @Before
    public void setUp() {
        given(this.input.getPortfolioFiles()).willReturn(Collections.emptyList());
        given(this.portfolioConverter.convert(Collections.emptyList())).willReturn(Collections.singletonList(PORT_FOLIO));
        given(this.stockService.getLastClosingPrice(STOCK)).willReturn(CLOSING_PRICE);
        given(this.stockService.getLastClosingPrice(STOCK_1)).willReturn(CLOSING_PRICE_1);
        this.portfolioService = new PortfolioService(this.stockService, this.portfolioConverter, this.input);
    }

    @Test
    public void shouldUpdateWithClosingPrices() {
        final List<Stock> stocks = this.portfolioService.getPortFolios().get(0).getStocks();
        assertThat(stocks.get(0).getPrice()).isEqualTo(CLOSING_PRICE);
        assertThat(stocks.get(1).getPrice()).isEqualTo(CLOSING_PRICE_1);
    }
}