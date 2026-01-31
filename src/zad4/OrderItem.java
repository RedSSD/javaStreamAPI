package zad4;

class OrderItem {
    private final String productName;
    private final String category;
    private int quantity;
    private double price;

    public OrderItem(String productName, String category, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public String getCategory() { return category; }
}
