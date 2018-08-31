package fixtures;

import domain.Amount;
import domain.models.PortFolio;
import domain.models.Stock;

import java.util.Arrays;
import java.util.List;

import static domain.Amount.of;

public class PortFolioFixture {
    private static final String ID_1 = "TRU";
    private static final Amount PRICE_1 = of(4.1);
    private static final String NAME_1 = "Tecnicas";
    private static final Amount N_STOCKS_1 = of(500);
    private static final Amount BUYING_PRICE_1 = of(5.7);
    private static final Amount PAID_AFTER_TAXES_1 = of(2860);
    private static final Amount EXPECTED_PRICE_1 = of(6);
    public static final Stock STOCK_1 = new Stock(ID_1, PRICE_1, NAME_1, N_STOCKS_1, BUYING_PRICE_1, PAID_AFTER_TAXES_1, EXPECTED_PRICE_1);
    public static final String OWNER = "ARCA";
    public static final String ID = "IDX";
    public static final Amount PRICE = of(3.4);
    public static final String NAME = "Inditex";
    public static final Amount N_STOCKS = of(400);
    public static final Amount BUYING_PRICE = of(2.7);
    public static final Amount PAID_AFTER_TAXES = of(1090);
    public static final Amount EXPECTED_PRICE = of(3.5);
    public static final Amount PERC_PAID_TO_CLOSING = of(26);
    public static final Amount PERC_EXPECTED_TO_CLOSING = of(4);
    public static final Amount ABSOLUTE_GAIN = of(270);
    public static final Amount CURRENT_VALUE = of(1360);
    public static final Stock STOCK = new Stock(ID, PRICE, NAME, N_STOCKS, BUYING_PRICE, PAID_AFTER_TAXES, EXPECTED_PRICE);
    public static final List<Stock> STOCKS = Arrays.asList(STOCK, STOCK_1);
    public static final PortFolio PORT_FOLIO = new PortFolio(STOCKS, OWNER);
    public static final Amount TOTAL_INVESTED = of(3950);
    public static final Amount ABSOLUTE_TOTAL_GAINED = of(-540);
    public static final Amount CURRENT_VALUE_TOTAL = of(3410);
    public static final Amount TOTAL_PERCENTAGE = of(-1);
    public static final Amount EXPECTED_TOTAL_PERCENTAGE = of(19);
}
