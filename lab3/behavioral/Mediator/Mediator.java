package behavioral.Mediator;

public class Mediator {

    public interface EventMediator {
        void notify(String sender, String event);
    }

    public static class GoblinEventMediator implements EventMediator {
        private int goblinsDefeated = 0;
        private final int GOBLINS_TO_WIN = 3;

        @Override
        public void notify(String sender, String event) {
            if (sender.equals("Goblin") && event.equals("died")) {
                goblinsDefeated++;
                System.out.println("[Система]: Гобліна переможено! Прогрес: " + goblinsDefeated + "/" + GOBLINS_TO_WIN);
                
                if (goblinsDefeated == GOBLINS_TO_WIN) {
                    System.out.println("[Система]: Армію гоблінів розбито!");
                }
            }
        }
    }

    public static abstract class Entity {
        protected EventMediator mediator;

        public Entity(EventMediator mediator) {
            this.mediator = mediator;
        }
    }

    public static class Goblin extends Entity {
        public Goblin(EventMediator mediator) {
            super(mediator);
        }

        public void die() {
            System.out.println("Гоблін: *звук смерті*");
            mediator.notify("Goblin", "died");
        }
    }
}