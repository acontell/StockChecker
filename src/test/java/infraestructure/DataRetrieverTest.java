package infraestructure;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class DataRetrieverTest {
    private static final String TEST_FILE = "testFile";
    private static final String TEST_DIRECTORY = "test";
    private static final String TEST_FILE_CONTENT = "testing";
    private static final String EMPTY_STRING = "";
    private static final Path WRONG_PATH = Paths.get(EMPTY_STRING);
    private static final String LOCALHOST_URL = "http://localhost";
    private URL url;
    private Path path;
    private DataRetriever dataRetriever = new DataRetriever();

    @Before
    public void setUp() {
        this.path = this.dataRetriever.getResourcePath(TEST_DIRECTORY);
        this.url = this.dataRetriever.getResourceUrl(TEST_DIRECTORY);
    }

    @Test
    public void shouldReturnDataFromUrl() throws IOException {
        assertThat(this.dataRetriever.readUrl(this.url)).contains(TEST_FILE);
    }

    @Test
    public void shouldReturnListOfStrings() throws IOException {
        assertThat(this.dataRetriever.readFiles(this.path)).isEqualTo(singletonList(TEST_FILE_CONTENT));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() throws IOException {
        this.dataRetriever.readFiles(WRONG_PATH);
    }

    @Test
    public void shouldReturnResourcesPath() {
        assertThat(this.dataRetriever.getResourcePath(TEST_DIRECTORY)).isDirectory();
    }

    @Test
    public void shouldReturnUrl() throws MalformedURLException {
        assertThat(this.dataRetriever.getUrl(LOCALHOST_URL)).isEqualTo(new URL(LOCALHOST_URL));
    }
}