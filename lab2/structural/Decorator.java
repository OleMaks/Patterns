package structural;

public class Decorator {

    public interface TerrariaPlayer {
        void describe();
    }

    public static class BasicPlayer implements TerrariaPlayer {
        @Override
        public void describe() {
            System.out.println("Я заспавнився");
        }
    }

    public static abstract class PlayerDecorator implements TerrariaPlayer {
        protected TerrariaPlayer player;

        public PlayerDecorator(TerrariaPlayer player) {
            this.player = player;
        }
    }

    public static class HermesBoots extends PlayerDecorator {
        public HermesBoots(TerrariaPlayer player) {
            super(player);
        }

        @Override
        public void describe() {
            player.describe();
            System.out.println(" + Екіпіровано Черевики Гермеса: Я можу швидко бігати!");
        }
    }

    public static class RocketBoots extends PlayerDecorator {
        public RocketBoots(TerrariaPlayer player) {
            super(player);
        }

        @Override
        public void describe() {
            player.describe();
            System.out.println(" + Екіпіровано Ракетні черевики: Я можу літати!");
        }
    }

    public static class ShieldOfCthulhu extends PlayerDecorator {
        public ShieldOfCthulhu(TerrariaPlayer player) {
            super(player);
        }

        @Override
        public void describe() {
            player.describe();
            System.out.println(" + Екіпіровано Щит Ктулху: Я можу робити ривок!");
        }
    }
}