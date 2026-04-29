import structural.Adapter;
import structural.Composite;
import structural.Proxy;
import structural.Flyweight;
import structural.Facade;
import structural.Bridge;
import structural.Decorator;

public class Main {
    public static void main(String[] args) {
        System.out.println(" Структурні патерни\n");

        System.out.println("1 Патерн Адаптер (Adapter)");
        
        Adapter.MeleeWeapon normalSword = new Adapter.CopperShortsword();
        System.out.println("Звичайний удар: " + normalSword.swingSword());

        Adapter.MagicWeapon magic = new Adapter.MagicWeapon();
        Adapter.MeleeWeapon enchantedSword = new Adapter.MagicToMeleeAdapter(magic);
        System.out.println("Через адаптер: " + enchantedSword.swingSword() + "\n");

        System.out.println("2 Патерн Композит (Composite)");
        
        Composite.Chest mainChest = new Composite.Chest("Золота скриня");
        Composite.Chest potionPouch = new Composite.Chest("Мішечок для зілля");
        
        mainChest.add(new Composite.Item("Залізний злиток"));
        mainChest.add(new Composite.Item("Кактус"));
        
        potionPouch.add(new Composite.Item("Мале зілля лікування"));
        potionPouch.add(new Composite.Item("Мале зілля мани"));
        
        mainChest.add(potionPouch);
        
        mainChest.display();
        System.out.println();

        System.out.println("3 Патерн Проксі (Proxy)");
        
        Proxy.Entity slime = new Proxy.SimpleMob();
        slime.spawn();
        
        Proxy.Entity bossProxy = new Proxy.BossProxy();
        System.out.println("[Проксі створено, але бос ще не завантажений в пам'ять]");
        
        bossProxy.spawn(); 
        bossProxy.spawn(); 
        System.out.println();

        System.out.println("4 Патерн Легковик (Flyweight)");
        
        for (int i = 0; i < 3; i++) {
            Flyweight.Tile dirt = Flyweight.TileFactory.getTile("dirt");
            dirt.draw(i, 0);
        }
        
        for (int i = 0; i < 3; i++) {
            Flyweight.Tile stone = Flyweight.TileFactory.getTile("stone");
            stone.draw(i, 1);
        }
        System.out.println();

        System.out.println("5 Патерн Фасад (Facade)");
        
        Facade.WorldGenerationFacade worldGen = new Facade.WorldGenerationFacade();
        worldGen.createWorld();
        System.out.println();

        System.out.println("6 Патерн Міст (Bridge)");
        
        Bridge.Weapon mySword = new Bridge.Sword(new Bridge.LegendaryModifier());
        mySword.attack();
        
        System.out.println("Перековуємо меч у Гобліна...");
        mySword.setModifier(new Bridge.BrokenModifier());
        mySword.attack();
        
        Bridge.Weapon myBow = new Bridge.Bow(new Bridge.LegendaryModifier());
        myBow.attack();
        System.out.println();

        System.out.println("7 Патерн Декоратор (Decorator)");
        
        Decorator.TerrariaPlayer player = new Decorator.BasicPlayer();
        System.out.println("Базовий стан:");
        player.describe();
        System.out.println();
        
        System.out.println("Екіпіруємо аксесуари:");
        player = new Decorator.HermesBoots(player);
        player = new Decorator.RocketBoots(player);
        player = new Decorator.ShieldOfCthulhu(player);
        
        player.describe();
        System.out.println();
    }
}