package services;

import domain.Amount;
import domain.models.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static domain.Amount.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static services.PriceService.ERROR_AMOUNT;
import static services.StockWrapper.DEFAULT_NUMBER_OF_FAILS;
import static services.StockWrapper.MAXIMUM_RETRIES;


@RunWith(MockitoJUnitRunner.class)
public class StockWrapperTest {

    private static final Amount CLOSING_PRICE = of(12.0);
    private static int STARTING_NUMBER_OF_FAILS = 3;

    @Mock
    private Stock stock;

    private StockWrapper stockWrapper;

    @Before
    public void setUp() {
        given(this.stock.getPrice()).willReturn(CLOSING_PRICE);
        this.stockWrapper = new StockWrapper(this.stock, STARTING_NUMBER_OF_FAILS);
    }

    @Test
    public void beanTest() {
        final StockWrapper stockWrapper = new StockWrapper(this.stock);
        assertThat(stockWrapper.getStock()).isEqualTo(this.stock);
        assertThat(stockWrapper.getNumberOfFails()).isEqualTo(DEFAULT_NUMBER_OF_FAILS);
    }

    @Test
    public void shouldReturnErrorWhenStockHasWrongPriceAndNoExceedsMaximum() {
        given(this.stock.getPrice()).willReturn(ERROR_AMOUNT);
        assertThat(this.stockWrapper.hasErrors()).isTrue();
    }

    @Test
    public void shouldReturnNoErrorWhenStockHasCorrectPrice() {
        assertThat(this.stockWrapper.hasErrors()).isFalse();
    }

    @Test
    public void shouldReturnNoErrorWhenAmountErrorButNumberOfFailsExceedsMaximum() {
        given(this.stock.getPrice()).willReturn(ERROR_AMOUNT);
        this.stockWrapper = new StockWrapper(this.stock, MAXIMUM_RETRIES);
        assertThat(this.stockWrapper.hasErrors()).isFalse();
    }

    @Test
    public void shouldAddOneToFailsWhenErrorInPrice() {
        this.stockWrapper.update(ERROR_AMOUNT);
        assertThat(this.stockWrapper.getNumberOfFails()).isEqualTo(STARTING_NUMBER_OF_FAILS + 1);
    }

    @Test
    public void shouldAddZeroToFailsWhenNoErrorInPrice() {
        this.stockWrapper.update(CLOSING_PRICE);
        assertThat(this.stockWrapper.getNumberOfFails()).isEqualTo(STARTING_NUMBER_OF_FAILS);
    }

    @Test
    public void shouldUpdatePriceInStock() {
        this.stockWrapper.update(CLOSING_PRICE);
        verify(this.stock).setPrice(CLOSING_PRICE);
    }
}
