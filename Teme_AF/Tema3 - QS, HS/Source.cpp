#include <iostream>
#include <time.h>
#include <cstdlib>
#include "Profiler.h"
Profiler profiler("tema3");
using namespace std;

/**
 * @author Bordeianu Emanuel
 * @group 30225

   Specificatiile problemei: Compararea metodelor Quicksort si Heapsort

   caz favorabil: Heapsort O(n*logn), Quicksort O(n*logn);
   caz mediu statistic: Heapsort O(n*logn), Quicksort O(n*logn);
   caz defavorafil: Heapsort O(n*logn), Quicksort O(n*n);

 */



void swapp(int* a, int* b) {
	int aux = *a;
	*a = *b;
	*b = aux;
}


// mediu statistic

int partition(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	int pivot = a[high];
	atribuiri.count();

	int i = (low - 1);

	for (int j = low; j <= high - 1; j++) {

		comparatii.count();
		if (a[j] <= pivot) {
			i++;
			swapp(&a[i], &a[j]);
			atribuiri.count(3);
		}
	}
	swapp(&a[i + 1], &a[high]);
	atribuiri.count(3);

	return (i + 1);
}


int partition_random(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	srand(time(NULL));
	int random = rand() % (high - low) + low;
	swapp(&a[random], &a[high]);
	atribuiri.count(3);

	return partition(a, low, high, m);

}


void quick_sort(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	if (low < high) {
		int pi = partition_random(a, low, high, m);
		quick_sort(a, low, pi - 1, m);
		quick_sort(a, pi + 1, high, m);
	}
}




void heapify(int a[], int n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap", m);
	Operation comparatii = profiler.createOperation("Comparatii heap", m);

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

		heapify(a, n, largest, m);
	}
}



void heap_sort(int a[], int n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap", m);
	Operation comparatii = profiler.createOperation("Comparatii heap", m);

	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(a, n, i, m);

	for (int i = n - 1; i >= 0; i--) {
		swapp(&a[0], &a[i]);
		atribuiri.count(3);

		heapify(a, i, 0, m);
	}
}

// -----------------------------------------------------------------------
// -----------------------------------------------------------------------



// defav

int partitiond(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick D", m);
	Operation comparatii = profiler.createOperation("Comparatii quick D", m);

	int pivot = a[high];
	atribuiri.count();

	int i = (low - 1);

	for (int j = low; j <= high - 1; j++) {

		comparatii.count();
		if (a[j] < pivot) {
			i++;
			swapp(&a[i], &a[j]);
			atribuiri.count(3);
		}
	}
	swapp(&a[i + 1], &a[high]);
	atribuiri.count(3);

	return (i + 1);
}


void quick_sortd(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick D", m);
	Operation comparatii = profiler.createOperation("Comparatii quick D", m);

	if (low < high) {
		int pi = partitiond(a, low, high, m);
		quick_sortd(a, low, pi - 1, m);
		quick_sortd(a, pi + 1, high, m);
	}
}




void heapifyd(int a[], int n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap D", m);
	Operation comparatii = profiler.createOperation("Comparatii heap D", m);

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

		heapifyd(a, n, largest, m);
	}
}


void heap_sortd(int a[], int n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap D", m);
	Operation comparatii = profiler.createOperation("Comparatii heap D", m);

	for (int i = n / 2 - 1; i >= 0; i--)
		heapifyd(a, n, i, m);

	for (int i = n - 1; i >= 0; i--) {
		swapp(&a[0], &a[i]);
		atribuiri.count(3);

		heapifyd(a, i, 0, m);
	}
}

// ------------------------------------------------------
// ------------------------------------------------------




// fav

int partitionf(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick F", m);
	Operation comparatii = profiler.createOperation("Comparatii quick F", m);

	int mid = (low + high) / 2;
	int pivot = a[mid];
	atribuiri.count();

	int i = low - 1;
	int j = high + 1;

	while (true) {
		do {
			i++;
			comparatii.count();
		}while (a[i] < pivot);

		do {
			j--;
			comparatii.count();
		} while (a[j] > pivot);

		if (i >= j)
			return j;

		swapp(&a[i], &a[j]);
		atribuiri.count(3);
	}
}


