import java.util.*;
/**
 * Write a description of class SafeHaven here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SafeHaven {

    private ArrayList<Koala> koalas;//list
    private int relocate;

    /**
     * Constructor for objects of class SafeHaven
     */
    public SafeHaven() {
        // initialise instance variables
        koalas = new ArrayList<Koala>();
        relocate = 0;
    }


    public int getRelocate() {
        return relocate;
    }

    public void setRelocate(int relocate) {
        this.relocate = relocate;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public Koala getKoala(int index) {
        return this.koalas.get(index);
    }

    public void deleteKoala(int index) {
        koalas.remove(index);
    }

    public ArrayList<Koala> getKoalaList() {
        return this.koalas;
    }

    public void setKoala(Koala koala) {
        this.koalas.add(koala);
    }

    public int findOldestHealthyKoala() {
        int oldAge = 0;
        int oldestIndex = -1;
        for (int i = 0; i < koalas.size(); i++) {
            if (koalas.get(i).getStatus().equals("healthy") && koalas.get(i).getAge() > oldAge) {
                oldAge = koalas.get(i).getAge();
            }
        }

        for (int i = 0; i < koalas.size(); i++) {
            if (koalas.get(i).getStatus().equals("healthy") && koalas.get(i).getAge() == oldAge) {
                oldestIndex = i;
                break;
            }
        }
        //x
        return oldestIndex;
    }

    public int countHowManyhealthyKoalas() {
        int count = 0;
        for (int i = 0; i < koalas.size(); i++) {
            if (koalas.get(i).getStatus().equals("healthy"))
                count++;
        }
        return count;
    }

    public int countHowManyinjuredKoalas() {
        int count = 0;
        for (int i = 0; i < koalas.size(); i++) {
            if (koalas.get(i).getStatus().equals("injured"))
                count++;
        }
        return count;
    }

}
