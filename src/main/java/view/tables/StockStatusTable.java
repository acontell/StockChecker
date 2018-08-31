package view.tables;

import domain.models.PortFolio;
import domain.models.Stock;

import java.awt.*;

public class StockStatusTable extends PortfolioTable {

    static final String STOCKS_LABEL = "CURRENT STOCK'S STATUS";
    static final Font STOCKS_LABEL_FONT = new Font("Serif", Font.BOLD, 24);
    static final String[] COLUMN_NAMES = {"NAME", "WORST PAID PRICE", "EXPECTED", "CLOSING PRICE", "% PAID-EXP.", "% PAID-CLOS."};
    static final Dimension TABLE_DIMENSION = new Dimension(500, 250);
    static final int PERFORMANCE_COLUMN_START = 3;

    @Override
    int getColumnLength() {
        return COLUMN_NAMES.length;
    }

    @Override
    Object[] buildFirstRow() {
        return COLUMN_NAMES;
    }

    @Override
    Object[] buildRow(final Stock s) {
        return new Object[]{s.getName(), s.getStockBuyingPrice(), s.getExpectedPrice(), s.getPrice(), s.getDiffPaidExpected(), s.getDiffPaidClosing()};
    }

    @Override
    Object[] buildLastRow(final PortFolio portFolio) {
        return new Object[]{null, null, null, "AVERAGE:", portFolio.getExpectedTotalPercentage(), portFolio.getTotalPercentage()};
    }

    @Override
    Dimension getTableDimension() {
        return TABLE_DIMENSION;
    }

    @Override
    boolean isPerformanceDataColumn(final int columnNumber) {
        return columnNumber > PERFORMANCE_COLUMN_START;
    }

    @Override
    boolean isTotalsCell(final int column, final int row, final int lastRowNumber) {
        return column == PERFORMANCE_COLUMN_START && row == lastRowNumber;
    }

    @Override
    String getLabelText() {
        return STOCKS_LABEL;
    }

    @Override
    Font getLabelFont() {
        return STOCKS_LABEL_FONT;
    }
}
