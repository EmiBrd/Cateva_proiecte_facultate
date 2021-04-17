#include <iostream>
#include "Profiler.h"
Profiler profiler("tema2");
using namespace std;


/**
 * @author Bordeianu Emanuel
 * @group 30225

   Specificatiile problemei: Compararea metodelor de construire a unui heap: bottom-up, top-down si heapsort

   caz favorabil: heapsort O(n*logn);
   caz mediu statistic: heapsort O(n*logn);
   caz defavorafil: heapsort O(n*logn);
   bottom-up: O(n);
   top-down: O(n*logn)

 */


int Size = 0;
int Sized = 0;
//int Sizef = 0;


void swapp(int* a, int* b){

	int t = *a;
	*a = *b;
	*b = t;
}



void heapify(int a[], int n, int i){

	int largest = i; 
	int l = 2 * i + 1; 
	int r = 2 * i + 2; 

	
	if ((l < n) && (a[l] > a[largest]))
		largest = l;
	

	if ((r < n) && (a[r] > a[largest]))
		largest = r;

	if (largest != i){
		swapp(&a[i], &a[largest]);
		
		heapify(a, n, largest);
	}
}



void heap_sort(int a[], int n) {
	

	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(a, n, i);

	for (int i = n - 1; i >= 0; i--) {
		swapp(&a[0], &a[i]);

		heapify(a, i, 0);
	}
}



void heapifyy(int a[], int n, int i) {
	Operation atribuiri = profiler.createOperation("Atribuiri bot", n);
	Operation comparatii = profiler.createOperation("Comparatii bot", n);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < n) && (a[l] > a[largest]))
		largest = l;

	comparatii.count();
	if ((r < n) && (a[r] > a[largest]))
		largest = r;

	if (largest != i) {
		swapp(&a[i], &a[largest]);
		atribuiri.count(3);
		heapifyy(a, n, largest);
	}
}




void bottom_up(int a[], int n){
	Operation atribuiri = profiler.createOperation("Atribuiri bot", n);
	Operation comparatii = profiler.createOperation("Comparatii bot", n);

	for (int i = n / 2 - 1; i >= 0; i--){
		heapifyy(a, n, i);
	}
}



int Parent(int i){

	return (i + 1) / 2 - 1;
}



void Max_Heap_Insert(int a[], int key,int n){
	
	Size++;
	int i = Size;
	
	Operation atribuiri = profiler.createOperation("Atribuiri top", n);
	Operation comparatii = profiler.createOperation("Comparatii top", n);
	
	a[i] = key;
	atribuiri.count();

	comparatii.count();
	while ((i > 0) && (a[Parent(i)] < a[i])){
		int aux = a[i];
		a[i] = a[Parent(i)];
		a[Parent(i)] = aux;
		atribuiri.count(3);
		i = Parent(i);

		comparatii.count();
	}

}


void top_down(int a[], int n){
	Operation atribuiri = profiler.createOperation("Atribuiri top", n);
	Operation comparatii = profiler.createOperation("Comparatii top", n);

	Size = 0;
	
	for (int i = 1; i < n; i++)
		Max_Heap_Insert(a, a[i],n);
}
// ---------------------------------------------
// ---------------------------------------------

// defav



void heapifyyd(int a[], int n, int i) {
	Operation atribuiri = profiler.createOperation("Atribuiri bot D", n);
	Operation comparatii = profiler.createOperation("Comparatii bot D", n);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < n) && (a[l] > a[largest]))
		largest = l;

	comparatii.count();
	if ((r < n) && (a[r] > a[largest]))
		largest = r;

	if (largest != i) {
		swapp(&a[i], &a[largest]);
		atribuiri.count(3);
		heapifyyd(a, n, largest);
	}
}




void bottom_upd(int a[], int n) {
	Operation atribuiri = profiler.createOperation("Atribuiri bot D", n);
	Operation comparatii = profiler.createOperation("Comparatii bot D", n);

	for (int i = n / 2 - 1; i >= 0; i--) {
		heapifyyd(a, n, i);
	}
}



int Parentd(int i) {

	return (i + 1) / 2 - 1;
}


void Max_Heap_Insertd(int a[], int key, int n) {

	Sized++;
	int i = Sized;

	Operation atribuiri = profiler.createOperation("Atribuiri top D", n);
	Operation comparatii = profiler.createOperation("Comparatii top D", n);

	a[i] = key;
	atribuiri.count();

	comparatii.count();
	while ((i > 0) && (a[Parentd(i)] < a[i])) {
		int aux = a[i];
		a[i] = a[Parentd(i)];
		a[Parentd(i)] = aux;
		atribuiri.count(3);
		i = Parentd(i);

		comparatii.count();
	}

}


void top_downd(int a[], int n) {
	Operation atribuiri = profiler.createOperation("Atribuiri top D", n);
	Operation comparatii = profiler.createOperation("Comparatii top D", n);

	Sized = 0;

	for (int i = 1; i < n; i++)
		Max_Heap_Insertd(a, a[i], n);
}
// ----------------------------------------------
// ----------------------------------------------



