=====================================
====== Utilisation du Makefile ======
=====================================


IMPORTANT :
Pour utiliser le Makefile, vous devez avoir sur votre
machine un interpréteur perl (/usr/bin/perl) et l'éditeur
de fichiers emacs.


Le Makefile fourni permet de générer la spécification
à partir des fichiers .org dans les formats html, tex et pdf.


Pour tout lancer d'un seul coup :
     make


Pour la création du format html uniquement :
     make html
Pour le format tex uniquement :
     make tex
Pour le format pdf uniquement :
     make pdf

Les fichiers seront placés dans /html (resp /tex et /pdf).
Pour générer les pdf, il faut avoir généré les tex d'abord.
Mise en garde : la cible 'clean' du Makefile supprimera
tous les types de fichiers (html, tex et pdf), utiliser
clean(html|tex|pdf) pour ne supprimer que ce que vous souhaitez.


Note : lors de la création des fichiers html et tex, l'éditeur
de texte emacs s'ouvre et se referme à plusieurs reprises, 
ne pas essayer de l'en empêcher.
