package ITEM;

public class Axe extends Weapon
{

//------------------------------------

    //Constructor
    public Axe(int x, int y)
    {
        super("Axe", x, y, 30, 0, 1, 0);
    }

    @Override
    public String getMark()
    {return "A";}

    @Override
    public int getType()
    {return 1;}

    @Override
    public String toString(){
        return this.getName() + "[Atk: " + this.getAtk() + ", Def: " + this.getDef() + ", Range: " + this.getRange() + ", HP: " + this.getHp() + "]";
    }
}
