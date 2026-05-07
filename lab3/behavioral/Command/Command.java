package behavioral.Command;

public class Command {

    public interface GameAction {
        void execute();
    }

    public static class Player {
        public void heal() {
            System.out.println(" Гравець випиває Зілля Лікування. Здоров'я відновлено!");
        }

        public void swingSword() {
            System.out.println(" Гравець робить мах мечем!");
        }
    }

    public static class HealCommand implements GameAction {
        private Player player;

        public HealCommand(Player player) {
            this.player = player;
        }

        @Override
        public void execute() {
            player.heal();
        }
    }

    public static class AttackCommand implements GameAction {
        private Player player;

        public AttackCommand(Player player) {
            this.player = player;
        }

        @Override
        public void execute() {
            player.swingSword();
        }
    }

    public static class Hotkey {
        private GameAction action;
        private String keyName;

        public Hotkey(String keyName) {
            this.keyName = keyName;
        }

        public void bindAction(GameAction action) {
            this.action = action;
            System.out.println(" [Налаштування]: Дію прив'язано до клавіші '" + keyName + "'");
        }

        public void press() {
            System.out.print(" Натиснуто [" + keyName + "]: ");
            if (action != null) {
                action.execute();
            } else {
                System.out.println("дію не призначено.");
            }
        }
    }
}