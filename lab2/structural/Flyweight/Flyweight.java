package structural.Flyweight;

import java.util.HashMap;
import java.util.Map;

public class Flyweight {

    public interface Tile {
        void draw(int x, int y);
    }

    public static class DirtTile implements Tile {
        public DirtTile() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void draw(int x, int y) {
            System.out.println("Малюємо Блок землі на координатах [" + x + ", " + y + "]");
        }
    }

    public static class StoneTile implements Tile {
        public StoneTile() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void draw(int x, int y) {
            System.out.println("Малюємо Блок каменю на координатах [" + x + ", " + y + "]");
        }
    }

    public static class TileFactory {
        private static Map<String, Tile> tilePool = new HashMap<>();

        public static Tile getTile(String type) {
            if (tilePool.containsKey(type)) {
                return tilePool.get(type);
            } else {
                Tile tile = null;
                if ("dirt".equals(type)) {
                    System.out.println("Завантаження текстури землі в пам'ять");
                    tile = new DirtTile();
                } else if ("stone".equals(type)) {
                    System.out.println("Завантаження текстури каменю в пам'ять");
                    tile = new StoneTile();
                }
                tilePool.put(type, tile);
                return tile;
            }
        }
    }
}