package view.tables;

import org.junit.Test;

import static fixtures.PortFolioFixture.PORT_FOLIO;
import static fixtures.PortFolioFixture.STOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static view.tables.FiscalStatusTable.COLUMN_NAMES;
import static view.tables.FiscalStatusTable.PERFORMANCE_COLUMN_START;
import static view.tables.FiscalStatusTable.STOCKS_LABEL;
import static view.tables.FiscalStatusTable.STOCKS_LABEL_FONT;
import static view.tables.FiscalStatusTable.TABLE_DIMENSION;

public class FiscalStatusTableTest {

    private static final Object[] EXPECTED_ROW = {STOCK.getName(), STOCK.getPricePaidAfterTaxes(), STOCK.getCurrentValue(), STOCK.getAbsoluteGain()};
    private static final Object[] EXPECTED_LAST_ROW = {"TOTALS:", PORT_FOLIO.getTotalInvested(), PORT_FOLIO.getCurrentValueTotal(), PORT_FOLIO.getAbsoluteTotalGained()};
    private final FiscalStatusTable fiscalStatusTable = new FiscalStatusTable();

    @Test
    public void beanTest() {
        assertThat(this.fiscalStatusTable.getColumnLength()).isEqualTo(COLUMN_NAMES.length);
        assertThat(this.fiscalStatusTable.buildFirstRow()).isEqualTo(COLUMN_NAMES);
        assertThat(this.fiscalStatusTable.buildRow(STOCK)).isEqualTo(EXPECTED_ROW);
        assertThat(this.fiscalStatusTable.buildLastRow(PORT_FOLIO)).isEqualTo(EXPECTED_LAST_ROW);
        assertThat(this.fiscalStatusTable.getTableDimension()).isEqualTo(TABLE_DIMENSION);
        assertThat(this.fiscalStatusTable.getLabelText()).isEqualTo(STOCKS_LABEL);
        assertThat(this.fiscalStatusTable.getLabelFont()).isEqualTo(STOCKS_LABEL_FONT);
        assertThat(this.fiscalStatusTable.isPerformanceDataColumn(PERFORMANCE_COLUMN_START)).isFalse();
        assertThat(this.fiscalStatusTable.isTotalsCell(-1, 0, 0)).isFalse();
        assertThat(this.fiscalStatusTable.isTotalsCell(0, 1, 0)).isFalse();
        assertThat(this.fiscalStatusTable.isTotalsCell(0, 0, 0)).isTrue();
    }
}