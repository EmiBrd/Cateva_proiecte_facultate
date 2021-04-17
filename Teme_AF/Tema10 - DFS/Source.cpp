#include <iostream>
#include<stdio.h>
#include <stdlib.h>
#include <string.h>
#include <cstdlib>
#include <time.h>
#include "Profiler.h"
Profiler p("tema10");
#include <stack> 
using namespace std;
#define MAX 10000


int timee = 0;
int nr = 1;

enum Color {
	WHITE, GRAY, BLACK     // WHITE = 0, GRAY = 1, BLACK = 2
};

typedef struct node {
	int key;
	int DT;
	int FT;
	node* parent;
	node* next;
	Color color;
}node;

typedef struct{
	int V;
	int E;
	node* list[MAX];
}graph;

typedef struct {
	int key;
	int disc;
	int low;
	bool stackMember;
}tarjann;


void push(node** first, int val) { 
	node* el = (node*)malloc(sizeof(node));
	el->key = val;
	el->next = (*first);
	*(first) = el;
}

int pop(node** stiva){
	int n = -1;
	if (*stiva != NULL) {
		node* firstElement = (*stiva);
		*stiva = (*stiva)->next;
		n = firstElement->key;
		free(firstElement);
	}
	return n;
}


node* newNode(int givenkey) {
	node* p = (node*)malloc(sizeof(node));
	p->key = givenkey;
	p->color = WHITE;
	p->DT = 0;
	p->FT = 0;
	p->next = NULL;
	p->parent = NULL;
	return p;
}


graph* newGraph(int V, int E) {
	graph* G = (graph*)malloc(sizeof(graph));
	G->V = V;
	G->E = E;
	for (int i = 0; i < V; i++) {
		G->list[i] = NULL;
	}
	return G;
}


bool verif_muchie(graph* G, int a, int b) {
	node* p = G->list[a];
	while (p != NULL) {
		if (p->key == b)
			return false;
		p = p->next;
	}
	return true;
}


void addEdge(graph** G, int a, int b) {
	node* nod = newNode(b);
	if ((*G)->list[a] == NULL) {
		(*G)->list[a] = nod;
	}
	else {
		node* p_stanga = (*G)->list[a];
		node* q_dreapta = (*G)->list[a];
		while ((q_dreapta->next != NULL) && (q_dreapta->key < b)) {
			p_stanga = q_dreapta;
			q_dreapta = q_dreapta->next;
		}
		if ((p_stanga == (*G)->list[a]) && (p_stanga->key > b)) {
			(*G)->list[a] = nod;
			(*G)->list[a]->next = p_stanga;
		}

		else if (q_dreapta->next == NULL && q_dreapta->key < b) {
			q_dreapta->next = nod;
		}
		else {
			p_stanga->next = nod;
			nod->next = q_dreapta;
		}
	}
}


void DFS_Visit(graph* G, node* a, node** v_nod) {
	timee++;
	a->DT = timee;
	a->color = GRAY;

	node* adj = G->list[a->key];
	while (adj != NULL) {
		if (v_nod[adj->key]->color == WHITE) {
			v_nod[adj->key]->parent = a;
			DFS_Visit(G, v_nod[adj->key], v_nod);
		}
		adj = adj->next;
	}
	a->color = BLACK;
	timee++;
	a->FT = timee;
}

node** DFS(graph* G) {
	timee = 0;
	node** v_nod = (node**)malloc(G->V * sizeof(node*));

	for (int i = 0; i < G->V; i++) {
		v_nod[i] = newNode(i);
	}

	for (int i = 0; i < G->V; i++) {
		if (v_nod[i]->color == WHITE) {
			DFS_Visit(G, v_nod[i], v_nod);
		}
	}
	return v_nod;
}


void Topologic_Visit(graph* G, node* a, node** v_nod , node** L) {
	DFS_Visit(G, a, v_nod);
	push(L, a->key);
}

