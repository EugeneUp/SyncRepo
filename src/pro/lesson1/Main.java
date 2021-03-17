package pro.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] array = new String[]{"Hello", "world", "and", "all", "people"};
        swapItems(array, 0, 4);
        System.out.println(convertToArrayList(array));

        Apple firstApple = new Apple();
        Apple secondApple = new Apple();
        Orange firstOrange = new Orange();
        Orange secondOrange = new Orange();

        Box<Apple> appleBoxFirst = new Box<>();
        Box<Apple> appleBoxSecond = new Box<>();
        Box<Orange> orangeBoxFirst = new Box<>();
        Box<Orange> orangeBoxSecond = new Box<>();

        appleBoxFirst.addFruit(firstApple);
        appleBoxFirst.addFruit(secondApple);
        appleBoxSecond.addFruit(firstApple);
        appleBoxSecond.addFruit(secondApple);
        appleBoxSecond.addFruit(secondApple);
        orangeBoxFirst.addFruit(firstOrange);
        orangeBoxFirst.addFruit(secondOrange);
        orangeBoxFirst.addFruit(secondOrange);
        orangeBoxSecond.addFruit(firstOrange);
        orangeBoxSecond.addFruit(secondOrange);

        System.out.println("AppleBoxFirst.Weight = " + appleBoxFirst.getWeight());
        System.out.println("AppleBoxSecond.Weight = " + appleBoxSecond.getWeight());
        System.out.println("OrangeBoxFirst.Weight = " + orangeBoxFirst.getWeight());
        System.out.println("OrangeBoxSecond.Weight = " + orangeBoxSecond.getWeight());

        System.out.println("compare appleBox2 to orangeBox2: " + appleBoxSecond.compare(orangeBoxSecond));
        System.out.println("compare orangeBox1 to appleBox1: " + orangeBoxFirst.compare(appleBoxFirst));
        System.out.println("compare orangeBox1 to orangeBox2: " + orangeBoxFirst.compare(orangeBoxSecond));
        System.out.println("compare appleBox1 to appleBox2: " + appleBoxFirst.compare(appleBoxSecond));
        appleBoxFirst.intersperse(appleBoxSecond);
        System.out.println("appleBoxFirst.intersperse(appleBoxSecond) size appleBoxFirst: " + appleBoxFirst.getSize());
        System.out.println("appleBoxFirst.intersperse(appleBoxSecond) size appleBoxSecond: " + appleBoxSecond.getSize());
        orangeBoxSecond.intersperse(orangeBoxFirst);
        System.out.println("orangeBoxSecond.intersperse(orangeBoxFirst) size orangeBoxSecond: " + orangeBoxSecond.getSize());
        System.out.println("orangeBoxSecond.intersperse(orangeBoxFirst) size orangeBoxFirst: " + orangeBoxFirst.getSize());

        System.out.println("AppleBoxFirst.Weight = " + appleBoxFirst.getWeight());
        System.out.println("AppleBoxSecond.Weight = " + appleBoxSecond.getWeight());
        System.out.println("OrangeBoxFirst.Weight = " + orangeBoxFirst.getWeight());
        System.out.println("OrangeBoxSecond.Weight = " + orangeBoxSecond.getWeight());
    }

    public static <T> void swapItems(T[] arr, int item1, int item2) {
        T temp = arr[item1];
        arr[item1] = arr[item2];
        arr[item2] = temp;
        System.out.println("swapItems(" + item1 + ", " + item2 +
                "): " + Arrays.asList(arr) + "\n");
    }

    public static <T> ArrayList<T> convertToArrayList(T[] array){
        return new ArrayList<>(Arrays.asList(array));
    }
}