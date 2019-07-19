package igor.learning.service;

import igor.learning.model.TodoData;
import igor.learning.model.TodoItem;

public interface ITodoItemService {

    void addItem(TodoItem todoItem);
    void removeItem(int id);
    TodoItem getItem(int id);
    void updateItem(TodoItem todoItem);
    TodoData getData();
}
