package creational;

public class Builder {

    public static class ArmorSet {
        private String helmet;
        private String chestplate;
        private String leggings;

        public void setHelmet(String helmet) { this.helmet = helmet; }
        public void setChestplate(String chestplate) { this.chestplate = chestplate; }
        public void setLeggings(String leggings) { this.leggings = leggings; }

        @Override
        public String toString() {
            return "[" + helmet + ", " + chestplate + ", " + leggings + "]";
        }
    }

    public interface ArmorBuilder {
        void buildHelmet();
        void buildChestplate();
        void buildLeggings();
        ArmorSet getArmorSet();
    }

    public static class CactusArmorBuilder implements ArmorBuilder {
        private ArmorSet armor = new ArmorSet();

        @Override
        public void buildHelmet() { armor.setHelmet("Кактусовий шолом"); }
        @Override
        public void buildChestplate() { armor.setChestplate("Кактусовий нагрудник"); }
        @Override
        public void buildLeggings() { armor.setLeggings("Кактусові поножі"); }
        @Override
        public ArmorSet getArmorSet() { return armor; }
    }

    public static class JungleArmorBuilder implements ArmorBuilder {
        private ArmorSet armor = new ArmorSet();

        @Override
        public void buildHelmet() { armor.setHelmet("Шолом джунглів"); }
        @Override
        public void buildChestplate() { armor.setChestplate("Нагрудник джунглів"); }
        @Override
        public void buildLeggings() { armor.setLeggings("Поножі джунглів"); }
        @Override
        public ArmorSet getArmorSet() { return armor; }
    }


    public static class ArmorCrafter {
        private ArmorBuilder builder;

        public ArmorCrafter(ArmorBuilder builder) {
            this.builder = builder;
        }

        public void constructArmor() {
            builder.buildHelmet();
            builder.buildChestplate();
            builder.buildLeggings();
        }

        public ArmorSet getArmor() {
            return builder.getArmorSet();
        }
    }
}