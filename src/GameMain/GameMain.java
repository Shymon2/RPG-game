package GameMain;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import BasicSetting.BasicSetting;
import CollisionChecker.CollisionChecker;
import ENTITY.Player;
import ENTITY.Wolf;
import ITEM.Inventory;
import ITEM.Item;
import ITEM.Weapon;
import ENTITY.Character;
import ENTITY.Lion;
import MAP.*;

public class GameMain implements BasicSetting
{
     
    //Create hero
    public Player player = new Player("Hero", 100, 100, 10, 10, 1);

    //Create monsters
    public List<Character> monsters;
    

    //Create Map
    String map1FilePath = "src\\FileText\\map1.txt";       //NOTICE: this link can be changed when moving to another device
    public Map map = new Map1(map1FilePath, this);

    
    //Create Inventory
    Inventory inventory = new Inventory(maxNumItems);

    //Create tool to check collision
    public CollisionChecker cCheck = new CollisionChecker(this);

    //Creat scanner to read input
    private Scanner keyboard = new Scanner(System.in);


//--------------------------------------------------------

    //Constructor
    public GameMain()
    {
        this.SettingMonsters();
    }


    //SettingMonsters
    public void SettingMonsters()
    {
        this.monsters = new LinkedList<>();
        monsters.add(new Wolf(7, 4));
        monsters.add(new Wolf(9, 4));
        monsters.add(new Lion(8, 4));
    }
    //Clears Screen in java
    public void clrscr() throws InterruptedException
    {
        try {              
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        } catch (IOException ex) {}    
    }

