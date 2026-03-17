package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PropertyTest {

    private final PropertyAddress validAddress = new PropertyAddress("123 Main Street");
    private final Price validPrice = new Price("500000");
    private final Size validSize = new Size("1200");

    @Test
    public void constructor_nullAddress_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Property(null, validPrice, validSize));
    }

    @Test
    public void constructor_nullPrice_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Property(validAddress, null, validSize));
    }

    @Test
    public void constructor_nullSize_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Property(validAddress, validPrice, null));
    }

    @Test
    public void isSameProperty() {
        Property property = new Property(validAddress, validPrice, validSize);

        assertTrue(property.isSameProperty(property));

        assertFalse(property.isSameProperty(null));

        Property sameAddressDifferentDetails = new Property(validAddress, new Price("600000"), new Size("1500"));
        assertTrue(property.isSameProperty(sameAddressDifferentDetails));

        Property differentAddress = new Property(new PropertyAddress("456 Orchard Road"), validPrice, validSize);
        assertFalse(property.isSameProperty(differentAddress));
    }
    @Test
    public void toString_withPropertyType_includesType() {
        Property property = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        assertTrue(property.toString().contains("Type: HDB"));
    }

    @Test
    public void toString_withoutPropertyType_noTypeLabel() {
        Property property = new Property(validAddress, validPrice, validSize);
        assertFalse(property.toString().contains("Type:"));
    }

    @Test
    public void equals_differentPropertyType_returnsFalse() {
        Property property = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        Property differentType = new Property(validAddress, validPrice, validSize, new PropertyType("Condo"));
        assertFalse(property.equals(differentType));
    }

    @Test
    public void equals_samePropertyType_returnsTrue() {
        Property property = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        Property sameType = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        assertTrue(property.equals(sameType));
    }

    @Test
    public void equals_oneNullPropertyType_returnsFalse() {
        Property withType = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        Property withoutType = new Property(validAddress, validPrice, validSize);
        assertFalse(withType.equals(withoutType));
    }

    @Test
    public void withRemarks_preservesAllFields() {
        Property property = new Property(validAddress, validPrice, validSize, new PropertyType("HDB"));
        Property withRemarks = property.withRemarks("Near MRT");

        assertEquals("Near MRT", withRemarks.getRemarks());
        assertEquals(property.getAddress(), withRemarks.getAddress());
        assertEquals(property.getPrice(), withRemarks.getPrice());
        assertEquals(property.getSize(), withRemarks.getSize());
        assertEquals(property.getPropertyType(), withRemarks.getPropertyType());
    }

    @Test
    public void setAndGetRemarks() {
        Property property = new Property(validAddress, validPrice, validSize);
        assertNull(property.getRemarks());
        property.setRemarks("Near MRT");
        assertEquals("Near MRT", property.getRemarks());
    }

    @Test
    public void equals() {
        Property property = new Property(validAddress, validPrice, validSize);
        Property propertyCopy = new Property(validAddress, validPrice, validSize);
        Property differentProperty = new Property(new PropertyAddress("456 Orchard Road"), validPrice, validSize);

        assertTrue(property.equals(property));

        assertTrue(property.equals(propertyCopy));

        assertFalse(property.equals(null));

        assertFalse(property.equals("123"));

        assertFalse(property.equals(differentProperty));
    }

}
