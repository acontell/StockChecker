package view.tables;

import domain.Amount;
import domain.models.PortFolio;
import domain.models.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public abstract class PortfolioTable extends JPanel {

    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color UNDER_PERFORMANCE_COLOR = new Color(255, 102, 102);
    private static final Color OVER_PERFORMANCE_COLOR = new Color(102, 255, 102);

    abstract int getColumnLength();

    abstract Object[] buildFirstRow();

    abstract Object[] buildRow(Stock stock);

    abstract Object[] buildLastRow(PortFolio portFolio);

    abstract Dimension getTableDimension();

    abstract String getLabelText();

    abstract Font getLabelFont();

    abstract boolean isPerformanceDataColumn(final int columnNumber);

    abstract boolean isTotalsCell(final int column, final int row, final int lastRowNumber);

    public JComponent[] getTable(final PortFolio portFolio) {
        return new JComponent[]{addLabel(), addScrollPane(setTableProperties(buildTable(buildTableContents(portFolio), buildFirstRow())))};
    }

    private Object[][] buildTableContents(final PortFolio portFolio) {
        final List<Stock> stocks = portFolio.getStocks();
        final Object[][] stcks = new Object[stocks.size() + 1][getColumnLength()];
        for (int i = 0; i < stocks.size(); i++) {
            stcks[i] = buildRow(stocks.get(i));
        }
        stcks[stocks.size()] = buildLastRow(portFolio);
        return stcks;
    }

    private JScrollPane addScrollPane(final JTable table) {
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(getTableDimension());
        return jScrollPane;
    }

    private JTable setTableProperties(final JTable table) {
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.changeSelection(0, 0, false, false);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        return table;
    }

    private JLabel addLabel() {
        JLabel label = new JLabel(getLabelText());
        label.setFont(getLabelFont());
        return label;
    }

    JTable buildTable(final Object[][] data, final Object[] columnNames) {
        return new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                Object valueAt = getModel().getValueAt(convertRowIndexToModel(row), convertColumnIndexToModel(column));
                c.setBackground(isPerformanceDataColumn(column) ? getBigDecimalColor((Amount) valueAt) : DEFAULT_COLOR);
                c.setFont(c.getFont().deriveFont(isTotalsCell(column, row, data.length - 1) ? Font.BOLD : Font.PLAIN));
                return c;
            }
        };
    }

    private Color getBigDecimalColor(final Amount b) {
        return b.compareTo(Amount.ZERO) > 0 ? OVER_PERFORMANCE_COLOR : UNDER_PERFORMANCE_COLOR;
    }
}
