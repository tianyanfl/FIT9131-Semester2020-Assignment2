import java.util.*;
import java.io.*;
/**
 * Write a description of class KoalaRescue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KoalaRescue {

    private String leaderName;//
    private int budget;
    private Reserve reserve;
    private Scanner sc;

    /**
     * Constructor for objects of class KoalaRescue
     */
    public KoalaRescue() {
        // initialise instance variables
        leaderName = "";
        budget = 0;
        reserve = new Reserve();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        KoalaRescue koalaRescue = new KoalaRescue();
        koalaRescue.startSimulation();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public void startSimulation() {
        setName();
        setBudget();
        int totalDeath = 0;
        int treeLost=0;
        reserve.readTreeInformation();

        for (int i = 0; i < reserve.getPointsNumberDetails(); i++) {
            reserve.setKoalas(i);
        }

        for (int i = 0; i < 10; i++) {
            reserve.setPredators(i);
        }

        int originalTreeNumber = reserve.treesTotal();
        int originalBudget = budget;
        for (int i = 0; i < reserve.getPoints().size(); i++) {
//            int originalKoalaNumber = reserve.getPoints().get(i).getKoalaList().size();
            System.out.println("You are observating no." + (i + 1) + " observation point");
            treeLost +=  observationPointAfterTreeDie(i);
            displayPointInfo(i);
            int pointDead = doAction(i);
            totalDeath += pointDead;

//            int currentKoalaNumber = reserve.getPoints().get(i).getKoalaList().size();
            System.out.println("Dead Koala Count is " + pointDead);
            System.out.println("Current budget is " + budget);
        }

        int originalHealthyNumber = reserve.healthyKoalaTotal();
        int injuredInHaven = reserve.countHowManyInjuredKoalas();
        int relocateKoalaNumber = reserve.getSafeHaven().getRelocate();
        double spent = originalBudget - budget;

        displayAfterWork(treeLost, originalHealthyNumber, injuredInHaven, relocateKoalaNumber, spent);
        //check any koala die
        //if ()
        //{
        //}

        //写文件
        writeResult(originalTreeNumber);
    }

    public ArrayList<String> getAllTrees() {
        ArrayList<String> allTrees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String info = "";
            for (int j = 0; j < 5; j++) {
                info += this.reserve.getPoints().get(i).getAmountOfTree(j) + ",";
            }
            allTrees.add(info.substring(0, (info.length() - 1)));
        }
        return allTrees;
    }

    public void writeResult(int number) {
        ArrayList<String> allTrees = getAllTrees();
        try {
            PrintWriter fw = new PrintWriter("updatedTrees.txt ");
            for (String s : allTrees) {
                fw.println(s);
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Can not find the file");
        } finally {

        }
    }

    public int observationPointAfterTreeDie(int pointNumber) {
        int deadTree = 0;
        for (int i = 0; i < reserve.getPoints().get(pointNumber).getTreeList().size(); i++)
        //5
        {
            Tree tree = reserve.getPoints().get(pointNumber).getTreeList().get(i);
            if (tree.getNumber() > 0 && treeDie()) {
                System.out.println("A tree damages");
                tree.setNumber(tree.getNumber() - 1);
                deadTree++;
            }
            //else
            //{
            //System.out.println("no die");
            //}
        }
        return deadTree;
    }

    public boolean treeDie() {
        RandomNumber rm = new RandomNumber();
        int randomNumber = rm.getGenerateRandomNumber(100, 1);
        if (randomNumber >= 1 && randomNumber <= 5) {
            return true;
        } else
            return false;
    }

    public void displayAfterWork(int lossTreeNumber, int healthKoaLaNumber, int injuredMoveNumber, int relocated, double remainBudget) {
        System.out.println("================================");
        System.out.println("Thank you for your rescue action");
        System.out.println("Number of trees lost: " + lossTreeNumber);
        System.out.println("Healthy Koalas number: " + healthKoaLaNumber);
        System.out.println("Injured koalas taken to the safe haven: " + injuredMoveNumber);
        System.out.println("Relocated Number is: " + relocated);
        System.out.println("Amount spent: " + remainBudget);
    }

    public void displayActionMenu() {
        System.out.println("================================");
        System.out.println("You can choose the next options");
        System.out.println("A:move an injured koala to safehaven");
        System.out.println("B:move a safe koala to safehaven");
        System.out.println("C:relocate a koala to this location ");
        System.out.println("D:take no actions");
    }

    public boolean choose(String choose) {
        if (!choose.equalsIgnoreCase("A") && !choose.equalsIgnoreCase("B")
                && !choose.equalsIgnoreCase("C") && !choose.equalsIgnoreCase("D")) {
            System.out.println("action input error, input again");
            return false;
        }
        return true;
    }

    public int doAction(int pointNumber) {

        displayActionMenu();

        //input choice
        String choose = "";
        while (true) {
            //input choice
            do {
                choose = sc.next();
            } while (!choose(choose));

            if (choose.equalsIgnoreCase("A")) {
                if (budget >= 20 && reserve.sendKoala(pointNumber)) {
                    budget = budget - 20;
                }
            }//验证字符
            else if (choose.equalsIgnoreCase("B")) {
                if (budget >= 10 && reserve.sendHealthyKoala(pointNumber)) {
                    budget = budget - 10;
                }
            } else if (choose.equalsIgnoreCase("C")) {
                //int oldestKoalaIndex = reserve.getSafeHaven().findOldestKoala();
                if (budget >= 5 && reserve.relocateKoala(pointNumber)) {
                    budget = budget + 5;
                }
            } else {
                break;
            }
            displayPointInfo(pointNumber);
        }

        System.out.println("take no actions");
        int totalDeath = noAction(pointNumber);
        return totalDeath;
    }

    public int noAction(int pointNumber) {
        int total = reserve.getPoints().get(pointNumber).allInjuredDie();
        total += reserve.shortageFood(pointNumber);
        total += reserve.shortageOfShelter(pointNumber);
        total += reserve.predators(pointNumber);
        return total;
    }

    public void displayPointInfo(int pointNumber) {
        System.out.println("================================");
        System.out.println("The number of injured koalas " + reserve.getPoints().get(pointNumber).countHowManyInjuredKoalas());
        System.out.println("The number of healthy koalas " + reserve.getPoints().get(pointNumber).countHowManyhealthyKoalas());
        System.out.println("The weight of available food " + reserve.getPoints().get(pointNumber).countFood());
        System.out.println("The number of shelter tree " + reserve.getPoints().get(pointNumber).countHowManyShelter());
        System.out.println("The number of predators " + reserve.getPoints().get(pointNumber).getPredatorsNumber());
        System.out.println("The available budget " + budget);
    }

    public void setName() {
        while (!validateName(leaderName)) {
            System.out.println("Welcome, please enter your name");
            leaderName = sc.next();
        }
    }

    public void setBudget() {
        do {
            System.out.println("Please set your budget from 100 to 200");
            try {
                budget = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please insert a correct budget");
            }
        } while (validateBudget(budget) == false);
    }

    public boolean validateBudget(int budget) {
        if (budget >= 200 || budget <= 100)
            return false;
        else
            return true;
    }

    public boolean validateName(String name) {
        if (name.trim().length() >= 16 || name.trim().length() == 0)
            return false;
        else {
            for (int i = 0; i < name.trim().length(); i++) {
                if (name.toUpperCase().charAt(i) > 'Z' || name.toUpperCase().charAt(i) < 'A')
                    return false;
            }
        }
        return true;
    }
}
