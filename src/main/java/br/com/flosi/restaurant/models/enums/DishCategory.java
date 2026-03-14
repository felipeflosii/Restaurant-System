package br.com.flosi.restaurant.models.enums;

public enum DishCategory {

    APPETIZER("Appetizer", "Starter dishes served before the main course"),
    SOUP("Soup", "Hot or cold liquid dishes"),
    SALAD("Salad", "Fresh vegetable-based dishes"),

    MAIN_COURSE("Main Course", "Primary dish of the meal"),
    SIDE_DISH("Side Dish", "Accompaniments served with the main course"),

    FAST_FOOD("Fast Food", "Quick-prepared items like burgers and fries"),
    PASTA("Pasta", "Pasta-based dishes"),
    PIZZA("Pizza", "Pizza varieties"),
    SEAFOOD("Seafood", "Fish and seafood dishes"),
    GRILL("Grill", "Grilled meats and barbecue items"),
    VEGETARIAN("Vegetarian", "Meat-free dishes"),
    VEGAN("Vegan", "Plant-based dishes without animal products"),

    DESSERT("Dessert", "Sweet dishes served after meals"),
    BAKERY("Bakery", "Breads, cakes and baked goods"),

    BEVERAGE("Beverage", "General drinks"),
    HOT_BEVERAGE("Hot Beverage", "Coffee, tea and hot drinks"),
    COLD_BEVERAGE("Cold Beverage", "Juices, sodas and cold drinks"),
    ALCOHOLIC_BEVERAGE("Alcoholic Beverage", "Beer, wine and cocktails"),

    COMBO("Combo", "Meal combinations or promotional bundles"),
    SPECIAL("Special", "Chef specials or limited-time dishes");

    private final String displayName;
    private final String description;

    DishCategory(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public static DishCategory fromDisplayName(String value) {
        for (DishCategory category : DishCategory.values()) {
            if (category.displayName.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid menu category: " + value);
    }
}