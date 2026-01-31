package zad4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zadanie4 {

    private static final String STATUS_ZREALIZOWANE = "ZREALIZOWANE";
    private static final int TOP_LIMIT = 3;

    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
                new Order("ORD001", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Mysz Logitech", "Elektronika", 2, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD002", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD003", "W TRAKCIE", Arrays.asList(
                        new OrderItem("Smartfon iPhone", "Elektronika", 1, 4500)
                )),
                new Order("ORD004", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 2, 1200),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Lampa LED", "Oświetlenie", 3, 120)
                )),
                new Order("ORD005", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800),
                        new OrderItem("Lampa LED", "Oświetlenie", 2, 120)
                )),
                new Order("ORD006", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 1, 150),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD007", "ANULOWANE", Arrays.asList(
                        new OrderItem("Tablet Samsung", "Elektronika", 1, 2200)
                )),
                new Order("ORD008", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Lampa LED", "Oświetlenie", 1, 120),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                )),
                new Order("ORD009", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 3, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD010", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                ))
        );

        Stream<Order> completedOrdersStream = orders.stream()
                .filter(o -> STATUS_ZREALIZOWANE.equals(o.getStatus()));

        Stream<OrderItem> orderItemsStream = completedOrdersStream
                .flatMap(o -> o.getItems().stream());

        Map<String, Map<String, Long>> groupedCounts =
                orderItemsStream.collect(Collectors.groupingBy(
                        OrderItem::getCategory,
                        Collectors.groupingBy(
                                OrderItem::getProductName,
                                Collectors.counting()
                        )
                ));

        Map<String, List<ProductStats>> result = groupedCounts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                                .limit(TOP_LIMIT)
                                .map(e -> new ProductStats(e.getKey(), e.getValue()))
                                .collect(Collectors.toList())
                ));

        result.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }
}