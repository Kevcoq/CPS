all : gen cleantmp

gen :
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

cleantmp :
	rm -f *.log *.aux *.toc *.out *~

clean :
	rm -f *.pdf *.log *.aux *.toc *.out *~

