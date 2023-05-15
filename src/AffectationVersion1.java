import java.util.List;

import fr.ulille.but.sae2_02.graphes.*;
import java.util.*;

public class AffectationVersion1{

    public static void main(String[] args){

        GrapheNonOrienteValue<String> graph= new GrapheNonOrienteValue<String>();

        List<String> employé=new ArrayList<>();
        employé.add("A");
        employé.add("B");
        employé.add("C");
        List<String> tache=new ArrayList<>();
        tache.add("Z");
        tache.add("X");
        tache.add("Y");

        CalculAffectation<String> calcul = new CalculAffectation<String>(graph,employé,tache);

        graph.ajouterSommet("A");
        graph.ajouterSommet("B");
        graph.ajouterSommet("C");
        graph.ajouterSommet("X");
        graph.ajouterSommet("Y");
        graph.ajouterSommet("Z");

        graph.ajouterArete("A","X",1);
        graph.ajouterArete("A","Y",0);
        graph.ajouterArete("A","Z",1);
        graph.ajouterArete("B","X",1);
        graph.ajouterArete("B","Y",(-9));
        graph.ajouterArete("B","Z",0);
        graph.ajouterArete("C","X",0);
        graph.ajouterArete("C","Y",2);
        graph.ajouterArete("C","Z",0);

        //System.out.println(graph.toString());
        System.out.println(calcul.calculerAffectation());
        System.out.println(calcul.getCout());
    }

}
