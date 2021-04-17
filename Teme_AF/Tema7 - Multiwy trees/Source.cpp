#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <cstdlib>
#include <time.h>
#include "Profiler.h"
Profiler profiler("tema7");
using namespace std;
#define MAX 10000
#define COUNT 10



typedef struct r2 {
	int key, length;
	struct r2* v_children[MAX], * urm;
}r2;


typedef struct r3 {
	int key;
	struct r3* left, * right;
}r3;


r2* NewNode(int givenkey) {
	r2* p = (r2*)malloc(sizeof(r2));
	p->key = givenkey;
	p->length = 0;
	p->urm = NULL;
	return(p);
}

/*
// Pentru indici de la 0
void index(r2* v_index[], int n) {
	for (int i = 0; i < n; i++) {
		v_index[i] = NewNode(i);
	}
}

r2* t1(r2* root, int n, int* v) {
	root = (r2*)malloc(sizeof(r2));
	root->urm = NULL;
	r2* roott = NULL;
	r2* v_index[MAX];

	index(v_index, n);

	for (int i = 0; i < n; i++) {
		if (v[i] != -1) {
			root = v_index[v[i]];
			root->v_children[root->length] = v_index[i];
			root->urm = v_index[i];
			root->length++;
		}
		else {
			roott = v_index[i];
		}
	}
	return roott;
}
*/


// Pentru indici de la 1
void index(r2* v_index[], int n) {
	for (int i = 0; i < n; i++) {
		v_index[i + 1] = NewNode(i + 1);
	}
}

// Pt indici de la 1
r2* t1(r2* root, int n, int* v) {
	//root = (r2*)malloc(sizeof(r2));
	//root->urm = NULL;
	r2* roott = NULL;
	r2* v_index[MAX];

	index(v_index, n);

	for (int i = 0; i < n; i++) {
		if (v[i] != -1) {
			root = v_index[v[i]];
			root->v_children[root->length] = v_index[i + 1];
			//root->urm = v_index[i + 1];
			root->length++;
		}
		else {
			roott = v_index[i + 1];
		}
	}
	return roott;
}


/*
// Pentru indici de la 0
void insert_last(r2** M, int poz, int givenkey) {
	r2* p = (r2*)malloc(sizeof(r2));
	p->key = givenkey;
	p->urm = NULL;
	r2* q = p;
	if (M[poz] == NULL) {
		M[poz] = p;
	}
	else {
		q = (r2*)malloc(sizeof(r2));
		q = M[poz];
		while (q->urm != NULL) {
			q = q->urm;
		}
		q->urm = p;
	}
}

void insereaza_in_vector(r2** M, int n, int* v) {
	for (int i = 0; i < n; i++) {
		if (v[i] != -1) {
			insert_last(M, v[i], i);
		}
	}
}

int Find_root(int* v, int n) {
	int index_root;
	for (int i = 0; i < n; i++) {
		if (v[i] == -1) {
			index_root = i;
			break;
		}
	}
	return index_root;
}

void Afisare_mway(r2** M, int n) {
	r2* p = (r2*)malloc(sizeof(r2));
	p->urm = NULL;
	for (int i = 0; i < n; i++) {
		cout << i << ": ";
		if (M[i]!=NULL) {
			p = M[i];
			while (p!=NULL) {
				cout << p->key << " | ";
				p = p->urm;
			}
		}
		cout << endl;
	}
	cout << endl;
}
*/


// Pentru indici de la 1
void insert_last(r2** M, int poz, int givenkey) {
	r2* p = (r2*)malloc(sizeof(r2));
	p->key = givenkey;
	p->urm = NULL;
	r2* q = p;
	if (M[poz] == NULL) {
		M[poz] = p;
	}
	else {
		q = (r2*)malloc(sizeof(r2));
		q = M[poz];
		while (q->urm != NULL) {
			q = q->urm;
		}
		q->urm = p;
	}
}

void insereaza_in_vector(r2** M, int n, int* v) {
	for (int i = 0; i < n; i++) {
		if (v[i] != -1) {
			insert_last(M, v[i], i + 1);
		}
	}
}

int Find_root(int* v, int n) {
	int index_root;
	for (int i = 0; i < n; i++) {
		if (v[i] == -1) {
			index_root = i + 1;
			break;
		}
	}
	return index_root;
}

