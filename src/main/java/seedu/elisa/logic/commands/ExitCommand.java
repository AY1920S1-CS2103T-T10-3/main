package seedu.elisa.logic.commands;

import seedu.elisa.model.ItemModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "ELISee you again!";

    @Override
    public CommandResult execute(ItemModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
