package game;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Task2_2 {
    public static void main(String[] args) throws InterruptedException {
        String[] matches = {
                "Динамо 2:1 Шахтар",
                "Шахтар 3:0 Металіст",
                "Зоря 1:1 Ворскла",
                "Дніпро 2:0 Карпати",
                "Чорноморець 0:1 Олександрія"
        };

        System.out.println("ЧАСТИНА А: Холодний Observable");
        Observable<String> coldObservable = Observable.fromArray(matches);


        coldObservable.subscribe(res -> System.out.println("Глядач 1 (Холодний) побачив: " + res));
        System.out.println("---");
        coldObservable.subscribe(res -> System.out.println("Глядач 2 (Холодний) побачив: " + res));


        System.out.println("\n=== ЧАСТИНА В: Гарячий Observable ===");
        
        Observable<String> timedMatches = Observable.zip(
                Observable.fromArray(matches),
                Observable.interval(1, TimeUnit.SECONDS),
                (match, tick) -> match
        );

        ConnectableObservable<String> hotObservable = timedMatches.publish();

        hotObservable.subscribe(res -> System.out.println("[Глядач 1 - Гарячий] отримує трансляцію: " + res));

        System.out.println("--> ТРАНСЛЯЦІЯ ПОЧАЛАСЯ <--");
        hotObservable.connect();

        Thread.sleep(2500);

        System.out.println("--> Глядач 2 щойно підключився до трансляції! <--");
        hotObservable.subscribe(res -> System.out.println("[Глядач 2 - Гарячий] отримує трансляцію: " + res));

        Thread.sleep(4000);
    }
}