#include <iostream>
#include <time.h>
#include <cstdlib>
#include<stdlib.h>
#include<stdio.h>
#include "Profiler.h"
Profiler profiler("tema4");
using namespace std;


/**
 * @author Bordeianu Emanuel
 * @group 30225

   Specificatiile problemei: Interclasare k-liste

 */



typedef struct node {
	int key;
	struct node* next;
}node;


void swapp(node** a, node** b){
	node* aux = *a;
	*a = *b;
	*b = aux;
}


node* newnode(int k){
	node* p = (node*)malloc(sizeof(node));
	p->key = k;
	p->next = NULL;
	return p;
}


void inserare_dreapta(node** first, int key){
	node* p = newnode(key);
	node* q = *first;

	if (*first == NULL) {
		*first = p;
		return;
	}

	while (q->next != NULL){
		q = q->next;
	}
	q->next = p;
}


node* stergere(node** first){
	if (*first == NULL)
		return NULL;
	node* p = *first;
	*first = (*first)->next;
	return p;
}


void afisare(node* first) {
	while (first != NULL) {
		cout << first->key << " ";
		first = first->next;
	}
	cout << endl;
}


void from_vector_int_to_node(int a[], int n, node** first){
	for (int i = 0; i < n; i++){
		inserare_dreapta(first, a[i]);
	}
}





/// k = 5
void heapify_5(node*** heap, int* n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_5", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_5", m);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < *n) && ((*heap)[l]->key < (*heap)[largest]->key)) {
		largest = l;
	}

	comparatii.count();
	if ((r < *n) && ((*heap)[r]->key < (*heap)[largest]->key)) {
		largest = r;
	}

	if (i != largest) {
		swapp(&(*heap)[i], &(*heap)[largest]);
		atribuiri.count(3);
		heapify_5(&(*heap), n, largest, m);
	}
}


void bottom_up_5(node*** heap, int* n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_5", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_5", m);

	for (int i = *n / 2 - 1; i >= 0; i--) {
		heapify_5(&(*heap), n, i, m);
	}
}

void SortedMerge_5(node*** vectorul, int n, int k) {

	int size1 = n / k;
	int size2 = n % k;

	*vectorul = (node**)calloc(k, sizeof(node*));
	int* a = (int*)calloc(size1, sizeof(int));

	for (int i = 0; i < k - 1; i++) {
		FillRandomArray(a, size1, 10, 50000, false, 1);
		from_vector_int_to_node(a, size1, &(*vectorul)[i]);
	}

	a = (int*)malloc((size1 + size2) * sizeof(int));
	FillRandomArray(a, (size1 + size2), 10, 50000, false, 1);
	from_vector_int_to_node(a, (size1 + size2), &(*vectorul)[k - 1]);

}


void mergeklists_5(node*** vectorul, int* heap_size, int nr_elements, node** lista_sortata, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_5", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_5", m);

	*lista_sortata = NULL;
	bottom_up_5(vectorul, heap_size, m);

	comparatii.count();
	while (*heap_size > 0) {
		inserare_dreapta(lista_sortata, (*vectorul)[0]->key);
		stergere(&(*vectorul)[0]);

		if ((*vectorul)[0] == NULL) {

			swapp(&(*vectorul)[0], &(*vectorul)[*heap_size - 1]);
			atribuiri.count(3);
			*heap_size = *heap_size - 1;
		}
		heapify_5(vectorul, heap_size, 0, m);
		comparatii.count();
	}
}
// -------------------------------------------------------------------------
// -------------------------------------------------------------------------



// k = 10
void heapify_10(node*** heap, int* n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_10", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_10", m);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < *n) && ((*heap)[l]->key < (*heap)[largest]->key)) {
		largest = l;
	}

	comparatii.count();
	if ((r < *n) && ((*heap)[r]->key < (*heap)[largest]->key)) {
		largest = r;
	}

	if (i != largest) {
		swapp(&(*heap)[i], &(*heap)[largest]);
		atribuiri.count(3);
		heapify_10(&(*heap), n, largest, m);
	}
}


void bottom_up_10(node*** heap, int* n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_10", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_10", m);

	for (int i = *n / 2 - 1; i >= 0; i--) {
		heapify_10(&(*heap), n, i, m);
	}
}

