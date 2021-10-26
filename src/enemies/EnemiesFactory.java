package enemies;

public class EnemiesFactory {

    public Enemy makeEnemy(String newEnemyType) {

        return switch (newEnemyType) {
            case "Skeleton" -> new Skeleton();
            case "Zombie" -> new Zombie();
            case "Orc" -> new Orc();
            case "Goblin" -> new Goblin();
            case "Troll" -> new Troll();
            default -> null;
        };
    }
}
