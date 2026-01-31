package zad4;

import java.util.List;

class Order {
    private String orderId;
    private final String status;
    private final List<OrderItem> items;

    public Order(String orderId, String status, List<OrderItem> items) {
        this.orderId = orderId;
        this.status = status;
        this.items = items;
    }

    public String getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }
}
