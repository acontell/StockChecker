package services;

import domain.Amount;
import domain.models.Stock;

import static services.PriceService.ERROR_AMOUNT;

class StockWrapper {
    static final int MAXIMUM_RETRIES = 4;
    static final int DEFAULT_NUMBER_OF_FAILS = 0;
    private int numberOfFails;
    private Stock stock;

    StockWrapper(final Stock stock, final int numberOfFails) {
        this.stock = stock;
        this.numberOfFails = numberOfFails;
    }

    StockWrapper(final Stock stock) {
        this(stock, DEFAULT_NUMBER_OF_FAILS);
    }

    StockWrapper update(final Amount lastClosingPrice) {
        updateNumberOfFails(lastClosingPrice);
        updateStockPrice(lastClosingPrice);
        return this;
    }

    private void updateStockPrice(Amount lastClosingPrice) {
        this.stock.setPrice(lastClosingPrice);
    }

    private void updateNumberOfFails(final Amount price) {
        this.numberOfFails += isErrorPrice(price) ? 1 : 0;
    }

    private boolean isErrorPrice(final Amount price) {
        return ERROR_AMOUNT.equals(price);
    }

    boolean hasErrors() {
        return isErrorPrice(this.stock.getPrice()) && this.numberOfFails < MAXIMUM_RETRIES;
    }

    Stock getStock() {
        return this.stock;
    }

    int getNumberOfFails() {
        return this.numberOfFails;
    }
}
