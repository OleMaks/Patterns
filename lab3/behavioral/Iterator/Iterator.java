package behavioral.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Iterator {

    public interface InventoryIterator {
        boolean hasNext();
        Item next();
    }

    public static class Item {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public interface Inventory {
        InventoryIterator createIterator();
    }

    public static class PlayerInventory implements Inventory {
        private List<Item> items = new ArrayList<>();

        public void addItem(Item item) {
            items.add(item);
        }

        @Override
        public InventoryIterator createIterator() {
            return new InventoryIteratorImpl(this);
        }

        private class InventoryIteratorImpl implements InventoryIterator {
            private PlayerInventory inventory;
            private int index = 0;

            public InventoryIteratorImpl(PlayerInventory inv) {
                this.inventory = inv;
            }

            @Override
            public boolean hasNext() {
                return index < inventory.items.size();
            }

            @Override
            public Item next() {
                if (this.hasNext()) {
                    return inventory.items.get(index++);
                }
                return null;
            }
        }
    }
}