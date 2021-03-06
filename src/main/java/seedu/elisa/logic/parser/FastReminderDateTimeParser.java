package seedu.elisa.logic.parser;

import static seedu.elisa.commons.core.Messages.MESSAGE_INVALID_FAST_REMINDER_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.elisa.logic.parser.exceptions.FastReminderParseException;
import seedu.elisa.logic.parser.exceptions.ParseException;

/**
 * Parse stringDateTime in fast reminder format (ie 10.min.later) to a LocalDateTime object.
 */
public class FastReminderDateTimeParser implements DateTimeParser {

    private static final String MESSAGE_BEYOND_RANGE = "That's a bit too far don't you think? "
            + "I can only accept positive integers less than 100. For example: 100.day.later or 2.min.later";
    private static final String DAY_INDICATOR = "DAY";
    private static final String HOUR_INDICATOR = "HOUR";
    private static final String MIN_INDICATOR = "MIN";

    private static final Pattern BASIC_INPUT_FORMAT =
            Pattern.compile("(?<quantity>[1-9]\\d*)(\\.)(?<unit>MIN|HOUR|DAY)(\\.LATER)$");

    /**
     * Parse this stringDateTime into a LocalDateTime representation
     * @param stringDateTime of the unprocessed date time string
     * @return LocalDateTime representation of the stringDateTime
     * @throws ParseException if the format of stringDateTime is incorrect or if the value given is greater than 100
     */
    public LocalDateTime parseDateTime(String stringDateTime) throws ParseException {
        //stringDateTime should be of format "10.min.later" or "3.hour.later" or "2.day.later"
        String processedString = stringDateTime.toUpperCase();

        final Matcher matcher = BASIC_INPUT_FORMAT.matcher(processedString);
        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_INVALID_FAST_REMINDER_FORMAT);
        }

        final String quantity = matcher.group("quantity");
        final String unit = matcher.group("unit");
        final int intQuantity = Integer.valueOf(quantity);

        if (intQuantity > 100) {
            throw new FastReminderParseException(MESSAGE_BEYOND_RANGE);
        }

        LocalDateTime current = LocalDateTime.now();
        LocalDateTime processedDateTime = LocalDateTime.now(); // just to initialize

        try {
            switch (unit) {
            case DAY_INDICATOR:
                processedDateTime = current.plusDays(intQuantity);
                break;
            case HOUR_INDICATOR:
                processedDateTime = current.plusHours(intQuantity);
                break;
            case MIN_INDICATOR:
                processedDateTime = current.plusMinutes(intQuantity);
                break;
            default:
                // nothing
            }
        } catch (DateTimeException e) {
            throw new ParseException(e.getMessage());
        }

        return processedDateTime;
    }
}
