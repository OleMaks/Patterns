package creational.Singleton;

public class Singleton {
    

    private static Singleton instance = null;
    
    private String worldName;


    private Singleton() {
        this.worldName = "Terraria World (Corruption)";
        System.out.println("Генерація нового світу: створення океанів, посадка джунглів, генерація руди...");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void spawnBoss() {
        System.out.println("Ви відчуваєте злу присутність... Пробуджується Око Ктулху у світі " + worldName + "!");
    }
}