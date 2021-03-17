package pro.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> box;

    public Box(T... fruits) {
        this.box = new ArrayList<>(Arrays.asList(fruits));
    }

    public int getSize() {
        return box.size();
    }

    public float getWeight() {
        float totalWeight = 0.0F;
        for (int i = 0; i < box.size(); i++) {
            totalWeight += box.get(i).getWeight();
        }
        return totalWeight;
    }

    public void addFruit(T fruit) {
        this.box.add(fruit);
    }

    public boolean compare(Box<? extends Fruit> boxToCompare) {
        return Math.abs(this.getWeight() - boxToCompare.getWeight()) >= 0
                && (Math.abs(this.getWeight() - boxToCompare.getWeight()) < 0.0001);
    }

    public void intersperse(Box<T> whereToIntersperse){
        whereToIntersperse.box.addAll(this.box);
        this.box.clear();
    }
}