// favor
/*
void heapifyyf(int a[], int n, int i) {
	Operation atribuiri = profiler.createOperation("Atribuiri bot F", n);
	Operation comparatii = profiler.createOperation("Comparatii bot F", n);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < n) && (a[l] > a[largest]))
		largest = l;

	comparatii.count();
	if ((r < n) && (a[r] > a[largest]))
		largest = r;

	if (largest != i) {
		swapp(&a[i], &a[largest]);
		atribuiri.count(3);
		heapifyyf(a, n, largest);
	}
}




void bottom_upf(int a[], int n) {
	Operation atribuiri = profiler.createOperation("Atribuiri bot F", n);
	Operation comparatii = profiler.createOperation("Comparatii bot F", n);

	for (int i = n / 2 - 1; i >= 0; i--) {
		heapifyyf(a, n, i);
	}
}



int Parentf(int i) {

	return (i + 1) / 2 - 1;
}


void Max_Heap_Insertf(int a[], int key, int n) {

	Sizef++;
	int i = Sizef;

	Operation atribuiri = profiler.createOperation("Atribuiri top F", n);
	Operation comparatii = profiler.createOperation("Comparatii top F", n);

	a[i] = key;
	atribuiri.count();

	comparatii.count();
	while ((i > 0) && (a[Parentf(i)] < a[i])) {
		int aux = a[i];
		a[i] = a[Parentf(i)];
		a[Parentf(i)] = aux;
		atribuiri.count(3);
		i = Parentf(i);

		comparatii.count();
	}

}


void top_downf(int a[], int n) {
	Operation atribuiri = profiler.createOperation("Atribuiri top F", n);
	Operation comparatii = profiler.createOperation("Comparatii top F", n);

	Sized = 0;

	for (int i = 1; i < n; i++)
		Max_Heap_Insertf(a, a[i], n);
}
*/



// ----------------------------------------------
// ----------------------------------------------
void afisare(int a[], int n) {
	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
	cout << endl;
}



int main(){

	/*
	int a[] = { 0,1,2,3,4,5,6,7,8,9 };
	int da = sizeof(a) / sizeof(int);
	cout << "BU:" << endl;
	bottom_up(a, da);
	for (int i = 0; i < da; i++)
		cout << a[i] << " ";
	cout << endl<<endl;


	cout << "Heapsort:" << endl;
	int a1[] = { 9,8,7,6,5,4,3,2,1,0 };
	int da1 = sizeof(a1) / sizeof(int);

	heap_sort(a1, da1);
	for (int i = 0; i < da1; i++)
		cout << a1[i] << " ";
	cout << endl << endl;
	
	cout << "TD:" << endl;
	int a2[] = { 0, 1,2,3,4,5,6,7,8,9 };
	int da2 = sizeof(a2) / sizeof(int);
	
	top_down(a2, da2);
	for (int i = 0; i < da2; i++)
		cout << a2[i] << " ";

	cout << endl << endl;
	*/


	
	cout << "Heapsort:" << endl;
	int a1[] = { 9,8,7,6,5,4,3,2,1,0 };
	int da1 = sizeof(a1) / sizeof(int);

	heap_sort(a1, da1);
	afisare(a1, da1);

	// ---------------------------------------
	// ---------------------------------------

	// cazul mediu statistic
	
	int vbot[10000];
	int vtop[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		FillRandomArray(vbot, i);
		FillRandomArray(vtop, i);
		bottom_up(vbot, i);
		top_down(vtop, i);
	}

	profiler.createGroup("GR ATRIBUIRI", "Atribuiri bot",  "Atribuiri top");
	profiler.createGroup("GR COMPARATII", "Comparatii bot", "Comparatii top");
	profiler.addSeries("Tot op bot", "Comparatii bot", "Atribuiri bot");
	profiler.addSeries("Tot op top", "Comparatii top", "Atribuiri top");
	profiler.createGroup("Total operatii", "Tot op bot", "Tot op top");

	// -------------------------------------------------------
	// -------------------------------------------------------

	// cazul defavorabil


	int vbotd[10000];
	int vtopd[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		FillRandomArray(vbotd, i, 10, 50000, false, 1);
		FillRandomArray(vtopd, i, 10, 50000, false, 1);
		bottom_upd(vbotd, i);
		top_downd(vtopd, i);
	}

	profiler.createGroup("GR ATRIBUIRI D", "Atribuiri bot D", "Atribuiri top D");
	profiler.createGroup("GR COMPARATII D", "Comparatii bot D", "Comparatii top D");
	profiler.addSeries("Tot op bot D", "Comparatii bot D", "Atribuiri bot D");
	profiler.addSeries("Tot op top D", "Comparatii top D", "Atribuiri top D");
	profiler.createGroup("Total operatii D", "Tot op bot D", "Tot op top D");

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------


	// fav
	/*
	int vbotf[10000];
	int vtopf[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		FillRandomArray(vbotf, i, 10, 50000, false, 2);
		FillRandomArray(vtopf, i, 10, 50000, false, 2);
		bottom_upf(vbotf, i);
		top_downf(vtopf, i);
	}

	profiler.createGroup("GR ATRIBUIRI F", "Atribuiri bot F", "Atribuiri top F");
	profiler.createGroup("GR COMPARATII F", "Comparatii bot F", "Comparatii top F");
	profiler.addSeries("Tot op bot F", "Comparatii bot F", "Atribuiri bot F");
	profiler.addSeries("Tot op top F", "Comparatii top F", "Atribuiri top F");
	profiler.createGroup("Total operatii F", "Tot op bot F", "Tot op top F");
	*/

	profiler.showReport();
	

	return 0;
}

