package grenobleeat.database;

public class Restaurant{
    public static void printRestaurantList(){
        String restaurantFields[] = {"emailrest", "nomrest", "addrrest", "nbplacerest"};
        String restList = JavaConnectorDB.printTableElementList("restaurant", restaurantFields);
        System.out.println(restList);
    }
}
