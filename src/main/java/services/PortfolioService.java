package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import converters.PortfolioConverter;
import domain.models.PortFolio;
import domain.models.Stock;
import infraestructure.Input;

import java.util.List;

@Singleton
public class PortfolioService {
    private final PortfolioConverter portfolioConverter;
    private final StockService stockService;
    private final Input input;

    @Inject
    public PortfolioService(final StockService stockService,
                            final PortfolioConverter portfolioConverter,
                            final Input input) {
        this.portfolioConverter = portfolioConverter;
        this.stockService = stockService;
        this.input = input;
    }

    public List<PortFolio> getPortFolios() {
        return updatePortfolios(this.portfolioConverter.convert(this.input.getPortfolioFiles()));
    }

    private List<PortFolio> updatePortfolios(final List<PortFolio> portFolios) {
        portFolios.forEach(this::updateStocks);
        return portFolios;
    }

    private void updateStocks(final PortFolio portFolio) {
        portFolio.getStocks().forEach(this::updateStock);
    }

    private void updateStock(final Stock stock) {
        stock.setPrice(this.stockService.getLastClosingPrice(stock));
    }
}
