package com.example.Superstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class StoreController {

    List<Item> items = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {

        int index = getGradeIndex(id);
        Item item = new Item();
        model.addAttribute("item", index == -1000 ? item : items.get(index));
        model.addAttribute("categories", Constants.CATEGORIES);

        return "form";
    }

    @PostMapping("/handleSubmit")
    public String handleSubmit(@Valid Item item, BindingResult result) {

        if (item.getPrice() <= item.getDiscount())
            result.rejectValue("discount", "", "Price cannot be less than discount");

        if (result.hasErrors())
            return "form";
        int index = getGradeIndex(item.getId());

        if (index == Constants.NOT_FOUND) {
            items.add(item);
        } else {
            items.set(index, item);
        }

        return "redirect:/inventory";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", items);
        return "inventory";
    }

    @GetMapping("/delete")
    public String handleDelete(@RequestParam(required = false) String id) {
        int index = getGradeIndex(id);

        if (index != -1000) {
            items.remove(index);
        }
        ;

        return "redirect:/inventory";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                return i;
            }
        }

        return Constants.NOT_FOUND;
    }
}
