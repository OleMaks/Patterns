package behavioral.Strategy;

public class Strategy {

    public interface AttackStrategy {
        void attack();
    }


    public static class PhaseOneStrategy implements AttackStrategy {
        @Override
        public void attack() {
            System.out.println(" Око Ктулху: кружляє над гравцем і випускає маленьких мобів.");
        }
    }

    public static class PhaseTwoStrategy implements AttackStrategy {
        @Override
        public void attack() {
            System.out.println(" Око Ктулху (Фаза 2): голосно ричить і починає агресивно робити ривки!");
        }
    }


    public static class EyeOfCthulhu {
        private AttackStrategy currentStrategy;

        public EyeOfCthulhu(AttackStrategy startingStrategy) {
            this.currentStrategy = startingStrategy;
        }

        public void setStrategy(AttackStrategy newStrategy) {
            this.currentStrategy = newStrategy;
            System.out.println(" [Система]: Бос змінює свою стратегію бою!");
        }

        public void performAttack() {
            currentStrategy.attack();
        }
    }
}