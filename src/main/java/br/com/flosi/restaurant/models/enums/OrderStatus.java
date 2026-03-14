package br.com.flosi.restaurant.models.enums;

public enum OrderStatus {

    CREATED("Created", "Order has been created but not yet paid"),
    CONFIRMED("Confirmed", "Order confirmed by the restaurant"),
    PREPARING("Preparing", "Order is being prepared"),
    READY("Ready", "Order is ready for pickup or delivery"),
    OUT_FOR_DELIVERY("Out for Delivery", "Order is on the way to the customer"),
    DELIVERED("Delivered", "Order delivered successfully"),
    CANCELLED("Cancelled", "Order has been cancelled"),
    PAID("Paid", "Payment has been completed");

    private final String displayName;
    private final String description;

    OrderStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromDisplayName(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.displayName.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid order status: " + value);
    }
}