void SortedMerge_10(node*** vectorul, int n, int k) {

	int size1 = n / k;
	int size2 = n % k;

	*vectorul = (node**)calloc(k, sizeof(node*));
	int* a = (int*)calloc(size1, sizeof(int));

	for (int i = 0; i < k - 1; i++) {
		FillRandomArray(a, size1, 10, 50000, false, 1);
		from_vector_int_to_node(a, size1, &(*vectorul)[i]);
	}

	a = (int*)malloc((size1 + size2) * sizeof(int));
	FillRandomArray(a, (size1 + size2), 10, 50000, false, 1);
	from_vector_int_to_node(a, (size1 + size2), &(*vectorul)[k - 1]);

}


void mergeklists_10(node*** vectorul, int* heap_size, int nr_elements, node** lista_sortata, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_10", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_10", m);

	*lista_sortata = NULL;
	bottom_up_10(vectorul, heap_size, m);

	comparatii.count();
	while (*heap_size > 0) {
		inserare_dreapta(lista_sortata, (*vectorul)[0]->key);
		stergere(&(*vectorul)[0]);

		if ((*vectorul)[0] == NULL) {
		
			swapp(&(*vectorul)[0], &(*vectorul)[*heap_size - 1]);
			atribuiri.count(3);
			*heap_size = *heap_size - 1;
		}
		heapify_10(vectorul, heap_size, 0, m);
		comparatii.count();
	}
}
// -------------------------------------------------------------------------
// -------------------------------------------------------------------------




// k = 100
void heapify_100(node*** heap, int* n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_100", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_100", m);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < *n) && ((*heap)[l]->key < (*heap)[largest]->key)) {
		largest = l;
	}

	comparatii.count();
	if ((r < *n) && ((*heap)[r]->key < (*heap)[largest]->key)) {
		largest = r;
	}

	if (i != largest) {
		swapp(&(*heap)[i], &(*heap)[largest]);
		atribuiri.count(3);
		heapify_100(&(*heap), n, largest, m);
	}
}


void bottom_up_100(node*** heap, int* n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_100", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_100", m);

	for (int i = *n / 2 - 1; i >= 0; i--) {
		heapify_100(&(*heap), n, i, m);
	}
}

void SortedMerge_100(node*** vectorul, int n, int k) {

	int size1 = n / k;
	int size2 = n % k;

	*vectorul = (node**)calloc(k, sizeof(node*));
	int* a = (int*)calloc(size1, sizeof(int));

	for (int i = 0; i < k - 1; i++) {
		FillRandomArray(a, size1, 10, 50000, false, 1);
		from_vector_int_to_node(a, size1, &(*vectorul)[i]);
	}

	a = (int*)malloc((size1 + size2) * sizeof(int));
	FillRandomArray(a, (size1 + size2), 10, 50000, false, 1);
	from_vector_int_to_node(a, (size1 + size2), &(*vectorul)[k - 1]);

}


void mergeklists_100(node*** vectorul, int* heap_size, int nr_elements, node** lista_sortata, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_100", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_100", m);

	*lista_sortata = NULL;
	bottom_up_100(vectorul, heap_size, m);

	comparatii.count();
	while (*heap_size > 0) {
		inserare_dreapta(lista_sortata, (*vectorul)[0]->key);
		stergere(&(*vectorul)[0]);

		if ((*vectorul)[0] == NULL) {

			swapp(&(*vectorul)[0], &(*vectorul)[*heap_size - 1]);
			atribuiri.count(3);
			*heap_size = *heap_size - 1;
		}
		heapify_100(vectorul, heap_size, 0, m);
		comparatii.count();
	}
}
// -------------------------------------------------------------------------
// -------------------------------------------------------------------------





// k = 10 pana la 500 din 10 in 10
void heapify_500(node*** heap, int* n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_500", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_500", m);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < *n) && ((*heap)[l]->key < (*heap)[largest]->key)) {
		largest = l;
	}

	comparatii.count();
	if ((r < *n) && ((*heap)[r]->key < (*heap)[largest]->key)) {
		largest = r;
	}

	if (i != largest) {
		swapp(&(*heap)[i], &(*heap)[largest]);
		atribuiri.count(3);
		heapify_500(&(*heap), n, largest, m);
	}
}


void bottom_up_500(node*** heap, int* n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_500", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_500", m);

	for (int i = *n / 2 - 1; i >= 0; i--) {
		heapify_500(&(*heap), n, i, m);
	}
}

