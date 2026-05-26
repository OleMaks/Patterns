package game;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task1_2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList(
                "Київ", "Харків", "Одеса", "Дніпро", "Запоріжжя",
                "Кривий Ріг", "Миколаїв", "Херсон", "Кропивницький",
                "Черкаси", "Суми", "Хмельницький", "Чернівці", "Каховка"
        );

        System.out.println(" . ІМПЕРАТИВНИЙ ПІДХІД (for-loop)");
        List<String> filteredCities = new ArrayList<>();
        for (String city : cities) {
            if (city.startsWith("К")) {
                filteredCities.add(city.toUpperCase());
            }
        }
        Collections.sort(filteredCities);
        for (String city : filteredCities) {
            System.out.println(city);
        }

        System.out.println("\n2. ФУНКЦІОНАЛЬНИЙ ПІДХІД (Java Streams)");
        cities.stream()
                .filter(city -> city.startsWith("К"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        System.out.println("\n3. РЕАКТИВНИЙ ПІДХІД (RxJava Observable)");
        Observable.fromIterable(cities)
                .filter(city -> city.startsWith("К"))
                .map(String::toUpperCase)
                .sorted()
                .subscribe(System.out::println);
    }
}