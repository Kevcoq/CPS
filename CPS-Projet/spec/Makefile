all : html tex pdf

html : genhtml movehtml
tex : gentex transf movetex
pdf : genpdf cleantmp movepdf

genhtml : *.org
	emacs bloc.org -f org-export-as-html chose.org -f org-export-as-html gangster.org -f org-export-as-html \
	gestionCombat.org -f org-export-as-html moteurJeu.org -f org-export-as-html objetequipable.org -f org-export-as-html \
	objetmarchand.org -f org-export-as-html objet.org -f org-export-as-html peronnage.org -f org-export-as-html \
	position.org -f org-export-as-html terrain.org -f org-export-as-html --kill

gentex : *.org
	emacs bloc.org -f org-export-as-latex chose.org -f org-export-as-latex gangster.org -f org-export-as-latex \
	gestionCombat.org -f org-export-as-latex moteurJeu.org -f org-export-as-latex objetequipable.org -f org-export-as-latex \
	objetmarchand.org -f org-export-as-latex objet.org -f org-export-as-latex personnage.org -f org-export-as-latex \
	position.org -f org-export-as-latex terrain.org -f org-export-as-latex --kill

transf :
	./transf.pl bloc.tex
	rm -f bloc.tex
	mv __fichier_tmp__.tex bloc.tex
	./transf.pl chose.tex
	rm -f chose.tex
	mv __fichier_tmp__.tex chose.tex
	./transf.pl gangster.tex
	rm -f gangster.tex
	mv __fichier_tmp__.tex gangster.tex
	./transf.pl gestionCombat.tex
	rm -f gestionCombat.tex
	mv __fichier_tmp__.tex gestionCombat.tex
	./transf.pl moteurJeu.tex
	rm -f moteurJeu.tex
	mv __fichier_tmp__.tex moteurJeu.tex
	./transf.pl objetequipable.tex
	rm -f objetequipable.tex
	mv __fichier_tmp__.tex objetequipable.tex
	./transf.pl objetmarchand.tex
	rm -f objetmarchand.tex
	mv __fichier_tmp__.tex objetmarchand.tex
	./transf.pl objet.tex
	rm -f objet.tex
	mv __fichier_tmp__.tex objet.tex
	./transf.pl personnage.tex
	rm -f personnage.tex
	mv __fichier_tmp__.tex personnage.tex
	./transf.pl position.tex
	rm -f position.tex
	mv __fichier_tmp__.tex position.tex
	./transf.pl terrain.tex
	rm -f terrain.tex
	mv __fichier_tmp__.tex terrain.tex

genpdf :
	if [ ! -e ./tex/bloc.tex ]; then echo "Erreur : no .tex file" ; exit 1; fi
	pdflatex ./tex/bloc.tex
	pdflatex ./tex/chose.tex
	pdflatex ./tex/gangster.tex
	pdflatex ./tex/gestionCombat.tex
	pdflatex ./tex/moteurJeu.tex
	pdflatex ./tex/objetequipable.tex
	pdflatex ./tex/objetmarchand.tex
	pdflatex ./tex/objet.tex
	pdflatex ./tex/personnage.tex
	pdflatex ./tex/position.tex
	pdflatex ./tex/terrain.tex

movepdf :
	mv *.pdf ./pdf/

movetex :
	mv *.tex ./tex

movehtml :
	mv *.html ./html

cleantmp :
	rm -f *.log *.aux *.toc *.out *~

clean :
	rm -f ./pdf/*.pdf ./tex/*.tex ./html/*.html *.log *.aux *.toc *.out *~

