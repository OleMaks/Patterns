package creational;


public class Factory {


    public interface Enemy {
        String spawn();
    }

    public static class GreenSlime implements Enemy {
        @Override
        public String spawn() {
            return "Зелений слиз стрибає до вас";
        }
    }

    public static class Zombie implements Enemy {
        @Override
        public String spawn() {
            return "Зомбі повільно наближається";
        }
    }

    public Enemy getEnemy(String timeOfDay) {
        if ("night".equalsIgnoreCase(timeOfDay)) {
            return new Zombie();
        } else {
            return new GreenSlime();
        }
    }
}