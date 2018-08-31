package view.tables;

import org.junit.Test;

import static fixtures.PortFolioFixture.PORT_FOLIO;
import static fixtures.PortFolioFixture.STOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static view.tables.StockStatusTable.COLUMN_NAMES;
import static view.tables.StockStatusTable.PERFORMANCE_COLUMN_START;
import static view.tables.StockStatusTable.STOCKS_LABEL;
import static view.tables.StockStatusTable.STOCKS_LABEL_FONT;
import static view.tables.StockStatusTable.TABLE_DIMENSION;

public class StockStatusTableTest {

    private static final Object[] EXPECTED_ROW = {STOCK.getName(), STOCK.getStockBuyingPrice(), STOCK.getExpectedPrice(), STOCK.getPrice(), STOCK.getDiffPaidExpected(), STOCK.getDiffPaidClosing()};
    private static final Object[] EXPECTED_LAST_ROW = {null, null, null, "AVERAGE:", PORT_FOLIO.getExpectedTotalPercentage(), PORT_FOLIO.getTotalPercentage()};
    private final StockStatusTable stockStatusTable = new StockStatusTable();

    @Test
    public void beanTest() {
        assertThat(this.stockStatusTable.getColumnLength()).isEqualTo(COLUMN_NAMES.length);
        assertThat(this.stockStatusTable.buildFirstRow()).isEqualTo(COLUMN_NAMES);
        assertThat(this.stockStatusTable.buildRow(STOCK)).isEqualTo(EXPECTED_ROW);
        assertThat(this.stockStatusTable.buildLastRow(PORT_FOLIO)).isEqualTo(EXPECTED_LAST_ROW);
        assertThat(this.stockStatusTable.getTableDimension()).isEqualTo(TABLE_DIMENSION);
        assertThat(this.stockStatusTable.getLabelText()).isEqualTo(STOCKS_LABEL);
        assertThat(this.stockStatusTable.getLabelFont()).isEqualTo(STOCKS_LABEL_FONT);
        assertThat(this.stockStatusTable.isPerformanceDataColumn(PERFORMANCE_COLUMN_START)).isFalse();
        assertThat(this.stockStatusTable.isTotalsCell(-1, 0, 0)).isFalse();
        assertThat(this.stockStatusTable.isTotalsCell(0, 1, 0)).isFalse();
        assertThat(this.stockStatusTable.isTotalsCell(PERFORMANCE_COLUMN_START, 0, 0)).isTrue();
    }
}