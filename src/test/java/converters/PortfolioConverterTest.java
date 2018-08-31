package converters;

import deserializers.StockDeserializer;
import domain.models.PortFolio;
import fixtures.ConverterFixture;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PortfolioConverterTest extends ConverterFixture {
    private static final int FIRST = 0;
    private static final int NUMBER_OF_PORTFOLIOS = 1;

    private final PortfolioConverter portfolioConverter = new PortfolioConverter(new StockDeserializer());

    @Test
    public void shouldConvertPortfolioFromJson() {
        final List<PortFolio> result = this.portfolioConverter.convert(Collections.singletonList(PORTFOLIO_JSON));
        assertThat(result.size()).isEqualTo(NUMBER_OF_PORTFOLIOS);
        shouldConvertPortfolioWell(result.get(FIRST));
    }
}
