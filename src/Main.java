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

    // Calcule la somme des dés
    private static int sommeDes(Des[] resultats) {
        int somme = 0;
        for (Des resultat : resultats) {
            somme += resultat.getFace();
        }
        return somme;
    }

    // Calcule les points en fonction de la combinaison détectée
    private static int calculerPoints(Des[] resultats, String combinaison) {
        switch (combinaison) {
            case "Yahtzee":
                return 50;
            case "Carré":
                return sommeDes(resultats);
            case "Brelan":
                return sommeDes(resultats);
            case "Full House":
                return 25;
            case "Petite Suite":
                return 30;
            case "Grande Suite":
                return 40;
            case "Paire":
                return sommeDes(resultats);
            default:
                return 0;
        }
    }

    // Détecte la combinaison des dés
    private static String detecterCombinaison(Des[] resultats) {
        int[] MemeFace = new int[6];

        // Compter le nombre de fois que chaque face apparaît
        for (Des resultat : resultats) {
            int face = resultat.getFace();
            MemeFace[face - 1]++;
        }

        // Variables pour identifier les combinaisons
        boolean brelan = false;
        boolean carre = false;
        boolean yahtzee = false;
        int paires = 0;

        // Analyse des meme faces
        for (int occurrence : MemeFace) {
            if (occurrence == 5) {
                yahtzee = true;
            } else if (occurrence == 4) {
                carre = true;
            } else if (occurrence == 3) {
                brelan = true;
            } else if (occurrence == 2) {
                paires++;
            }
        }

        // Détecter les suites
        boolean petiteSuite = (MemeFace[0] >= 1 && MemeFace[1] >= 1 && MemeFace[2] >= 1 && MemeFace[3] >= 1) ||
                (MemeFace[1] >= 1 && MemeFace[2] >= 1 && MemeFace[3] >= 1 && MemeFace[4] >= 1) ||
                (MemeFace[2] >= 1 && MemeFace[3] >= 1 && MemeFace[4] >= 1 && MemeFace[5] >= 1);

        boolean grandeSuite = (MemeFace[0] == 1 && MemeFace[1] == 1 && MemeFace[2] == 1 && MemeFace[3] == 1 && MemeFace[4] == 1) ||
                (MemeFace[1] == 1 && MemeFace[2] == 1 && MemeFace[3] == 1 && MemeFace[4] == 1 && MemeFace[5] == 1);

        // Retourne la combinaison détectée
        if (yahtzee) {
            return "Yahtzee";
        }
        if (carre) {
            return "Carré";
        }
        if (brelan && paires == 1) {
            return "Full House";
        }
        if (brelan) {
            return "Brelan";
        }
        if (grandeSuite) {
            return "Grande Suite";
        }
        if (petiteSuite) {
            return "Petite Suite";
        }
        if (paires == 2) {
            return "Double Paire";
        }
        if (paires == 1) {
            return "Paire";
        }

        return "Aucune combinaison";
    }

    public static void main(String[] args) {

        Des[] resultats = lancerDes();
        Scanner scanner = new Scanner(System.in);

        int pointsAuTotal = 0;

        for (int index = 0; index < NBRE_LANCERS_AUTORISE; index++) {
            resultatDes(resultats);

            // Détecte et affiche la combinaison réalisée
            String combinaison = detecterCombinaison(resultats);
            System.out.println("Combinaison réalisée : " + combinaison);

            // Calcul des points en fonction de la combinaison
            int points = calculerPoints(resultats, combinaison);
            pointsAuTotal += points;

            // Afficher les points reçus et le total
            System.out.println("Vous avez reçu " + points + " points pour cette combinaison.");
            System.out.println("Total cumulé des points : " + pointsAuTotal);

            // Ne pas demander de relancer des dés après le dernier lancer
            if (index < NBRE_LANCERS_AUTORISE - 1) {
                System.out.print("Voulez-vous relancer des dés ? (oui/non) : ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("oui")) {
                    System.out.print("Quel(s) dé(s) voulez-vous relancer ? (numéro entre 1 et 5 séparé(s) d'un espace) : ");
                    String choix = scanner.nextLine();
                    String[] choixDes = choix.split(" ");

                    for (String choixDe : choixDes) {
                        int choixNombre = Integer.parseInt(choixDe);

                        if (choixNombre >= 1 && choixNombre <= NBRE_DE) {
                            resultats[choixNombre - 1].Lance();
                        } else {
                            System.out.println("Numéro de dé invalide : " + choixNombre);
                        }
                    }
                } else {
                    break;
                }
            }
        }

        System.out.println("Le jeu est terminé. Votre score total est de : " + pointsAuTotal);
    }
}