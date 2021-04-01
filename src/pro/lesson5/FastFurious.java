package pro.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FastFurious {
        public static final int CARS_COUNT = 4;
        public static CountDownLatch cdlStartRace = new CountDownLatch(CARS_COUNT);
        public static CountDownLatch cdlFinishRace = new CountDownLatch(CARS_COUNT);
        public static CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        public static Semaphore semaphore = new Semaphore(CARS_COUNT / 2);
        public static Lock winnerLock = new ReentrantLock();
        public static void main(String[] args) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            }

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }

            try {
                cdlStartRace.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                cdlFinishRace.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

