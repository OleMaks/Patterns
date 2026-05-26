package game;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Task3_1 {
    record Product(String name, double priceUsd) {}

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Навушники Sony", 49.99),
                new Product("Клавіатура Logitech", 129.00),
                new Product("Монітор LG 27\"", 399.00),
                new Product("USB-хаб Anker", 35.00),
                new Product("Веб-камера Logitech", 149.00),
                new Product("Килимок для миші", 18.00),
                new Product("SSD Samsung 1TB", 110.00)
        );

        System.out.println("=== Обробка товарів через RxJava ===");

        Observable.fromIterable(products)
                .filter(product -> product.priceUsd() > 100.0)
                
                .map(product -> {
                    double priceUah = product.priceUsd() * 41.5;
                    
                    return String.format(Locale.US, "%s - %.2f грн (є в наявності)", product.name(), priceUah);
                })
                
                .subscribe(System.out::println);
    }
}