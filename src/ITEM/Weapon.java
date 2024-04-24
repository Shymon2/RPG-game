package ITEM;

public class Weapon extends Item
{      
    private int attack;
    private int defense;
    private int range;
    private int hp;

//-------------------------------------

    //Constructor
    public Weapon(String name, int x, int y, int attack, int defense, int range, int hp)
    {
        super(name, x, y);
        this.attack = attack;
        this.defense = defense;
        this.range = range;
        this.hp = hp;
    }

    // Getter
    public int getAtk(){
        return attack;
    }
    public int getDef(){
        return defense;
    }
    public int getRange(){
        return range;
    }
    public int getHp(){
        return hp;
    }
    

    @Override
    public String getMark(){return "WP";}

    @Override
    public int getType()
    {return -1;}

    
}
