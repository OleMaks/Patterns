package game;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task7_2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("retry() з експоненційним backoff\n");
        
        AtomicInteger attempts = new AtomicInteger(0);

        Observable.create(emitter -> {
            int currentAttempt = attempts.incrementAndGet();
            System.out.println("[МЕРЕЖА] Підключення до сервера... Спроба #" + currentAttempt);
            
            if (currentAttempt < 3) {
                System.out.println("[МЕРЕЖА] (-) Помилка з'єднання (Timeout)!");
                emitter.onError(new RuntimeException("Timeout error"));
            } else {
                System.out.println("[МЕРЕЖА] (+) Успішно підключено!");
                emitter.onNext("Секретні дані сервера");
                emitter.onComplete();
            }
        })
        .retryWhen(errors -> 
            errors.zipWith(Observable.range(1, 3), (error, retryCount) -> retryCount)
                  .flatMap(retryCount -> {
                      long delay = (long) Math.pow(2, retryCount); 
                      System.out.println("[СИСТЕМА] Чекаємо " + delay + " сек перед наступною спробою...");
                      return Observable.timer(delay, TimeUnit.SECONDS);
                  })
        )
        .subscribe(
                data -> System.out.println("[РЕЗУЛЬТАТ] Отримано: " + data),
                error -> System.out.println("[ФІНАЛ] Помилка: " + error.getMessage())
        );

        Thread.sleep(10000);
    }
}