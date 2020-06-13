
/**
 * Write a description of class Tree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tree
{
    // instance variables - replace the example below with your own
    private int number;
    private String type;
    // private double weightLeaves;
    /**
     * Constructor for objects of class Tree
     */
    public Tree()
    {
        // initialise instance variables
        this.type = "";
        this.number = 0;
    }

    /**
     * Constructor for objects of class Tree
     */
    public Tree(String type, int number)
    {
        // initialise instance variables
        this.type = type;
        this.number = number;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setType(String type)
    {
        // put your code here
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return this.number;
    }

    public double eachTreeCanCreate()
    {
        double kgLeavesPerDay = 0.0;
        if (type.equals("Manna Gum"))
            kgLeavesPerDay = 1.00;
        else if (type.equals("Swamp Gum"))
            kgLeavesPerDay = 0.34;
        else if (type.equals("Blue Gum"))
            kgLeavesPerDay = 0.9;
        else if (type.equals("River Red Gum"))
            kgLeavesPerDay = 0.4;

        double totalFood = kgLeavesPerDay * number;
        return totalFood;
    }


}
