.data
	newline: .asciiz "\n"
	numar_trans1_1: .asciiz "\nNr de tranzitii al numarului nr1 = "
	numar_trans1_2: .asciiz "\nNr de tranzitii al numarului nr2 = "
	numar_trans2: .asciiz "  este:\n"
	egalitee: .asciiz "\nNumerele au numar egal de tranzitii \n"
	nr1_mai_mare_decat_nr2: .asciiz "\n nr1 are numar de tranzitii mai mare decat nr2\n"
	este_mai_mare: .asciiz "  este mai mare decat  "
	nr2_mai_mare_decat_nr1: .asciiz "\n nr2 are numar de tranzitii mai mare decat nr1\n"
.text

addi $t0, $zero, 28 # numarul1
add $t1, $zero, $t0 # copie a lui nr1
add $t2, $zero, $t0 # copie a lui nr1 care va fi modificata
addi $k0, $zero, 0 # numarul de tranzitii este 0 (zero)
# il vom muta pe $t2 cu o pozitie spre dreapta

beq $t0, 0, etich1 # daca numarul este 0 => mergem la sfarsit de program

and $s1, $t0, 1
add $s3, $zero, $s1 #copie a restului de la impartirea cu 2
srl $t0, $t0, 1
srl $t2, $t2, 1 # numarul este shift-at cu o poz spre dreapta
addi $k0, $zero, 1 #numarul de tranzitii este 1 (unu)

etich4:
beq $t2, 0, etich3
and $s1, $t0, 1
add $s5, $zero, $s1 # restul numarului care a fost div cu 2 inainte de bucla
srl $t0, $t0, 1
srl $t2, $t2, 1
beq $s3, $s5, x_egal_y
addi, $k0, $k0, 1
x_egal_y:
add, $s3, $zero, $s5 # sau $s1 in loc de $s5
bge $t2, 0, etich4

etich3:
etich1:


#Print mesaj # Nr de translatii al nr 
li $v0, 4
la $a0, numar_trans1_1
syscall
#Print %d
li $v0, 1
add $a0, $zero, $t1
syscall
#Print rest de mesaj # este:
li $v0, 4
la $a0, numar_trans2
syscall
#Print afisare nr trans # %d = nr translatii
li $v0, 1
add $a0, $zero, $k0
syscall

add $t8, $zero, $k0 # retinem numarul de translatii pt nr1


## --------------------------------------------------
## --------------------------------------------------
## --------------------------------------------------
## --------------------------------------------------



addi $t0, $zero, 20 # numarul2
add $t1, $zero, $t0 # copie a lui nr2
add $t2, $zero, $t0 # copie a lui nr2 care va fi modificata
addi $k0, $zero, 0 # numarul de tranzitii este 0 (zero)
# il vom muta pe $t2 cu o pozitie spre dreapta

beq $t0, 0, etich11 # daca numarul este 0 => mergem la sfarsit de program

and $s1, $t0, 1
add $s3, $zero, $s1 #copie a restului de la impartirea cu 2
srl $t0, $t0, 1
srl $t2, $t2, 1 # numarul este shift-at cu o poz spre dreapta
addi $k0, $zero, 1 #numarul de tranzitii este 1 (unu)

etich41:
beq $t2, 0, etich31
and $s1, $t0, 1
add $s5, $zero, $s1 # restul numarului care a fost div cu 2 inainte de bucla
srl $t0, $t0, 1
srl $t2, $t2, 1
beq $s3, $s5, x_egal_y1
addi, $k0, $k0, 1
x_egal_y1:
add, $s3, $zero, $s5 # sau $s1 in loc de $s5
bge $t2, 0, etich41

etich31:
etich11:


#Print mesaj # Nr de translatii al nr 
li $v0, 4
la $a0, numar_trans1_1
syscall
#Print %d
li $v0, 1
add $a0, $zero, $t1
syscall
#Print rest de mesaj # este:
li $v0, 4
la $a0, numar_trans2
syscall
#Print afisare nr trans # %d = nr translatii
li $v0, 1
add $a0, $zero, $k0
syscall

add $t9, $zero, $k0 # retinem numarul de translatii pt nr1



## --------------------------------------------------
## --------------------------------------------------
## --------------------------------------------------
## --------------------------------------------------



add $t0, $zero, $t8  # in $t0 avem nr de tranzitii pt nr1
add $t1, $zero, $t9  # in $t1 avem nr de tranzitii pt nr2

beq $t0, $t1, egale  # daca sunt egale, afisam mesaj de egalitate

bgt $t0, $t1, mai_mare_nr1 # daca nr_translatii_nr1 > nr_translatii_nr2, 
			    # afisam mesaj corespunzator
		
blt $t0, $t1, mai_mare_nr2 # daca nr_translatii_nr2 > nr_translatii_nr1, 
			    # afisam mesaj corespunzator


egale:
#Print mesaj de egalitate
li $v0, 4
la $a0, egalitee
syscall
addi $s0, $zero, 2
beq $s0, 2, egale2

# Print nr1 are mai multe tranzitii decat nr2
mai_mare_nr1:
li $v0, 4
la $a0, nr1_mai_mare_decat_nr2
syscall
#Print %d = nr tranzitii nr1, adica $t0
li $v0, 1
add $a0, $zero, $t0
syscall
#Print este mai mare
li $v0, 4
la $a0, este_mai_mare
syscall
#Print %d = nr tranzitii nr2, adica $t1
li $v0, 1
add $a0, $zero, $t1
syscall

addi $s0, $zero, 3
beq $s0, 3, egale3

# Print nr2 are mai multe tranzitii decat nr1
mai_mare_nr2:
li $v0, 4
la $a0, nr2_mai_mare_decat_nr1
syscall
#Print %d = nr tranzitii nr2, adica $t1
li $v0, 1
add $a0, $zero, $t1
syscall
#Print este mai mare
li $v0, 4
la $a0, este_mai_mare
syscall
#Print %d = nr tranzitii nr1, adica $t0
li $v0, 1
add $a0, $zero, $t0
syscall

addi $s0, $zero, 4
beq $s0, 4, egale4


egale2:
egale3:
egale4:



