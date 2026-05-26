package game;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Task5_1 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> images = Observable.just(
                "photo_1.jpg", "photo_2.jpg", "photo_3.jpg"
        );

        System.out.println(" Асинхронна обробка зображень\n");

        images
            .subscribeOn(Schedulers.io())
            .map(image -> {
                Thread.sleep(1000);
                printLog("ЗАВАНТ", "Завантаження", image);
                return image;
            })
            
            .observeOn(Schedulers.computation())
            .map(image -> {
                Thread.sleep(500);
                printLog("СТИСК", "Стиснення", image);
                return image;
            })
            
            .observeOn(Schedulers.trampoline())
            .subscribe(
                image -> printLog("ФОТО", "Відображення", image)
            );

        Thread.sleep(6000);
    }
    private static void printLog(String tag, String action, String image) {
        String threadName = Thread.currentThread().getName();
        System.out.printf("[%s] \t[%s] \t%s: \t%s%n", threadName, tag, action, image);
    }
}