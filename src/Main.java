import java.util.Scanner;

public class Main {
    private static Platform platform = new Platform();
    private static Boolean isRunning = true;

    private static char tableauDeBord() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Ajouter un Etudiant à la liste");
        System.out.println("4 - Ajouter un Etudiant à la liste");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        sc.close();
        return c;
    }

    private static Teenager addToPlatform() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Age : ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Adresse : ");
        platform.addTeenager(new Teenager(nom, prenom, age,));
    }

    public static void main(String[] args) {
        while (isRunning) {
            if (tableauDeBord() == '1') {
                System.out.println("Vous avez choisi 1");
                addToPlatform();
            }
            if (tableauDeBord() == '2') System.out.println("Vous avez choisi 2");
            if (tableauDeBord() == '3') System.out.println("Vous avez choisi 3");
            if (tableauDeBord() == '3') System.out.println("Vous avez choisi 4");
            return;
        }
    }
}
