import java.util.*;
/**
 * Write a description of class ObservationPoints here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ObservationPoints {
    // instance variables - replace the example below with your own
    private ArrayList<Koala> koalaList;
    private ArrayList<Tree> treeList;
    private int predatorsNumber;

    /**
     * Constructor for objects of class ObservationPoints
     */
    public ObservationPoints() {

        treeList = new ArrayList<Tree>();
        koalaList = new ArrayList<Koala>();
        predatorsNumber = 0;
    }

    public ObservationPoints(ArrayList<Tree> treeList, ArrayList<Koala> koalaList, int predatorsNumber) {
        // initialise instance variables
        this.treeList = treeList;
        this.koalaList = koalaList;
        this.predatorsNumber = predatorsNumber;
    }

    public int countHowManyInjuredKoalas() {
        int count = 0;
        for (int i = 0; i < koalaList.size(); i++) {
            if (koalaList.get(i).getStatus().equals("injured"))
                count++;
        }
        return count;
    }

    public double countFood() {
        double total = 0.0;
        for (int i = 0; i < treeList.size(); i++) {
            total = total + treeList.get(i).eachTreeCanCreate();
        }
        return total;
    }

    public int countHowManyhealthyKoalas() {
        int count = 0;
        for (int i = 0; i < koalaList.size(); i++) {
            if (koalaList.get(i).getStatus().equals("healthy"))
                count++;
        }
        return count;
    }

    public int countHowManyShelter() {
        int count = 0;
        for (int i = 0; i < treeList.size(); i++) {
            if (treeList.get(i).getType().equals("Wattle"))
                count = treeList.get(i).getNumber();
        }
        return count;
    }
    //public Tree buildTree(int number, int index)
    //{
    //   Tree tree = new Tree();
    //   if (index == 0)
    //      tree.setType("Manna Gum");
    //   else if (index == 1)
    //       tree.setType("Swap Gum");
    //   else if (index == 2)
    //        tree.setType("Blue Gum");
    //    else if (index == 3)
    //       tree.setType("River Red Gum");
    //   else 
    //       tree.setType("Wattle");
    //   tree.setNumber(number);

    //   return tree;
    //}

    public void addOneMoreTreeList(int index, int number) {
        Tree tree = new Tree();
        if (index == 0) {
            String treeTypeInfo = "Manna Gum";
            tree.setType(treeTypeInfo);
        } else if (index == 1)
            tree.setType("Swamp Gum");
        else if (index == 2)
            tree.setType("Blue Gum");
        else if (index == 3)
            tree.setType("River Red Gum");
        else
            tree.setType("Wattle");

        tree.setNumber(number);
        treeList.add(tree);
    }

    public int getAmountOfTree(int index) {
        String treeTypeInfo;
        if (index == 0)
            treeTypeInfo = "Manna Gum";
        else if (index == 1)
            treeTypeInfo = "Swamp Gum";
        else if (index == 2)
            treeTypeInfo = "Blue Gum";
        else if (index == 3)
            treeTypeInfo = "River Red Gum";
        else
            treeTypeInfo = "Wattle";
        for (Tree t : this.treeList) {
            if (t.getType().equals(treeTypeInfo))
               return t.getNumber();
        }
        return 0;
    }

    public void addOneMoreKoalaList(String healtyStatus, int age) {
        Koala koala = new Koala();
        koala.setStatus(healtyStatus);
        koala.setAge(age);

        koalaList.add(koala);
    }

    public void addOneKoalaList(String status, int age) {

    }

    public ArrayList<Tree> getTreeList() {
        return treeList;
    }

    public void setOneKoala(Koala koala) {
        koalaList.add(koala);
    }

    public Tree getTree(int index) {
        return treeList.get(index);
    }

    public ArrayList<Koala> getKoalaList() {
        return koalaList;
    }

    public Koala getKoala(int index) {
        return koalaList.get(index);
    }

    public int getPredatorsNumber() {
        return this.predatorsNumber;
    }

    public void setPredatorsNumber(int predatorsNumber) {
        this.predatorsNumber = predatorsNumber;
    }

    public boolean enoughFood() {
        //total food  > koala
        if (countFood() >= (koalaList.size() + 1)) {
            System.out.println("oldes koala move to here can have enough food");
            return true;
        }
        return false;
    }

    public boolean shelterEnough() {
        if (countHowManyShelter() >= (koalaList.size() + 1)) {
            System.out.println("oldes koala move to here can have enough shelter");
            return true;
        }
        return false;
    }

    public boolean checkCanMoveToHere() {
        //food enough shelter enough predators
        if (enoughFood() && shelterEnough() && predatorsNumber < 3)
            return true;
        else
            return false;
    }

    public int allInjuredDie() {
        ArrayList<Koala> removedList = new ArrayList<>();
        for (int i = 0; i < koalaList.size(); i++) {
            if (koalaList.get(i).getStatus().equals("injured"))
                removedList.add(koalaList.get(i));
        }
        koalaList.removeAll(removedList);
        return removedList.size();
    }

    public boolean koalaDieDueToFood() {
        if (countFood() < koalaList.size()) {
            // 80% happen
            RandomNumber rm = new RandomNumber();
            int shortageNumber = rm.getGenerateRandomNumber(100, 1);
            if (shortageNumber <= 80 && shortageNumber >= 1) {
                koalaList.remove(koalaList.size() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean koalaDieDueToShelter() {
        if (countHowManyShelter() < koalaList.size()) {
            // 20% happen
            RandomNumber rm = new RandomNumber();
            int shortageNumber = rm.getGenerateRandomNumber(100, 1);
            if (shortageNumber <= 20 && shortageNumber >= 1) {
                koalaList.remove(koalaList.size() - 1);
                return true;
            }
        }
        return false;
    }

    public boolean koalaDieDueToPredators() {
        // 50% happen
        RandomNumber rm = new RandomNumber();
        int shortageNumber = rm.getGenerateRandomNumber(100, 1);
        if (shortageNumber <= 50 && shortageNumber >= 1) {
            koalaList.remove(koalaList.size() - 1);
            return true;
        }
        return false;
    }

    public int countHowManyDueToFood() {
        int numberOfKoalaExcessFood = 0;
        if (countFood() < koalaList.size()) {
            numberOfKoalaExcessFood =  koalaList.size() - (int)countFood();
        }
        return numberOfKoalaExcessFood;
    }

    public int countHowManyDueToShelter() {
        int numberOfKoalaExcessShelter = 0;
        if (countHowManyShelter() < koalaList.size()) {
            numberOfKoalaExcessShelter = koalaList.size() - countHowManyShelter();
        }
        return numberOfKoalaExcessShelter;
    }

}
