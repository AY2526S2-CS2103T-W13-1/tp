package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddress;
import seedu.address.model.property.Size;

public class PropertyCardTest {

    @BeforeAll
    public static void initToolkit() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // toolkit already initialized
        }
    }

    @Test
    public void constructor_setsProperty() throws Exception {
        Property property = new Property(
                new PropertyAddress("123 Clementi Ave 3"),
                new Price("1000000"),
                new Size("1200")
        );

        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            PropertyCard card = new PropertyCard(property, 1);
            assertEquals(property, card.property);
            latch.countDown();
        });
        latch.await();
    }
}
