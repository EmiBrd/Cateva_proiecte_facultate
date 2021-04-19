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


#Legenda : 
# $zero => $0
# $t1 => $1
# $s1 => $2
# $s3 => $3
# $s4 => $4
# $k0 => $5
# $k1 => $6


addi $k1, $0, 3 # este numarul hardcodat cu care se face compararea	#	001_000_110_0000100
addi $s4, $0, 1 							#	001_000_100_0000001
addi $t1, $0, 4 # numarul1                  					000_001_000_0000101

##### Nu are legatura cu proiectul, e doar pentru afisare
add $t9, $0, $t1
##### Sfarsit #####
	
beq $t1, $0, etich1 # daca numarul este 0 => mergem la sfarsit de program	100_000_001_randul 38
and $s1, $t1, $s4  ##---------------						000_001_100_010_0_100
add $s3, $0, $s1 #copie a restului de la impartirea cu 2			000_000_010_011_0_000
srl $t1, $t1, 1			#						000_001_000_001_1_000
addi $k0, $0, 1 #numarul de tranzitii este 1 (unu)				001_000_101_0000001
etich4:	beq $t1, $0, etich3	#						100_000_001_randul unde e etich3	
and $s1, $t1, $s4 ## restul de la impartirea cu nr mai mare decat 1		000_001_100_010_0_100
srl $t1, $t1, 1						#			000_001_000_001_1_000
beq $s3, $s1, x_egal_y					#			100_010_011_randul unde e s_egal_y
add $k0, $k0, $s4					#			000_101_100_101_0_000
x_egal_y: add, $s3, $0, $s1 # sau $s1 in loc de $s5				000_000_010_011_0_000	
j etich4						#			111_0000000001000
etich3:										
etich1:										


#Print mesaj # Nr de translatii al nr 
li $v0, 4
la $a0, numar_trans1_1
syscall
#Print %d
li $v0, 1
add $a0, $0, $t9
syscall
#Print rest de mesaj # este:
li $v0, 4
la $a0, numar_trans2
syscall
#Print afisare nr trans # %d = nr translatii
li $v0, 1
add $a0, $0, $k0
syscall
# Print end


bne $k0, $k1, inegalit 					# 		101_110_101_randul unde e eticheta
addi $k0, $0, 1 # 1 (unu) insemanca ca sunt egale	#		001_000_101_1000000
j equale						# 		111_randul unde e eticheta 
inegalit: addi $k0, $0, -1 # nu sunt egale 		# 		001_000_101_0000100
equale:


#Print newline
li $v0, 4
la $a0, newline
syscall
#Print
li $v0, 1
add $a0, $0, $k0
syscall
#Print end


