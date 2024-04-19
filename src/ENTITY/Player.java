package ENTITY;
import ITEM.Axe;
import ITEM.Item;
import ITEM.Weapon;

public class Player extends Character
{
    private Item currentWeapon = null;
    private Item currentArmor = null;
//-------------------------------------------

    //Constructor
    public Player(String name, int hp, int maxhp, int at, int def, int range)
    {
        super(name, hp, maxhp, at, def, 0, 0, range);
    }

    @Override
    public String getMark()
    {return "X";}

    public String getCurrentWeapon(){
        if(currentWeapon == null){
            return "No weapon";
        }
        else return ((Weapon)currentWeapon).toString();
    }
    public String getCurrentArmor(){
        if(currentArmor == null){
            return "No weapon";
        }
        else return ((Weapon)currentArmor).toString();
    }
    //Show state of player
    public void showState()
    {
        System.out.println("HP: " + this.getHP() + " / " + this.getMaxHp());
        System.out.println("Atk: " + this.getAttack());
    }
    //Unequip item
    public void unEquipItem(Item item){
        boolean isAtkWeapon;
            if(item.getType() == 3) 
                isAtkWeapon = false;
            else 
                isAtkWeapon = true;
        if(isAtkWeapon){
            if(currentWeapon != null){        
                this.setAttack(this.getAttack() - ((Weapon)item).getAtk());              
                this.setRange(this.getRange() - ((Weapon)item).getRange());
                currentWeapon = null;
            }
        }
        else{
            if (currentArmor != null) {
                this.setMaxHp(this.getMaxHp() - ((Weapon)item).getHp());
                if(this.getHP() > this.getMaxHp())
                    this.setHp(this.getMaxHp());               
                this.setDefense(this.getDefense() - ((Weapon)item).getDef());               
                currentArmor = null;
            }
        } 
    }

    //Equip weapon
    public void equipItem(Item item){       
        boolean isAtkWeapon;
            if(item.getType() == 3) 
                isAtkWeapon = false;
            else 
                isAtkWeapon = true;
        unEquipItem(item);
        if(isAtkWeapon){
            currentWeapon = item;
            this.setAttack(this.getAttack() + ((Weapon)item).getAtk());
            this.setRange(this.getRange() + ((Weapon)item).getRange());
        }
        else{
            this.setMaxHp(this.getMaxHp() + ((Weapon)item).getHp());
            this.setDefense(this.getDefense() + ((Weapon)item).getDef()); 
        }
           
    }
    public static void main(String arg[]){
        Player p1 = new Player("hero", 100, 100, 10, 10, 1);
        Item i1 = new Axe(0, 0);
        p1.equipItem(i1);
        p1.showState();
        System.out.println(p1.getCurrentWeapon());
        p1.unEquipItem(i1);
        p1.showState();
        System.out.println(p1.getCurrentWeapon());
    }
}
