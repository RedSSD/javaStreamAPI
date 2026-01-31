package zad4;

class ProductStats {
    private final String productName;
    private final long orderCount;

    public ProductStats(String productName, long orderCount) {
        this.productName = productName;
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return productName + " = " + orderCount;
    }
}
