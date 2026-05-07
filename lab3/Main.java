import behavioral.TemplateMethod.TemplateMethod;
import behavioral.Mediator.Mediator;
import behavioral.ChainOfResponsibility.ChainOfResponsibility;
import behavioral.Observer.Observer;
import behavioral.Strategy.Strategy;
import behavioral.Command.Command;
import behavioral.State.State;
import behavioral.Visitor.Visitor;
import behavioral.Iterator.Iterator;
import behavioral.Memento.Memento;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. Патерн Шаблонний метод (Template Method)");
        TemplateMethod.BossFight slimeFight = new TemplateMethod.KingSlimeFight();
        slimeFight.conductFight();
        System.out.println();

        System.out.println("2. Патерн Медіатор (Mediator)");
        Mediator.EventMediator gameEventSystem = new Mediator.GoblinEventMediator();
        Mediator.Goblin goblin = new Mediator.Goblin(gameEventSystem);
        goblin.die();
        System.out.println();

        System.out.println("3. Патерн Ланцюжок відповідальностей (Chain of Responsibility)");
        ChainOfResponsibility.DamageHandler immunity = new ChainOfResponsibility.ImmunityHandler(false); 
        ChainOfResponsibility.DamageHandler wormScarf = new ChainOfResponsibility.AccessoryHandler();
        ChainOfResponsibility.DamageHandler solarArmor = new ChainOfResponsibility.ArmorHandler(78); 
        immunity.setNext(wormScarf);
        wormScarf.setNext(solarArmor);
        immunity.handle(100); 

        System.out.println("4. Патерн Спостерігач (Observer)");
        Observer.TimeManager timeManager = new Observer.TimeManager();
        timeManager.attach(new Observer.Zombie());
        timeManager.attach(new Observer.NPC("Гід"));
        timeManager.setTime(true);
        System.out.println();

        System.out.println("5. Патерн Стратегія (Strategy)");
        Strategy.EyeOfCthulhu boss = new Strategy.EyeOfCthulhu(new Strategy.PhaseOneStrategy());
        boss.performAttack();
        boss.setStrategy(new Strategy.PhaseTwoStrategy());
        boss.performAttack();
        System.out.println();

        System.out.println("6. Патерн Команда (Command)");
        Command.Player actionPlayer = new Command.Player();
        Command.Hotkey healKey = new Command.Hotkey("H");
        healKey.bindAction(new Command.HealCommand(actionPlayer));
        healKey.press();
        System.out.println();

        System.out.println("7. Патерн Стан (State)");
        State.TerrariaPlayer statePlayer = new State.TerrariaPlayer(new State.HealthyState());
        statePlayer.performMove();
        statePlayer.setState(new State.PoisonedState());
        statePlayer.performAttack();
        System.out.println();

        System.out.println("8. Патерн Відвідувач (Visitor)");
        Visitor.Player visitorPlayer = new Visitor.Player();
        Visitor.TownNPC visitorNPC = new Visitor.TownNPC();
        Visitor.BloodMoonVisitor bloodMoon = new Visitor.BloodMoonVisitor();
        System.out.println("Починається Кривавий Місяць:");
        visitorPlayer.accept(bloodMoon);
        visitorNPC.accept(bloodMoon);
        System.out.println();

        System.out.println("9. Патерн Ітератор (Iterator)");
        Iterator.PlayerInventory inventory = new Iterator.PlayerInventory();
        inventory.addItem(new Iterator.Item("Мідне кайло"));
        inventory.addItem(new Iterator.Item("Зілля лікування"));
        Iterator.InventoryIterator it = inventory.createIterator();
        System.out.print("Вміст інвентарю: ");
        while (it.hasNext()) {
            System.out.print("[" + it.next().getName() + "] ");
        }
        System.out.println("\n");

        System.out.println("10. Патерн Мементо (Memento)");
        Memento.Player mementoPlayer = new Memento.Player(100, 10, 20);
        Memento.GameSaveSystem saveSystem = new Memento.GameSaveSystem();
        System.out.println("Дослідження світу...");
        mementoPlayer.printState();
        saveSystem.saveGame(mementoPlayer);
        System.out.println("Гравець впав у лаву!");
        mementoPlayer.setHealthAndPosition(10, 500, 800);
        mementoPlayer.printState();
        System.out.println("Загружаємо сейв...");
        saveSystem.loadGame(mementoPlayer); 
        mementoPlayer.printState();
        System.out.println();
    }
}