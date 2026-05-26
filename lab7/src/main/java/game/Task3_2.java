package game;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task3_2 {
    record FoodOrder(String orderId, List<String> items) {}

    public static void main(String[] args) throws InterruptedException {
        List<FoodOrder> orders = Arrays.asList(
                new FoodOrder("ZAM-01", Arrays.asList("Піца Маргарита", "Кола 0.5л")),
                new FoodOrder("ZAM-02", Arrays.asList("Борщ", "Вареники", "Компот")),
                new FoodOrder("ZAM-03", Arrays.asList("Суші-сет 20шт", "Місо-суп"))
        );

        System.out.println("ЧАСТИНА А: Розгортання через flatMap()");
        Observable.fromIterable(orders)
                .flatMap(order -> Observable.fromIterable(order.items()))
                .subscribe(item -> System.out.println(">> " + item));

        System.out.println("\nЧАСТИНА В: flatMap() vs concatMap() із затримкою");
        
        System.out.println("1. Запуск flatMap (Асинхронно)");
        Observable.fromIterable(orders)
                .flatMap(order -> Observable.fromIterable(order.items())
                        .delay(500, TimeUnit.MILLISECONDS))
                .subscribe(item -> System.out.println("[flatMap] Готово: " + item));

        Thread.sleep(1000); 

        System.out.println("\n2. Запуск concatMap (Послідовно)");
        Observable.fromIterable(orders)
                .concatMap(order -> Observable.fromIterable(order.items())
                        .delay(500, TimeUnit.MILLISECONDS))
                .subscribe(item -> System.out.println("[concatMap] Готово: " + item));

        Thread.sleep(4000);
    }
}