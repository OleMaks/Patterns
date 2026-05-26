package game;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public class Task4_2 {

    public static Maybe<String> findInCache(String key) {
        if ("user:1".equals(key)) {
            return Maybe.just("{'name': 'Леся', 'age':28}");
        } else if ("user:2".equals(key)) {
            return Maybe.empty();
        } else {
            return Maybe.error(new RuntimeException("Redis недоступний"));
        }
    }

    public static Completable validateInput() {
        return Completable.fromAction(() -> {
            System.out.println("[ПОШУК] Перевірка даних...");
            System.out.println("(+) Дані валідні");
        });
    }

    public static Completable saveToDatabase(boolean simulateError) {
        return Completable.create(emitter -> {
            System.out.println("[DB] Збереження в БД...");
            if (simulateError) {
                emitter.onError(new RuntimeException("Помилка доступу до БД"));
            } else {
                System.out.println("(+) Збережено");
                emitter.onComplete();
            }
        });
    }

    public static Single<String> generateToken() {
        return Single.fromCallable(() -> {
            System.out.println("[ТОКЕН] Токен: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.demo");
            return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.demo";
        });
    }

    public static void main(String[] args) {
        System.out.println("Частина А: Maybe");
        
        String[] keysToTest = {"user:1", "user:2", "user:error"};
        
        for (String key : keysToTest) {
            findInCache(key)
                .defaultIfEmpty("Завантажено з БД")
                .subscribe(
                    value -> {
                        if (value.equals("Завантажено з БД")) {
                            System.out.println("[КЕШ (-)] Кеш-міс. Значення: " + value);
                        } else {
                            System.out.println("[КЕШ (+)] Знайдено: " + value);
                        }
                    },
                    error -> System.out.println("[КЕШ (!)] Помилка: " + error.getMessage())
                );
        }

        System.out.println("\n Частина В: Completable + andThen() (УСПІШНИЙ СЦЕНАРІЙ)");
        validateInput()
            .andThen(saveToDatabase(false))
            .andThen(generateToken())
            .subscribe(
                token -> System.out.println("(+) Реєстрацію завершено успішно!"),
                error -> System.out.println("(-) Помилка реєстрації: " + error.getMessage())
            );

        System.out.println("\nЧастина В: Completable + andThen() (СЦЕНАРІЙ З ПОМИЛКОЮ)");
        validateInput()
            .andThen(saveToDatabase(true))
            .andThen(generateToken())   
            .subscribe(
                token -> System.out.println("(+) Реєстрацію завершено успішно!"),
                error -> System.out.println("(-) Помилка реєстрації: " + error.getMessage())
            );
    }
}