package domain.models;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static fixtures.PortFolioFixture.ABSOLUTE_TOTAL_GAINED;
import static fixtures.PortFolioFixture.CURRENT_VALUE_TOTAL;
import static fixtures.PortFolioFixture.EXPECTED_TOTAL_PERCENTAGE;
import static fixtures.PortFolioFixture.OWNER;
import static fixtures.PortFolioFixture.PORT_FOLIO;
import static fixtures.PortFolioFixture.STOCKS;
import static fixtures.PortFolioFixture.TOTAL_INVESTED;
import static fixtures.PortFolioFixture.TOTAL_PERCENTAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class PortFolioTest {

    @Test
    public void beanTest() {
        assertThat(PORT_FOLIO.getStocks()).isEqualTo(STOCKS);
        assertThat(PORT_FOLIO.getOwner()).isEqualTo(OWNER);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(PortFolio.class).verify();
    }

    @Test
    public void shouldCalculateTotalInvested() {
        assertThat(PORT_FOLIO.getTotalInvested()).isEqualTo(TOTAL_INVESTED);
    }

    @Test
    public void shouldCalculateAbsoluteTotalGained() {
        assertThat(PORT_FOLIO.getAbsoluteTotalGained()).isEqualTo(ABSOLUTE_TOTAL_GAINED);
    }

    @Test
    public void shouldCalculateCurrentValueTotal() {
        assertThat(PORT_FOLIO.getCurrentValueTotal()).isEqualTo(CURRENT_VALUE_TOTAL);
    }

    @Test
    public void shouldCalculateTotalPercentage() {
        assertThat(PORT_FOLIO.getTotalPercentage()).isEqualTo(TOTAL_PERCENTAGE);
    }

    @Test
    public void shouldCalculateExpectedTotalPercentage() {
        assertThat(PORT_FOLIO.getExpectedTotalPercentage()).isEqualTo(EXPECTED_TOTAL_PERCENTAGE);
    }
}