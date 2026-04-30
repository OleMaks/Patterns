package structural.Adapter;

public class Adapter {

    public interface MeleeWeapon {
        String swingSword();
    }

    public static class MagicWeapon {
        public String castSpell() {
            return "Випуск магічної водяної кулі на 30 урону!";
        }
    }

    public static class CopperShortsword implements MeleeWeapon {
        @Override
        public String swingSword() {
            return "Змах мечем на 10 урону!";
        }
    }

    public static class MagicToMeleeAdapter implements MeleeWeapon {
        private MagicWeapon magicWeapon;

        public MagicToMeleeAdapter(MagicWeapon magicWeapon) {
            this.magicWeapon = magicWeapon;
        }

        @Override
        public String swingSword() {
            return "Адаптований замах: " + magicWeapon.castSpell();
        }
    }
}