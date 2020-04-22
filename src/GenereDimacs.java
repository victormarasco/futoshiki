import java.io.*;
import java.util.*; 
public class GenereDimacs {

	/* @param
		String s : un ensemble de clauses au format DIMACS
	   @return
		int nb_clauses : le nombre de clauses de l'ensemble s
	   @algo
		compte le nombre de saut de lignes lus
	*/
	public static int nombreDeClauses(String s) {
		int nb_clauses=0;
		int i=0;
		while(i!=s.length()) {
			if(s.charAt(i)=='\n') {
				nb_clauses++;
			}
			i++;
		}
		return nb_clauses;
	}

	/* @param
		int taille : la taille du carré latin
	   @return
		String clauses : les clauses correspondants aux règles du carré latin
	   @algo
		boucles sur les différentes règles
	*/
	public static String clausesCarreLatin(int taille) {
		int n=taille;
		int i=0,j,k,l;	// i=n_lig ; j=n_col ; k=val_case ; l = variable utilisee pour i!=l puis j!=l
		String ilExiste; // il existe k tel que x(i,j,k)
		String clauses="";	// pour le "il existe k tel que x(i,j,k)"
		while(i!=n) {	// tant que i != n_lig
			j=0;
			while(j!=n) {	// tant que j != n_col
				k=1;
				ilExiste="";
				while(k!=n+1) {
					l=0;
					while(l!=n) {		// pour tout l!=j : x(i,j,k) => non(x(i,l,k))
						if(l!=j) {
							clauses+="-"+(i*n*n+j*n+k)+" -"+(i*n*n+l*n+k)+" 0\n";
						}
						l++;
					}
					l=0;
					while(l!=n) {		// pour tout l!=i : x(i,j,k) => non(x(l,j,k))
						if(l!=i) {
							clauses+="-"+(i*n*n+j*n+k)+" -"+(l*n*n+j*n+k)+" 0\n";
						}
						l++;
					}
					ilExiste+=i*n*n+j*n+k+" ";
					k++;
				}
				clauses+=ilExiste+"0\n";
				j++;
			}
			i++;
		}
		return clauses;
	}

	/* @param
		int n_lig : le numero de ligne entre 0 et N-1
		int n_col : le numero de colonne entre 0 et N-1
		int val : la valeur de la case
		int taille : la taille N du futoshiki
	   @return
		String clause : la clause associee a une case remplie
	*/
	public static String clauseCaseRemplie(int n_lig, int n_col, int val, int taille) {
		String clause = (n_lig*taille*taille+n_col*taille+val)+" 0\n";
		return clause;
	}

	/* @param
		int n_lig : le numero de ligne entre 0 et N-1
		int n_col : le numero de colonne entre 0 et N-1
		int taille : la taille N du futoshiki
	   @return
		String clauses : les clauses associees a une case inferieure a la suivante en ligne 
	*/
	public static String caseInferieureALaSuivanteLigne(int n_lig, int n_col, int taille) {
		int val=1;
		String clauses="";
		while(val!=taille+1) {
			int val_suivante=1;
			while(val_suivante!=val+1) { // pour tout k, x(i,j,k) => pour tout l<=k, non(x(i,j+1,l))
				clauses+="-"+(n_lig*taille*taille+n_col*taille+val)+
					" -"+(n_lig*taille*taille+(n_col+1)*taille+val_suivante)+" 0\n";
				val_suivante++;
			}
			val++;
		}
		return clauses;
	}

	/* @param
		int n_lig : le numero de ligne entre 0 et N-1
		int n_col : le numero de colonne entre 0 et N-1
		int taille : la taille N du futoshiki
	   @return
		String clauses : les clauses associees a une case superieure a la suivante en ligne 
	*/
        public static String caseSuperieureALaSuivanteLigne(int n_lig, int n_col, int taille) {
                int val=1;
                String clauses="";
                while(val!=taille+1) {
                        int val_suivante=taille;
                        while(val_suivante>val) {	// pour tout k, x(i,j,k) => pour tout l>=k, non(x(i,j+1,l))
                                clauses+="-"+(n_lig*taille*taille+n_col*taille+val)+
                                        " -"+(n_lig*taille*taille+(n_col+1)*taille+val_suivante)+" 0\n";
                                val_suivante--;
                        }
                        val++;
                }
                return clauses;
        }

