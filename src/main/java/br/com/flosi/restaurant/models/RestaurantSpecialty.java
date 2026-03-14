package br.com.flosi.restaurant.models;

public enum RestaurantSpecialty {

    ITALIAN("Italian", "Pasta, pizza and traditional Italian cuisine"),
    JAPANESE("Japanese", "Sushi, ramen and traditional Japanese cuisine"),
    BRAZILIAN("Brazilian", "Traditional Brazilian dishes and churrasco"),
    MEXICAN("Mexican", "Tacos, burritos and traditional Mexican cuisine"),
    CHINESE("Chinese", "Traditional Chinese dishes and dim sum"),
    AMERICAN("American", "Burgers, sandwiches and American classics"),
    FRENCH("French", "Traditional French cuisine and pastries"),
    MEDITERRANEAN("Mediterranean", "Greek, Turkish and Mediterranean dishes"),
    SEAFOOD("Seafood", "Fresh fish and seafood specialties"),
    VEGETARIAN("Vegetarian", "Meat-free and plant-based dishes"),
    VEGAN("Vegan", "100% plant-based dishes"),
    FAST_FOOD("Fast Food", "Quick-service meals and snacks"),
    PIZZA("Pizza", "Pizza varieties and Italian-American dishes"),
    STEAKHOUSE("Steakhouse", "Grilled meats and steaks"),
    BAKERY("Bakery", "Breads, cakes and baked goods"),
    CAFE("Cafe", "Coffee, light meals and snacks"),
    BUFFET("Buffet", "Self-service with varied cuisine options"),
    FUSION("Fusion", "Creative mix of multiple culinary traditions");

    private final String displayName;
    private final String description;

    RestaurantSpecialty(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public static RestaurantSpecialty fromDisplayName(String value) {
        for (RestaurantSpecialty specialty : RestaurantSpecialty.values()) {
            if (specialty.displayName.equalsIgnoreCase(value)) {
                return specialty;
            }
        }
        throw new IllegalArgumentException("Invalid restaurant specialty: " + value);
    }
}