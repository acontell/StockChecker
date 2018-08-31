package infraestructure;

import com.google.inject.Singleton;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Thread.currentThread;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Singleton
class DataRetriever {
    private static final String CHARSET = "UTF-8";

    List<String> readFiles(final Path path) throws IOException {
        return Files.list(path)
                .map(this::readAllBytes)
                .map(String::new)
                .collect(toList());
    }

    private byte[] readAllBytes(final Path file) {
        try {
            return Files.readAllBytes(file);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    String readUrl(final URL url) throws IOException {
        return IOUtils.toString(url, Charset.forName(CHARSET));
    }

    Path getResourcePath(final String resource) {
        final URL url = requireNonNull(getResourceUrl(resource));
        return Paths.get(url.getPath());
    }

    URL getResourceUrl(final String resource) {
        return currentThread()
                .getContextClassLoader()
                .getResource(resource);
    }

    URL getUrl(final String url) throws MalformedURLException {
        return new URL(url);
    }
}
