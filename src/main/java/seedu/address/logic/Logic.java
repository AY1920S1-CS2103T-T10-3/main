package seedu.address.logic;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ElisaState;
import seedu.address.model.ElisaStateHistory;
import seedu.address.model.ElisaStateManager;
import seedu.address.model.ItemStorage;
import seedu.address.model.item.VisualizeList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ItemStorage getItemStorage();

    /*
    /** Returns an unmodifiable view of the filtered list of persons
    ObservableList<Person> getFilteredPersonList();
    */

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //ObservableList<Person> getFilteredPersonList();
    VisualizeList getVisualList();

    void setState(ElisaStateManager state);

    ElisaState getState();

    ElisaStateHistory getElisaStateHistory();

    void updateModelLists();
}
