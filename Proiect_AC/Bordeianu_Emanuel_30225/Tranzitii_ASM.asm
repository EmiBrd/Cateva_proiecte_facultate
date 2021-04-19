.386
.model flat, stdcall
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


includelib msvcrt.lib
extern exit: proc
extern printf: proc
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


public start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


.data
	msg DB "nr=", 0
	nrr1 DD 0
	nrr2 dd 0
	format1 DB "Nr de tranzitii al lui %d este: %d", 10, 0
	format2 DB "Nr de tranzitii al lui %d este: %d", 10, 0
	rezultat1 dd ?
	rezultat2 dd ?
	nr_iteratii dd 0
	nr_iteratii_format db "Nr de iteratii = %d", 10, 0
	f_inegalitate db "%d are mai multe tranzitii decat %d", 10, 0
	f_egalitate db "Numerele au numar egal de tranzitii", 10, 0
.code
start:

	; in eax retinem catul
	; in edx retinem restul
	mov eax, 10 ; aici punem numarul n
	mov nrr1, eax
	mov ebx, 0 ; nr de tranzitii
	mov ecx, 0
	mov edx, 0  
	mov esi, 2
	mov edi, 0
	mov ebp, eax
	
	cmp eax, 0
	je etich1
	
	;cmp eax, 1
	;je etich2
	
	div esi
	shr ebp, 1
	mov ebx, 1
	mov ecx, edx ; ecx este x din C
	etich4:
	cmp ebp, 0
	je etich3
	div esi
	shr ebp, 1
	mov edi, edx ; edi este y din C
	cmp edi, ecx
	je x_egal_y  ; daca x si y sunt egali => sarim de ebx++
	add ebx, 1
	x_egal_y:
	mov ecx, edi  ; x = y
	add nr_iteratii, 1
	cmp ebp, 0
	jge etich4
	
	;etich2:
	;mov ebx, 1
	etich3:
	
	etich1:
	mov rezultat1, ebx  ; daca afisez ebx sau 
					   ; rezultat e acelasi lucru
	
	push rezultat1
	push nrr1
	push offset format1
	call printf
	add ESP, 12
	;---------------------------------------------
	;---------------------------------------------
	;---------------------------------------------
	
	
	; in eax retinem catul
	; in edx retinem restul
	mov eax, 170 ; aici punem numarul n
	mov nrr2, eax
	mov ebx, 0 ; nr de tranzitii
	mov ecx, 0
	mov edx, 0  
	mov esi, 2
	mov edi, 0
	mov ebp, eax
	
	cmp eax, 0
	je etich12
	
	div esi
	shr ebp, 1
	mov ebx, 1
	mov ecx, edx ; ecx este x din C
	etich42:
	cmp ebp, 0
	je etich32
	div esi
	shr ebp, 1
	mov edi, edx ; edi este y din C
	cmp edi, ecx
	je x_egal_y2  ; daca x si y sunt egali => sarim de ebx++
	add ebx, 1
	x_egal_y2:
	mov ecx, edi  ; x = y
	add nr_iteratii, 1
	cmp ebp, 0
	jge etich42
	
	etich32:	
	etich12:
	mov rezultat2, ebx  ; daca afisez ebx sau 
					   ; rezultat e acelasi lucru
	
	push rezultat2
	push nrr2
	push offset format2
	call printf
	add ESP, 12
	;;-------------------------------------------
	;;-------------------------------------------
	;;-------------------------------------------
	
	
	
	mov ecx, rezultat1
	mov edx, rezultat2
	
	cmp ecx, edx
	je egale
	
	cmp ecx, edx
	jg inegalitate_rezultat1
	
	cmp edx, ecx
	jg inegalitate_rezultat2
	
	
	egale:
	mov edx, 1
	push offset f_egalitate
	call printf
	add esp, 4
	cmp edx, 1
	jmp egale2
	
	
	
	inegalitate_rezultat1:
	mov edx, -1
	push nrr2
	push nrr1
	push offset f_inegalitate
	call printf
	add esp, 12
	cmp edx, -1
	jmp egale3
	
	
	inegalitate_rezultat2:
	mov edx, 3
	push nrr1
	push nrr2
	push offset f_inegalitate
	call printf
	add esp, 12
	cmp edx, 3
	jmp egale4
	
	
	egale2:
	egale3:
	egale4:
	
	;apel functie exit
	push 0
	call exit
end start
