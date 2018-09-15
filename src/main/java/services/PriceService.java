package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.Amount;
import domain.models.Stock;
import parsers.StockParser;
import retrievers.StockRetriever;

@Singleton
class PriceService {
    private static final int MAX_RETRIES = 5;
    static final Amount ERROR_AMOUNT = Amount.ZERO;

    private final StockParser stockParser;
    private final StockRetriever stockRetriever;
    int millisecondsBetweenTries;

    @Inject
    PriceService(final StockParser stockParser, final StockRetriever stockRetriever) {
        this.stockParser = stockParser;
        this.stockRetriever = stockRetriever;
        this.millisecondsBetweenTries = 750;
    }

    Amount getLastClosingPrice(final Stock stock) {
        return tryAndGetClosingPrice(stock, 0);
    }

    private Amount tryAndGetClosingPrice(final Stock stock, final int numberOfTries) {
        return numberOfTries < MAX_RETRIES ? retrievePrice(stock, numberOfTries) : ERROR_AMOUNT;
    }

    private Amount retrievePrice(final Stock stock, final int numberOfTries) {
        final Amount closingPrice = getClosingPrice(stock);
        return ERROR_AMOUNT.equals(closingPrice)
                ? waitAndTryAgain(stock, numberOfTries)
                : closingPrice;
    }

    private Amount getClosingPrice(final Stock stock) {
        try {
            return this.stockParser.getClosingPrice(this.stockRetriever.getStockInfo(stock));
        } catch (final RuntimeException e) {
            return ERROR_AMOUNT;
        }
    }

    private Amount waitAndTryAgain(final Stock stock, final int numberOfTries) {
        sleep(this.millisecondsBetweenTries * numberOfTries);
        return tryAndGetClosingPrice(stock, numberOfTries + 1);
    }

    private void sleep(final int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (final InterruptedException e) {
            System.out.println("WRONG!");
        }
    }
}
