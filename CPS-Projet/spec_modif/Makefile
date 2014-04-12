all : gentex transf genpdf cleantmp move

gentex :
	emacs bloc.org -nw -f org-export-as-latex --kill
	emacs chose.org -nw -f org-export-as-latex --kill
	emacs gangster.org -nw -f org-export-as-latex --kill
	emacs gestionCombat.org -nw -f org-export-as-latex --kill
	emacs moteurJeu.org -nw -f org-export-as-latex --kill
	emacs objetequipable.org -nw -f org-export-as-latex --kill
	emacs objetmarchand.org -nw -f org-export-as-latex --kill
	emacs objet.org -nw -f org-export-as-latex --kill
	emacs personnage.org -nw -f org-export-as-latex --kill
	emacs position.org -nw -f org-export-as-latex --kill
	emacs terrain.org -nw -f org-export-as-latex --kill

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
	pdflatex bloc.tex
	pdflatex chose.tex
	pdflatex gangster.tex
	pdflatex gestionCombat.tex
	pdflatex moteurJeu.tex
	pdflatex objetequipable.tex
	pdflatex objetmarchand.tex
	pdflatex objet.tex
	pdflatex personnage.tex
	pdflatex position.tex
	pdflatex terrain.tex

move :
	mv *.pdf ../pdf/

cleantmp :
	rm -f *.tex *.log *.aux *.toc *.out *~

clean :
	rm -f *.pdf *.tex ../pdf/*.pdf *.log *.aux *.toc *.out *~