node** Topologic(graph* G, node** L) {
	timee = 0;
	node** v_nod = (node**)malloc(G->V * sizeof(node*));

	for (int i = 0; i < G->V; i++) {
		v_nod[i] = newNode(i);
	}

	for (int i = 0; i < G->V; i++) {
		if (v_nod[i]->color == WHITE) {
			Topologic_Visit(G, v_nod[i], v_nod, L);
		}
	}
	return v_nod;
}


// -------------------------------------------------------------
// -------------------------------------------------------------
void DFS_Visitpn(graph* G, node* a, node** v_nod, int m) {
	Operation operation = p.createOperation("Function of nodes", m);
	timee++;
	a->DT = timee;
	a->color = GRAY;
	operation.count(2);
	node* adj = G->list[a->key];
	operation.count();
	operation.count();
	while (adj != NULL) {
		//operation.count();
		if (v_nod[adj->key]->color == WHITE) {
			operation.count();
			v_nod[adj->key]->parent = a;
			DFS_Visitpn(G, v_nod[adj->key], v_nod, m);
		}
		//operation.count();
		adj = adj->next;
		operation.count();
		operation.count();
	}
	
	a->color = BLACK;
	timee++;
	a->FT = timee;
}

node** DFSpn(graph* G, int m) {
	Operation operation = p.createOperation("Function of nodes", m);
	timee = 0;
	node** v_nod = (node**)malloc(G->V * sizeof(node*));

	for (int i = 0; i < G->V; i++) {
		operation.count(4);
		v_nod[i] = newNode(i);
	}

	for (int i = 0; i < G->V; i++) {
		operation.count();
		if (v_nod[i]->color == WHITE) {
			DFS_Visitpn(G, v_nod[i], v_nod, m);
		}
	}
	return v_nod;
}
// -------------------------------------------------------------
// -------------------------------------------------------------


void DFS_Visitpe(graph* G, node* a, node** v_nod, int m) {
	Operation operation = p.createOperation("Function of edges", m);
	timee++;
	a->DT = timee;
	a->color = GRAY;
	operation.count(2);
	node* adj = G->list[a->key];
	operation.count();
	operation.count();
	while (adj != NULL) {
		//operation.count();
		if (v_nod[adj->key]->color == WHITE) {
			operation.count();
			v_nod[adj->key]->parent = a;
			DFS_Visitpe(G, v_nod[adj->key], v_nod, m);
		}
		//operation.count();
		adj = adj->next;
		operation.count();
		operation.count();
	}
	//operation.count();
	a->color = BLACK;
	timee++;
	a->FT = timee;
}

node** DFSpe(graph* G, int m) {
	Operation operation = p.createOperation("Function of edges", m);
	timee = 0;
	node** v_nod = (node**)malloc(G->V * sizeof(node*));

	for (int i = 0; i < G->V; i++) {
		operation.count(4);
		v_nod[i] = newNode(i);
	}

	for (int i = 0; i < G->V; i++) {
		//operation.count();
		if (v_nod[i]->color == WHITE) {
			DFS_Visitpe(G, v_nod[i], v_nod, m);
		}
	}
	return v_nod;
}


graph* generateGraph(int V, int E){
	graph* G = newGraph(V, E);
	int maxE = (V * (V - 1)) / 2;

	if (V <= 0){
		cout << endl << "Nu sunt noduri" << endl;
		return NULL;
	}
	else if (E > maxE){
		cout << endl << "Nr max de muchii depasit" << endl;
		return NULL;
	}
	else{
		for (int i = 0; i < E; i++){
			int u = rand() % (V - 1) + 1;
			int v = rand() % (V - 1) + 1;

			while (verif_muchie(G, u, v) == false){
				u = rand() % (V - 1) + 1;
				v = rand() % (V - 1) + 1;
			}
			addEdge(&G, u, v);
		}
	}
	return G;
}


