# TP3 FAIS PAR GAEL TUCZAPSKI et TITOUAN MATHIEU

ici votre rapport
la class Graph:
méthode :
isVertex verifie si appartient au graphe
addArc ajoute un arc
addEdge ajoute un arc et son inverse et arête
outNeighbours renvoie la liste des arcs sortants d'un sommet donné.
getSuccessors renvoie la liste des successeurs d'un sommet donné.
getRandomNeighbour renvoie un voisin au hasard d'un sommet donné.

La class AldousBroder:
random : un objet de la class Random pour générer des nombres aléatoires
graph : un objet de la class Graph

on initialise le constructeur avec un graphe et un objet de la class Random

 generateRandomTree() génère un arbre couvrant aléatoire
elle choisit un sommet au hasard et le marque comme visité
Elle crée ensuite une liste d'arêtes appelée tree qui va contenir l'arbre couvrant généré.
Tant que la liste tree n'a pas atteint la taille du graphe moins 1 (ce qui signifie qu'il reste encore des sommets à couvrir)
elle sélectionne un voisin au hasard du sommet actuel en utilisant la méthode getRandomNeighbor() de la classe Graph.
Si ce voisin n'a pas encore été atteint elle ajoute une arête entre le sommet actuel et le voisin à la liste tree et marque le voisin comme atteint.
Enfin, elle définit le sommet actuel comme étant le voisin sélectionné et recommence l'itération.
Quand tous les sommets ont été atteints,
la méthode renvoie la liste tree qui contient l'arbre couvrant généré.


la class ParcoursAleatoire
random : un objet de la class Random pour générer des nombres aléatoires
graph : un objet de la class Graph

on initialise le constructeur avec un graphe et un objet de la class Random

La classe a 2 méthodes :

La première méthode generateRandomTree() 
génère un arbre couvrant aléatoire avec la deuxieme méthode 

La deuxième méthode generateRandomTreeFrom(int startVertex)
initialiser une liste d'arêtes appelée tree qui va contenir l'arbre couvrant généré
et un tableau de booléens appelé reached qui va marquer les sommets qui ont été atteints.
Elle ajoute le sommet de départ à une liste appelée toExplore et marque ce sommet comme atteint dans le tableau reached.
Tant que la liste toExplore n'est pas vide, la méthode retire au hasard un élément de la liste toExplore et le définit comme le sommet actuel.
Elle parcourt alors tous les voisins du sommet actuel en utilisant la méthode getSuccessors() de la classe Graph. 
Si un voisin n'a pas encore été atteint (c'est-à-dire s'il n'est pas marqué comme atteint dans le tableau reached), elle ajoute une arête entre le sommet actuel et le voisin à la liste tree et marque le voisin comme atteint dans le tableau reached. 
Elle ajoute également le voisin à la liste toExplore. Quand tous les voisins du sommet actuel ont été parcourus, 
la méthode recommence l'itération en sélectionnant un nouvel élément au hasard dans la liste toExplore.
Quand la liste toExplore est vide, cela signifie que tous les sommets ont été atteints et que l'arbre couvrant a été généré. 
La méthode renvoie alors la liste tree qui contient l'arbre couvrant généré.


la classe TreeMin :
Classe ou on a pas réussis car on a rencontré un soucis avec le poids des arêtes. 
Pourtant avec un graph fait manuellement il va passer par les plus petites arêtes tout en étant minimal.

import java.util.List;
import Graph.*;
import RandomTreeAlgos.*;
import RandomTreeAlgos.TreeMin;

public class Main {
public static void main(String[] args) {
// Créer un graphe avec quelques arêtes
Graph graph = new Graph(4);
Edge edge = new Edge(0, 1, 40.1);
Edge edge1 = new Edge(0, 2, 0.1);
Edge edge2 = new Edge(1, 2, 0.5);
Edge edge3 = new Edge(1, 3, 0);
Edge edge4 = new Edge(2, 3, 10);
graph.addEdge(edge);
graph.addEdge(edge1);
graph.addEdge(edge2);
graph.addEdge(edge3);
graph.addEdge(edge4);

ParcoursAlea parcours = new ParcoursAlea(graph);
List<Edge> randomTree = parcours.generateRandomTree(); 
//test kruskal algo 
TreeMin treeMin = new TreeMin(graph); 
List<Edge> minimumSpanningTree = treeMin.kruskal(); 
for (Edge ex : minimumSpanningTree) { 
    System.out.println(ex.source + " -- " + ex.dest + " : " + ex.weight);
        }
        //TEST parcous aleatoire
        for (Edge e : randomTree) {
            System.out.println(e.source + " -> " + e.dest);
        }
    }
}
On peut voir qu'il va passer par les chemins le plus court mais nous n'avons pas réussis a le faire fonctionner avec le main car quand je mettais un poids on obtenais un dessin comme celui Wilson.Png qui est en ressource 
donc on a supposé que nous n'avons pas réussis . 


Explication du code TreeMin:

random : un objet de la class Random pour générer des nombres aléatoires
graph : un objet de la class Graph


méthode kulstra :
On parcourt toutes les arêtes du graphe que l'on va trier par ordre croissant 
Pour chaque arête on trouve les racines des sommets source eet destination
Si les racines sont différentes on ajoute l'arête à l'arbre couvrant 
et on fusionne les deux composantes connexes en une seule.

méthode find()
Si le sommet n'est pas sa propre racine la méthode appelle récursivement fin() 
sur le parent du sommet et met a jour le parent du sommet avec la racine trouvée