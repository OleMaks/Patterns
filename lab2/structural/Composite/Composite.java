package structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Composite {

    public interface InventoryComponent {
        void display();
    }

    public static class Item implements InventoryComponent {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        @Override
        public void display() {
            System.out.println(" - Предмет: " + name);
        }
    }

    public static class Chest implements InventoryComponent {
        private String chestName;
        private List<InventoryComponent> items = new ArrayList<>();

        public Chest(String chestName) {
            this.chestName = chestName;
        }

        public void add(InventoryComponent component) {
            items.add(component);
        }

        public void remove(InventoryComponent component) {
            items.remove(component);
        }

        @Override
        public void display() {
            System.out.println("[" + chestName + "] містить:");
            for (InventoryComponent item : items) {
                item.display();
            }
        }
    }
}