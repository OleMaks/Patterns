package behavioral.ChainOfResponsibility;

public class ChainOfResponsibility {

    public static abstract class DamageHandler {
        protected DamageHandler nextHandler;

        public void setNext(DamageHandler handler) {
            this.nextHandler = handler;
        }

        public abstract void handle(int damage);
    }

    public static class ImmunityHandler extends DamageHandler {
        private boolean isImmune;

        public ImmunityHandler(boolean isImmune) {
            this.isImmune = isImmune;
        }

        @Override
        public void handle(int damage) {
            if (isImmune) {
                System.out.println(" Гравець невразливий! Шкода ігнорується.");
            } else if (nextHandler != null) {
                System.out.println(" Гравець вразливий. Передаємо розрахунок далі...");
                nextHandler.handle(damage);
            }
        }
    }

    public static class AccessoryHandler extends DamageHandler {
        @Override
        public void handle(int damage) {
            int reducedDamage = (int) (damage * 0.83); 
            System.out.println(" Шарф поглинув частину удару. Залишилось шкоди: " + reducedDamage);
            if (nextHandler != null) {
                nextHandler.handle(reducedDamage);
            }
        }
    }

    public static class ArmorHandler extends DamageHandler {
        private int defense;

        public ArmorHandler(int defense) {
            this.defense = defense;
        }

        @Override
        public void handle(int damage) {
            int finalDamage = Math.max(1, damage - (defense / 2)); 
            System.out.println(" Броня стримала удар. Гравець отримує фінальні " + finalDamage + " одиниць шкоди!\n");
        }
    }
}