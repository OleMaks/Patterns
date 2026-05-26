package game;

import io.reactivex.rxjava3.core.Observable;

public class Task7_1 {
    public static void main(String[] args) {
        
        System.out.println("1. Використання onErrorReturn");
        Observable.<String>error(new RuntimeException("Сервер впав (500 Internal Error)"))
                .onErrorReturnItem("Резервний користувач (Guest)")
                .subscribe(
                        item -> System.out.println("[РЕЗУЛЬТАТ] Отримано: " + item),
                        error -> System.out.println("Цей рядок не виконається, бо ми врятували потік!")
                );

        System.out.println("\n2. Використання onErrorResumeNext");
        Observable.<String>error(new RuntimeException("База даних недоступна"))
                .onErrorResumeNext(error -> {
                    System.out.println("[ЛОГ] Перехоплено помилку: " + error.getMessage() + ". Перемикаємось на кеш...");
                    return Observable.just("Дані з кешу: Запис 1", "Дані з кешу: Запис 2");
                })
                .subscribe(
                        item -> System.out.println("[РЕЗУЛЬТАТ] " + item)
                );
    }
}