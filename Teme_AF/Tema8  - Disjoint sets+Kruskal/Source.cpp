#include <iostream>
#include <stdio.h> 
#include <stdlib.h> 
#include <cstdlib>
#include <time.h>
#include "Profiler.h"
Profiler profiler("tema8");
using namespace std;



typedef struct Edge {
	int src, dest, weight;
}Edge;


typedef struct Graph {
	int V, E;
	struct Edge* edge;
}Graph;

typedef struct disjoint {
	int parent;
	int rank;
}disjoint;


Graph* createGraph(int V, int E) {
	Graph* graph = (Graph*)malloc((sizeof(Graph)));
	graph->V = V;
	graph->E = E;

	(graph)->edge = (Edge*)malloc((E + 1) * sizeof(Edge));

	return graph;
}


void make_set(disjoint* x, int i) {
	x[i].parent = i;
	x[i].rank = 0;
}

int find_sett(disjoint x[], int i) {
	if (x[i].parent != i) {
		x[i].parent = find_sett(x, x[i].parent);
	}
	return x[i].parent;
}

/*
void link(subset* x, int i, int j) {
	if (x[i].rank > x[j].rank) {
		x[j].parent = i;
	}
	else {
		x[i].parent = j;
		if (x[i].rank == x[j].rank) {
			x[j].rank = x[j].rank + 1;
		}
	}
}
*/

void unionn(disjoint* x, int i, int j) {
	int fx = find_sett(x, i);
	int fy = find_sett(x, j);

	if (x[fx].rank > x[fy].rank)
		x[fy].parent = fx;

	else {
		x[fx].parent = fy;
		if (x[fx].rank == x[fy].rank) {
			x[fy].rank = x[fy].rank + 1;
		}
	}
}

// ------------------------------------------------
// ------------------------------------------------

void heapify(Edge a[], int n, int i) {
	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;
	//comparatii.count();
	if ((l < n) && (a[l].weight > a[largest].weight))
		largest = l;

	//comparatii.count();
	if ((r < n) && (a[r].weight > a[largest].weight))
		largest = r;

	if (largest != i) {
		Edge aux = a[i];
		a[i] = a[largest];
		a[largest] = aux;
		//swapp(&a[i], &a[largest]);
		//atribuiri.count(3);

		heapify(a, n, largest);
	}
}

void heap_sort(Edge a[], int n) {

	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(a, n, i);

	for (int i = n - 1; i >= 0; i--) {
		Edge aux = a[0];
		a[0] = a[i];
		a[i] = aux;
		//swapp(&a[0], &a[i]);
		//atribuiri.count(3);

		heapify(a, i, 0);
	}
}

// ------------------------------------------------
// ------------------------------------------------


void heapify(Edge a[], int n, int i, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri hs", m);
	Operation comparatii = profiler.createOperation("Comparatii hs", m);

	int largest = i;
	int l = 2 * i + 1;
	int r = 2 * i + 2;

	comparatii.count();
	if ((l < n) && (a[l].weight > a[largest].weight))
		largest = l;

	comparatii.count();
	if ((r < n) && (a[r].weight > a[largest].weight))
		largest = r;

	if (largest != i) {
		Edge aux = a[i];
		a[i] = a[largest];
		a[largest] = aux;
		//swapp(&a[i], &a[largest]);
		atribuiri.count(3);

		heapify(a, n, largest, m);
	}
}

void heap_sort(Edge a[], int n, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri hs", m);
	Operation comparatii = profiler.createOperation("Comparatii hs", m);

	for (int i = n / 2 - 1; i >= 0; i--)
		heapify(a, n, i, m);

	for (int i = n - 1; i >= 0; i--) {
		Edge aux = a[0];
		a[0] = a[i];
		a[i] = aux;
		//swapp(&a[0], &a[i]);
		atribuiri.count(3);

		heapify(a, i, 0, m);
	}
}

// ------------------------------------------------
// ------------------------------------------------


int partition(Edge a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	Edge pivot = a[high];
	atribuiri.count();

	int i = (low - 1);

	for (int j = low; j <= high - 1; j++) {

		comparatii.count();
		if (a[j].weight <= pivot.weight) {
			i++;
			//swapp(&a[i], &a[j]);
			Edge aux = a[i];
			a[i] = a[j];
			a[j] = aux;
			atribuiri.count(3);
		}
	}
	//swapp(&a[i + 1], &a[high]);
	Edge aux1 = a[i + 1];
	a[i + 1] = a[high];
	a[high] = aux1;
	atribuiri.count(3);

	return (i + 1);
}


