package behavioral.Visitor;


public class Visitor {

    public interface GameEventVisitor {
        void visit(Player player);
        void visit(TownNPC npc);
        void visit(Enemy enemy);
    }

    public interface Entity {
        void accept(GameEventVisitor visitor);
    }

    public static class Player implements Entity {
        @Override
        public void accept(GameEventVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class TownNPC implements Entity {
        @Override
        public void accept(GameEventVisitor visitor) {
            visitor.visit(this); 
        }
    }

    public static class Enemy implements Entity {
        @Override
        public void accept(GameEventVisitor visitor) {
            visitor.visit(this); 
        }
    }

    // 4. Конкретний відвідувач 1: Кривавий місяць
    public static class BloodMoonVisitor implements GameEventVisitor {
        @Override
        public void visit(Player player) {
            System.out.println(" Гравець: Бачить, як вода і небо стають криваво-червоними.");
        }

        @Override
        public void visit(TownNPC npc) {
            System.out.println(" Міський NPC: Скаржиться на монстрів і відмовляється гуляти на вулиці.");
        }

        @Override
        public void visit(Enemy enemy) {
            System.out.println(" Ворог (Зомбі): Отримує бафф до сили і тепер може вибивати двері в будинках!");
        }
    }


    public static class HolyRainVisitor implements GameEventVisitor {
        @Override
        public void visit(Player player) {
            System.out.println(" Гравець: Відновлює здоров'я завдяки краплям цілющого дощу.");
        }

        @Override
        public void visit(TownNPC npc) {
            System.out.println(" Міський NPC: Почувається у безпеці і продає товари зі знижкою.");
        }

        @Override
        public void visit(Enemy enemy) {
            System.out.println(" Ворог: Отримує періодичну шкоду від святої води і тікає!");
        }
    }
}