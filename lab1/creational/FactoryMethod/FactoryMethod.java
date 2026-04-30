package creational.FactoryMethod;

public class FactoryMethod {

    public interface Weapon {
        String use();
    }

    public static class Broadsword implements Weapon {
        @Override
        public String use() {
            return "Залізний меч";
        }
    }

    public static class WoodenBow implements Weapon {
        @Override
        public String use() {
            return "Дерев'яний лук";
        }
    }

    public static abstract class CraftingStation {
        public abstract Weapon craftWeapon();
        
        public void showCraftingProcess() {
            Weapon weapon = craftWeapon();
            System.out.println("Створено: " + weapon.use());
        }
    }

    public static class IronAnvil extends CraftingStation {
        @Override
        public Weapon craftWeapon() {
            return new Broadsword();
        }
    }

    public static class WorkBench extends CraftingStation {
        @Override
        public Weapon craftWeapon() {
            return new WoodenBow();
        }
    }
}