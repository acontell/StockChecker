package view;

import com.google.inject.Singleton;
import domain.models.PortFolio;
import view.tables.FiscalStatusTable;
import view.tables.PortfolioTable;
import view.tables.StockStatusTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Singleton
public class PortfolioTableFormat {
    private static final String TITLE = "Stock's checker";
    private static final GridLayout GRID_LAYOUT = new GridLayout(1, 1);

    public JFrame getJFrame(final List<PortFolio> portFolios) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(buildTabPanel(portFolios));
        frame.pack();
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel buildTabPanel(final List<PortFolio> portFolios) {
        JPanel container = new JPanel(GRID_LAYOUT);
        container.add(buildTabs(portFolios));
        return container;
    }

    private JTabbedPane buildTabs(final List<PortFolio> portFolios) {
        JTabbedPane tabbedPane = new JTabbedPane();
        portFolios.forEach(portFolio -> addPortfolioTab(tabbedPane, portFolio));
        return tabbedPane;
    }

    private void addPortfolioTab(final JTabbedPane tabbedPane, final PortFolio portFolio) {
        tabbedPane.addTab(portFolio.getOwner(), null, buildJPanel(portFolio, new StockStatusTable(), new FiscalStatusTable()), null);
    }

    private JPanel buildJPanel(final PortFolio portFolio, final PortfolioTable... portfolioTables) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        for (PortfolioTable portFolioTable : portfolioTables) {
            addToJPanel(panel, portFolioTable.getTable(portFolio));
        }
        return panel;
    }

    private void addToJPanel(final JPanel panel, final JComponent[]... matrix) {
        for (JComponent[] components : matrix) {
            for (JComponent component : components) {
                panel.add(component);
            }
        }
    }
}
