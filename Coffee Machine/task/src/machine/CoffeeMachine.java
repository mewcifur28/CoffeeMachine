package machine;
import java.util.Scanner;

public class CoffeeMachine {
    enum MachineResource {
        WATER, MILK, COFFEE_BEANS, DISPOSABLE_CUPS, CASH
    }

    static int[] resourceLevels;
    static int[] espresso;
    static int[] latte;
    static int[] cappuccino;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        initValues();
        String query;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        query = scanner.next();
        System.out.println();

        boolean breakLoop = query.equals("exit");

        while (!breakLoop) {

            switch (query) {
                case "buy":
                    makeCoffee();
                    break;

                case "fill":
                    fillResources();
                    break;

                case "take":
                    emptyCashDrawer();
                    break;

                case "remaining":
                    printResourcesLevel();
                    break;

                case "exit":
                    breakLoop = true;
                    break;
            }

            if (breakLoop) {
                break;
            }

            System.out.println("Write action (buy, fill, take, remaining, exit):");
            query = scanner.next();
        }
    }

    public static void initValues() {

        resourceLevels = new int[5]; // {400, 540, 120, 9, 550}
        resourceLevels[MachineResource.WATER.ordinal()] = 400;
        resourceLevels[MachineResource.MILK.ordinal()] = 540;
        resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] = 120;
        resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] = 9;
        resourceLevels[MachineResource.CASH.ordinal()] = 550;

        espresso = new int[5]; // {250, 0, 16, 1, 4}
        espresso[MachineResource.WATER.ordinal()] = 250;
        espresso[MachineResource.MILK.ordinal()] = 0;
        espresso[MachineResource.COFFEE_BEANS.ordinal()] = 16;
        espresso[MachineResource.DISPOSABLE_CUPS.ordinal()] = 1;
        espresso[MachineResource.CASH.ordinal()] = 4;

        latte = new int[5]; // {350, 75, 20, 1, 7}
        latte[MachineResource.WATER.ordinal()] = 350;
        latte[MachineResource.MILK.ordinal()] = 75;
        latte[MachineResource.COFFEE_BEANS.ordinal()] = 20;
        latte[MachineResource.DISPOSABLE_CUPS.ordinal()] = 1;
        latte[MachineResource.CASH.ordinal()] = 7;

        cappuccino = new int[5]; // {200, 100, 12, 1, 6}
        cappuccino[MachineResource.WATER.ordinal()] = 200;
        cappuccino[MachineResource.MILK.ordinal()] = 100;
        cappuccino[MachineResource.COFFEE_BEANS.ordinal()] = 12;
        cappuccino[MachineResource.DISPOSABLE_CUPS.ordinal()] = 1;
        cappuccino[MachineResource.CASH.ordinal()] = 6;
    }

    public static void printResourcesLevel() {

        System.out.println("The coffee machine has:");
        System.out.println(resourceLevels[MachineResource.WATER.ordinal()] + " ml of water");
        System.out.println(resourceLevels[MachineResource.MILK.ordinal()] + " ml of milk");
        System.out.println(resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] + " g of coffee beans");
        System.out.println(resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] + " disposable cups");
        System.out.println("$" + resourceLevels[MachineResource.CASH.ordinal()] + " of money");
        System.out.println();
    }

    public static void emptyCashDrawer() {
        System.out.println("I gave you $" + resourceLevels[MachineResource.CASH.ordinal()]);
        System.out.println();
        resourceLevels[MachineResource.CASH.ordinal()] = 0;
    }

    public static void fillResources() {
        int resourceQuantityAdded;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water you want to add: ");
        resourceQuantityAdded = scanner.nextInt();
        resourceLevels[MachineResource.WATER.ordinal()] += resourceQuantityAdded;

        System.out.println("Write how many ml of milk you want to add: ");
        resourceQuantityAdded = scanner.nextInt();
        resourceLevels[MachineResource.MILK.ordinal()] += resourceQuantityAdded;

        System.out.println("Write how many grams of coffee beans you want to add: ");
        resourceQuantityAdded = scanner.nextInt();
        resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] += resourceQuantityAdded;

        System.out.println("Write how many disposable cups of coffee you want to add: ");
        resourceQuantityAdded = scanner.nextInt();
        resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] += resourceQuantityAdded;
    }

    public static void makeCoffee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String choice = scanner.next();
        MachineResource sparseResource = MachineResource.CASH;

        switch(choice) {
            case "1":
                if (resourceLevels[MachineResource.WATER.ordinal()] < espresso[MachineResource.WATER.ordinal()]) {
                    sparseResource = MachineResource.WATER;
                } else if (resourceLevels[MachineResource.MILK.ordinal()] < espresso[MachineResource.MILK.ordinal()]) {
                    sparseResource = MachineResource.MILK;
                } else if (resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] < espresso[MachineResource.COFFEE_BEANS.ordinal()]) {
                    sparseResource = MachineResource.COFFEE_BEANS;
                } else if (resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] < espresso[MachineResource.DISPOSABLE_CUPS.ordinal()]) {
                    sparseResource = MachineResource.DISPOSABLE_CUPS;
                } else {
                    resourceLevels[MachineResource.WATER.ordinal()] -= espresso[MachineResource.WATER.ordinal()];
                    resourceLevels[MachineResource.MILK.ordinal()] -= espresso[MachineResource.MILK.ordinal()];
                    resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] -= espresso[MachineResource.COFFEE_BEANS.ordinal()];
                    resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] -= espresso[MachineResource.DISPOSABLE_CUPS.ordinal()];
                    resourceLevels[MachineResource.CASH.ordinal()] += espresso[MachineResource.CASH.ordinal()];
                }
                break;

            case "2":
                if (resourceLevels[MachineResource.WATER.ordinal()] < latte[MachineResource.WATER.ordinal()]) {
                    sparseResource = MachineResource.WATER;
                } else if (resourceLevels[MachineResource.MILK.ordinal()] < latte[MachineResource.MILK.ordinal()]) {
                    sparseResource = MachineResource.MILK;
                } else if (resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] < latte[MachineResource.COFFEE_BEANS.ordinal()]) {
                    sparseResource = MachineResource.COFFEE_BEANS;
                } else if (resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] < latte[MachineResource.DISPOSABLE_CUPS.ordinal()]) {
                    sparseResource = MachineResource.DISPOSABLE_CUPS;
                } else {
                    resourceLevels[MachineResource.WATER.ordinal()] -= latte[MachineResource.WATER.ordinal()];
                    resourceLevels[MachineResource.MILK.ordinal()] -= latte[MachineResource.MILK.ordinal()];
                    resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] -= latte[MachineResource.COFFEE_BEANS.ordinal()];
                    resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] -= latte[MachineResource.DISPOSABLE_CUPS.ordinal()];
                    resourceLevels[MachineResource.CASH.ordinal()] += latte[MachineResource.CASH.ordinal()];
                }
                break;

            case "3":
                if (resourceLevels[MachineResource.WATER.ordinal()] < cappuccino[MachineResource.WATER.ordinal()]) {
                    sparseResource = MachineResource.WATER;
                } else if (resourceLevels[MachineResource.MILK.ordinal()] < cappuccino[MachineResource.MILK.ordinal()]) {
                    sparseResource = MachineResource.MILK;
                } else if (resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] < cappuccino[MachineResource.COFFEE_BEANS.ordinal()]) {
                    sparseResource = MachineResource.COFFEE_BEANS;
                } else if (resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] < cappuccino[MachineResource.DISPOSABLE_CUPS.ordinal()]) {
                    sparseResource = MachineResource.DISPOSABLE_CUPS;
                } else {
                    resourceLevels[MachineResource.WATER.ordinal()] -= cappuccino[MachineResource.WATER.ordinal()];
                    resourceLevels[MachineResource.MILK.ordinal()] -= cappuccino[MachineResource.MILK.ordinal()];
                    resourceLevels[MachineResource.COFFEE_BEANS.ordinal()] -= cappuccino[MachineResource.COFFEE_BEANS.ordinal()];
                    resourceLevels[MachineResource.DISPOSABLE_CUPS.ordinal()] -= cappuccino[MachineResource.DISPOSABLE_CUPS.ordinal()];
                    resourceLevels[MachineResource.CASH.ordinal()] += cappuccino[MachineResource.CASH.ordinal()];
                }
                break;

            case "back":
                return;
        }

        // output depending on sparseResource
        if (sparseResource == MachineResource.CASH) {
            System.out.println("I have enough resources, making you a coffee!");

        } else if(sparseResource == MachineResource.WATER) {
            System.out.println("Sorry, not enough water!");

        }  else if(sparseResource == MachineResource.MILK) {
            System.out.println("Sorry, not enough milk!");

        } else if(sparseResource == MachineResource.COFFEE_BEANS) {
            System.out.println("Sorry, not enough coffee beans!");

        } else {
            System.out.println("Sorry, not enough disposable cups!");

        }

        System.out.println();
    }
}
