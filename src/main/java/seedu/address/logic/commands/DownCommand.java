package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemModel;

/**
 * Command for scrolling down.
 */
public class DownCommand extends ScrollCommand {

    public DownCommand(String input) {
        super(input);
    }

    @Override
    public CommandResult execute(ItemModel model) throws CommandException {
        requireNonNull(model);
        if (pane.equals("Illegal")) {
            throw new CommandException(MESSAGE_USAGE);
        }
        return new DownCommandResult(MESSAGE_SUCCESS, pane);
    }
}
