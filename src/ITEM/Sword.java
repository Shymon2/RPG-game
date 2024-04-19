package ITEM;

public class Sword extends Weapon
{

//--------------------------------------------

    //Constructor
    public Sword(int x, int y)
    {
        super("Sword", x, y, 10, 0, 3, 0);
    }


    @Override
    public String getMark()
    {return "S";}

    @Override
    public int getType()
    {return 1;}

    @Override
    public String toString(){
        return this.getName() + "[Atk: " + this.getAtk() + ", Def: " + this.getDef() + ", Range: " + this.getRange() + ", HP: " + this.getHp() + "]";
    }

    
}
