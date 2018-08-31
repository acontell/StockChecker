package view.tables;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import static fixtures.PortFolioFixture.PORT_FOLIO;
import static fixtures.PortFolioFixture.STOCK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static view.tables.StockStatusTable.COLUMN_NAMES;
import static view.tables.StockStatusTable.PERFORMANCE_COLUMN_START;
import static view.tables.StockStatusTable.STOCKS_LABEL;
import static view.tables.StockStatusTable.STOCKS_LABEL_FONT;
import static view.tables.StockStatusTable.TABLE_DIMENSION;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioTableTest {

    private static final Object[] EXPECTED_ROW = {STOCK.getName(), STOCK.getStockBuyingPrice(), STOCK.getExpectedPrice(), STOCK.getPrice(), STOCK.getDiffPaidExpected(), STOCK.getDiffPaidClosing()};
    private static final Object[] EXPECTED_LAST_ROW = {null, null, null, "AVERAGE:", PORT_FOLIO.getExpectedTotalPercentage(), PORT_FOLIO.getTotalPercentage()};
    private static final Object[][] TABLE = {EXPECTED_ROW, EXPECTED_ROW, EXPECTED_ROW, EXPECTED_ROW, EXPECTED_LAST_ROW};
    private final PortfolioTable stockStatusTable = new StockStatusTable();

    @Mock
    private TableCellRenderer tableCellRenderer;
    @Mock
    private Component component;

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
        assertThat(this.stockStatusTable.getTable(PORT_FOLIO)).isNotNull();
    }

    @Test
    public void shouldReturnCustomJTable() {
        final JTable jTable = this.stockStatusTable.buildTable(TABLE, COLUMN_NAMES);
        given(this.tableCellRenderer.getTableCellRendererComponent(any(), any(), anyBoolean(), anyBoolean(), anyInt(), anyInt()))
                .willReturn(this.component);
        given(this.component.getFont()).willReturn(mock(Font.class));
        assertThat(jTable.prepareRenderer(this.tableCellRenderer, 0, 0)).isNotNull();
        assertThat(jTable.prepareRenderer(this.tableCellRenderer, 4, 4)).isNotNull();
    }
}