int partition_random(Edge a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	srand(time(NULL));
	int random = rand() % (high - low) + low;
	Edge aux = a[random];
	a[random] = a[high];
	a[high] = aux;
	//swapp(&a[random], &a[high]);
	atribuiri.count(3);

	return partition(a, low, high, m);

}


void quick_sort(Edge a[], int low, int high, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	if (low < high) {
		int pi = partition_random(a, low, high, m);
		quick_sort(a, low, pi - 1, m);
		quick_sort(a, pi + 1, high, m);
	}
}

// ------------------------------------------------
// ------------------------------------------------


void KruskalMST(Graph* graph) {

	int V = graph->V;
	int E = graph->E;
	struct Edge* result = (struct Edge*)malloc((V + 1) * sizeof(struct Edge));
	int e = 0;  
	int i = 0;   

	heap_sort(graph->edge, graph->E);
	//quick_sort(graph->edge, 0, graph->E, 1235);

	disjoint* fathers = (disjoint*)malloc(E * sizeof(disjoint));

	for (int v = 0; v < V; ++v) {
		make_set(fathers, v);
	}

	while (e < V - 1 && i < graph->E) {
		struct Edge next_edge = (graph)->edge[i++];

		int x = find_sett(fathers, next_edge.src);
		int y = find_sett(fathers, next_edge.dest);

		if (x != y) {
			result[e++] = next_edge;
			unionn(fathers, x, y);
		}
	}

	printf("Following are the edges in the constructed MST\n");
	for (i = 0; i < e; ++i)
		printf("%d -- %d == %d\n", result[i].src, result[i].dest, result[i].weight);

	return;
}

// ------------------------------------------------
// ------------------------------------------------


void KruskalMSThs(Graph* graph, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri hs", m);
	Operation comparatii = profiler.createOperation("Comparatii hs", m);

	int V = graph->V;
	int E = graph->E;
	struct Edge* result = (struct Edge*)malloc((V + 1) * sizeof(struct Edge));
	int e = 0;  // pt result[] 
	int i = 0;  // noduri sortate

	heap_sort(graph->edge, graph->E, m);

	disjoint* fathers = (disjoint*)malloc(E * sizeof(disjoint));

	for (int v = 0; v < V; ++v) {
		make_set(fathers, v);
	}

	while (e < V - 1 && i < graph->E) {
		struct Edge next_edge = (graph)->edge[i++];

		int x = find_sett(fathers, next_edge.src);
		int y = find_sett(fathers, next_edge.dest);
		atribuiri.count(2);

		if (x != y) {
			result[e++] = next_edge;
			unionn(fathers, x, y);
		}

	}
	/*
	printf("Following are the edges in the constructed MST\n");
	for (i = 0; i < e; ++i)
		printf("%d -- %d == %d\n", result[i].src, result[i].dest, result[i].weight);
	*/

	return;
}

// ------------------------------------------------
// ------------------------------------------------


void KruskalMSTqs(Graph* graph, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri quick", m);
	Operation comparatii = profiler.createOperation("Comparatii quick", m);

	int V = graph->V;
	int E = graph->E;
	struct Edge* result = (struct Edge*)malloc((V + 1) * sizeof(struct Edge));
	int e = 0; 
	int i = 0;  

	
	//heap_sort(graph->edge, graph->E, m);
	quick_sort(graph->edge, 0, graph->E, m);

	disjoint* fathers = (disjoint*)malloc(E * sizeof(disjoint));

	for (int v = 0; v < V; ++v) {
		make_set(fathers, v);
	}


	while (e < V - 1 && i < graph->E) {
		struct Edge next_edge = (graph)->edge[i++];

		int x = find_sett(fathers, next_edge.src);
		int y = find_sett(fathers, next_edge.dest);
		atribuiri.count(2);

		if (x != y) {
			result[e++] = next_edge;
			unionn(fathers, x, y);
		}

	}
	/*
	printf("Following are the edges in the constructed MST\n");
	for (i = 0; i < e; ++i)
		printf("%d -- %d == %d\n", result[i].src, result[i].dest, result[i].weight);
	*/

	return;
}


