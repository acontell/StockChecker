package retrievers;

import com.google.gson.JsonObject;
import domain.models.Stock;
import infraestructure.Input;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class StockRetrieverTest {
    private static final String JSON_STRING = "{}";
    private static final JsonObject JSON_OBJECT = new JsonObject();
    private static final String STOCK_ID = "1";

    @Mock
    private Input input;
    @Mock
    private Stock stock;

    private StockRetriever stockRetriever;

    @Before
    public void setUp() {
        given(this.input.retrieveStock(STOCK_ID)).willReturn(JSON_STRING);
        given(this.stock.getId()).willReturn(STOCK_ID);
        this.stockRetriever = new StockRetriever(this.input);
    }

    @Test
    public void shouldReturnJsonObject() {
        assertThat(this.stockRetriever.getStockInfo(this.stock)).isEqualTo(JSON_OBJECT);
    }
}