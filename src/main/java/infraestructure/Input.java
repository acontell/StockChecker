package infraestructure;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Singleton
public class Input {
    static final String ALPHA_VANTAGE_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&apikey=yourkey&symbol=";
    static final String DIRECTORY_NAME = "portfolios";
    private final DataRetriever dataRetriever;

    @Inject
    public Input(final DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public List<String> getPortfolioFiles() {
        try {
            return this.dataRetriever.readFiles(getStockFilesPath());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getStockFilesPath() {
        return this.dataRetriever.getResourcePath(DIRECTORY_NAME);
    }

    public String retrieveStock(final String stockId) {
        try {
            return this.dataRetriever.readUrl(this.dataRetriever.getUrl(ALPHA_VANTAGE_URL + stockId));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