void Afis_noduri(node** v, int size){
	if (v == NULL){
		cout << endl << "-" << endl;
		return;
	}

	for (int i = 0; i < size; i++){
		if (v[i] == NULL) {
			cout << " -" << endl;
		}			
				
		else{
			cout << "Nr: " << v[i]->key <<"\t   "<< "C: " << v[i]->color;
			cout << "\t   " << "DT: " << v[i]->DT << "\t" << "FT: " << v[i]->FT << endl;
		}
	}
}


int minimm(int a, int b){
	if (a >= b)
		return b;
	return a;
}

// --------------------------------------------------------------------------
// --------------------------------------------------------------------------
void Afis_tarjan(tarjann* t){
	if (t == NULL){
		cout << " - ";
		return;
	}
	else{
		cout << t->key<<"  ";
		cout << "(" << t->disc - 1 << ", " << t->low - 1 << ")" << endl;
	}
}


int timeee = 0;
void SSCUtil(graph* G, tarjann* a, tarjann** v_trjn, stack <int>* st){
	//timee = 0;
	a->disc = a->low = ++timeee;
	st->push(a->key);
	a->stackMember = true;

	node* adj = G->list[a->key];
	while (adj != NULL){
		tarjann* b = v_trjn[adj->key];
		if (b->disc == NULL){
			SSCUtil(G, b, v_trjn, st);
			a->low = minimm(a->low, b->low);
		}
		else if (b->stackMember == true){
			a->low = minimm(a->low, b->disc);
		}
		adj = adj->next;
	}

	int w = 0;
	node* l = NULL;
	if (a->low == a->disc){
		while (st->top() != a->key) {
			w = st->top();
			v_trjn[w]->stackMember = false;
			push(&l, w);
			st->pop();
		}
		w = st->top();
		v_trjn[w]->stackMember = false;
		push(&l, w);
		st->pop();


		cout << endl << "SCC "<<nr <<": "<< endl;
		//nr = nr+1;
		while (l != NULL){
			Afis_tarjan(v_trjn[l->key]);
			l = l->next;
		}
		nr = nr + 1;
	}
}

void tarjan(graph* G){
	stack <int> st;
	tarjann** v_trjn = (tarjann**)malloc(G->V * sizeof(tarjann*));

	for (int i = 0; i < G->V; i++){
		v_trjn[i] = (tarjann*)malloc(sizeof(tarjann));
		v_trjn[i]->key = i;
		v_trjn[i]->disc = NULL;
		v_trjn[i]->low = NULL;
		v_trjn[i]->stackMember = false;
	}
	for (int j = 0; j < G->V; j++){
		if (v_trjn[j]->disc == NULL)
			SSCUtil(G, v_trjn[j], v_trjn, &st);
	}
}



