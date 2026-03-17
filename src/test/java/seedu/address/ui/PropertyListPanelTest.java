package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyAddress;
import seedu.address.model.property.Size;

public class PropertyListPanelTest {

    @BeforeAll
    public static void initToolkit() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // toolkit already initialized
        }
    }

    @Test
    public void constructor_createsPanel() throws Exception {
        Set<Property> properties = new HashSet<>();
        properties.add(new Property(
                new PropertyAddress("123 Clementi Ave 3"),
                new Price("1000000"),
                new Size("1200")
        ));

        Person person = new Person(
                new Name("Alice"),
                new Phone("91234567"),
                new Email("alice@email.com"),
                new HashSet<>(),
                properties
        );

        ObservableList<Person> personList = FXCollections.observableArrayList(person);

        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            PropertyListPanel panel = new PropertyListPanel(personList);
            assertNotNull(panel.getRoot());
            latch.countDown();
        });
        latch.await();
    }
}
