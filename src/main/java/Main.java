import static com.google.inject.Guice.createInjector;
import static javax.swing.SwingUtilities.invokeLater;

public class Main {
    public static void main(final String[] args) {
        invokeLater(createInjector().getInstance(Starter.class)::start);
    }
}
