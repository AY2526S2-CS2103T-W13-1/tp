package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ViewPropertyCommandParserTest {

    private final ViewPropertyCommandParser parser = new ViewPropertyCommandParser();

    @Test
    void parse_invalidArgs_throwsParseException() {
        // Empty input
        assertThrows(ParseException.class, () -> parser.parse(""));

        // Non-numeric input
        assertThrows(ParseException.class, () -> parser.parse("abc"));

        // Negative number
        assertThrows(ParseException.class, () -> parser.parse("-1"));

        // Zero is invalid (Index is 1-based)
        assertThrows(ParseException.class, () -> parser.parse("0"));
    }

    @Test
    void parse_validArgs_returnsViewPropertyCommand() {
        assertParseSuccess(parser, "1", new ViewPropertyCommand(INDEX_FIRST_PERSON));
    }
}