package domain.models;

import domain.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {
    private String id;
    private Amount price;
    private String name;
    private Amount nStocks;
    private Amount stockBuyingPrice;
    private Amount pricePaidAfterTaxes;
    private Amount expectedPrice;

    public Amount getDiffPaidClosing() {
        return getPrice().subtract(getStockBuyingPrice()).percentage(getStockBuyingPrice());
    }

    public Amount getDiffPaidExpected() {
        return getExpectedPrice().subtract(getPrice()).percentage(getStockBuyingPrice());
    }

    public Amount getAbsoluteGain() {
        return getCurrentValue().subtract(getPricePaidAfterTaxes());
    }

    public Amount getCurrentValue() {
        return getPrice().multiply(this.getNStocks());
    }
}
