package behavioral.TemplateMethod;

public class TemplateMethod {

    public static abstract class BossFight {
        public final void conductFight() {
            prepareArena();
            summonBoss();
            fight();
            collectLoot();
        }

        public abstract void prepareArena();
        public abstract void summonBoss();
        public abstract void collectLoot();

        public void fight() {
            System.out.println(" Починається битва!");
        }
    }

    public static class KingSlimeFight extends BossFight {
        @Override
        public void prepareArena() {
            System.out.println("Будуємо довгу плоску арену з дерева.");
        }

        @Override
        public void summonBoss() {
            System.out.println("Використовуємо Корону слизу.");
        }

        @Override
        public void collectLoot() {
            System.out.println("Збираємо дропи.");
        }
    }

    public static class EyeOfCthulhuFight extends BossFight {
        @Override
        public void prepareArena() {
            System.out.println("Будуємо багатоярусну арену з багаттями.");
        }

        @Override
        public void summonBoss() {
            System.out.println("Використовуємо Підозріле око вночі.");
        }

        @Override
        public void collectLoot() {
            System.out.println("Збираємо дропи.");
        }
    }
}