void Afisare_mway(r2** M, int n) {
	r2* p = (r2*)malloc(sizeof(r2));
	p->urm = NULL;
	for (int i = 1; i <= n; i++) {
		cout << i << ": ";
		if (M[i] != NULL) {
			p = M[i];
			while (p != NULL) {
				cout << p->key << " | ";
				p = p->urm;
			}
		}
		cout << endl;
	}
	cout << endl;
}


void inordine(r3* root) {
	if (root == NULL)
		return;
	inordine(root->left);
	printf("%d ", root->key);
	inordine(root->right);
}

void preordine(r3* root) {
	if (root == NULL)
		return;
	cout << root->key << " ";
	preordine(root->left);
	preordine(root->right);
}

void postordine(r3* root) {
	if (root == NULL)
		return;
	postordine(root->left);
	postordine(root->right);
	cout << root->key << " ";
}



r3* t2(r2* root) {
	r3* p = (r3*)malloc(sizeof(r3));
	//p = NULL;
	//p->left = NULL;
	//p->right = NULL;
	r3* roott = (r3*)malloc(sizeof(r3));
	roott->key = root->key;
	roott->left = NULL;
	roott->right = NULL;

	if (root->length != 0) {
		p = t2(root->v_children[0]);
		roott->left = p;

		for (int i = 1; i < root->length; i++) {
			p->right = t2(root->v_children[i]);
			p = p->right;
		}
	}
	else {
		goto etich;
	}
etich:

	return roott;
}



void pretty_print_r1p(r3* root, int space) {
	if (root == NULL)
		return;
	space += 10;
	pretty_print_r1p(root->right, space);
	cout << endl;
	for (int i = 10; i < space; i++)
		cout << " ";
	cout << root->key << "\n";
	pretty_print_r1p(root->left, space);
}



void pretty_print_r1(int* v, int n, int niv, int val_root) {
	for (int i = 0; i < n; i++) {
		if (v[i] == val_root) {
			for (int j = 0; j < niv; j++) {
				printf("    ");
			}
			cout << i + 1 << endl << endl;
			pretty_print_r1(v, n, niv + 1, i + 1);
		}
	}
	//cout << endl;
}


void pretty_print_r2(r2* root, int niv) {
	for (int i = 0; i < niv; i++) {
		printf("    ");
	}
	cout << root->key << endl << endl;
	for (int j = 0; j < root->length; j++) {
		pretty_print_r2(root->v_children[j], niv + 1);
	}
	//cout << endl;
}


void pretty_print_r3(r3* root, int niv) {
	if (root != NULL) {
		for (int i = 0; i < niv; i++) {
			printf("    ");
		}
		cout << root->key << endl << endl;
		pretty_print_r3(root->left, niv + 1);
		//cout << root->key << endl << endl;
		pretty_print_r3(root->right, niv + 1);
	}
	//cout << endl;
}


int main()
{

	//int v[] = { 7, 1, 7, 1, 3, 1, -1, 3 };
	int v[] = { 2, 7, 5, 2, 7, 7, -1, 5, 2 };
	int n = sizeof(v) / sizeof(int);

	cout << "Indicii:" << endl;
	for (int i = 0; i < n; i++) {
		cout << i + 1 << " ";
	}
	cout << endl;
	cout << "Vectorul:" << endl;
	for (int i = 0; i < n; i++) {
		cout << v[i] << " ";
	}
	cout << endl << "Radacina este:" << endl;
	int b = Find_root(v, n);
	cout << b << endl;
	//cout << endl<<endl;

	cout << endl << "R1:" << endl << endl;
	pretty_print_r1(v, n, 0, -1);
	// ----------------------------------------------
	// ----------------------------------------------


	cout << endl << "R2:" << endl << endl;

	r2** M = (r2**)malloc((n + 1) * sizeof(r2*));
	for (int i = 0; i <= n; i++) {
		M[i] = (r2*)malloc((n + 1) * sizeof(r2));
		M[i] = 0;
	}

	insereaza_in_vector(M, n, v);
	cout << "Arbore mway:" << endl << endl;
	Afisare_mway(M, n);
	cout << endl;

	r2* root1 = NULL;
	r2* root2 = t1(root1, n, v);
	pretty_print_r2(root2, 0);
	// ------------------------------------------------
	// ------------------------------------------------



	cout << endl << "R3:" << endl << endl;
	cout << "Inordine:" << endl;
	r3* root3 = t2(root2);
	inordine(root3);
	cout << endl << "Preordine:" << endl;
	preordine(root3);
	cout << endl << "Postordine:" << endl;
	postordine(root3);
	cout << endl << endl;
	pretty_print_r3(root3, 0);



	return 0;
}