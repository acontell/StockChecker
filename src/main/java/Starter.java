import com.google.inject.Inject;
import com.google.inject.Singleton;
import services.PortfolioService;
import view.PortfolioTableFormat;

@Singleton
class Starter {
    private final PortfolioService portfolioService;
    private final PortfolioTableFormat portfolioTableFormat;

    @Inject
    Starter(final PortfolioService portfolioService,
            final PortfolioTableFormat portfolioTableFormat) {
        this.portfolioService = portfolioService;
        this.portfolioTableFormat = portfolioTableFormat;
    }

    void start() {
        this.portfolioTableFormat
                .getJFrame(this.portfolioService.getPortFolios())
                .setVisible(true);
    }
}
