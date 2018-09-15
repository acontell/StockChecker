package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import converters.PortfolioConverter;
import domain.models.PortFolio;
import infraestructure.Input;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Singleton
public class PortfolioService {
    private final PortfolioConverter portfolioConverter;
    private final PriceService priceService;
    private final Input input;

    @Inject
    public PortfolioService(final PriceService priceService,
                            final PortfolioConverter portfolioConverter,
                            final Input input) {
        this.portfolioConverter = portfolioConverter;
        this.priceService = priceService;
        this.input = input;
    }

    public List<PortFolio> getPortFolios() {
        return updatePortfolios(this.portfolioConverter.convert(this.input.getPortfolioFiles()));
    }

    private List<PortFolio> updatePortfolios(final List<PortFolio> portFolios) {
        portFolios.forEach(this::updatePortFolio);
        return portFolios;
    }

    private void updatePortFolio(final PortFolio portFolio) {
        tryUpdate(getWrappedStocks(portFolio));
    }

    private List<StockWrapper> getWrappedStocks(final PortFolio portFolio) {
        return portFolio.getStocks()
                .stream()
                .map(StockWrapper::new)
                .collect(toList());
    }

    private void tryUpdate(final List<StockWrapper> stockWrappers) {
        if (!stockWrappers.isEmpty()) {
            tryUpdate(updateList(stockWrappers));
        }
    }

    private List<StockWrapper> updateList(final List<StockWrapper> stockWrappers) {
        return stockWrappers.stream()
                .map(this::update)
                .filter(StockWrapper::hasErrors)
                .collect(toList());
    }

    private StockWrapper update(final StockWrapper stockWrapper) {
        return stockWrapper.update(this.priceService.getLastClosingPrice(stockWrapper.getStock()));
    }
}
