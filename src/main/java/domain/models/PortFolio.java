package domain.models;

import domain.Amount;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class PortFolio {
    private final List<Stock> stocks;
    private final String owner;

    public Amount getTotalInvested() {
        return this.stocks.stream()
                .map(Stock::getPricePaidAfterTaxes)
                .reduce(Amount.ZERO, Amount::add);
    }

    public Amount getAbsoluteTotalGained() {
        return this.stocks.stream()
                .map(Stock::getAbsoluteGain)
                .reduce(Amount.ZERO, Amount::add);
    }

    public Amount getCurrentValueTotal() {
        return this.stocks.stream()
                .map(Stock::getCurrentValue)
                .reduce(Amount.ZERO, Amount::add);
    }

    public Amount getTotalPercentage() {
        return this.stocks.stream()
                .map(Stock::getDiffPaidClosing)
                .reduce(Amount.ZERO, this::addPartial);
    }

    private Amount addPartial(final Amount acc, final Amount diff) {
        return acc.add(diff.divide(Amount.of(stocks.size())));
    }

    public Amount getExpectedTotalPercentage() {
        return this.stocks.stream()
                .map(Stock::getDiffPaidExpected)
                .reduce(Amount.ZERO, this::addPartial);
    }
}
