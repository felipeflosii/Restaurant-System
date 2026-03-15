package br.com.flosi.restaurant.models.enums;

public enum UserRole {

    WAITER("Waiter", "Takes orders and serves customers at tables"),
    CASHIER("Cashier", "Handles payments and closes customer bills"),
    KITCHEN("Kitchen Staff", "Prepares and assembles dishes"),
    BARTENDER("Bartender", "Prepares drinks and manages the bar"),
    MANAGER("Manager", "Full access to all system features");

    private final String displayName;
    private final String description;

    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public static UserRole fromDisplayName(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.displayName.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid user role: " + value);
    }
}
