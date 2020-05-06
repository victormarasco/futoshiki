**Utilisation :**

se placer dans src
compiler les sources java : javac *.java
puis taper la commande suivante

./futoshiki.sh ../tests/fichier_tests.FUTO

Remplacer fichier_tests.FUTO par un fichier au format FUTO

placez le bien dans le repertoire tests.

# ATTENTION
Vous avez besoin d'installer le sat solveur minisat pour executer cette
commande. sinon vous pouvez installer le solveur sat de votre 
choix et remplacer la ligne correspondante dans le shell 



**EXEMPLE DU FUTOSHIKI DE WIKIPEDIA**
```shell
./futoshiki ../tests/futoWikipedia.FUTO

        ----------------------
       |   FUTOSHIKI SOLVER   |
        ----------------------


+---+   +---+   +---+   +---+   +---+   
|   | > |   |   |   | > |   | > |   |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
| 4 |   |   |   |   |   |   |   | 2 |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
|   |   |   |   | 4 |   |   |   |   |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
|   |   |   |   |   |   |   | < | 4 |
+---+   +---+   +---+   +---+   +---+   
  ∨                                  
+---+   +---+   +---+   +---+   +---+   
|   | < |   | < |   |   |   |   |   |
+---+   +---+   +---+   +---+   +---+   

===================================================================

+---+   +---+   +---+   +---+   +---+   
| 5 | > | 4 |   | 3 | > | 2 | > | 1 |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
| 4 |   | 3 |   | 1 |   | 5 |   | 2 |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
| 2 |   | 1 |   | 4 |   | 3 |   | 5 |
+---+   +---+   +---+   +---+   +---+   
                                     
+---+   +---+   +---+   +---+   +---+   
| 3 |   | 5 |   | 2 |   | 1 | < | 4 |
+---+   +---+   +---+   +---+   +---+   
  ∨                                  
+---+   +---+   +---+   +---+   +---+   
| 1 | < | 2 | < | 5 |   | 4 |   | 3 |
+---+   +---+   +---+   +---+   +---+   

```
