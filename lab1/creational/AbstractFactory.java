package creational;

public class AbstractFactory {


    public interface Block {
        String getBlockName();
    }

    public interface Enemy {
        String getEnemyName();
    }

    public static class Ebonstone implements Block {
        @Override
        public String getBlockName() { return "Ебонітовий камінь"; }
    }
    
    public static class EaterOfSouls implements Enemy {
        @Override
        public String getEnemyName() { return "Пожирач душ"; }
    }

    public static class Crimstone implements Block {
        @Override
        public String getBlockName() { return "Кримтановий камінь"; }
    }
    
    public static class Crimera implements Enemy {
        @Override
        public String getEnemyName() { return "Крімера"; }
    }

    public interface BiomeFactory {
        Block createBlock();
        Enemy createEnemy();
    }

    public static class CorruptionFactory implements BiomeFactory {
        @Override
        public Block createBlock() { return new Ebonstone(); }
        
        @Override
        public Enemy createEnemy() { return new EaterOfSouls(); }
    }

    public static class CrimsonFactory implements BiomeFactory {
        @Override
        public Block createBlock() { return new Crimstone(); }
        
        @Override
        public Enemy createEnemy() { return new Crimera(); }
    }
}