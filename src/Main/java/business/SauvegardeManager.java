package business;

import java.io.*;

public class SauvegardeManager {


    private static final String NOM_FICHIER = "sauvegarde_puissance4.ser";

    /**
     * Sauvegarde l'objet Partie dans un fichier.
     */
    public static void sauvegarder(Partie partie) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOM_FICHIER))) {
            oos.writeObject(partie);
        }
    }

    /**
     * Charge l'objet Partie depuis le fichier.
     */
    public static Partie charger() throws IOException, ClassNotFoundException {
        File fichier = new File(NOM_FICHIER);
        if (!fichier.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOM_FICHIER))) {
            return (Partie) ois.readObject();
        }
    }
}