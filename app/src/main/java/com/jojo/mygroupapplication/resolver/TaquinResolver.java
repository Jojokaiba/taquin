package com.jojo.mygroupapplication.resolver;

import com.jojo.mygroupapplication.data.Matrice;
import com.jojo.mygroupapplication.views.DrawingView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaquinResolver {
    public static boolean resolve(Matrice depart , Matrice cible , DrawingView drawingView) {

        Set<Matrice> visitees = new HashSet<>();
        List<Matrice> chemin = new ArrayList<>();

        System.out.println("=== BACKTRACKING START ===");

        boolean trouve = backtracking(depart, cible, visitees, chemin);

        if (trouve) {
            System.out.println("\n=== SOLUTION TROUVÉE ===");
            int step = 0;
            for (Matrice m : chemin) {
                System.out.println("Étape " + step++);
                m.afficher();
                drawingView.setCurrent(m);

                try {
                    Thread.sleep(1500);
                }catch(InterruptedException e) {


                }

            }
        } else {
            System.out.println("Aucune solution trouvée.");
        }

        System.out.println("Configurations explorées : " + visitees.size());
        return trouve;
    }

    // Backtracking
    public static boolean backtracking(Matrice current,
                                       Matrice cible,
                                       Set<Matrice> visitees,
                                       List<Matrice> chemin) {

        chemin.add(current);
        visitees.add(current);

        if (current.equals(cible)) {
            return true;
        }

        List<Matrice> possibilites = current.getPossibilites();

        // tri heuristique
        possibilites.sort(Comparator.comparingDouble(m -> m.distance(cible)));

        for (Matrice next : possibilites) {
            if (!visitees.contains(next)) {

                if (backtracking(next, cible, visitees, chemin)) {
                    return true;
                }
            }
        }

        // retour arrière
        chemin.remove(chemin.size() - 1);

        return false;
    }
}
