import java.io.*;
import java.util.*; 

public class InterpretationReponse {

	/* retourne la taille du futoshiki :
	   compte le nombre de caractère d'une ligne et renvoi le (nombre de char+1)/2
	   en effet par exemple la lign 1<2<3 prouve que c'est un futoshiki 3x3 !
	*/
	public static int tailleFuto(String s) {
		int i=0;
		while(s.charAt(i)!='\n') {
			i++;
		}
		return (i+1)/2;
	}

	/* range le contenu d'un fichier dans un string en recopiant les sauts de ligne */
	public static String contenuFichier(String s) {
		String cont="";
		String ligne;
		try{
			Reader r = new FileReader(s);
			BufferedReader br = new BufferedReader(r);
			while ( (ligne = br.readLine()) != null) {
	    		
				cont+=ligne+"\n";
	    		}
			r.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return cont;
		
	
    	}

	/* interprete la reponse dimacs d'un sat solveur et stocke les valeurs positives dans une tableau 
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
			

	
	/* a completer avec le groupe 
	*/
	public static void main(String args[]) {
		String futoRempli="";
		String reponseSat=args[0];
		String futoVide=args[1];
		String futoVids=contenuFichier(args[1]);
		int n_lig;
		int n_col;
		
		int taille=tailleFuto(futoVids);
		int ptrFutoVide;
		int vals [] = new int [taille*taille];
		int num_val=0;
		boolean sat=true;

		try {
			Reader r1 = new FileReader(reponseSat);
			BufferedReader br1 = new BufferedReader(r1);
			String lig=br1.readLine();
			if(lig.charAt(0)=='U') {
				sat=false;
			}	
			else {
				lig=br1.readLine();
				vals=valeurs(lig,taille);
			}
			r1.close();
			Reader r2 = new FileReader(futoVide);
			BufferedReader br2 = new BufferedReader(r2);
			lig=br2.readLine();
			while(lig!=null) {
				int k=0;
				while(k!=lig.length()) {
					futoRempli+=vals[num_val]+"";	
					num_val++;
					k++;
					if(k!=lig.length()) {
						futoRempli+=lig.charAt(k)+"";
						k++;
					}
				}
				futoRempli+="\n";
				lig=br2.readLine();
				if(lig!=null) {
					futoRempli+=lig+"\n";
					lig=br2.readLine();
				}
	
			}
			r2.close();
			System.out.print(futoRempli);
			}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
				
