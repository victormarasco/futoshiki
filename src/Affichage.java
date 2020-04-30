import java.io.*;
import java.util.*; 

/* classe utilisée pour l'affichage des fichiers au format FUTO
*/

public class Affichage {

	// afficher | 1 | > | 3 | < ... au lieu de 1>3<...
	static void afficherLigneChiffres(String ligne) {
                int i=0;
		while(i!=ligne.length()) {
                        if(ligne.charAt(i)=='0') {
				System.out.print("|   |");
			}
			else {
				System.out.print("| "+ligne.charAt(i)+" |");
			}
			i++;
			if(i!=ligne.length()) {
				if(ligne.charAt(i)=='|') {
	                                System.out.print("   ");
	                        }
	                        else {
					if(ligne.charAt(i)=='>') {
	                                	System.out.print(" > ");
					}
					if(ligne.charAt(i)=='<') {
						System.out.print(" < ");
					}
				}
				i++;
			}		
		}
		System.out.println();
	}


	// affichage d'une interligne ou on peut avoir des relation entre les cases ou rien 
	static void afficherLigneRelations(String ligne) {
                int i=0;
                while(i!=ligne.length()) {
			// si pas de relation, afficher 5 espaces
                        if(ligne.charAt(i)=='|') {
                                System.out.print("     ");
                        }
			// sinon afficher 2 espaces + la relation + 2 espaces
                        else {
				if(ligne.charAt(i)=='>') {
                                	System.out.print("  "+'\u2228'+"  ");   // caractère ou logique
				}
				if(ligne.charAt(i)=='<') {
					System.out.print("  "+'\u2227'+"  ");   // caractère et logique
				}
			}
			i++;
			// si on n'est pas a la fin de la ligne, afficher 3 espaces (voir plus haut)
			if(i!=ligne.length()) {
				System.out.print("   ");
				i++;
			}
		}
		System.out.println();
	}

	// affichage d'un bloc de "+" et de "-" puis d'un bloc d'espace : le haut et le bas d'une case
 	static void afficherNfoisBlocTirets(int n) {
		int i=0;
		while(i!=n) {
			System.out.print("+---+   ");
			i++;
		}
		System.out.println();
	}


	public static void main(String a[]) {
		int n;
		try {
			BufferedReader br = new BufferedReader(new FileReader(a[0]));
			String lig=br.readLine();		
			/* si le fichier fini par 'li' ie : _rempli et que le contenu commence par 0
			   alors le futoshiki n'a pas de solutions, il a été rempli avec des zéros
			*/ 
			if(a[0].charAt(a[0].length()-2)=='l' && a[0].charAt(a[0].length()-1)=='i' && lig.charAt(0)=='0') {
				System.out.println("Ce Futoshiki n'a pas de solutions");
			}
			else { 	
				n=(lig.length()+1)/2;
				while(lig!=null) {
					afficherNfoisBlocTirets(n);
					afficherLigneChiffres(lig);
                                	afficherNfoisBlocTirets(n);
					lig=br.readLine();
					if(lig!=null) {
						afficherLigneRelations(lig);
						lig=br.readLine();
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