void quick_sortf(int a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick F", m);
	Operation comparatii = profiler.createOperation("Comparatii quick F", m);

	if (low < high) {
		int pi = partitionf(a, low, high, m);
		quick_sortf(a, low, pi, m);
		quick_sortf(a, pi + 1, high, m);
	}
}





void heapifyf(int a[], int n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap F", m);
	Operation comparatii = profiler.createOperation("Comparatii heap F", m);

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

		heapifyf(a, n, largest, m);
	}
}


void heap_sortf(int a[], int n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri heap F", m);
	Operation comparatii = profiler.createOperation("Comparatii heap F", m);

	for (int i = n / 2 - 1; i >= 0; i--)
		heapifyf(a, n, i, m);

	for (int i = n - 1; i >= 0; i--) {
		swapp(&a[0], &a[i]);
		atribuiri.count(3);

		heapifyf(a, i, 0, m);
	}
}

// ----------------------------------------------------------
// ----------------------------------------------------------



void afisare(int a[], int n) {

	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
	cout << endl;
}



int main() {


	/*
	int vheap[] = { 9, 8, 7, 6, 5, 4,3,2,1,0 };
	int vquick[] = { 9, 8, 7, 6, 5, 4,3,2,1,0};
	//int vquick[] = { 10, 80, 30, 90, 40, 50, 70 };
	int dh = sizeof(vheap) / sizeof(int);
	int dq = sizeof(vquick) / sizeof(int);

	cout << "Heap_sort:" << endl;
	heap_sortf(vheap, dh, dh);
	afisare(vheap, dh);
	cout << "Qiucksort:" << endl;
	quick_sortf(vquick, 0, dq-1, dq);
	afisare(vquick, dq);
	*/

	// ------------------------------------------
	// ------------------------------------------


	// cazul mediu statistic

	
	int vheap[10000];
	int vquick[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		FillRandomArray(vquick, i);
		FillRandomArray(vheap, i);
		quick_sort(vquick, 0, i - 1, i);
		heap_sort(vheap, i, i);
	}


	profiler.createGroup("GR ATRIBUIRI", "Atribuiri quick", "Atribuiri heap");
	profiler.createGroup("GR COMPARATII", "Comparatii quick", "Comparatii heap");
	profiler.addSeries("Tot op quick", "Comparatii quick", "Atribuiri quick");
	profiler.addSeries("Tot op heap", "Comparatii heap", "Atribuiri heap");
	profiler.createGroup("Total operatii", "Tot op quick", "Tot op heap");

	// ------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------


	// defav

	int vheapd[10000];
	int vquickd[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		FillRandomArray(vquickd, i, 10, 50000, false, 2);
		FillRandomArray(vheapd, i, 10, 50000, false, 2);
		quick_sortd(vquickd, 0, i - 1, i);
		heap_sortd(vheapd, i, i);
	}


	profiler.createGroup("GR ATRIBUIRI D", "Atribuiri quick D", "Atribuiri heap D");
	profiler.createGroup("GR COMPARATII D", "Comparatii quick D", "Comparatii heap D");
	profiler.addSeries("Tot op quick D", "Comparatii quick D", "Atribuiri quick D");
	profiler.addSeries("Tot op heap D", "Comparatii heap D", "Atribuiri heap D");
	profiler.createGroup("Total operatii D", "Tot op quick D", "Tot op heap D");

	// ------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------



	// fav

	int vheapf[10000];
	int vquickf[10000];

	for (int i = 100; i <= 10000; i = i + 100) {
		//FillRandomArray(vquickf, i);
		FillRandomArray(vquickf, i, 10, 50000, false, 1);
		FillRandomArray(vheapf, i, 10, 50000, false, 1);
		quick_sortf(vquickf, 0, i - 1, i);
		heap_sortf(vheapf, i, i);
	}


	profiler.createGroup("GR ATRIBUIRI F", "Atribuiri quick F", "Atribuiri heap F");
	profiler.createGroup("GR COMPARATII F", "Comparatii quick F", "Comparatii heap F");
	profiler.addSeries("Tot op quick F", "Comparatii quick F", "Atribuiri quick F");
	profiler.addSeries("Tot op heap F", "Comparatii heap F", "Atribuiri heap F");
	profiler.createGroup("Total operatii F", "Tot op quick F", "Tot op heap F");


	profiler.showReport();
	

	return 0;
}