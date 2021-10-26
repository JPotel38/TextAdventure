public class WeaponsFactory {

    public Weapon makeWeapon(String newWeaponType) {
        Weapon newWeapon = null;

        return switch (newWeaponType) {
            case "Iron sword" -> new IronSword();
            case "Iron shield" -> new IronShield();
            case "Bow" -> new Bow();
            case "Steel katana" -> new SteelKatana();
            default -> null;
        };
    }
}
