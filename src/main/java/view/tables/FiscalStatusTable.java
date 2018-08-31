package view.tables;

import domain.models.PortFolio;
import domain.models.Stock;

import java.awt.*;

public class FiscalStatusTable extends PortfolioTable {

    static final String STOCKS_LABEL = "FISCAL STATUS";
    static final Font STOCKS_LABEL_FONT = new Font("Serif", Font.BOLD, 24);
    static final String[] COLUMN_NAMES = {"NAME", "PAID", "CURRENT VALUE", "DIFFERENCE"};
    static final Dimension TABLE_DIMENSION = new Dimension(500, 250);
    static final int PERFORMANCE_COLUMN_START = 2;

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
        return new Object[]{s.getName(), s.getPricePaidAfterTaxes(), s.getCurrentValue(), s.getAbsoluteGain()};
    }

    @Override
    Object[] buildLastRow(final PortFolio portFolio) {
        return new Object[]{"TOTALS:", portFolio.getTotalInvested(), portFolio.getCurrentValueTotal(), portFolio.getAbsoluteTotalGained()};
    }

    @Override
    Dimension getTableDimension() {
        return TABLE_DIMENSION;
    }

    @Override
    String getLabelText() {
        return STOCKS_LABEL;
    }

    @Override
    Font getLabelFont() {
        return STOCKS_LABEL_FONT;
    }

    @Override
    boolean isPerformanceDataColumn(final int columnNumber) {
        return columnNumber > PERFORMANCE_COLUMN_START;
    }

    @Override
    boolean isTotalsCell(final int column, final int row, final int lastRowNumber) {
        return column == 0 && row == lastRowNumber;
    }
}
