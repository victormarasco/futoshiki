### futoshiki ###
# $1 est le fichier au format FUTO contenant le futoshiki a resoudre
# on stocke le fichier de CNF au format DIMACS dans futo.out
java GenereFormule $1 > futo.out
# on donne a minisat le fichier futo.out qui donne sa reponse
# dans sol.out et stocke ce qu'affiche minisat dans la console dans
# le fichier ne_pas_afficher.out ........
minisat futo.out sol.out > ne_pas_afficher.out
# on interprete la reponse du sat solveur de on stocke le fichier
# au format FUTO du futoshiki resolu (ou non) dans le fichier $1_rempli
# ie: on ajoute le mot cle "_rempli" au nom de fichier contenant le futoshiki
# pas encore rempli
java InterpretationReponse sol.out $1 > $1_rempli
# affichage : une entete pour faire joli
echo "        ----------------------"
echo '       |   FUTOSHIKI SOLVER   |'
echo '        ----------------------'
echo
echo
# affichage : on affiche le fichier du futoshiki fourni non resolu
java Affichage $1
# on affiche une barre et un saut de ligne
echo
echo '==================================================================='
echo
# on afficher le fichier du futoshiki fourni RESOLU
java Affichage $1_rempli
echo
# suppression des fichiers inutiles au logiciel, commenter la ligne si
# on veut des détails
rm *.out
rm $1_rempli
