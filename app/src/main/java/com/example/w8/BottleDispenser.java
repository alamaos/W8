package com.example.w8;

import java.util.*;

public class BottleDispenser {
    private ArrayList<Bottle> bottle_array;
    private int bottles;
    private double money;
    private ArrayList<String> bottlesToString;

    private static BottleDispenser bd_machine = new BottleDispenser();

    public BottleDispenser() {
        bottles = 5;
        money = 0;

        bottle_array = new ArrayList<>();
        bottle_array.add(new Bottle("Pepsi Max","Pepsi",0.3,0.5,1.8));
        bottle_array.add(new Bottle("Pepsi Max","Pepsi",0.3,1.5,2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero","Coca-Cola",0.3,0.5,2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero","Coca-Cola",0.3,1.5,2.5));
        bottle_array.add(new Bottle("Fanta Zero","Coca-Cola",0.3,0.5,1.95));
    }
    public static BottleDispenser getInstance(){
        return bd_machine;
    }
    public int getBottles(){
        return bottles;
    }

    public ArrayList<String> getBottlesString() {
        bd_machine.stringBottles();
        return bottlesToString;
    }

    public ArrayList<Bottle> getBottle_array() {
        return bottle_array;
    }
    public void setBottles(int bottles){
        this.bottles = bottles;
    }
    public double getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }
    public void addMoney(double adding) {
        money += adding;
        System.out.println("Klink! Added more money!");
    }

    public int buyBottle(int dc) {
        if (bottles > 0) {
            if (money >= bottle_array.get(dc).getPrice()) {
                bottles -= 1;
                money -= bottle_array.get(dc).getPrice();
                return 1;
            } else {
                System.out.println("Add money first!");
                return 2;
            }
        }else {
            System.out.println("Sorry! Out of bottles!");
            return 3;
        }
    }

    public void returnMoney() {
        if (money > 0) {
            System.out.printf("Klink klink. Money came out! You got %.2f€ back",  money);
            money = 0;
        } else {
            money = 0;
            System.out.println("Klink klink!! All money gone!");
        }
    }
    public void listBottles(){
        for (int i = 0; i< bottles;i++) {
            Bottle d = bottle_array.get(i);
            System.out.println(i+1 + ". Name: "+d.getName());
            System.out.print("  Size: "+d.getSize());
            System.out.println("    Price: "+d.getPrice());
        }
    }
    public void stringBottles(){
        bottlesToString = new ArrayList<>();
        int a = 1;
        for (Bottle i : bottle_array){
            bottlesToString.add(a + "." + i.getName()+" "+ i.getSize()+ "l "+ i.getPrice()+"€");
            a++;
        }
    }
}