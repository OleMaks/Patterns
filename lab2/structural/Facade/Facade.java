package structural.Facade;

public class Facade {

    public static class TerrainGenerator {
        public void generateSurface() {
            System.out.println(" - Генерація поверхні...");
        }
    }

    public static class CaveGenerator {
        public void digCaves() {
            System.out.println(" - Викопування печер...");
        }
    }

    public static class OrePopulator {
        public void placeOres() {
            System.out.println(" - Розподіл руди...");
        }
    }

    public static class WorldGenerationFacade {
        private TerrainGenerator terrain;
        private CaveGenerator caves;
        private OrePopulator ores;

        public WorldGenerationFacade() {
            this.terrain = new TerrainGenerator();
            this.caves = new CaveGenerator();
            this.ores = new OrePopulator();
        }

        public void createWorld() {
            System.out.println("Початок генерації нового світу Террарії...");
                        System.out.println("(інтересні факти про терарію)");
            try {
                terrain.generateSurface();
                Thread.sleep(1000); 
                caves.digCaves();
                Thread.sleep(1000);
                ores.placeOres();
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Світ успішно створено!");
        }
    }
}