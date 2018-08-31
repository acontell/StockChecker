package domain.models;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static fixtures.PortFolioFixture.ABSOLUTE_GAIN;
import static fixtures.PortFolioFixture.BUYING_PRICE;
import static fixtures.PortFolioFixture.CURRENT_VALUE;
import static fixtures.PortFolioFixture.EXPECTED_PRICE;
import static fixtures.PortFolioFixture.ID;
import static fixtures.PortFolioFixture.NAME;
import static fixtures.PortFolioFixture.N_STOCKS;
import static fixtures.PortFolioFixture.PAID_AFTER_TAXES;
import static fixtures.PortFolioFixture.PERC_EXPECTED_TO_CLOSING;
import static fixtures.PortFolioFixture.PERC_PAID_TO_CLOSING;
import static fixtures.PortFolioFixture.PRICE;
import static fixtures.PortFolioFixture.STOCK;
import static org.assertj.core.api.Assertions.assertThat;

public class StockTest {

    @Test
    public void beanTest() {
        assertThat(STOCK.getId()).isEqualTo(ID);
        assertThat(STOCK.getPrice()).isEqualTo(PRICE);
        assertThat(STOCK.getName()).isEqualTo(NAME);
        assertThat(STOCK.getNStocks()).isEqualTo(N_STOCKS);
        assertThat(STOCK.getStockBuyingPrice()).isEqualTo(BUYING_PRICE);
        assertThat(STOCK.getPricePaidAfterTaxes()).isEqualTo(PAID_AFTER_TAXES);
        assertThat(STOCK.getExpectedPrice()).isEqualTo(EXPECTED_PRICE);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Stock.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void shouldCalculatePercentagePaidToClosingPrice() {
        assertThat(STOCK.getDiffPaidClosing()).isEqualTo(PERC_PAID_TO_CLOSING);
    }

    @Test
    public void shouldCalculatePercentageExpectedPriceToClosingPrice() {
        assertThat(STOCK.getDiffPaidExpected()).isEqualTo(PERC_EXPECTED_TO_CLOSING);
    }

    @Test
    public void shouldCalculateAbsoluteGain() {
        assertThat(STOCK.getAbsoluteGain()).isEqualTo(ABSOLUTE_GAIN);
    }

    @Test
    public void shouldCalculateCurrentValue() {
        assertThat(STOCK.getCurrentValue()).isEqualTo(CURRENT_VALUE);
    }
}