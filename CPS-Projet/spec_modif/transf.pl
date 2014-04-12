#!/usr/bin/perl -l

open(LECT, "<$ARGV[0]") or die "No file $ARGV[0]\n";
open(ECR, ">__fichier_tmp__.tex") or die "Can't open tmp file.txt\n";

while ($var = <LECT>) {
    chomp $var;
    $var =~ s/^(\\author{).*(})$/\1Kevin Coquart, Quentin Bunel\2/;
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

