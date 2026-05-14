package lab5;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        System.out.println("1. Патерн Стратегія");
        double basePrice = 1000.0;
        
        Function<Double, Double> tenPercentDiscount = price -> price * 0.9;
        Function<Double, Double> fixedDiscount = price -> price - 200;

        System.out.println("Базова ціна: " + basePrice);
        System.out.println("Ціна зі знижкою 10%: " + calculatePrice(basePrice, tenPercentDiscount));
        System.out.println("Ціна з фіксованою знижкою: " + calculatePrice(basePrice, fixedDiscount));
        System.out.println();


        System.out.println("2. Патерн Фабричний метод");
        Product p1 = ProductFactory.createProduct("PHONE");
        Product p2 = ProductFactory.createProduct("LAPTOP");
        p1.use();
        p2.use();
        System.out.println();



        System.out.println("3. Патерн Декоратор");
        Function<Double, Double> baseSalary = salary -> salary;
        Function<Double, Double> withBonus = baseSalary.andThen(s -> s + 500.0);
        Function<Double, Double> withTaxes = withBonus.andThen(s -> s * 0.8);

        double initialSalary = 2000.0;
        double finalSalary = withTaxes.apply(initialSalary);
        System.out.println("Початкова зарплата: " + initialSalary);
        System.out.println("Зарплата після бонусів та податків (Декоратор): " + finalSalary);
        System.out.println();


        System.out.println("4. Патерн Навколишнє виконання");
        Resource.use(resource -> {
            resource.doWork("Читання даних з бази...");
            resource.doWork("Оновлення записів...");
        });
    }


    public static double calculatePrice(double price, Function<Double, Double> discountStrategy) {
        return discountStrategy.apply(price);
    }

    interface Product { void use(); }
    
    static class Phone implements Product {
        public void use() { System.out.println("Використовується Телефон."); }
    }
    
    static class Laptop implements Product {
        public void use() { System.out.println("Використовується Ноутбук."); }
    }

    static class ProductFactory {
        private static final Map<String, Supplier<Product>> factoryMap = new HashMap<>();
        
        static {
            factoryMap.put("PHONE", Phone::new);
            factoryMap.put("LAPTOP", Laptop::new);
        }

        public static Product createProduct(String type) {
            Supplier<Product> productSupplier = factoryMap.get(type.toUpperCase());
            if (productSupplier != null) {
                return productSupplier.get();
            }
            throw new IllegalArgumentException("Невідомий тип продукту: " + type);
        }
    }

    static class Resource {
        private Resource() {
            System.out.println("[Resource] З'єднання відкрито.");
        }

        public void doWork(String task) {
            System.out.println("[Resource] Виконання задачі: " + task);
        }

        private void close() {
            System.out.println("[Resource] З'єднання безпечно закрито.");
        }

        public static void use(Consumer<Resource> block) {
            Resource resource = new Resource();
            try {
                block.accept(resource);
            } finally {
                resource.close();
            }
        }
    }
}