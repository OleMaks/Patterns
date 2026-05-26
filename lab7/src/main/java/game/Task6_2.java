package game;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Task6_2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Симуляція Backpressure (Стратегія DROP)\n");

        Flowable.<Integer>create(emitter -> {
            for (int i = 1; i <= 500; i++) {
                emitter.onNext(i);
            }
            System.out.println("[ДЖЕРЕЛО] Усі 500 елементів випущено в трубу миттєво!");
            emitter.onComplete();
            
        }, BackpressureStrategy.DROP) 

        .observeOn(Schedulers.io())
        
        .subscribe(
            item -> {
                Thread.sleep(10);
                System.out.println("[СЛУХАЧ] Оброблено: " + item);
            },
            error -> System.out.println("Помилка: " + error),
            () -> System.out.println("Роботу завершено ")
        );

        Thread.sleep(2000);
    }
}