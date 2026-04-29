package structural;

public class Bridge {

    public interface Modifier {
        String applyModifier();
    }

    public static class LegendaryModifier implements Modifier {
        @Override
        public String applyModifier() {
            return "Легендарний";
        }
    }

    public static class BrokenModifier implements Modifier {
        @Override
        public String applyModifier() {
            return "Зламаний";
        }
    }

    public static abstract class Weapon {
        protected Modifier modifier;

        public Weapon(Modifier modifier) {
            this.modifier = modifier;
        }

        public void setModifier(Modifier modifier) {
            this.modifier = modifier;
        }

        public abstract void attack();
    }

    public static class Sword extends Weapon {
        public Sword(Modifier modifier) {
            super(modifier);
        }

        @Override
        public void attack() {
            System.out.println(modifier.applyModifier() + " Меч атакує!");
        }
    }

    public static class Bow extends Weapon {
        public Bow(Modifier modifier) {
            super(modifier);
        }

        @Override
        public void attack() {
            System.out.println(modifier.applyModifier() + " Лук стріляє!");
        }
    }
}