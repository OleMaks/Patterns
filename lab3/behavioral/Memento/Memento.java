package behavioral.Memento;

public class Memento {

    public static class Player {
        private int health;
        private int x, y;

        public Player(int health, int x, int y) {
            this.health = health;
            this.x = x;
            this.y = y;
        }

        public void setHealthAndPosition(int health, int x, int y) {
            this.health = health;
            this.x = x;
            this.y = y;
        }

        public void printState() {
            System.out.println("   Гравець [Здоров'я: " + health + " HP | Координати: (" + x + ", " + y + ")]");
        }

        public Object save() {
            return new PlayerMemento(health, x, y);
        }

        public void restore(Object objMemento) {
            PlayerMemento memento = (PlayerMemento) objMemento;
            this.health = memento.savedHealth;
            this.x = memento.savedX;
            this.y = memento.savedY;
        }

        private class PlayerMemento {
            private final int savedHealth;
            private final int savedX;
            private final int savedY;

            public PlayerMemento(int health, int x, int y) {
                this.savedHealth = health;
                this.savedX = x;
                this.savedY = y;
            }
        }
    }

    public static class GameSaveSystem {
        private Object savedState;

        public void saveGame(Player player) {
            this.savedState = player.save();
            System.out.println(" [Система]: Гра збережена (Чекпоінт створено).");
        }

        public void loadGame(Player player) {
            if (savedState != null) {
                player.restore(savedState);
                System.out.println(" [Система]: Гра завантажена. Гравця повернуто на точку збереження.");
            } else {
                System.out.println(" [Система]: Немає збережень.");
            }
        }
    }
}