void SortedMerge_500(node*** vectorul, int n, int k) {

	int size1 = n / k;
	int size2 = n % k;

	*vectorul = (node**)calloc(k, sizeof(node*));
	int* a = (int*)calloc(size1, sizeof(int));

	for (int i = 0; i < k - 1; i++) {
		FillRandomArray(a, size1, 10, 50000, false, 1);
		from_vector_int_to_node(a, size1, &(*vectorul)[i]);
	}

	a = (int*)malloc((size1 + size2) * sizeof(int));
	FillRandomArray(a, (size1 + size2), 10, 50000, false, 1);
	from_vector_int_to_node(a, (size1 + size2), &(*vectorul)[k - 1]);

}


void mergeklists_500(node*** vectorul, int* heap_size, int nr_elements, node** lista_sortata, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri kliste_500", m);
	Operation comparatii = profiler.createOperation("Comparatii kliste_500", m);

	*lista_sortata = NULL;
	bottom_up_500(vectorul, heap_size, m);

	comparatii.count();
	while (*heap_size > 0) {
		inserare_dreapta(lista_sortata, (*vectorul)[0]->key);
		stergere(&(*vectorul)[0]);

		if ((*vectorul)[0] == NULL) {

			swapp(&(*vectorul)[0], &(*vectorul)[*heap_size - 1]);
			atribuiri.count(3);
			*heap_size = *heap_size - 1;
		}
		heapify_500(vectorul, heap_size, 0, m);
		comparatii.count();
	}
}




int main()
{
	
	/*
	node** vectorul;
	node* lista_sortata;
	int n = 30;
	int k = 5;
	SortedMerge_5(&vectorul, n, k);
	mergeklists_5(&vectorul, &k, n, &lista_sortata, n);
	afisare(lista_sortata);
	*/


	
	// k = 5
	node** vectorul_5;
	node* lista_sortata_5;
	int n_5;
	int k_5=5;

	for (int i = 100; i <= 10000; i = i+100) {
		k_5 = 5;
		SortedMerge_5(&vectorul_5, i, k_5);
		mergeklists_5(&vectorul_5, &k_5, i, &lista_sortata_5, i);
	}



	// k = 10
	node** vectorul_10;
	node* lista_sortata_10;
	int n_10;
	int k_10=10;
	for (int i = 100; i <= 10000; i = i + 100) {
		k_10 = 10;
		SortedMerge_10(&vectorul_10, i, k_10);
		mergeklists_10(&vectorul_10, &k_10, i, &lista_sortata_10, i);
	}



	// k = 100
	node** vectorul_100;
	node* lista_sortata_100;
	int n_100;
	int k_100=100;
	for (int i = 100; i <= 10000; i = i + 100) {
		k_100 = 100;
		SortedMerge_100(&vectorul_100, i, k_100);
		mergeklists_100(&vectorul_100, &k_100, i, &lista_sortata_100, i);
	}



	// k=10 pana la 500 din 10 n 10
	node** vectorul_500;
	node* lista_sortata_500;
	int n_500 = 10000;
	for (int k_500 = 10; k_500 <= 500; k_500 = k_500 + 10) {
		SortedMerge_500(&vectorul_500, n_500, k_500);
		int heap_size_500 = k_500;
		mergeklists_500(&vectorul_500, &heap_size_500, n_500, &lista_sortata_500, k_500);
		
	}
	profiler.addSeries("Total k_500", "Atribuiri kliste_500", "Comparatii kliste_500");
	


	profiler.createGroup("GR ATRIBUIRI", "Atribuiri kliste_5", "Atribuiri kliste_10", "Atribuiri kliste_100");
	profiler.createGroup("GR COMPARATII", "Comparatii kliste_5", "Comparatii kliste_10", "Comparatii kliste_100");
	profiler.addSeries("Tot op kliste_5", "Comparatii kliste_5", "Atribuiri kliste_5");
	profiler.addSeries("Tot op kliste_10", "Comparatii kliste_10", "Atribuiri kliste_10");
	profiler.addSeries("Tot op kliste_100", "Comparatii kliste_100", "Atribuiri kliste_100");
	profiler.createGroup("Total operatii", "Tot op kliste_5", "Tot op kliste_10", "Tot op kliste_100");
	

	profiler.showReport();
	

	return 0;
	
}

