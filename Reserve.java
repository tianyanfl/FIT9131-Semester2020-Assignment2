import java.util.*;
import java.io.*;
/**
 * Write a description of class Reserve here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reserve {

    private ArrayList<ObservationPoints> points;
    private SafeHaven safeHaven;
    //random

    /**
     * Constructor for objects of class Reserve
     */
    public Reserve() {
        points = new ArrayList<ObservationPoints>();

        safeHaven = new SafeHaven();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public ObservationPoints getPoint(int index) {
        return points.get(index);
    }

    public SafeHaven getSafeHaven() {
        return safeHaven;
    }


    public ArrayList<ObservationPoints> getPoints() {
        return this.points;
    }

    public boolean sendKoala(int pointNumber) {
        ObservationPoints point = points.get(pointNumber);
        for (int i = 0; i < point.getKoalaList().size(); i++) {
            if (point.getKoalaList().get(i).getStatus().equals("injured")) {
                safeHaven.setKoala(point.getKoala(i));
                // set safeHaven
                // delect
                point.getKoalaList().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean sendHealthyKoala(int pointNumber) {
        ObservationPoints point = points.get(pointNumber);
        for (int i = 0; i < point.getKoalaList().size(); i++) {
            if (point.getKoalaList().get(i).getStatus().equals("healthy")) {
                safeHaven.setKoala(point.getKoala(i));
                // set safeHaven
                // delect
                point.getKoalaList().remove(i);
                return true;
            }
        }
        return false;
    }

    public void readTreeInformation() {
        try {
            FileReader readFile = new FileReader("trees.txt");
            Scanner scanner = new Scanner(readFile);

            while (scanner.hasNextLine()) {
                ObservationPoints ob = new ObservationPoints();
                String everyLine = scanner.nextLine();
                String[] transferString = everyLine.split(",");
                for (int i = 0; i < transferString.length; i++) {
                    int number = Integer.parseInt(transferString[i]);
                    // build tree
                    ob.addOneMoreTreeList(i, number);// a op

                }
                points.add(ob);

            }
            readFile.close();
        } catch (Exception e) {
            System.out.println("Can not find file");
        } finally {

        }
    }

    public void setKoalas(int pointNumber) {
        RandomNumber rm = new RandomNumber();
        int healthyNumber = rm.getGenerateRandomNumber(9, 0);
        for (int i = 0; i < healthyNumber; i++) {
            //每次生成年龄
            int age = rm.getGenerateRandomNumber(18, 1);
            points.get(pointNumber).addOneMoreKoalaList("healthy", age);
        }
        int injuredNumber = rm.getGenerateRandomNumber(2, 0);
        for (int i = 0; i < injuredNumber; i++) {
            int age = rm.getGenerateRandomNumber(18, 1);
            points.get(pointNumber).addOneMoreKoalaList("injured", age);
        }
    }

    //choose c send koala to ob
    public boolean relocateKoala(int pointNumber) {
        ObservationPoints point = points.get(pointNumber);
        if (point.checkCanMoveToHere() && pointNumber >= 1) {
            int oldestKoala = safeHaven.findOldestHealthyKoala();
            if (oldestKoala >= 0) {
                points.get(pointNumber).setOneKoala(safeHaven.getKoala(oldestKoala));
                safeHaven.deleteKoala(oldestKoala);
                safeHaven.setRelocate(safeHaven.getRelocate() + 1);
                return true;
            }
        } else //加什么原因不能移动
        {
            System.out.println("Sorry you can not move it");
        }

        return false;
    }

    public void setPredators(int pointNumber) {
        RandomNumber rm = new RandomNumber();
        int numberOfPredators = rm.getGenerateRandomNumber(4, 0);
        points.get(pointNumber).setPredatorsNumber(numberOfPredators);
    }

    public int getPointsNumberDetails() {
        return points.size();
    }

    //public SafeHaven getSafeHaven()
    //{
    //   return safeHaven;
    //}

    public int shortageFood(int pointNumber) {
        int totalDeath = 0;
        int exceed = points.get(pointNumber).countHowManyDueToFood();
        for (int i = 0; i < exceed; i++) {
            if (points.get(pointNumber).koalaDieDueToFood()) {
                totalDeath += 1;
            }
        }
        return totalDeath;
    }

    public int shortageOfShelter(int pointNumber) {
        int totalDeath = 0;
        int exceed = points.get(pointNumber).countHowManyDueToShelter();
        for (int i = 0; i < exceed; i++) {
            if (points.get(pointNumber).koalaDieDueToShelter()) {
                totalDeath += 1;
            }
        }
        return totalDeath;
    }

    public int predators(int pointNumber) {
        if (points.get(pointNumber).getPredatorsNumber() > 3){
            if (points.get(pointNumber).koalaDieDueToPredators()) {
                return 1;
            }
        }
        return 0;
    }

    public int treesTotal() {
        int count = 0;
        for (int i = 0; i < points.size(); i++) {
            count += points.get(i).getTreeList().size();
        }
        return count;
    }

    public int healthyKoalaTotal() {
        int count = 0;
        for (int i = 0; i < points.size(); i++) {
            count += points.get(i).countHowManyhealthyKoalas();
        }

        count += safeHaven.countHowManyhealthyKoalas();

        return count;
    }

    public int countHowManyInjuredKoalas() {
        return safeHaven.countHowManyinjuredKoalas();
    }
}
