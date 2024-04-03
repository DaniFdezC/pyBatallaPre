import java.util.Random;
import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        final int ATTACK = 1;
        final int DEFENSE = 2;

        int[] warriorDamageWeapons = {7, 15, 30};
        double[] warriorSuccessChanceWeapons = {0.5, 0.25, 0.12};
        int warriorHealth = 40;
        int warriorArmor = 0;


        int[] vampireDamageWeapons = {5, 10, 20};
        double[] vampireSuccessChanceWeapons = {0.9, 0.6, 0.4};
        int vampireHealth = 30;

        boolean bothCharactersAlive = warriorHealth > 0 && vampireHealth > 0;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (bothCharactersAlive){

            switch (chooseAction(2, scanner)) {
                case ATTACK:
                    int warriorChosenWeaponIndex = chooseWeapon(warriorDamageWeapons.length, scanner);

                    int warriorChosenWeaponDamage = warriorDamageWeapons[warriorChosenWeaponIndex];
                    double warriorChosenWeaponSuccess = warriorSuccessChanceWeapons[warriorChosenWeaponIndex];

                    double chanceWarriorToAttackThisTurn = random.nextDouble();

                    if (chanceWarriorToAttackThisTurn < warriorChosenWeaponSuccess){
                        vampireHealth -= warriorChosenWeaponDamage;
                        System.out.println("El guerrero ataca al vampiro y le inflinge " + warriorChosenWeaponDamage + " puntos de daño");
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


            if (vampireHealth <= 0){
                System.out.println("El guerrero ha derrotado al vampiro");
                bothCharactersAlive = false;
            } else {

                int vampireChosenWeaponIndex = random.nextInt(3);
                int vampireChosenWeaponDamage = vampireDamageWeapons[vampireChosenWeaponIndex];
                double vampireChosenWeaponSuccess = vampireSuccessChanceWeapons[vampireChosenWeaponIndex];

                double chanceVampireToAttackThisTurn = random.nextDouble();

                if (chanceVampireToAttackThisTurn < vampireChosenWeaponSuccess){
                    int vampireDamageDealt = vampireChosenWeaponDamage - warriorArmor;
                    warriorHealth -= vampireDamageDealt;
                    System.out.println("El vampiro ataca al guerrero y le inflinge " + vampireDamageDealt + " puntos de daño");
                } else {
                    System.out.println("El vampiro falla su ataque");
                }

                if (warriorHealth <= 0){
                    System.out.println("El vampiro ha derrotado al guerrero");
                    bothCharactersAlive = false;
                }
            }

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

}