package ITEM;

import java.util.*;
import java.util.stream.Stream;

public class Inventory 
{
    private int cap;
    public List<Item> items;

//------------------------------------------------------------
    //Constructor
    public Inventory(int cap)
    {
        this.cap = cap;
        this.items = new ArrayList<>(cap);
    }

    //Display
    public void displayInventory()
    {
        //Sort list by type
        List<Item> sortedList = this.items.stream()
                                    .sorted((item1, item2) -> {
                                        return item1.getType() - item2.getType();
                                    })
                                    .sorted((item1, item2) -> {
                                        return item1.getName().compareToIgnoreCase(item2.getName());
                                    })
                                    .toList();
        
        System.out.printf("|%10s | %20s | %10s |\n", "No.",
                                                       "Name",
                                                       "Type");
        
        for(int i = 0; i < sortedList.size(); i++)
        {
            System.out.printf("|%10s | %20s | %10s |\n", i + 1, 
                                                        sortedList.get(i).getName(),
                                                        sortedList.get(i).getType());
        }
       
    }       

    public static void main(String[] args) 
    {
        
        Item[] list = new Item[5];
        list[0] = new Axe(0, 0);
        list[1] = new Sword(0, 0);
        list[2] = new Axe(0, 0);
        list[3] = new Sword(0, 0);
        list[4] = new Axe(0, 0);

        Inventory inven = new Inventory(5);
        /* 
        
        for(int i = 0; i < 5; i++)
        {
           inven.addItem(list[i]);
        }
        */

        inven.displayInventory();
        System.out.println("\n");
        System.out.println(inven.items.isEmpty());


        //inven.removeItemByIndex(0);
        //inven.displayInventory();
        //System.out.println("\n");


    }
    

}
