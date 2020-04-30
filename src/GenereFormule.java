import java.io.*;
import java.util.*; 
public class GenereFormule {

	int NB_CLAUSES;	// nombre de clauses
	int NB_VARS;	// nombre de variables
	String formule;	// CNF au format DIMACS

	GenereFormule(String nomFic) {
		String lig;	// ligne courante
		int n_lig;	// numero de ligne courante
		int n_col;	// pour parcourir de la ligne courante
		this.NB_CLAUSES=0;
		this.formule="";
		try{
			BufferedReader br = new BufferedReader(new FileReader(nomFic));
			lig=br.readLine();
			this.NB_VARS=(lig.length()+1)/2*(lig.length()+1)/2*(lig.length()+1)/2;
			n_lig=0;
			while (lig!=null) { 							
				n_col=0;
				// parcours d'une ligne paire (contenant des cases et des relations)
				while(n_col!=lig.length()) {
					if(lig.charAt(n_col)-48!=0) {
						caseRemplie(n_lig,n_col/2,lig.charAt(n_col)-48);
					}
					n_col++;
					if(n_col!=lig.length()) {
						if(lig.charAt(n_col)=='>') {
							supLig(n_lig,(n_col-1)/2);
						}
						if(lig.charAt(n_col)=='<') {
							infLig(n_lig,(n_col-1)/2);
						}			
						n_col++;
					}
				}
				// parcours d'une ligne impaire (ne contenant que des relations)
				lig=br.readLine();
				if(lig!=null) {
					n_col=0;
					while(n_col!=lig.length()) {
						if(lig.charAt(n_col)=='>') {
							supCol(n_lig,n_col/2);
						}
						if(lig.charAt(n_col)=='<') {
							infCol(n_lig,n_col/2);
						}			
						n_col++;
						if(n_col!=lig.length()) {
							n_col++;
						}
					}
					lig=br.readLine();
				}
				n_lig++;			
			}			
			carreLatin();
		}
		catch(Exception e) {
			System.out.println(e);
	    	}
	}		

	/* renvoi le numero de la variable x(n_lig,n_col,val) */
	int numVar(int n_lig, int n_col, int val) {
	return 	n_lig*(int)(Math.cbrt(this.NB_VARS))*(int)(Math.cbrt(this.NB_VARS))+n_col*(int)(Math.cbrt(this.NB_VARS))+val;
	}


	/* ajoute les regles du carre latin */
	void carreLatin() {
		int i=0,j,k,l;	// i=n_lig ; j=n_col ; k=val_case ; l = variable utilisee pour i!=l et j!=l
		String existe;
		int n=(int)(Math.cbrt(this.NB_VARS));
		while(i!=n) {
			j=0;
			while(j!=n) {
				k=1;
				existe="";
				while(k!=n+1) {
					l=0;
					while(l!=n) {		// pour tout l!=j : x(i,j,k) => non(x(i,l,k))
						if(l!=j) {
							this.formule+="-"+numVar(i,j,k)+" -"+numVar(i,l,k)+" 0\n";
							this.NB_CLAUSES++;
						}
						l++;
					}
					l=0;
					while(l!=n) {		// pour tout l!=i : x(i,j,k) => non(x(l,j,k))
						if(l!=i) {
							this.formule+="-"+numVar(i,j,k)+" -"+numVar(l,j,k)+" 0\n";
							this.NB_CLAUSES++;
						}
						l++;
					}
					existe+=numVar(i,j,k)+" ";
					k++;
				}
				this.formule+=existe+"0\n"; // il existe k tel que x(i,j,k)
				this.NB_CLAUSES++;
				j++;
			}
			i++;
		}
	}

	/* ajoute une clause si une case est preremplie avec un chiffre */
	void caseRemplie(int n_lig, int n_col, int val) {
		this.formule+=numVar(n_lig,n_col,val)+" 0\n";
		this.NB_CLAUSES++;
	}

	/* ajoute les clauses associees a une case inferieure a la suivante en ligne */
	void infLig(int n_lig, int n_col) {
		int val=1;
		while(val!=Math.cbrt(NB_VARS)+1) {
			int val_suivante=1;
			while(val_suivante!=val+1) { // pour tout k, x(i,j,k) => pour tout l<=k, non(x(i,j+1,l))
                                this.formule+="-"+numVar(n_lig,n_col,val)+" -"+numVar(n_lig,n_col+1,val_suivante)+" 0\n";
				this.NB_CLAUSES++;
				val_suivante++;
			}
			val++;
		}
	}

	/* ajoute les clauses associees a une case superieure a la suivante en ligne*/
        void supLig(int n_lig, int n_col) {
                int val=1;
                while(val!=(int)(Math.cbrt(this.NB_VARS))+1) {
                        int val_suivante=(int)(Math.cbrt(this.NB_VARS));
                        while(val_suivante!=val) {	// pour tout k, x(i,j,k) => pour tout l>=k, non(x(i,j+1,l))
                                this.formule+="-"+numVar(n_lig,n_col,val)+" -"+numVar(n_lig,n_col+1,val_suivante)+" 0\n";
				this.NB_CLAUSES++;
                                val_suivante--;
                        }
                        val++;
                }
        }

	/* ajoute les clauses associees a une case inferieure a la suivante en colonne */
        void infCol(int n_lig, int n_col) {
		int val=1;
		while(val!=(int)(Math.cbrt(this.NB_VARS))+1) {
			int val_suivante=1;
			while(val_suivante!=val+1) { // pour tout k, x(i,j,k) => pour tout l<=k, non(x(i,j+1,l))
                                this.formule+="-"+numVar(n_lig,n_col,val)+" -"+numVar(n_lig+1,n_col,val_suivante)+" 0\n";
				this.NB_CLAUSES++;
				val_suivante++;
			}
			val++;
		}
        }

	/* ajoute les clauses associees a une case superieure a la suivante en colonne */
        void supCol(int n_lig, int n_col) {
                int val=1;
                while(val!=(int)(Math.cbrt(this.NB_VARS))+1) {
                        int val_suivante=(int)(Math.cbrt(this.NB_VARS));
                        while(val_suivante!=val) {	// pour tout k, x(i,j,k) => pour tout l>=k, non(x(i,j+1,l))
                                this.formule+="-"+numVar(n_lig,n_col,val)+" -"+numVar(n_lig+1,n_col,val_suivante)+" 0\n";
				this.NB_CLAUSES++;
                                val_suivante--;
                        }
                        val++;
                }
        }

	/* prend en entree un nom de fichier au format FUTO et imprime les règles */
	public static void main(String args[]) {
		GenereFormule gf = new GenereFormule(args[0]);
		System.out.print("p cnf "+gf.NB_VARS+" "+gf.NB_CLAUSES+"\n"+gf.formule);
	}

}
