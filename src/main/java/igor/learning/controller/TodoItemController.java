package igor.learning.controller;

import igor.learning.model.TodoData;
import igor.learning.service.ITodoItemService;
import igor.learning.util.Mappings;
import igor.learning.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TodoItemController {
    // == fields ==
    private final ITodoItemService todoItemService;

    @Autowired
    public TodoItemController(ITodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    // == model attributed ==
    @ModelAttribute
    public TodoData todoData() {
        return todoItemService.getData();
    }

    // == handler methods ==
    // http://localhost:8080/todo-list/items
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS;
    }
}
