package creational.Prototype;

public class Prototype {

    public interface TerrariaPrototype {
        TerrariaPrototype doClone();
    }

    public static class DirtBlock implements TerrariaPrototype {
        private String type;

        public DirtBlock(String type) {
            this.type = type;
        }

        @Override
        public TerrariaPrototype doClone() {
            return new DirtBlock(this.type);
        }

        @Override
        public String toString() {
            return "Блок: " + type;
        }
    }
}