import domain.models.PortFolio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.PortfolioService;
import view.PortfolioTableFormat;

import javax.swing.*;
import java.util.List;

import static fixtures.PortFolioFixture.PORT_FOLIO;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StarterTest {
    private static final List<PortFolio> PORT_FOLIOS = singletonList(PORT_FOLIO);
    @Mock
    private PortfolioService portfolioService;
    @Mock
    private PortfolioTableFormat portfolioTableFormat;
    @Mock
    private JFrame jFrame;

    private Starter starter;

    @Before
    public void setUp() {
        given(this.portfolioService.getPortFolios()).willReturn(PORT_FOLIOS);
        given(this.portfolioTableFormat.getJFrame(PORT_FOLIOS)).willReturn(this.jFrame);
        this.starter = new Starter(this.portfolioService, this.portfolioTableFormat);
    }

    @Test
    public void shouldGenerateJFrame() {
        this.starter.start();
        verify(this.portfolioTableFormat).getJFrame(PORT_FOLIOS);
    }

    @Test
    public void shouldSetJFrameVisibilityToTrue() {
        this.starter.start();
        verify(this.jFrame).setVisible(true);
    }
}
