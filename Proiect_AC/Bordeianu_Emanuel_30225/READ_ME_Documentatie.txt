
 
---- Observatii importante ----
---- Va rog sa cititi cu atentie ----

La inceput, cerinta proiectului era sa calculez numarul de tranzitii
a doua numere si sa le compar in functie de numarul de tranzitii.
Aceste informatii sunt detaliate la puctul "1)".
Am implementat aceasta cerinta in C, apoi in Assembly (cel facut in
anul 1, semestrul 2), apoi am implementat si in "Mars MIPS" (simulator
pentru Assembly-ul in versiunea cu $ ). Un site folositor pentru descarcarea 
acestuia este la adresa:  https://www.youtube.com/watch?v=u5Foo6mmW0I&fbclid=IwAR1vgxJI-8t8pXiLAW7vtDoa1FvmWAYknm6iBYm5bPJNEyQZbVRPQkzY1iY .
Cand am trecut la partea de codificare a instructiunilor, am realizat ca 
proiectul este prea vast pentru MIPS16 (aveam aproape 200 de linii de cod).
Ce-i drept este ca am facut si afisari (care nu se cer) pentru a
da un aspect placut la output. Din acest motiv, am avut multe linii de cod.
In consecinta, i-am cerut domnului Mircea Paul Muresan sa imi ofere o
inlesnire a implementarii acestui proiect.
In final, cerinta este de a calcula numarul de tranzitii al unui numar 
si de a-l compara cu un numar hardcodat.

 


---- Explicatii fisiere trimise ----

Am considerat ca nu ar fi chiar "fair" sa muncesc degeaba pentru prima
cerinta, asa ca am trimis mai multe fisiere in care demonstrez ca
am facut si cerinta initiala, dar si cerinta finala (cea simplificata).
In proiectul din Vivado am pus varianta simplificata, cea de final.

In fisierul "main.c" am pus codul in C corespunzator cerintei initiale.
In fisierul "Tranzitii_ASM.asm" este codul in Assembly (cel facut in 
anul 1, semestrul 2) corespunzator cerintei initiale.
In fisierul "Tranzitii_MIPS_mare.asm" este codul in Assembly-ul cu $.
In fisierul "Tranzitii_MIPS_mare.txt" este codul in Assembly-ul cu $ in format txt.
In fisierul "Tranzitii_mips_simplificat_final.asm" este codul in
Assembly-ul cu $, dar este versiunea simplificata.
In fisierul "Tranzitii_mips_simplificat_final.txt" este codul in
Assembly-ul cu $, in format txt, pe versiunea simplificata.
In "test_env_mare.txt" am pus tot codul pe care l-am rulat in Active HDL.






1) Scopul programului este de a calcula numarul de tranzitii, a doua numere, si de a compara cele doua numere in functie de tranzitii. De exemplu, pentru nr1 = 28, adica 011100 in binar, si nr2 = 10, adica 01010 in binar; vom avea numarul de tranzitii pentru nr1 egal cu 2. Pentru nr2, numarul de tranzitii va fi egal cu 4. In final, vom compara cele doua numere in functie de numarul de tranzitii si vom scrie un mesaj adecvat in urma comparatiei facute. Un exemplu de mesaj este: “nr2 are numar de tranzitii mai mare decat nr1”.



2) Detaliere instructiuni
Am pus comentarii la instructiuni in timp ce scriam codul.
In "Tranzitii_mips_simplificat_final.asm" nu am scris codul structurat
frumos pe fiecare linie, deoarece am folosit si afisari care nu sunt
incluse in partea de proiect din Vivado, dar m-au ajuat pentru a urmari
corectitudinea rezultatelor.



3) Proiectul este finalizat. Am facut toate componentele necesare
+ test_env. Este o problema la niste comentarii in instruction fetch.
In legenda am scris ca $zero = $0. Uneori n-am mai inlocuit $zero cu $0.
In programul de Assembly cu $ este totul modificat cum trebuie.
Acolo putem vedea exact ceea ce trebuie.
Vivado ruleaza lent si nu are rost sa mai modific doar pentru cateva comentarii. 



PS: Sper ca am fost suficient de explicit.  :))

---------------------------------------------------------------------



