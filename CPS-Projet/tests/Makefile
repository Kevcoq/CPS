all : html tex pdf

html : genhtml transfhtml movehtml
tex : gentex transftex movetex
pdf : genpdf movepdf cleantmp


genhtml :
	emacs index.org -f org-export-as-html tBloc.org -f org-export-as-html tChose.org -f org-export-as-html \
	tGestionCombat.org -f org-export-as-html tMoteurJeu.org -f org-export-as-html tObjetEquipable.org -f org-export-as-html \
	tObjetMarchand.org -f org-export-as-html tObjet.org -f org-export-as-html tPersonnage.org -f org-export-as-html \
	tPosition.org -f org-export-as-html tTerrain.org -f org-export-as-html --kill

gentex : *.org
	emacs index.org -f org-export-as-latex tBloc.org -f org-export-as-latex tChose.org -f org-export-as-latex \
	tGestionCombat.org -f org-export-as-latex tMoteurJeu.org -f org-export-as-latex tObjetEquipable.org -f org-export-as-latex \
	tObjetMarchand.org -f org-export-as-latex tObjet.org -f org-export-as-latex tPersonnage.org -f org-export-as-latex \
	tPosition.org -f org-export-as-latex tTerrain.org -f org-export-as-latex --kill

genpdf :
	if [ ! -e ./tex/tBloc.tex ]; then echo "Erreur : no .tex file" ; exit 1; fi
# pdflatex ./tex/index.tex
	pdflatex ./tex/tBloc.tex
	pdflatex ./tex/tChose.tex
	pdflatex ./tex/tGestionCombat.tex
	pdflatex ./tex/tMoteurJeu.tex
	pdflatex ./tex/tObjetEquipable.tex
	pdflatex ./tex/tObjetMarchand.tex
	pdflatex ./tex/tObjet.tex
	pdflatex ./tex/tPersonnage.tex
	pdflatex ./tex/tPosition.tex
	pdflatex ./tex/tTerrain.tex

transfhtml :
	./html/transf.pl index.html
	rm -f index.html
	mv __fichier_tmp__.html index.html
	./html/transf.pl tBloc.html
	rm -f tBloc.html
	mv __fichier_tmp__.html tBloc.html
	./html/transf.pl tChose.html
	rm -f tChose.html
	mv __fichier_tmp__.html tChose.html
	./html/transf.pl tGestionCombat.html
	rm -f tGestionCombat.html
	mv __fichier_tmp__.html tGestionCombat.html
	./html/transf.pl tMoteurJeu.html
	rm -f tMoteurJeu.html
	mv __fichier_tmp__.html tMoteurJeu.html
	./html/transf.pl tObjetEquipable.html
	rm -f tObjetEquipable.html
	mv __fichier_tmp__.html tObjetEquipable.html
	./html/transf.pl tObjetMarchand.html
	rm -f tObjetMarchand.html
	mv __fichier_tmp__.html tObjetMarchand.html
	./html/transf.pl tObjet.html
	rm -f tObjet.html
	mv __fichier_tmp__.html tObjet.html
	./html/transf.pl tPersonnage.html
	rm -f tPersonnage.html
	mv __fichier_tmp__.html tPersonnage.html
	./html/transf.pl tPosition.html
	rm -f tPosition.html
	mv __fichier_tmp__.html tPosition.html
	./html/transf.pl tTerrain.html
	rm -f tTerrain.html
	mv __fichier_tmp__.html tTerrain.html


transftex :
	./tex/transf.pl tBloc.tex
	rm -f tBloc.tex
	mv __fichier_tmp__.tex tBloc.tex
	./tex/transf.pl tChose.tex
	rm -f tChose.tex
	mv __fichier_tmp__.tex tChose.tex
	./tex/transf.pl tGestionCombat.tex
	rm -f tGestionCombat.tex
	mv __fichier_tmp__.tex tGestionCombat.tex
	./tex/transf.pl tMoteurJeu.tex
	rm -f tMoteurJeu.tex
	mv __fichier_tmp__.tex tMoteurJeu.tex
	./tex/transf.pl tObjetEquipable.tex
	rm -f tObjetEquipable.tex
	mv __fichier_tmp__.tex tObjetEquipable.tex
	./tex/transf.pl tObjetMarchand.tex
	rm -f tObjetMarchand.tex
	mv __fichier_tmp__.tex tObjetMarchand.tex
	./tex/transf.pl tObjet.tex
	rm -f tObjet.tex
	mv __fichier_tmp__.tex tObjet.tex
	./tex/transf.pl tPersonnage.tex
	rm -f tPersonnage.tex
	mv __fichier_tmp__.tex tPersonnage.tex
	./tex/transf.pl tPosition.tex
	rm -f tPosition.tex
	mv __fichier_tmp__.tex tPosition.tex
	./tex/transf.pl tTerrain.tex
	rm -f tTerrain.tex
	mv __fichier_tmp__.tex tTerrain.tex


movehtml :
	mv *.html ./html

movetex :
	mv *.tex ./tex

movepdf :
	mv *.pdf ./pdf

cleanhtml :
	rm -f html/*.html

cleantex :
	rm -f tex/*.tex

cleanpdf :
	rm -f pdf/*.pdf

cleantmp :
	rm -f *.log *.aux *.toc *.out *~

clean : cleanhtml cleantex cleanpdf cleantmp

