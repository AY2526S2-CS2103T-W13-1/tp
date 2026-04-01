package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.logic.commands.FilterClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterClientCommand object
 */
public class FilterClientCommandParser implements Parser<FilterClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterClientCommand
     * and returns a FilterClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        String name = argMultimap.getValue(PREFIX_NAME).orElse("").trim();

        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = name.split("\\s+");
        for (String nameKeyword : nameKeywords) {
            ParserUtil.parseName(nameKeyword);
        }

        return new FilterClientCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
