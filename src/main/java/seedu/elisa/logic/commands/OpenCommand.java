package seedu.elisa.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.elisa.commons.core.Messages;
import seedu.elisa.commons.core.index.Index;
import seedu.elisa.commons.core.item.Item;
import seedu.elisa.logic.commands.exceptions.CommandException;
import seedu.elisa.model.ItemModel;
import seedu.elisa.model.item.VisualizeList;

/**
 * Creates an open command to expand the view of an item.
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";
    public static final String MESSAGE_SUCCESS = "opening up item %d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens up the desired item to view more details.\n"
            + "Parameters: INDEX (Must be a positive integer within the current list) \n"
            + "Example: " + COMMAND_WORD + " 1";

    private Index index;
    private VisualizeList beforeOpen;

    public OpenCommand(Index index) {
        this.index = index;
    }

    /**
     * Executes this command
     * @param model {@code Model} which the command should operate on.
     * @return the command result of executing this command
     * @throws CommandException if the index given is invalid
     */
    public CommandResult execute(ItemModel model) throws CommandException {
        requireNonNull(model);
        beforeOpen = model.getVisualList().deepCopy(); // for undo. Undo action is technically close.
        VisualizeList lastShownList = model.getVisualList(); //shallow copy just to get item

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item toOpen = lastShownList.get(index.getZeroBased());
        return new OpenCommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()), toOpen);
    }

}
