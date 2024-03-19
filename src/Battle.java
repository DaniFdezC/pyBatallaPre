import java.util.Random;
import java.util.Scanner;

public class Battle {
    public static void main(String[] args) {
        int warriorHealth = 20;
        int warriorAttack = 2;
        double warriorSuccessChance = 0.5;

        int vampireHealth = 10;
        int vampireAttack = 5;
        double vampireSuccessChance = 0.5;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (warriorHealth > 0 && vampireHealth > 0){

            double chanceWarriorToAttackThisTurn = random.nextDouble();

            if (chanceWarriorToAttackThisTurn < warriorSuccessChance){
                vampireHealth -= warriorAttack;
                System.out.println("El guerrero ataca al vampiro y le inflinge " + warriorAttack + " puntos de daño");
            } else {
                System.out.println("El guerrero falla su ataque");
            }

            if (vampireHealth >= 0){
                System.out.println("El guerrero ha derrotado al vampiro");
            } else {

                double chanceVampireToAttackThisTurn = random.nextDouble();

                if (chanceVampireToAttackThisTurn < vampireSuccessChance){
                    warriorHealth -= vampireAttack;
                    System.out.println("El vampiro ataca al guerrero y le inflinge " + vampireAttack + " puntos de daño");
                } else {
                    System.out.println("El vampiro falla su ataque");
                }

                if (warriorHealth <= 0){
                    System.out.println("El vampiro ha derrotado al guerrero");
                }
            }

            System.out.println("Vida actual del guerrero: " + warriorHealth);
            System.out.println("Vida actual del vampiro" + vampireHealth);
            System.out.println();
            System.out.print("Presiona enter para continuar...");
            scanner.nextLine();

        }

        scanner.close();
    }
}