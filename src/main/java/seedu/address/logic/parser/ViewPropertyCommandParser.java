package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewPropertyCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewPropertyCommandParser implements Parser<ViewPropertyCommand> {

    public ViewPropertyCommand parse(String args) throws ParseException {

        Index index = ParserUtil.parseIndex(args);

        return new ViewPropertyCommand(index);
    }
}
