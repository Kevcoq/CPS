#!/usr/bin/perl -l


open(LECT, "<$ARGV[0]") or die "No file $ARGV[0]\n";
open(ECR, ">__fichier_tmp__.html") or die "Can't open tmp file\n";

while ($var = <LECT>) {
    chomp $var;
    $var =~ s|\<title\>(\w)(\w+)\</title\>|\<title\>\U\1\E\2\</title\>|;
    $var =~ s/(\<meta name="author").*//;
    $var =~ s/(\<h1 class="title"\>)(\w)(\w+)(\<\/h1\>)/\1\U\2\E\3\4/;
    $var =~ s/(\<p class="author"\> )Author: (\w+)/\1Authors: Kevin Coquart, Quentin Bunel/;
    $var =~ s/\<a href="mailto:.*//;
    print ECR $var;
}

close LECT;
close ECR;
