package structural;

public class Proxy {

    public interface Entity {
        void spawn();
    }

    public static class SimpleMob implements Entity {
        @Override
        public void spawn() {
            System.out.println("Звичайний моб заспавнився.");
        }
    }

    public static class BossMob implements Entity {
        public BossMob() {
            try {
                System.out.println("Гід був убитий");
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void spawn() {
            System.out.println("Стіна плоті прокинулася!");
        }
    }

    public static class BossProxy implements Entity {
        private BossMob bossMob;

        @Override
        public void spawn() {
            if (bossMob == null) {
                bossMob = new BossMob();
            }
            bossMob.spawn();
        }
    }
}