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
import static services.PriceService.ERROR_AMOUNT;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceTest {

    private static final Amount CLOSING_PRICE = of(15.4);
    private static final Amount CLOSING_PRICE_1 = of(12.3);
    @Mock
    private PriceService priceService;
    @Mock
    private PortfolioConverter portfolioConverter;
    @Mock
    private Input input;

    private PortfolioService portfolioService;

    @Before
    public void setUp() {
        givenLastClosingPrice();
        given(this.input.getPortfolioFiles()).willReturn(Collections.emptyList());
        given(this.portfolioConverter.convert(Collections.emptyList())).willReturn(Collections.singletonList(PORT_FOLIO));
        this.portfolioService = new PortfolioService(this.priceService, this.portfolioConverter, this.input);
    }

    private void givenLastClosingPrice() {
        given(this.priceService.getLastClosingPrice(STOCK)).willReturn(CLOSING_PRICE);
        given(this.priceService.getLastClosingPrice(STOCK_1)).willReturn(CLOSING_PRICE_1);
    }

    @Test
    public void shouldUpdateWithClosingPrices() {
        assertPricesAreUpdated(getStocks());
    }

    private List<Stock> getStocks() {
        return this.portfolioService.getPortFolios().get(0).getStocks();
    }

    private void assertPricesAreUpdated(final List<Stock> stocks) {
        assertThat(getFirstStock().getPrice()).isEqualTo(CLOSING_PRICE);
        assertThat(stocks.get(1).getPrice()).isEqualTo(CLOSING_PRICE_1);
    }

    @Test
    public void shouldRetryRetrievingClosingPriceWhenError() {
        given(this.priceService.getLastClosingPrice(STOCK)).willReturn(ERROR_AMOUNT, ERROR_AMOUNT, CLOSING_PRICE);
        given(this.priceService.getLastClosingPrice(STOCK_1)).willReturn(ERROR_AMOUNT, ERROR_AMOUNT, ERROR_AMOUNT, ERROR_AMOUNT, CLOSING_PRICE_1);
        assertPricesAreUpdated(getStocks());
    }

    @Test
    public void shouldRetryUpToFiveRetries() {
        given(this.priceService.getLastClosingPrice(STOCK)).willReturn(ERROR_AMOUNT, ERROR_AMOUNT, ERROR_AMOUNT, ERROR_AMOUNT, ERROR_AMOUNT, CLOSING_PRICE);
        assertThat(getFirstStock().getPrice()).isEqualTo(ERROR_AMOUNT);
    }

    private Stock getFirstStock() {
        return getStocks().get(0);
    }
}