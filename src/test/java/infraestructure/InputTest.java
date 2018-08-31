package infraestructure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static infraestructure.Input.ALPHA_VANTAGE_URL;
import static infraestructure.Input.DIRECTORY_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class InputTest {

    private static final List<String> LIST_OF_FILES = new ArrayList<>();
    private static final Path DIRECTORY_PATH = Paths.get(DIRECTORY_NAME);
    private static final String STOCK_ID = "1";
    private static final String STOCK_INFO = "info";

    @Mock
    private DataRetriever dataRetriever;
    private URL stockUrl;

    private Input input;

    @Before
    public void setUp() throws IOException {
        this.input = new Input(this.dataRetriever);
        this.stockUrl = new URL("http://localhost");
        givenDataRetrieverScenario();
    }

    private void givenDataRetrieverScenario() throws IOException {
        given(this.dataRetriever.getResourcePath(DIRECTORY_NAME)).willReturn(DIRECTORY_PATH);
        given(this.dataRetriever.readFiles(DIRECTORY_PATH)).willReturn(LIST_OF_FILES);
        given(this.dataRetriever.getUrl(ALPHA_VANTAGE_URL + STOCK_ID)).willReturn(this.stockUrl);
        given(this.dataRetriever.readUrl(this.stockUrl)).willReturn(STOCK_INFO);
    }

    @Test
    public void shouldReturnListOfPortfolioFiles() {
        assertThat(this.input.getPortfolioFiles()).isEqualTo(LIST_OF_FILES);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenIOException() throws IOException {
        given(this.dataRetriever.readFiles(DIRECTORY_PATH)).willThrow(new IOException());
        this.input.getPortfolioFiles();
    }

    @Test
    public void shouldReturnStockInfo() {
        assertThat(this.input.retrieveStock(STOCK_ID)).isEqualTo(STOCK_INFO);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionWhenIOExceptionInStock() throws IOException {
        given(this.dataRetriever.readUrl(this.stockUrl)).willThrow(new IOException());
        this.input.retrieveStock(STOCK_ID);
    }
}