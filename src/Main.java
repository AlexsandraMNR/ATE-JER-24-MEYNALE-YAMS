package src;

import java.util.Scanner;

public class Main {

    private static final int NBRE_LANCERS_AUTORISE = 3;
    private static final int NBRE_DE = 5;

    // Lance chaque dé et retourne les résultats dans un tableau
    private static Des[] lancerDes() {
        Des[] resultats = new Des[NBRE_DE];
        for (int index = 0; index < NBRE_DE; index++) {
            resultats[index] = new Des();
            resultats[index].Lance();
        }
        return resultats;
    }

    // Affiche le résultat de chaque dé
    private static void resultatDes(Des[] resultats) {
        for (int index = 0; index < resultats.length; index++) {
            System.out.println("Dé " + (index + 1) + " : " + resultats[index].getFace());
        }
    }

    // Affiche la somme des dés
    private static int sommeDes(Des[] resultats) {
        int somme = 0;
        for (Des resultat : resultats) {
            somme += resultat.getFace();
        }
        return somme;
    }

    public static void main(String[] args) {

        Des[] resultats = lancerDes();
        Scanner scanner = new Scanner(System.in);

        for (int index = 0; index < NBRE_LANCERS_AUTORISE; index++) {
            resultatDes(resultats);

            int sommeDes = sommeDes(resultats);
            System.out.println("La somme des dés est : " + sommeDes);

            System.out.print("Voulez-vous relancer des dés ? (oui/non) : ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("oui")) {
                System.out.print("Quel dés voulez vous relancer ? (numéro entre 1 et 5 séparé d'un espace)");
                String choix = scanner.nextLine();
                String[] choixDes = choix.split(" ");

                for (String choixDe : choixDes) {
                    int choixNombre = Integer.parseInt(choixDe);

                    if (choixNombre >= 1 && choixNombre <= NBRE_DE) {
                        resultats[choixNombre - 1].Lance();
                    } else {
                        System.out.println("Numéro de dé invalide : " + choixNombre);
                        break;
                    }
                }

            }
        }
    }
}