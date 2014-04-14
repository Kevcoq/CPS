#!/usr/bin/perl -l

# Script de transformation de fichiers .tex
# On effectue ici la correspondance entre les symboles spéciaux
# et leur code au format .tex

open(LECT, "<$ARGV[0]") or die "No file $ARGV[0]\n";
open(ECR, ">__fichier_tmp__.tex") or die "Can't open tmp file\n";

while ($var = <LECT>) {
    chomp $var;
    $var =~ s/(\\title{)(\w)(\w+})/\1\U\2\E\3/;
    $var =~ s/^(\\author{).*(})$/\1Kevin Coquart, Quentin Bunel\2/;
    $var =~ s|\\href\{file:///(.*?)\.org}{(\w*?)}|\2|g;
    $var =~ s/×/\$\\times\$/g;
    $var =~ s/∧/\$\\wedge\$/g;
    $var =~ s/∨/\$\\vee\$/g;
    $var =~ s/→/\$\\to\$/g;
    $var =~ s/¬/\$\\neg\$/g;
    $var =~ s/≠/\$\\neq\$/g;   
    $var =~ s/⩽/\$\\leqslant\$/g;
    $var =~ s/≤/\$\\leqslant\$/g;
    $var =~ s/∅/\$\\varnothing\$/g;
    $var =~ s/∀/\$\\forall\$/g;
    $var =~ s/∃/\$\\exists\$/g;
    $var =~ s/∈/\$\\in\$/g;
    $var =~ s/⋃/\$\\cup\$/g;
    print ECR $var;
}

close LECT;
close ECR;

