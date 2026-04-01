package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FilterClientCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FilterClientCommandParserTest {

    private FilterClientCommandParser parser = new FilterClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                 FilterClientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterClientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidName_throwsParseException() {
        assertParseFailure(parser, " n/Alice@Bob", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        assertParseFailure(parser, " n/Alice n/Bob",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_validArgs_returnsFilterClientCommand() {
        // no leading and trailing whitespaces
        FilterClientCommand expectedFilterClientCommand =
                new FilterClientCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFilterClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ Alice \n \t Bob  \t", expectedFilterClientCommand);
    }

}