    // Menu of move 2nd layer
    public void moveControl()
    {   
        int choice;
        boolean status = true;
        do
        {          
            System.out.println("\n------------------------------------------------------\n");
            System.out.println("1. Move Up");
            System.out.println("2. Move Down");
            System.out.println("3. Move Left");
            System.out.println("4. Move Right");
            System.out.println("5. No Move");
            System.out.println("6. Back to menu");
            System.out.print("Enter your choice: ");
            choice = keyboard.nextInt();
            switch (choice) 
            {
                case 1:

                    characterMove("up", player);
                    updatePlayer();
                    updateItems();
                    updateMonsters();
                    map.drawMap();
                    player.showState();
                    break;

                case 2:

                    characterMove("down", player);
                    updatePlayer();
                    updateItems();
                    updateMonsters();
                    map.drawMap();
                    player.showState();
                    break;

                case 3:

                    characterMove("left", player);
                    updatePlayer();
                    updateItems();
                    updateMonsters();
                    map.drawMap();
                    player.showState();
                    break;

                case 4:

                    characterMove("right", player);
                    updatePlayer();
                    updateItems();
                    updateMonsters();
                    map.drawMap();
                    player.showState();
                    break;

                case 5:

                    updatePlayer();
                    updateMonsters();
                    map.drawMap();
                    player.showState();
                    break;

                case 6:

                    status = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while(status == true);
    }

    // Menu of inventory 2nd layer
    public void inventoryControl(){
        System.out.println("\n------------------------------------------------------\n");
        if(inventory.items.isEmpty())         
            System.out.println("Empty inventory");
        else{
            inventory.displayInventory();
            System.out.println("Attack weapon: " + player.getCurrentWeapon());
            System.out.println("Defense weapon: " + player.getCurrentArmor());
            int choice, choice1;
            boolean status = true;
            do{              
                System.out.print("Enter a number to show item (Exit: 0 | Range: 1 - " + inventory.items.size() + "): ");
                choice = keyboard.nextInt();
                if(choice == 0) 
                    status = false;
                else if(0 < choice && choice <= inventory.items.size()){
                    boolean status1 = true;
                    System.out.println("\n------------------------------------------------------\n");  
                    System.out.println(((Weapon)inventory.items.get(choice - 1)).toString());
                    do{               
                        System.out.println("1. Equip item");
                        System.out.println("2. Unequip item");
                        System.out.println("3. Remove item");
                        System.out.print("Enter your choice: ");
                        choice1 = keyboard.nextInt();
                        if(choice1 == 1){
                            player.equipItem(((Weapon)inventory.items.get(choice - 1)));
                            System.out.println("Equip sucessful");
                            System.out.println("\n------------------------------------------------------\n");
                            inventory.displayInventory();
                            System.out.println("Attack weapon: " + player.getCurrentWeapon());
                            System.out.println("Defense weapon: " + player.getCurrentArmor());
                            status1 = false;
                        }
                        else if (choice1 == 2){
                            player.unEquipItem(((Weapon)inventory.items.get(choice - 1)));
                            System.out.println("Unequip sucessful");
                            System.out.println("\n------------------------------------------------------\n");
                            inventory.displayInventory();
                            System.out.println("Attack weapon: " + player.getCurrentWeapon());
                            System.out.println("Defense weapon: " + player.getCurrentArmor());
                            status1 = false;
                        }
                        else if(choice1 == 3){
                            inventory.items.remove(choice - 1);
                            System.out.println("Remove sucessful");
                            System.out.println("\n------------------------------------------------------\n");
                            inventory.displayInventory();
                            System.out.println("Attack weapon: " + player.getCurrentWeapon());
                            System.out.println("Defense weapon: " + player.getCurrentArmor());
                            status1 = false;
                        }
                        else System.out.println("Invalid choice");
                    } while (status1 == true);          
                }
                else System.out.println("Invalid choice");
            }while(status == true);
            System.out.println("\n------------------------------------------------------\n");
            map.drawMap();
            player.showState();
        }
    }
    //Start Game
    public void start()
    {
        //
        map.drawMap();
        System.out.println("");

        //Read input from keyboard

        int choice;

        do
        {
            System.out.println("\n------------------------------------------------------\n");
            System.out.println("1. Move");
            System.out.println("2. Show Inventory");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                
                    moveControl();
                    break;
                case 2:
                    
                    inventoryControl();
                    break;
                case 3:
                    System.out.println("Thanks for playing");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }           
        } while(choice != 3);
        //this.map.drawMap();
    }
    
    public void characterMove(String direction, Character obj)            //Move with cheking collision
    {
        switch (direction) 
        {
            case "up":
                        if(!cCheck.collideSolidAfterUp(obj))
                            obj.moveUp();
                        break;

            case "down":
                        if(!cCheck.collideSolidAfterDown(obj))
                            obj.moveDown();
                        break;
                
            case "left":
                        if(!cCheck.collideSolidAfterLeft(obj))
                            obj.moveLeft();
                        break;

            case "right":
                        if(!cCheck.collideSolidAfterRight(obj))
                            obj.moveRight();
                        break;

            default:
                    System.out.println("Invalid direction!");
                    break;
        }
    }


    public void randomMove(Character monster)
    {
        Random random = new Random();
        int ranNum = random.nextInt(100) + 1;       //1,2,3,...,100

        if (ranNum <= 25) 
        {
            if(!cCheck.monsterCollideMonsterAfter("up", monster, this.monsters))
            {
                characterMove("up", monster);
            }
            
        }
        else if (ranNum > 25 && ranNum <= 50)
        {
            if(!cCheck.monsterCollideMonsterAfter("down", monster, this.monsters))
            {
                characterMove("down", monster);
            }
        }
        else if(ranNum > 50 && ranNum <= 75)
        {
            if(!cCheck.monsterCollideMonsterAfter("left", monster, this.monsters))
            {
                characterMove("left", monster);
            }
        }
        else
        {
            if(!cCheck.monsterCollideMonsterAfter("right", monster, this.monsters))
            {
                characterMove("right", monster);
            }
        }
    }


    public void updateMonsters()
    {
        for(int i = 0; i < this.monsters.size(); i++)
        {
            randomMove(this.monsters.get(i));
        }
    }


    public void updateItems()
    {
        if(this.map.containItemAt(player.getX(), player.getY()) == true)
        {
            Item itemToPick = this.map.correspondingItemAt(player.getX(), player.getY());

            //Add item to inventory when picking
            this.inventory.items.add(itemToPick);

            //Remove the item (just be picked) from map
            this.map.items.remove(itemToPick);         
        }
    }


    public void updatePlayer()
    {
        map.tile[map.tileManager[player.getY()][player.getX()]].applyEffectTo(player);
    }


    public static void main(String[] args) 
    {
        GameMain game = new GameMain();
        game.start();
    }


}
