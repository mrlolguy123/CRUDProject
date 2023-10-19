package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> browseByName()
    {
        return itemRepository.findAllByOrderByNameAsc();
    }

    public List<Item> browseById()
    {
        return itemRepository.findAllByOrderByIdAsc();
    }

    public void addItem(String name, String desc, String img)
    {
        Item i = new Item(name, desc, img);
        itemRepository.save(i);
    }

    public void delItem(Integer id)
    {
        itemRepository.deleteById(id);
    }

    public List<Item> getAllItems()
    {
        return itemRepository.findAll();
    }

    public Item getItemById(Integer id)
    {
        Optional<Item> opItem = itemRepository.findById(id);
        if(opItem.isPresent())
        {
            Item i = opItem.get();
            return i;
        }
        return null;
    }

    public List<Item> getAllItemsByName(String name)
    {
        return itemRepository.findAllByNameContains(name);
    }

    public List<Item> GetAllItemsById(Integer id)
    {
        return itemRepository.findAllByIdEquals(id);
    }


}
