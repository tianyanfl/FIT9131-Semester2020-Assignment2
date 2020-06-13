
/**
 * Write a description of class RandomNumber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomNumber
{
    private int maximumNumber;
    private int minimumNumber;
    /**
     *  A non-parameterised constructor for objects of class RandomNumber
     */
    public RandomNumber()
    {

        this.maximumNumber = 0;
        this.minimumNumber = 0;
    }

    /**
     *  Parameterised constructor for objects of class RandomNumber
     */
    public RandomNumber(int maximumNumber, int minimumNumber)
    {
        this.maximumNumber = maximumNumber;
        this.minimumNumber = minimumNumber;
    }

    /**
     * A method for generating random Numbers
     *
     * @return a random number
     */
    public int getGenerateRandomNumber(int maximumNumber, int minimumNumber)
    {
        return (int)(Math.random() * (maximumNumber - minimumNumber +1) + minimumNumber);
    }

    public int getMaximumNumber()
    {
        return maximumNumber;
    }

    public int getMinimumNumber()
    {
        return minimumNumber;
    }

    /**
     * Mutator method
     *
     * @param  maximumNumber
     */
    public void setMaximumNumber(int maximumNumber)
    {
        this.maximumNumber = maximumNumber;
    }

    public void setMinimumNumber(int minimumNumber)
    {
        this.minimumNumber = minimumNumber;
    }
}