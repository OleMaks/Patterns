package game;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class Task6_1 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> keystrokes = Observable.create(emitter -> {
            String[] inputs = {"К", "Ки", "Киї", "Київ", "Київ ", "Київ К", "Київ Ки"};
            long[] delays = {50, 80, 120, 100, 400, 60, 350};

            for (int i = 0; i < inputs.length; i++) {
                emitter.onNext(inputs[i]);
                Thread.sleep(delays[i]);
            }
            emitter.onComplete();
        });

        System.out.println("Симуляція пошуку (debounce)");

        keystrokes
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(
                        query -> System.out.println("[ПОШУК] Запит до АРІ: \"" + query + "\""),
                        error -> System.out.println("Помилка: " + error),
                        () -> System.out.println(" Введення завершено")
                );

        Thread.sleep(2000);
    }
}