int main()
{
	/* 
			 70
		0--------1
		|      / |
	  45|    /   |100
		|  /50   |
		|/       |         
		2--------3
			40       
					*/
	int V = 4;  
	int E = 5;   
	struct Graph* graph = createGraph(V, E);
	// 0-1 
	graph->edge[0].src = 0;
	graph->edge[0].dest = 1;
	graph->edge[0].weight = 70;
	// 0-2 
	graph->edge[1].src = 0;
	graph->edge[1].dest = 2;
	graph->edge[1].weight = 45;
	// 0-3 
	graph->edge[2].src = 1;
	graph->edge[2].dest = 2;
	graph->edge[2].weight = 50;
	
	// 1-3 
	graph->edge[3].src = 1;
	graph->edge[3].dest = 3;
	graph->edge[3].weight = 100;
	// 2-3 
	graph->edge[4].src = 2;
	graph->edge[4].dest = 3;
	graph->edge[4].weight = 40;
	
	/*
	graph->edge[3].src = 11;
	graph->edge[3].dest = 31;
	graph->edge[3].weight = 100;
	
	graph->edge[4].src = 21;
	graph->edge[4].dest = 31;
	graph->edge[4].weight = 40;
	*/
	

	KruskalMST(graph);
	cout << endl << endl;
	// ----------------------------------------------------------
	// ----------------------------------------------------------


	/*
	fflush(stdin);
	int V1 = 100;
	int E1 = 400;
	Graph* graph1 = createGraph(V1, E1);
	for (int i = 0; i < 400; i = i++) {
		graph1->edge[i].src = 0;
		graph1->edge[i].dest = (rand() % 101) + 1;
		graph1->edge[i].weight = (rand() % 401) + 1;
	}
	KruskalMST(graph1);
	cout << endl << endl;
	*/


	/*
	int v_dest[10001];
	int v_weight[10001];
	FillRandomArray(v_dest, 101, 1, 101, true, 0);
	FillRandomArray(v_weight, 101, 10, 5000, true, 0);
	
	fflush(stdin);
	for (int n = 10; n <= 100; n = n + 10) {
		int V2 = n;
		int E2 = 4 * n;
		//int V4 = n;
		//int E4 = 4 * n;
		Graph* graph2 = createGraph(V2, E2);
		//Graph* graph4 = createGraph(V4, E4);
		for (int i = 0; i < 4 * n; i++) {
			graph2->edge[i].src = n - 10;
			graph2->edge[i].dest = (rand() % n) + 1;
			//graph2->edge[i].dest = v_dest[i];
			graph2->edge[i].weight = (rand() % 4 * n) + 1;
			//graph2->edge[i].weight = v_weight[i];
			
			//graph4->edge[i].src = 0;
			//graph4->edge[i].dest = (rand() % n) + 1;
			//graph4->edge[i].weight = (rand() % 4 * n) + 1;
			
		}
		KruskalMST(graph2);
		//KruskalMSTqs(graph4, n);
	}
	*/


	// ----------------------------------------------------------
	// ----------------------------------------------------------
	fflush(stdin);
	for (int n = 100; n <= 10000; n = n + 100) {
		int V3 = n;
		int E3 = 4*n;
		//int V4 = n;
		//int E4 = 4 * n;
		Graph* graph3 = createGraph(V3, E3);
		//Graph* graph4 = createGraph(V4, E4);
		for (int i = 0; i < 4*n; i++) {
			graph3->edge[i].src = n-100;
			//graph3->edge[i].dest = n;
			graph3->edge[i].dest = (rand() % n)+1;
			graph3->edge[i].weight = (rand() % 4*n)+1;
			/*
			graph4->edge[i].src = 0;
			graph4->edge[i].dest = (rand() % n) + 1;
			graph4->edge[i].weight = (rand() % 4 * n) + 1;
			*/
		}
		KruskalMSThs(graph3, n);
		//KruskalMSTqs(graph4, n);
	}
	

	//profiler.addSeries("Tot op qs", "Comparatii quick", "Atribuiri quick");
	profiler.addSeries("Tot op hs", "Comparatii hs", "Atribuiri hs");
	profiler.createGroup("Total operatii", "Tot op hs");
	profiler.showReport();


	return 0;
}

