package seedu.elisa.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /*ELISA prefix definitions */
    public static final Prefix PREFIX_DATETIME = new Prefix("-d");
    public static final Prefix PREFIX_REMINDER = new Prefix("-r");
    public static final Prefix PREFIX_SNOOZE = new Prefix("-s");
    public static final Prefix PREFIX_PRIORITY = new Prefix("-p");
    public static final Prefix PREFIX_TAG = new Prefix("-t");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-desc");
    public static final Prefix PREFIX_DELETE_EVENT = new Prefix("--e");
    public static final Prefix PREFIX_DELETE_TASK = new Prefix("--tk");
    public static final Prefix PREFIX_DELETE_REMINDER = new Prefix("--r");
    public static final Prefix PREFIX_AUTO_RESCHEDULE = new Prefix("-auto");
}
