package game;

import dagger.Component;
import javax.inject.Inject;


class ItemLogger {
    @Inject public ItemLogger() {}
    public void log(String message) { System.out.println("[Лог]: " + message); }
}

class HealthManager {
    @Inject public HealthManager() {}
    public void setupHealth() { System.out.println("Здоров'я гравця ініціалізовано: 100 HP."); }
}

class WorldGenerator {
    @Inject public WorldGenerator() {}
    public void generate() { System.out.println("Генерація світу: Створення біомів та руди..."); }
}

class NetworkClient {
    @Inject public NetworkClient() {}
    public void connect() { System.out.println("Підключення до багатокористувацького сервера успішне."); }
}


class Inventory {
    private final ItemLogger logger;

    @Inject
    public Inventory(ItemLogger logger) {
        this.logger = logger;
    }

    public void loadItems() {
        logger.log("Інвентар завантажено (Дерев'яний меч).");
    }
}


class TerrariaPlayer {
    private final Inventory inventory;
    private final HealthManager healthManager;

    @Inject
    public TerrariaPlayer(Inventory inventory, HealthManager healthManager) {
        this.inventory = inventory;
        this.healthManager = healthManager;
    }

    public void spawn() {
        System.out.println("Спавн Гравця");
        healthManager.setupHealth();
        inventory.loadItems();
        System.out.println("Гравець існує");
    }
}


class TerrariaGame {
    private final TerrariaPlayer player;
    private final WorldGenerator worldGenerator;
    private final NetworkClient networkClient;

    @Inject
    public TerrariaGame(TerrariaPlayer player, WorldGenerator worldGenerator, NetworkClient networkClient) {
        this.player = player;
        this.worldGenerator = worldGenerator;
        this.networkClient = networkClient;
    }

    public void start() {
        System.out.println("ЗАПУСК TERRARIA");
        networkClient.connect();
        worldGenerator.generate();
        player.spawn();
        System.out.println("ГРА ПОЧАЛАСЯ");
    }
}


@Component
interface GameComponent {

    TerrariaGame getGame();
}


public class Main {
    public static void main(String[] args) {

        GameComponent component = DaggerGameComponent.create();
        

        TerrariaGame game = component.getGame();
        
        game.start();
    }
}