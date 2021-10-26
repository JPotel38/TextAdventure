import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // System object
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 100;
        int mp = 50;
        int attackDamage = 50;
        int spellDamage = 80;
        int numHealthPotions = 3;
        int numManaPotions = 1;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage
        int manaPotionDropChance = 30; // Percentage
        int manaPotionRegenAmount = 15;

        boolean running = true;

        System.out.println("""
                As a mercenary, you were hired to clean the local evil dungeon.
                'Time to enter it, I guess', you think yourself.
                An enemy is already waiting for you. It wilb be an amazing day !""");

        GAME:
        while (running) {
            System.out.println("-----------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " has appeared ! #\n");

            while (enemyHealth > 0) {
                System.out.println("\t#Your HP: " + health);
                System.out.println("\t#Your MP: " + mp);
                System.out.println("\t#" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do ?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Drink mana potion");
                System.out.println("\t4. Run !");

                String input = in.nextLine();
                switch (input) {
                    case "1":
                        System.out.println("\t1. Use weapon.");
                        System.out.println("\t2. Use fireball (10 mp).");
                        input = in.nextLine();
                        if (input.equals("1")) {
                            int damageDealt = rand.nextInt(attackDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);

                            enemyHealth -= damageDealt;
                            health -= damageTaken;

                            System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                            System.out.println("\t> You receive " + damageTaken + " in retaliation.");

                            if (health < 1) {
                                System.out.println("\t> You have taken too much damage !");
                            }
                        } else if (input.equals("2")) {
                            int damageDealt = rand.nextInt(spellDamage);
                            int damageTaken = rand.nextInt(enemyAttackDamage);
                            enemyHealth -= damageDealt;
                            health -= damageTaken;
                            mp -= 10;

                            System.out.println("\t> You burn the " + enemy + " for " + damageDealt + " damage.");
                            System.out.println("\t> You receive " + damageTaken + " in retaliation.");

                            System.out.println("\t> You lose 10 mp. You have " + mp + " left.");

                            if (health < 1) {
                                System.out.println("\t> You have taken too much damage !");
                            }
                        }
                        break;
                    case "2":
                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + " hp." +
                                    "\n\t> You now have " + health + " hp !" +
                                    "\n\t> You have " + numHealthPotions + " health potions left. \n");
                        } else {
                            System.out.println("You have no health potions left ! Defeat enemies to have a chance to get one !");
                        }
                        break;
                    case "3":
                        if (numManaPotions > 0) {
                            mp += manaPotionRegenAmount;
                            numManaPotions--;
                            System.out.println("\t> You drink a mana potion, regen your magic for " + manaPotionRegenAmount + " mp." +
                                    "\n\t> You now have " + mp + " mp !" +
                                    "\n\t> You have " + numManaPotions + " mana potions left. \n");
                        } else {
                            System.out.println("You have no mana potions left ! Defeat enemies to have a chance to get one !");
                        }
                        break;
                    case "4":
                        System.out.println("you run away from the " + enemy + " !");
                        continue GAME;
                    default:
                        System.out.println("\tInvalid command !");
                        break;
                }
            }
            if (health < 1) {
                System.out.println("You limp out of the dungeon, weak from the battle...");
                break;
            }
            System.out.println("-----------------------------------------------");
            System.out.println(" # " + enemy + " was defetead! #");
            System.out.println(" # You have " + health + " hp left. # ");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! #");
                System.out.println((" # You now have " + numHealthPotions + " health potion(s). # "));
            }
            if (rand.nextInt(100) < manaPotionDropChance) {
                numManaPotions++;
                System.out.println(" # The " + enemy + " dropped a mana potion! #");
                System.out.println((" # You now have " + numManaPotions + " mana potion(s). # "));
            }
            System.out.println("-----------------------------------------------");
            System.out.println("What would you like to do now ?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command !");
                input = in.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("You continue your adventure !");
            } else {
                System.out.println("You exit the dungeon, successful from your adventure!");
                break;
            }
        }
        System.out.println("##############################");
        System.out.println("# Thanks for playing, dude ! #");
        System.out.println("##############################");
    }
}
