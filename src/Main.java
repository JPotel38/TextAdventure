import enemies.EnemiesFactory;
import enemies.Enemy;
import weapons.Weapon;
import weapons.WeaponsFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // System object
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton", "Zombie", "Orc", "Goblin", "Troll"};
        int enemiesKilled = 0;
        String[] weapons = {"Iron sword", "Steel katana"};
        WeaponsFactory weaponsFactory = new WeaponsFactory();
        EnemiesFactory enemiesFactory = new EnemiesFactory();

        // Player variables
        int health = 100;
        int mp = 50;
        int spellDamage = 80;
        int numHealthPotions = 3;
        int numManaPotions = 1;
        int goldAmount = 0;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage
        int manaPotionDropChance = 30; // Percentage
        int manaPotionRegenAmount = 15;
        int goldDropChance = 30; // Percentage
        int goldDropped = 40; // Percentage
        Weapon weapon = weaponsFactory.makeWeapon("Iron sword");
        int weaponDropChance = 50; // Percentage
        int attackDamage = weapon.getDamage();

        ArrayList<Weapon> weaponInventory = new ArrayList<>();
        weaponInventory.add(weapon);

        boolean running = true;

        System.out.println("""
                As a mercenary, you were hired to clean the local evil dungeon.
                'Time to enter it, I guess', you think yourself.""");

        GAME:
        while (running) {

            label:
            while (health > 0) {
                System.out.println("-----------------------------------------------");

                System.out.println("\t1. Status");
                System.out.println("\t2. Inventory");
                System.out.println("\t3. Drink health potion");
                System.out.println("\t4. Drink mana potion");
                System.out.println("\t5. Keep advancing");
                String input = in.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("\t#Your HP: " + health);
                        System.out.println("\t#Your MP: " + mp);
                        break label;
                    case "2":
                        for (Weapon i : weaponInventory) {
                            System.out.println("\t#" + i.getName() + " " + i.getDamage() + " damage");
                        }
                        break label;
                    case "3":
                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + " hp." +
                                    "\n\t> You now have " + health + " hp !" +
                                    "\n\t> You have " + numHealthPotions + " health potions left. \n");
                        } else {
                            System.out.println("You have no health potions left ! Defeat enemies to have a chance to get one !");
                        }
                        break label;
                    case "4":
                        if (numManaPotions > 0) {
                            mp += manaPotionRegenAmount;
                            numManaPotions--;
                            System.out.println("\t> You drink a mana potion, regen your magic for " + manaPotionRegenAmount + " mp." +
                                    "\n\t> You now have " + mp + " mp !" +
                                    "\n\t> You have " + numManaPotions + " mana potions left. \n");
                        } else {
                            System.out.println("You have no mana potions left ! Defeat enemies to have a chance to get one !");
                        }
                        break label;
                    case "5":
                        System.out.println("-----------------------------------------------");

                        Enemy enemy = enemiesFactory.makeEnemy(enemies[rand.nextInt(enemies.length)]);
                        String enemyName = enemy.getName();
                        int enemyHealth = enemy.getHp();
                        int enemyAttackDamage = enemy.getDamage();

                        System.out.println("\t# " + enemyName + " has appeared ! #\n");

                        while (enemyHealth > 0) {
                            System.out.println("\t#Your HP: " + health);
                            System.out.println("\t#Your MP: " + mp);
                            System.out.println("\t#" + enemyName + "'s HP: " + enemyHealth);
                            System.out.println("\n\tWhat would you like to do ?");
                            System.out.println("\t1. Attack");
                            System.out.println("\t2. Run !");

                            input = in.nextLine();
                            switch (input) {
                                case "1" -> {
                                    System.out.println("\t1. Use weapon.");
                                    System.out.println("\t2. Use fireball (10 mp).");
                                    input = in.nextLine();
                                    if (input.equals("1")) {
                                        int damageDealt = rand.nextInt(attackDamage);
                                        int damageTaken = rand.nextInt(enemyAttackDamage);

                                        enemyHealth -= damageDealt;
                                        health -= damageTaken;

                                        System.out.println("\t> You strike the " + enemyName + " with your " + weapon.getName() + " for " + damageDealt + " damage.");
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

                                        System.out.println("\t> You burn the " + enemyName + " for " + damageDealt + " damage.");
                                        System.out.println("\t> You receive " + damageTaken + " in retaliation.");
                                        System.out.println("\t> You lose 10 mp. You have " + mp + " mp left.");

                                        if (health < 1) {
                                            System.out.println("\t> You have taken too much damage !");
                                        }
                                    }
                                }
                                case "2" -> {
                                    System.out.println("you run away from the " + enemyName + " !");
                                    continue GAME;
                                }
                                default -> System.out.println("\tInvalid command !");
                            }
                        }
                        if (health < 1) {
                            System.out.println("You limp out of the dungeon, weak from the battle...");
                            break;
                        }
                        System.out.println("-----------------------------------------------");
                        System.out.println(" # " + enemyName + " was defetead! #");
                        enemiesKilled++;
                        System.out.println(" # You have " + health + " hp left. # ");
                        if (rand.nextInt(100) < healthPotionDropChance) {
                            numHealthPotions++;
                            System.out.println(" # The " + enemyName + " dropped a health potion! #");
                            System.out.println((" # You now have " + numHealthPotions + " health potion(s). # "));
                        }
                        if (rand.nextInt(100) < manaPotionDropChance) {
                            numManaPotions++;
                            System.out.println(" # The " + enemyName + " dropped a mana potion! #");
                            System.out.println((" # You now have " + numManaPotions + " mana potion(s). # "));
                        }
                        if (rand.nextInt(100) < goldDropChance && (enemyName.equals(enemies[2]) || enemyName.equals(enemies[3]))) {
                            goldDropped = rand.nextInt(goldDropped);
                            goldAmount += goldDropped;
                            System.out.println(" # The " + enemyName + " dropped " + goldDropped + " gold ! Lucky you ! #");
                            System.out.println((" # You now have " + goldAmount + " gold. # "));
                        }
                        if (rand.nextInt(100) < weaponDropChance) {
                            String weaponDropped = weapons[rand.nextInt(weapons.length)];
                            Weapon weaponAdded = weaponsFactory.makeWeapon(weaponDropped);
                            System.out.println(" # The " + enemyName + " dropped a " + weaponDropped + " ! #");
                            weaponInventory.add(weaponAdded);
                        }
                        System.out.println("-----------------------------------------------");
                        System.out.println("What would you like to do now ?");
                        System.out.println("1. Continue wandering in the dungeon");
                        System.out.println("2. Exit dungeon");

                        input = in.nextLine();

                        while (!input.equals("1") && !input.equals("2")) {
                            System.out.println("Invalid command !");
                            input = in.nextLine();
                        }

                        if (input.equals("1")) {
                            System.out.println("You continue your adventure !");
                        } else {
                            System.out.println("You exit the dungeon, successful from your adventure ! You killed " + enemiesKilled + " enemy/ies and leave with " + goldAmount + " gold !");
                            System.out.println("##############################");
                            System.out.println("# Thanks for playing, dude ! #");
                            System.out.println("##############################");
                            break label;
                        }
                        break;
                    default:
                        System.out.println("Invalid command !");
                        break;
                }
            }
        }
    }
}
