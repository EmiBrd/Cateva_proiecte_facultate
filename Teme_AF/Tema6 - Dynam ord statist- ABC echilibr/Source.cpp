#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <cstdlib>
#include <time.h>
#include "Profiler.h"
Profiler profiler("tema6");
using namespace std;
#define NR_ELEM 10000
#define COUNT 10



typedef struct node {
	int key, size;
	node* left, * right;
}node;



node* newNode(int givenkey) {
	node* p = (node*)malloc(sizeof(node));
	p->key = givenkey;
	p->left = NULL;
	p->right = NULL;
	return(p);
}


node* creare_arb_seminar(int low, int high, int m, int* v) {
	Operation atribuiri = profiler.createOperation("Atribuiri insert", m);
	Operation comparatii = profiler.createOperation("Comparatii insert", m);

	comparatii.count();
	if (low > high) {
		return NULL;
	}
	int mid = (low + high) / 2;
	node* p = newNode(v[mid]);
	p->size = high - low + 1;
	atribuiri.count(2);

	p->left = creare_arb_seminar(low, mid - 1, m, v);
	atribuiri.count();
	p->right = creare_arb_seminar(mid + 1, high, m, v);
	atribuiri.count();

	return p;
}


int rank_tree(node* root) {
	if ((root) == NULL)
		return 0;
	else
		return 1 + rank_tree((root)->left) + rank_tree((root)->right);
}


node* os_select(node* root, int givenKey, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri select", m);
	Operation comparatii = profiler.createOperation("Comparatii select", m);

	int r = rank_tree((root)->left) + 1;
	atribuiri.count();

	comparatii.count();
	if (givenKey == r)
		return (root);

	else if (givenKey < r) {
		comparatii.count();
		return os_select((root)->left, givenKey, m);
	}

	else {
		return os_select((root)->right, givenKey - r, m);
	}
}


node* searchKey(node* root, int key) {
	if (root == NULL || root->key == key)
		return root;
	if (key < root->key)
		return searchKey(root->left, key);
	else
		return searchKey(root->right, key);
}


node* find_min(node* root) {
	node* p = root;
	while (p && p->left != NULL) {
		p = p->left;
	}
	return p;
}


node* find_minimum(node* root) {
	if (root == NULL)
		return NULL;
	else if (root->left != NULL)
		return find_minimum(root->left);
	else
		return root;
}


node* find_maxim(node* root) {
	if (root != NULL) {
		if (root->right != NULL)
			return find_maxim(root->right);

		else
			return root;
	}
	else {
		return NULL;
	}
}


node* os_delete(node* root, int givenkey, int m) {
	Operation atribuiri = profiler.createOperation("Atribuiri delete", m);
	Operation comparatii = profiler.createOperation("Comparatii delete", m);

	node* p;
	comparatii.count();
	if (root == NULL) {
		return NULL;
	}

	root->size = root->size - 1;;
	atribuiri.count();

	comparatii.count();
	if (root->key > givenkey) {
		root->left = os_delete(root->left, givenkey, m);
		atribuiri.count();
	}

	else if (root->key < givenkey) {
		comparatii.count();
		root->right = os_delete(root->right, givenkey, m);
		atribuiri.count();
	}

	else {
		comparatii.count();
		if (root->left == NULL) {
			p = root->right;
			atribuiri.count();
			free(root);
			return p;
		}

		else if (root->right == NULL) {
			comparatii.count();
			p = root->left;
			atribuiri.count();
			free(root);
			return p;
		}

		//node* r = find_minimum(root->right);
		node* r = find_min(root->right);
		root->key = r->key;
		root->right = os_delete(root->right, r->key, m);
		atribuiri.count(3);
	}
	return root;
}


void print2DUtil(node* root, int space) {
	if (root == NULL)
		return;

	space += COUNT;
	print2DUtil(root->right, space);
	cout << endl;
	for (int i = COUNT; i < space; i++)
		cout << " ";

	cout << root->key << endl;
	print2DUtil(root->left, space);
}

void print2D(node* root) {
	print2DUtil(root, 0);
}


void inordine(node* root) {
	if (root == NULL)
		return;
	inordine(root->left);
	printf("%d ", root->key);
	inordine(root->right);

}

void preordine(node* root) {
	if (root == NULL)
		return;
	cout << root->key << " ";
	preordine(root->left);
	preordine(root->right);
}

void postordine(node* root) {
	if (root == NULL)
		return;
	postordine(root->left);
	postordine(root->right);
	cout << root->key << " ";
}



int main()
{
	
	int v[10001];
	cout << "Arbore:" << endl;
	FillRandomArray(v, 10000, 0, 10000, true, 1);
	node* root = creare_arb_seminar(0, 10, 10000, v);
	print2D(root);
	cout << endl << endl;
	cout << "Inordine:" << endl;
	inordine(root);
	cout << endl << "Preordine:" << endl;
	preordine(root);
	cout << endl << "Postordine:" << endl;
	postordine(root);
	cout << endl;

	int aux = os_select(root, 5, 10000)->key;
	root = os_delete(root, aux, 10000);
	aux = os_select(root, 6, 10000)->key;
	root = os_delete(root, aux, 10000);
	aux = os_select(root, 7, 10000)->key;
	root = os_delete(root, aux, 10000);
	print2D(root);
	cout << endl << endl;
	cout << "Inordine:" << endl;
	inordine(root);
	cout << endl << "Preordine:" << endl;
	preordine(root);
	cout << endl << "Postordine:" << endl;
	postordine(root);
	cout << endl;

	
	
	for (int j = 0; j < 5; j++) {
		for (int i = 100; i <= 10000; i = i + 100) {
			int a[10001];
			//int* a = (int*)malloc(i * sizeof(int));
			FillRandomArray(a, i, 1, i, true, 1);
			node* root = creare_arb_seminar(0, i - 1, i, a);
			int k = i;
			while (k > 1) {
				int sel = os_select(root, a[rand() % k], i)->key;
				root = os_delete(root, sel, i);
				k--;
			}
		}
	}



	profiler.createGroup("GR ATRIB OS", "Atribuiri select", "Atribuiri delete", "Atribuiri insert");
	profiler.createGroup("GR COMPAR OS", "Comparatii select", "Comparatii delete", "Comparatii insert");
	profiler.addSeries("Tot op insert", "Comparatii insert", "Atribuiri insert");
	profiler.addSeries("Tot op select", "Comparatii select", "Atribuiri select");
	profiler.addSeries("Tot op delete", "Comparatii delete", "Atribuiri delete");
	profiler.createGroup("Total operatii", "Tot op select", "Tot op delete", "Tot op insert");
	profiler.showReport();


	return 0;
}


