package igor.learning.controller;

import igor.learning.model.TodoData;
import igor.learning.model.TodoItem;
import igor.learning.service.ITodoItemService;
import igor.learning.util.AttributeNames;
import igor.learning.util.Mappings;
import igor.learning.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
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
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id,
                              Model model) {
        log.info("editing Item with id {}", id);
        TodoItem todoItem = todoItemService.getItem(id);

        if (todoItem == null) {
            todoItem = new TodoItem("", "", LocalDate.now());
        }

        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
        log.info("todoItem from form {}", todoItem);

        if (todoItem.getId() == 0) {
            todoItemService.addItem(todoItem);
        } else {
            todoItemService.updateItem(todoItem);
        }

        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        log.info("delete Item with id {}", id);
        todoItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {
        log.info("view Item with id {}", id);

        TodoItem todoItem = todoItemService.getItem(id);

        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);

        return ViewNames.VIEW_ITEM;
    }

}
