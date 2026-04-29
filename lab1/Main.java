import creational.Singleton;
import creational.Factory;
import creational.FactoryMethod;
import creational.AbstractFactory;
import creational.Builder;
import creational.Prototype;

public class Main {
    public static void main(String[] args) {
        System.out.println(" Породжувальні патерни\n");

        System.out.println("1 Патерн Одинак (Singleton)");
        
        Singleton world1 = Singleton.getInstance();
        world1.spawnBoss();
        
        Singleton world2 = Singleton.getInstance();
        
        if (world1 == world2) {
            System.out.println("world1 та world2 посилаються на один і той самий збережений світ.\n");
        }

        System.out.println("2 Патерн Фабрика (Simple Factory)");
        
        Factory enemyFactory = new Factory();
        
        Factory.Enemy dayMob = enemyFactory.getEnemy("day");
        System.out.println("День: " + dayMob.spawn());
        
        Factory.Enemy nightMob = enemyFactory.getEnemy("night");
        System.out.println("Ніч: " + nightMob.spawn() + "\n");

        System.out.println("3 Патерн Фабричний метод (Factory Method)");
        
        FactoryMethod.CraftingStation anvil = new FactoryMethod.IronAnvil();
        System.out.print("Ковадло: ");
        anvil.showCraftingProcess();
        
        FactoryMethod.CraftingStation workbench = new FactoryMethod.WorkBench();
        System.out.print("Верстак: ");
        workbench.showCraftingProcess();
        System.out.println();

        System.out.println("4 Патерн Абстрактна фабрика (Abstract Factory)");
        
        AbstractFactory.BiomeFactory corruption = new AbstractFactory.CorruptionFactory();
        System.out.println("Генерація Корупції:");
        System.out.println("Блок: " + corruption.createBlock().getBlockName());
        System.out.println("Ворог: " + corruption.createEnemy().getEnemyName() + "\n");
        
        AbstractFactory.BiomeFactory crimson = new AbstractFactory.CrimsonFactory();
        System.out.println("Генерація Крімзону:");
        System.out.println("Блок: " + crimson.createBlock().getBlockName());
        System.out.println("Ворог: " + crimson.createEnemy().getEnemyName() + "\n");

        System.out.println("5 Патерн Будівельник (Builder)");
        
        Builder.ArmorBuilder cactusBuilder = new Builder.CactusArmorBuilder();
        Builder.ArmorCrafter crafter = new Builder.ArmorCrafter(cactusBuilder);
        crafter.constructArmor();
        Builder.ArmorSet cactusArmor = crafter.getArmor();
        System.out.println("Скрафчено сет: " + cactusArmor);

        Builder.ArmorBuilder jungleBuilder = new Builder.JungleArmorBuilder();
        crafter = new Builder.ArmorCrafter(jungleBuilder);
        crafter.constructArmor();
        Builder.ArmorSet jungleArmor = crafter.getArmor();
        System.out.println("Скрафчено сет: " + jungleArmor + "\n");

        System.out.println("6 Патерн Прототип (Prototype)");
        
        Prototype.DirtBlock baseBlock = new Prototype.DirtBlock("Звичайна земля");
        System.out.println("Оригінал: " + baseBlock);
        
        Prototype.DirtBlock clonedBlock1 = (Prototype.DirtBlock) baseBlock.doClone();
        System.out.println("Клон 1: " + clonedBlock1);
        
        Prototype.DirtBlock clonedBlock2 = (Prototype.DirtBlock) baseBlock.doClone();
        System.out.println("Клон 2: " + clonedBlock2);
    }
}