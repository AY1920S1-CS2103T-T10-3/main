package seedu.elisa.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.elisa.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;

import seedu.elisa.commons.core.Messages;
import seedu.elisa.logic.Logic;
import seedu.elisa.logic.LogicManager;
import seedu.elisa.logic.commands.exceptions.CommandException;
import seedu.elisa.logic.parser.exceptions.ParseException;
import seedu.elisa.model.ElisaCommandHistory;
import seedu.elisa.model.ElisaCommandHistoryManager;
import seedu.elisa.model.ItemModel;
import seedu.elisa.model.ItemModelManager;
import seedu.elisa.storage.JsonItemStorage;
import seedu.elisa.storage.JsonUserPrefsStorage;
import seedu.elisa.storage.StorageManager;


public class UndoCommandTest {
    @TempDir
    public Path temporaryFolder;

    private ItemModel model;
    private ElisaCommandHistory historyManager;
    private Logic logic;

    @BeforeEach
    public void setUp() throws Exception {
        JsonItemStorage jsonItemStorage =
                new JsonItemStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(jsonItemStorage, userPrefsStorage);
        historyManager = new ElisaCommandHistoryManager();
        model = new ItemModelManager(storage.toModelType(), userPrefsStorage.readUserPrefs().get(), historyManager);
        logic = new LogicManager(model, storage);
    }

    @Test
    private void execute_nothingToUndo_failure() {
        assertThrows(CommandException.class, Messages.MESSAGE_NOTHING_TO_UNDO, (
            ) -> logic.execute(UndoCommand.COMMAND_WORD));
    }

    @Test
    private void execute_undoAddTask_success() throws CommandException, ParseException {
        logic.execute(AddTaskCommand.COMMAND_WORD + " a");
        assertCommandSuccess(UndoCommand.COMMAND_WORD,
                "Undo [" + AddTaskCommand.COMMAND_WORD + "] command successful! Try to do it right this time..", model);
    }

    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      ItemModel expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }
}
