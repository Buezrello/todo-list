package igor.learning.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TodoData {

    // == fields ==
    private static int idValue = 1;

    private final List<TodoItem> items = new ArrayList<>();

    // == constructors ==
    public TodoData() {
        // dummy data, using addItem() method - to set the id field
        addItem(new TodoItem("First", "First details", LocalDate.now()));
        addItem(new TodoItem("Second", "Second details", LocalDate.now()));
        addItem(new TodoItem("Third", "Third details", LocalDate.now()));
    }

    // == public methods ==
    public List<TodoItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(@NonNull TodoItem toAdd) {

        toAdd.setId(idValue);
        items.add(toAdd);
        idValue++;
    }

    public void removeItem(int id) {
        ListIterator<TodoItem> itemListIterator = items.listIterator();

        while (itemListIterator.hasNext()) {
            TodoItem item = itemListIterator.next();

            if (item.getId() == id) {
                itemListIterator.remove();
                break;
            }
        }
    }

    public TodoItem getItem(int id) {
        for (TodoItem todoItem : items) {
            if (todoItem.getId() == id)
                return todoItem;
        }

        return null;
    }

    public void updateItem(@NonNull TodoItem toUpdate) {
        ListIterator<TodoItem> itemListIterator = items.listIterator();

        while (itemListIterator.hasNext()) {
            TodoItem item = itemListIterator.next();

            if (item.equals(toUpdate)) {
                itemListIterator.set(toUpdate);
                break;
            }
        }
    }
}