int main()
{
	
	//  Graph random
	/*cout << "Graph random" << endl << endl;
	int noduri = 7;
	int muchii = 10;
	graph* G = generateGraph(noduri, muchii);
	node** dfs = DFS(G);
	Afis_noduri(dfs, noduri);
	cout << endl << endl;*/
	

	// DFS - Graph Geeksforgeeks Youtube
	cout << "Graph Geeksforgeeks Youtube" << endl;
	int noduri1 = 8;
	int muchii1 = 13;
	graph* G1 = newGraph(noduri1, muchii1);
	addEdge(&G1, 0, 1);
	addEdge(&G1, 1, 2);
	addEdge(&G1, 2, 0);
	addEdge(&G1, 3, 4);
	addEdge(&G1, 3, 7);
	addEdge(&G1, 4, 5);
	addEdge(&G1, 5, 0);
	addEdge(&G1, 5, 6);
	addEdge(&G1, 6, 0);
	addEdge(&G1, 6, 2);
	addEdge(&G1, 6, 4);
	addEdge(&G1, 7, 3);
	addEdge(&G1, 7, 5);
	node** dfs1 = DFS(G1);
	cout << endl;
	Afis_noduri(dfs1, noduri1);
	cout << endl << endl;



	// Exemplu Geeksforgeeks
	cout << "Exemplu Geeksforgeeks" << endl;
	int noduri7 = 4;
	int muchii7 = 6;
	graph* G7 = newGraph(noduri7, muchii7);
	addEdge(&G7, 0, 1);
	addEdge(&G7, 0, 2);
	addEdge(&G7, 1, 2);
	addEdge(&G7, 2, 0);
	addEdge(&G7, 2, 3);
	addEdge(&G7, 3, 3);
	node** dfs7 = DFS(G7);
	cout << endl;
	Afis_noduri(dfs7, noduri7);
	cout << endl << endl;




	// Sortare_Topologica - Graph Geeksforgeeks Youtube
	cout << "Sortare_Topologica - Graph Geeksforgeeks Youtube" << endl;
	int noduri2 = 8;
	int muchii2 = 13;
	graph* G2 = newGraph(noduri2, muchii2);
	addEdge(&G2, 0, 1);
	addEdge(&G2, 1, 2);
	addEdge(&G2, 2, 0);
	addEdge(&G2, 3, 4);
	addEdge(&G2, 3, 7);
	addEdge(&G2, 4, 5);
	addEdge(&G2, 5, 0);
	addEdge(&G2, 5, 6);
	addEdge(&G2, 6, 0);
	addEdge(&G2, 6, 2);
	addEdge(&G2, 6, 4);
	addEdge(&G2, 7, 3);
	addEdge(&G2, 7, 5);
	node* L2 = NULL;
	node** dfs2 = Topologic(G2, &L2);
	cout << endl;
	Afis_noduri(dfs2, noduri2);
	cout << endl << endl;

	

	// Tarjan - Graph Geeksforgeeks Youtube
	/*cout<<"Tarjan - Graph Geeksforgeeks Youtube"<<endl;
	int noduri3 = 8;
	int muchii3 = 13;
	graph* G3 = newGraph(noduri3, muchii3);
	addEdge(&G3, 0, 1);
	addEdge(&G3, 1, 2);
	addEdge(&G3, 2, 0);
	addEdge(&G3, 3, 4);
	addEdge(&G3, 3, 7);
	addEdge(&G3, 4, 5);
	addEdge(&G3, 5, 0);
	addEdge(&G3, 5, 6);
	addEdge(&G3, 6, 0);
	addEdge(&G3, 6, 2);
	addEdge(&G3, 6, 4);
	addEdge(&G3, 7, 3);
	addEdge(&G3, 7, 5);
	tarjan(G3);
	cout << endl << endl;*/
	

	

	// Tarjan - Graph Wikipedia
	cout << "Tarjan - Graph Wikipedia" << endl;
	int noduri4 = 8;
	int muchii4 = 14;
	graph* G4 = newGraph(noduri4, muchii4);
	addEdge(&G4, 0, 1);
	addEdge(&G4, 1, 2);
	addEdge(&G4, 2, 0);
	addEdge(&G4, 3, 1);
	addEdge(&G4, 3, 2);
	addEdge(&G4, 3, 4);
	addEdge(&G4, 4, 3);
	addEdge(&G4, 4, 5);
	addEdge(&G4, 5, 2);
	addEdge(&G4, 5, 6);
	addEdge(&G4, 6, 5);
	addEdge(&G4, 7, 4);
	addEdge(&G4, 7, 6);
	addEdge(&G4, 7, 7);
	tarjan(G4);
	cout << endl << endl;
	

	
	// Generare grafice
	int V = 100;
	int E = 4500;
	int v, e;
	for (e = 1000; e <= E; e = e + 100) {
		graph* G5 = generateGraph(V, e);
		node** dfs5 = DFSpe(G5, e);
	}

	for (v = 100; v <= 200; v = v + 10) {
		graph* G6 = generateGraph(v, E);
		node** dfs = DFSpn(G6, v);
	}

	p.showReport();	
	

	return 0;
}
