import java.io.*;
import java.util.*; 

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

	public static String afficherLigneRelations(String ligne) {
		String s="";
                int i=0;
                while(i!=ligne.length()) {
                        if(ligne.charAt(i)=='|') {
                                s+="     ";
                        }
                        else {
				if(ligne.charAt(i)=='>') {
                                	s+="  "+'\u2228'+"  ";
				}
				if(ligne.charAt(i)=='<') {
					s+="  "+'\u2227'+"  ";
				}
			}
			i++;
			if(i!=ligne.length()) {
				s+="   ";
				i++;
			}
		}
		return s;
	}

	public static String afficherNfoisBlocTirets(int n) {
		String s="";
		int i=0;
		while(i!=n) {
			s+="+---+"+"   ";
			i++;
		}
		return s;
	}

	public static String afficherNfoisBlocEspaces(int n) {
		String s="";
		int i=0;
		while(i!=n) {
			s+="    "+"    ";
			i++;
		}
		return s;
	}


	public static void main(String args[]) {
		String affichage="";
		int n;
		try {
			Reader r = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(r);
			String lig=br.readLine();		
			if(args[0].charAt(args[0].length()-1)=='i' && lig.charAt(0)=='0') {
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
					//affichage+=afficherNfoisBlocEspaces(n)+'\n';
					affichage+=afficherLigneRelations(lig)+'\n';
					//affichage+=afficherNfoisBlocEspaces(n)+'\n';
					lig=br.readLine();
				}
			}
			}
			System.out.println(affichage);
			}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
