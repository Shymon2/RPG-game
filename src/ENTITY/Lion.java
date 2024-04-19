package ENTITY;

public class Lion extends Character
{



//-----------------------------------------------

    //Constructor
    public Lion(int x, int y)
    {
        super("Lion", 50, 50, 15, 15, x, y, 5);
    }

    @Override
    public String getMark() 
    {
        return "L";
    }   
}
