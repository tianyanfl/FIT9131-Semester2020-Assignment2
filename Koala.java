
/**
 * Write a description of class Koala here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Koala
{
    // instance variables - replace the example below with your own
    private String status;
    private int age;

    /**
     * Constructor for objects of class Koala
     */
    public Koala()
    {
        // initialise instance variables
        status = "healthy";
        age = 0;
    }

    public Koala(String status, int age)
    {
        // initialise instance variables
        this.status = status;
        this.age = age;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setStatus(String status)
    {
        // put your code here
        this.status = status;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    public int getAge()
    {
        return this.age;
    }
}
