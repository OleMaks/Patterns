package game;

import io.reactivex.rxjava3.core.Single;

public class Task4_1 {

    public static Single<String> getUserById(int id) {
        if (id > 0) {
            return Single.just("Користувач #" + id + ": Іван Франко");
        } else {
            return Single.error(new IllegalArgumentException("ID не може бути від'ємним або нульовим"));
        }
    }

    public static void main(String[] args) {
        getUserById(42).subscribe(
                result -> System.out.println("(+) Знайдено: " + result),
                error -> System.out.println("(-) Помилка: " + error.getMessage())
        );

        getUserById(-1).subscribe(
                result -> System.out.println("(+) Знайдено: " + result),
                error -> System.out.println("(-) Помилка: " + error.getMessage())
        );
    }
}