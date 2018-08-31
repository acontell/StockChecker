package view;

import org.junit.Test;

import static fixtures.PortFolioFixture.PORT_FOLIO;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class PortfolioTableFormatTest {
    private final PortfolioTableFormat portfolioTableFormat = new PortfolioTableFormat();

    @Test
    public void shouldCreateJFrame() {
        assertThat(this.portfolioTableFormat.getJFrame(singletonList(PORT_FOLIO))).isNotNull();
    }
}
