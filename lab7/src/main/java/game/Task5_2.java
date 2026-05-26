package game;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

public class Task5_2 {
    record ServiceCall(String serviceName, int delayMs) {}

    public static void main(String[] args) {
        List<ServiceCall> services = Arrays.asList(
                new ServiceCall("UserService", 800),
                new ServiceCall("OrderService", 1200),
                new ServiceCall("RecommendationService", 600)
        );

        System.out.println("ЧАСТИНА А: Послідовне завантаження (concatMap)");
        long startSeq = System.currentTimeMillis();

        Observable.fromIterable(services)
                .concatMap(service ->
                        Observable.fromCallable(() -> simulateNetworkCall(service))
                )
                .blockingSubscribe(System.out::println);

        long endSeq = System.currentTimeMillis();
        System.out.println("Загальний час (послідовно): ~" + (endSeq - startSeq) + " мс\n");


        System.out.println("ЧАСТИНА В: Паралельне завантаження (flatMap + Schedulers.io()) ");
        long startPar = System.currentTimeMillis();

        Observable.fromIterable(services)
                .flatMap(service ->
                        Observable.fromCallable(() -> simulateNetworkCall(service))
                                .subscribeOn(Schedulers.io())
                )
                .blockingSubscribe(System.out::println);

        long endPar = System.currentTimeMillis();
        System.out.println("Загальний час (паралельно): ~" + (endPar - startPar) + " мс");
    }

    private static String simulateNetworkCall(ServiceCall service) throws InterruptedException {
        Thread.sleep(service.delayMs());
        String threadName = Thread.currentThread().getName();
        if (threadName.contains("RxCachedThreadScheduler")) {
            threadName = threadName.replace("RxCachedThreadScheduler", "io");
        }
        return String.format("[%s] (+) %s відповів за %d мс", threadName, service.serviceName(), service.delayMs());
    }
}