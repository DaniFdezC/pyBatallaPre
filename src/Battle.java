import java.util.Random;
import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        final int ATTACK = 1;
        final int DEFENSE = 2;

        final int WARRIOR_HEALTH_FAINT = 30;
        final int VAMPIRE_HEALTH_FAINT = 20;
        final int HEALTH_RECOVER_PER_TURN = 2;

        int[] warriorDamageWeapons = {7, 15, 30};
        double[] warriorSuccessChanceWeapons = {0.5, 0.25, 0.12};
        int warriorHealth = 60;
        int warriorArmor = 0;

        int[] vampireDamageWeapons = {5, 10, 20};
        double[] vampireSuccessChanceWeapons = {0.9, 0.6, 0.4};
        int vampireHealth = 40;

        boolean bothCharactersAlive = warriorHealth > 0 && vampireHealth > 0;

        boolean warriorFainted = false;
        boolean vampireFainted = false;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (bothCharactersAlive){

            if (warriorFainted){
                warriorHealth = healCharacter(warriorHealth, HEALTH_RECOVER_PER_TURN, "guerrero");
                warriorFainted = checkIfCharacterFainted(warriorHealth, WARRIOR_HEALTH_FAINT);
            } else {
                switch (chooseAction(2, scanner)) {
                    case ATTACK:
                        int warriorChosenWeaponIndex = chooseWeapon(warriorDamageWeapons.length, scanner);

                        int warriorChosenWeaponDamage = warriorDamageWeapons[warriorChosenWeaponIndex];
                        double warriorChosenWeaponSuccess = warriorSuccessChanceWeapons[warriorChosenWeaponIndex];

                        double chanceWarriorToAttackThisTurn = random.nextDouble();

                        if (chanceWarriorToAttackThisTurn < warriorChosenWeaponSuccess){
                            vampireHealth -= warriorChosenWeaponDamage;
                            System.out.println("El guerrero ataca al vampiro y le inflinge " + warriorChosenWeaponDamage + " puntos de daño");
                            vampireFainted = checkIfCharacterFainted(vampireHealth, VAMPIRE_HEALTH_FAINT);
                        } else {
                            System.out.println("El guerrero falla su ataque");
                        }
                        break;

                    case DEFENSE:
                        System.out.println("El guerrero se defiende");
                        warriorArmor = 5;
                        break;
                    default:
                        System.out.println("Elige una acción correcta!");
                        break;
                }
            }

            if (vampireHealth <= 0){
                System.out.println("El guerrero ha derrotado al vampiro");
                bothCharactersAlive = false;
            } else {

                if (vampireFainted){
                    vampireHealth = healCharacter(vampireHealth, HEALTH_RECOVER_PER_TURN, "vampiro");
                    vampireFainted = checkIfCharacterFainted(vampireHealth, VAMPIRE_HEALTH_FAINT);
                } else {
                    int vampireChosenWeaponIndex = random.nextInt(3);
                    int vampireChosenWeaponDamage = vampireDamageWeapons[vampireChosenWeaponIndex];
                    double vampireChosenWeaponSuccess = vampireSuccessChanceWeapons[vampireChosenWeaponIndex];

                    double chanceVampireToAttackThisTurn = random.nextDouble();

                    if (chanceVampireToAttackThisTurn < vampireChosenWeaponSuccess){
                        int vampireDamageDealt = vampireChosenWeaponDamage - warriorArmor;
                        warriorHealth -= vampireDamageDealt;
                        System.out.println("El vampiro ataca al guerrero y le inflinge " + vampireDamageDealt + " puntos de daño");
                        warriorFainted = checkIfCharacterFainted(warriorHealth, WARRIOR_HEALTH_FAINT);
                    } else {
                        System.out.println("El vampiro falla su ataque");
                    }
                }

                if (warriorHealth <= 0){
                    System.out.println("El vampiro ha derrotado al guerrero");
                    bothCharactersAlive = false;
                }
            }

            warriorArmor = 0;
            System.out.println("Vida actual del guerrero: " + warriorHealth);
            System.out.println("Vida actual del vampiro: " + vampireHealth);
            System.out.println();
            System.out.println("Presiona enter para continuar...");
            scanner.nextLine();

        }

        scanner.close();
    }

    public static int chooseWeapon(int maxChoices, Scanner scanner){
        int chosen;

        do {
            System.out.println("Elige un arma entre 3 opciones - Arma 1 ( 7 - 50%) ; Arma 2 (15 - 25%) ; Arma 3 (30 - 12%)");
            chosen = scanner.nextInt();
        } while (chosen < 1 || chosen > maxChoices);

        return chosen - 1;
    }

    public static int chooseAction(int maxChoices, Scanner scanner){
        int chosen;

        do {
            System.out.println("Elige una acción: 1 - Atacar ; 2 - Defenderse");
            chosen = scanner.nextInt();
        } while (chosen < 1 || chosen > maxChoices);

        return chosen;
    }

    public static int healCharacter(int characterHealth, int quantityToHeal, String character){
        int newHealth = characterHealth + quantityToHeal;
        System.out.println("El " + character + " está desmayado y se ha curado " + quantityToHeal +
                " ahora tiene "+ newHealth + " puntos de vida");

        return newHealth;
    }

    public static boolean checkIfCharacterFainted(int characterHealth, int characterHealthToFaint){
        return characterHealth < characterHealthToFaint;
    }

}