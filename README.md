**Utilisation :**

se placer dans src
compiler les sources java
puis taper la commande suivante

./futoshiki ../tests/fichier_tests.FUTO

Remplacer fichier_tests.FUTO par un fichier au format FUTO

placez le bien dans le repertoire tests.

# ATTENTION
Vous avez besoin d'installer le sat solveur minisat pour executer cette
commande. sinon vous pouvez installer le solveur sat de votre 
choix et remplacer la ligne correspondante dans le shell 



**EXEMPLE**
```shell
./futoshiki ../tests/futo2x2  


        ----------------------
       |   FUTOSHIKI SOLVER   |
        ----------------------


+---+   +---+   
|   | < |   |
+---+   +---+   
             
+---+   +---+   
|   |   |   |
+---+   +---+   

===================================================================

+---+   +---+   
| 1 | < | 2 |
+---+   +---+   
             
+---+   +---+   
| 2 |   | 1 |
+---+   +---+   


```
