package seedu.elisa.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.elisa.commons.core.item.Item;

/**
 * Serves as a controller for the OpenItem.fxml.
 */
public class OpenItem extends UiPart<Region> {
    private static final String FXML = "OpenItem.fxml";

    @FXML
    public final Item item;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label itemdetails;
    @FXML
    private Label completed;
    @FXML
    private Label priority;
    @FXML
    private Label startdate;
    @FXML
    private Label enddate;
    @FXML
    private Label reminderdate;
    @FXML
    private FlowPane tags;

    public OpenItem(Item item) {
        super(FXML);
        this.item = item;
        description.setText(item.getItemDescription().toString());
        itemdetails.setText(item.toString());
    }

    public OpenItem(ObservableList<Item> items) {
        super(FXML);
        this.item = items.get(0); // only 1 item
        description.setText(item.getItemDescription().toString());
        priority.setText("Priority: " + item.getPriority().toString());
        startdate.setText("Start Date: " + item.getEvent().get().getStartDateTime().toString());
        enddate.setText("End Date: " + item.getEvent().get().getEndDateTime().toString());
    }

}
