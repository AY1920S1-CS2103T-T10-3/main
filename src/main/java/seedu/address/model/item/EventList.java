package seedu.address.model.item;

/**
 * Object class to store all the items that are events within the program
 */
public class EventList extends ItemList {
    public EventList() {
        super();
    }

    /**
     * Sorts the event list based on the date of the event.
     * @return a sorted EventList of the current list
     */
    public ItemList sort() {
        EventList el = new EventList();
        for (Item item: list) {
            el.add(item);
        }

        el.list.sort((item1, item2) -> item1.getEvent().getDate().compareTo(item2.getEvent().getDate()));
        return el;
    }

    /**
     * Finds a substring within the description of an item.
     * @param searchString a string to be search for within the description of an item
     * @return a new EventList only containing the items that have the search string in their description
     */
    public ItemList find(String searchString) {
        EventList el = new EventList();
        return find(searchString, el);
    }
}