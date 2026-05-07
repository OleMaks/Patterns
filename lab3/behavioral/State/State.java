package behavioral.State;

public class State {


    public interface PlayerState {
        void move();
        void attack();
    }

    public static class HealthyState implements PlayerState {
        @Override
        public void move() {
            System.out.println(" Гравець біжить по біому.");
        }

        @Override
        public void attack() {
            System.out.println(" Гравець махає мечем, завдаючи 100% шкоди.");
        }
    }

    public static class PoisonedState implements PlayerState {
        @Override
        public void move() {
            System.out.println(" Гравець отримує періодичну шкоду від отрути...");
        }

        @Override
        public void attack() {
            System.out.println(" Гравець б'є слабше, сили покидають його (70% шкоди).");
        }
    }

    public static class TerrariaPlayer {
        private PlayerState currentState;

        public TerrariaPlayer(PlayerState state) {
            this.currentState = state;
        }


        public void setState(PlayerState state) {
            this.currentState = state;
            System.out.println(" [Система]: Стан гравця змінився!");
        }

        public void performMove() {
            currentState.move();
        }

        public void performAttack() {
            currentState.attack();
        }
    }
}