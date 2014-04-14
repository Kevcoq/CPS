all : html tex pdf

html : genhtml transfhtml movehtml
tex : gentex transf movetex
pdf : genpdf cleantmp movepdf

genhtml : *.org
	emacs bloc.org -f org-export-as-html chose.org -f org-export-as-html gangster.org -f org-export-as-html \
	gestionCombat.org -f org-export-as-html moteurJeu.org -f org-export-as-html objetequipable.org -f org-export-as-html \
	objetmarchand.org -f org-export-as-html objet.org -f org-export-as-html personnage.org -f org-export-as-html \
	position.org -f org-export-as-html terrain.org -f org-export-as-html --kill

gentex : *.org
	emacs bloc.org -f org-export-as-latex chose.org -f org-export-as-latex gangster.org -f org-export-as-latex \
	gestionCombat.org -f org-export-as-latex moteurJeu.org -f org-export-as-latex objetequipable.org -f org-export-as-latex \
	objetmarchand.org -f org-export-as-latex objet.org -f org-export-as-latex personnage.org -f org-export-as-latex \
	position.org -f org-export-as-latex terrain.org -f org-export-as-latex --kill

transf :
	./tex/transf.pl bloc.tex
	rm -f bloc.tex
	mv __fichier_tmp__.tex bloc.tex
	./tex/transf.pl chose.tex
	rm -f chose.tex
	mv __fichier_tmp__.tex chose.tex
	./tex/transf.pl gangster.tex
	rm -f gangster.tex
	mv __fichier_tmp__.tex gangster.tex
	./tex/transf.pl gestionCombat.tex
	rm -f gestionCombat.tex
	mv __fichier_tmp__.tex gestionCombat.tex
	./tex/transf.pl moteurJeu.tex
	rm -f moteurJeu.tex
	mv __fichier_tmp__.tex moteurJeu.tex
	./tex/transf.pl objetequipable.tex
	rm -f objetequipable.tex
	mv __fichier_tmp__.tex objetequipable.tex
	./tex/transf.pl objetmarchand.tex
	rm -f objetmarchand.tex
	mv __fichier_tmp__.tex objetmarchand.tex
	./tex/transf.pl objet.tex
	rm -f objet.tex
	mv __fichier_tmp__.tex objet.tex
	./tex/transf.pl personnage.tex
	rm -f personnage.tex
	mv __fichier_tmp__.tex personnage.tex
	./tex/transf.pl position.tex
	rm -f position.tex
	mv __fichier_tmp__.tex position.tex
	./tex/transf.pl terrain.tex
	rm -f terrain.tex
	mv __fichier_tmp__.tex terrain.tex

transfhtml :
	./html/transfhtml.pl bloc.html
	rm -f bloc.html
	mv __fichier_tmp__.html bloc.html
	./html/transfhtml.pl chose.html
	rm -f chose.html
	mv __fichier_tmp__.html chose.html
	./html/transfhtml.pl gangster.html
	rm -f gangster.html
	mv __fichier_tmp__.html gangster.html
	./html/transfhtml.pl gestionCombat.html
	rm -f gestionCombat.html
	mv __fichier_tmp__.html gestionCombat.html
	./html/transfhtml.pl moteurJeu.html
	rm -f moteurJeu.html
	mv __fichier_tmp__.html moteurJeu.html
	./html/transfhtml.pl objetequipable.html
	rm -f objetequipable.html
	mv __fichier_tmp__.html objetequipable.html
	./html/transfhtml.pl objetmarchand.html
	rm -f objetmarchand.html
	mv __fichier_tmp__.html objetmarchand.html
	./html/transfhtml.pl objet.html
	rm -f objet.html
	mv __fichier_tmp__.html objet.html
	./html/transfhtml.pl personnage.html
	rm -f personnage.html
	mv __fichier_tmp__.html personnage.html
	./html/transfhtml.pl position.html
	rm -f position.html
	mv __fichier_tmp__.html position.html
	./html/transfhtml.pl terrain.html
	rm -f terrain.html
	mv __fichier_tmp__.html terrain.html

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

cleanhtml :
	rm -f html/*.html

cleantex :
	rm -f tex/*.tex

cleanpdf :
	rm -f pdf/*.pdf

cleantmp :
	rm -f *.log *.aux *.toc *.out *~

clean : cleanhtml cleantex cleanpdf cleantmp