	/* @param
		int n_lig : le numero de ligne entre 0 et N-1
		int n_col : le numero de colonne entre 0 et N-1
		int taille : la taille N du futoshiki
	   @return
		String clauses : les clauses associees a une case inferieure a la suivante en colonne
	*/
        public static String caseInferieureALaSuivanteColonne(int n_lig, int n_col, int taille) {
		int val=1;
		String clauses="";
		while(val!=taille+1) {
			int val_suivante=1;
			while(val_suivante!=val+1) { // pour tout k, x(i,j,k) => pour tout l<=k, non(x(i,j+1,l))
				clauses+="-"+(n_lig*taille*taille+n_col*taille+val)+
					" -"+((n_lig+1)*taille*taille+n_col*taille+val_suivante)+" 0\n";
				val_suivante++;
			}
			val++;
		}
		return clauses;
        }

	/* @param
		int n_lig : le numero de ligne entre 0 et N-1
		int n_col : le numero de colonne entre 0 et N-1
		int taille : la taille N du futoshiki
	   @return
		String clauses : les clauses associees a une case superieure a la suivante en colonne 
	*/
        public static String caseSuperieureALaSuivanteColonne(int n_lig, int n_col, int taille) {
                int val=1;
                String clauses="";
                while(val!=taille+1) {
                        int val_suivante=taille;
                        while(val_suivante>val) {	// pour tout k, x(i,j,k) => pour tout l>=k, non(x(i,j+1,l))
                                clauses+="-"+(n_lig*taille*taille+n_col*taille+val)+
                                        " -"+((n_lig+1)*taille*taille+n_col*taille+val_suivante)+" 0\n";
                                val_suivante--;
                        }
                        val++;
                }
                return clauses;
        }

	/* lecture d'un fichier au format FUTO et impression des regles associees au format DIMACS */ 
	public static void main(String args[]) {
		int n_col;	// numero de colonne .. a tout moment il vaut n_col/2 !
		int n_lig;	// numero de ligne
		int taille;	// taille du jeu de futoshiki
		int nb_clauses;
		int nb_vars;
		String clauses="";
		String ligne;
		String futo="";
		try{
			Reader r = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(r);
			ligne=br.readLine();
			futo+=ligne+"\n";
			taille=(ligne.length()+1)/2;
			n_lig=0;
			while (ligne!=null) { 								
				n_col=0;
				// parcours d'une ligne paire (contenant des cases et des relations)
				while(n_col!=ligne.length()) {
					if(ligne.charAt(n_col)-48!=0) {
						clauses+=clauseCaseRemplie(n_lig,n_col/2,ligne.charAt(n_col)-48,taille);
					}
					n_col++;
					if(n_col!=ligne.length()) {
						if(ligne.charAt(n_col)=='>') {
							clauses+=caseSuperieureALaSuivanteLigne(n_lig,(n_col-1)/2,taille);
						}
						if(ligne.charAt(n_col)=='<') {
							clauses+=caseInferieureALaSuivanteLigne(n_lig,(n_col-1)/2,taille);
						}			
						n_col++;
					}
				}
				// parcours d'une ligne impaire (ne contenant que des relations)
				ligne=br.readLine();
				if(ligne!=null) {
					n_col=0;
					while(n_col!=ligne.length()) {
						if(ligne.charAt(n_col)=='>') {
							clauses+=caseSuperieureALaSuivanteColonne(n_lig,n_col/2,taille);
						}
						if(ligne.charAt(n_col)=='<') {
							clauses+=caseInferieureALaSuivanteColonne(n_lig,n_col/2,taille);
						}			
						n_col++;
						if(n_col!=ligne.length()) {
							n_col++;
						}
					}
					ligne=br.readLine();
				}
				n_lig++;			
			}	
			r.close();
			clauses+=clausesCarreLatin(taille);
			nb_clauses=nombreDeClauses(clauses);
			nb_vars=taille*taille*taille;
			System.out.print("p cnf "+nb_vars+" "+nb_clauses+"\n"+clauses);
		}
		catch(Exception e) {
			System.out.println(e);
	    	}

	}

}
