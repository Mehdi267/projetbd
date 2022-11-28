package grenobleeat.database;


public class Plat {
    public static void printMealsList(){
        String mealFields[] = {"nomplat", "descplat", "prixplat"};
        String mealList = JavaConnectorDB.printTableElementList("plat", mealFields);
        System.out.println(mealList);
    }
}
