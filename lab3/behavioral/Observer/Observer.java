package behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {

    public interface GameEntity {
        void update(boolean isNight);
    }

    public static class TimeManager {
        private List<GameEntity> entities = new ArrayList<>();
        private boolean isNight = false;

        public void attach(GameEntity entity) {
            entities.add(entity);
        }

        public void setTime(boolean isNight) {
            this.isNight = isNight;
            System.out.println(isNight ? "\n[Система]: Сонце сідає. Настає ніч..." : "\n[Система]: Сонце сходить. Настає ранок!");
            notifyEntities();
        }

        private void notifyEntities() {
            for (GameEntity entity : entities) {
                entity.update(isNight);
            }
        }
    }

    public static class Zombie implements GameEntity {
        @Override
        public void update(boolean isNight) {
            if (isNight) {
                System.out.println(" Зомбі: спавниться та шукає гравця!");
            } else {
                System.out.println(" Зомбі: згорає.");
            }
        }
    }


    public static class NPC implements GameEntity {
        private String name;

        public NPC(String name) {
            this.name = name;
        }

        @Override
        public void update(boolean isNight) {
            if (isNight) {
                System.out.println(" " + name + ": біжить у свій будинок і зачиняє двері.");
            } else {
                System.out.println(" " + name + ": Виходить на вулицю і вітається з гравцем.");
            }
        }
    }
}