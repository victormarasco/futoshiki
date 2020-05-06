import java.io.*;
import java.util.*; 

public class InterpretationReponse {


	/* interprete la reponse dimacs d'un sat solveur et stocke les valeurs positives dans un tableau 
	   attention : on stocke directement la valeur de la case et non la valeur de sa variable 
	*/
	public static int [] valeurs (String futo, int taille) {
		int [] val = new int [taille*taille];
		int i=0;
		int j=0;
		int n_lig=0;
		int n_col=0;
		int val_courante;
		while(futo.charAt(i)!='0') {			
			if(futo.charAt(i)=='-') {
				while(futo.charAt(i)!=' ') {
					i++;
				}
				i++;
			}
			else {
				String s="";
				while(futo.charAt(i)!=' ') {
					s+=futo.charAt(i);
					i++;
				}
				val_courante=Integer.parseInt(s)-n_lig*taille*taille-n_col*taille;
				val[j]=val_courante;
				j++;				
				n_col++;
				if(n_col==taille) {
					n_col=0;
					n_lig++;
				}
				i++;
			}
		}
		return val;
	}
			

	
	/* main : lis la reponse du sat solveur et le fichier du futoshiki donné
		renvoi le futoshiki rempli si SAT, le futoshiki rempli avec des 0 sinon
	*/
	public static void main(String args[]) {
		// chaine de caractère au format FUTO
		String futoRempli="";
		// fichier contenant la reponse du solveur SAT
		String reponseSat=args[0];
		// fichier au format FUTO contenant le puzzle initial non rempli
		String futoVide=args[1];
		int taille;
		int vals [];
		int ptrVal=0;

		try {
			
			Reader r = new FileReader(futoVide);
			BufferedReader br = new BufferedReader(r);
			String lig=br.readLine();
			taille =((lig.length()+1)/2);
			// il y a bien N**2 valeurs à recupérer
			vals= new int[taille*taille];
			// lecture du fichier reponse 
			Reader r1 = new FileReader(reponseSat);
			BufferedReader br1 = new BufferedReader(r1);
			String lig1=br1.readLine();
			// si SAT, remplissage du tableau de valeurs
			if(lig1.charAt(0)!='U') {
				lig1=br1.readLine();
				vals=valeurs(lig1,taille);
			}
			r1.close();
			// remplissage futoshiki rempli avec les valeurs de vals
			// si futoshiki n'a pas de solutions, on remplit avec des 0
			while(lig!=null) {
				// traitement ligne avec des cases
				int k=0;
				while(k!=lig.length()) {
					futoRempli+=vals[ptrVal];	
					ptrVal++;
					k++;
					if(k!=lig.length()) {
						futoRempli+=lig.charAt(k);
						k++;
					}
				}
				// saut de ligne, passage a la ligne suivante
				futoRempli+="\n";
				lig=br.readLine();
				// recopier la ligne impaire de relations entre les cases en colonne
				if(lig!=null) {
					futoRempli+=lig+"\n";
					lig=br.readLine();
				}
			}
			r.close();
			System.out.print(futoRempli);
			}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
