import java.io.*;
import java.util.*; 

/* classe utilisée pour l'affichage des fichiers au format FUTO
*/

public class Affichage {

	// afficher | 1 | > | 3 | < ... au lieu de 1>3<...
	public static String afficherLigneChiffres(String ligne) {
                String s="";
                int i=0;
		while(i!=ligne.length()) {
                        if(ligne.charAt(i)=='0') {
				s+="|   |";
			}
			else {
				s+="| "+ligne.charAt(i)+" |";
			}
			i++;
			if(i!=ligne.length()) {
				if(ligne.charAt(i)=='|') {
	                                s+="   ";
	                        }
	                        else {
					if(ligne.charAt(i)=='>') {
	                                	s+=" > ";
					}
					if(ligne.charAt(i)=='<') {
						s+=" < ";
					}
				}
				i++;
			}		
		}
		return s;
	}


	// affichage d'une interligne ou on peut avoir des relation entre les cases ou rien 
	public static String afficherLigneRelations(String ligne) {
		String s="";
                int i=0;
                while(i!=ligne.length()) {
			// si pas de relation, afficher 5 espaces
                        if(ligne.charAt(i)=='|') {
                                s+="     ";
                        }
			// sinon afficher 2 espaces + la relation + 2 espaces
                        else {
				if(ligne.charAt(i)=='>') {
                                	s+="  "+'\u2228'+"  ";   // caractère ou logique
				}
				if(ligne.charAt(i)=='<') {
					s+="  "+'\u2227'+"  ";   // caractère et logique
				}
			}
			i++;
			// si on n'est pas a la fin de la ligne, afficher 3 espaces (voir plus haut)
			if(i!=ligne.length()) {
				s+="   ";
				i++;
			}
		}
		return s;
	}

	// affichage d'un bloc de "+" et de "-" puis d'un bloc d'espace : le haut et le bas d'une case
 	public static String afficherNfoisBlocTirets(int n) {
		String s="";
		int i=0;
		while(i!=n) {
			s+="+---+"+"   ";
			i++;
		}
		return s;
	}

	public static void main(String args[]) {
		String affichage="";
		int n;
		try {
			// args[0] est un fichier au format FUTO
			String f = args[0];
			Reader r = new FileReader(f);
			BufferedReader br = new BufferedReader(r);
			String lig=br.readLine();		
			/* si le fichier fini par 'li' ie : _rempli et que le contenu commence par 0
			   alors le futoshiki n'a pas de solutions, il a été rempli avec des zéros
			*/ 
			if(f.charAt(f.length()-2)=='l' && f.charAt(f.length()-1)=='i' && lig.charAt(0)=='0') {
				System.out.println("Ce Futoshiki n'a pas de solutions");
			}
			else { 	
			n=(lig.length()+1)/2;
			while(lig!=null) {
				affichage+=afficherNfoisBlocTirets(n)+'\n';
				affichage+=afficherLigneChiffres(lig)+'\n';
                                affichage+=afficherNfoisBlocTirets(n)+'\n';
				lig=br.readLine();
				if(lig!=null) {
					affichage+=afficherLigneRelations(lig)+'\n';
					lig=br.readLine();
				}
			}
			}
			System.out.print(affichage);
			}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
