package com.example.crud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @GetMapping("/add")
    public String addItemPage()
    {
        return "add";
    }

    @PostMapping("/additem")
    public String addItemSubmit(Model model, @RequestParam String name, @RequestParam String description, @RequestParam String image)
    {
        itemService.addItem(name,description,image);
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(Model model, @PathVariable Integer id)
    {
        itemService.delItem(id);
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @GetMapping("/edit/{id}")
    public String edidItem(Model model, @PathVariable Integer id)
    {
        Item i = itemService.getItemById(id);
        model.addAttribute("name", i.getName());
        model.addAttribute("description", i.getDescription());
        model.addAttribute("image", i.getImage());
        return "edit";
    }

    @PostMapping("edititem")
    public String editItemSubmit(Model model, @RequestParam Integer id, @RequestParam String name, @RequestParam String description,  @RequestParam String image)
    {
        Item i = itemService.getItemById(id);
        i.setName(name);
        i.setDescription(description);
        i.setImage(image);
        List<Item> itemList = itemService.getAllItems();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @GetMapping("/namesort")
    public String nameSort(Model model)
    {
        List<Item> itemList = itemService.browseByName();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @GetMapping("/idsort")
    public String idSort(Model model)
    {
        List<Item> itemList = itemService.browseById();
        model.addAttribute("itemList", itemList);
        return "home";
    }

    @PostMapping("/search")
    public String searchByIdOrName(Model model, @RequestParam(name = "nameSearch", required = false) String name, @RequestParam(name = "idSearch", required = false) Integer id)
    {
        List<Item> itemList = itemService.getAllItems();

        if(!name.isEmpty()) { itemList = itemService.getAllItemsByName(name); }
        else if(id != null) { itemList = itemService.GetAllItemsById(id); }
        model.addAttribute("itemList", itemList);

        return "home";
    }


}
