#include <iostream>
#include <stdio.h>
#include <stdlib.h>
//#include <iomanip>
#include "Profiler.h"
Profiler profiler("tema5");
#include <math.h>
using namespace std;


typedef struct cell {
	int key;
	int status;
} Cell;


enum { FREE, OCCUPIED };


void afisare_normala(Cell* T, int m) {
	//printf("\n\nTabela de dispersie este \n");
	cout << endl << endl << "Tabela de dispersie este:" << endl;
	for (int i = 0; i < m; i++) {
		if (T[i].status == OCCUPIED)
			//printf("T[%d]=%d\n", i, T[i].key);
			cout << "T[" << i << "] = " << T[i].key << endl;
		else
			//printf("T[%d]= --\n", i);
			cout << "T[" << i << "] = " << "--" << endl;
	}
	cout << endl;
}


void set_table_free(Cell* T, int m) {
	for (int i = 0; i < m; i++) {
		T[i].status = FREE;
	}
}


int h_prime(int k, int m) {
	return 0;
}


int linear_probing(int k, int m, int i) {
	return (k + i) % m;
}


int quadratic_probing(int k, int m, int i) {
	return (k + i + (i * i)) % m;
}


int double_hashing(int k, int m, int i) {
	int power = 1;
	for (int j = 1; j <= k; j++)
		power = power * 2;
	return (k + i * power) % m;
}



void insert_key(int k, Cell* T, int m, int (*hash_func)(int k, int m, int i)) {
	/**
	int i = 0;
	while (i != m) {
		int h = hash_func(k, m, i);
		if (T[h].status == FREE) {
			T[h].key = k;
			T[h].status = OCCUPIED;
			break;
		}
		i++;
	}
	*/


	for (int i = 0; i < m; i++) {
		int h = hash_func(k, m, i);
		if (T[h].status == FREE) {
			T[h].key = k;
			T[h].status = OCCUPIED;
			return;
		}
	}

}



Cell search_key(int k, Cell* T, int m, int (*hash_func)(int k, int m, int i)) {

	Cell x;
	x.key = 0;

	while (x.key != m) {
		int j = hash_func(k, m, x.key);
		if (T[j].status == FREE) {
			x.status = -1;
			return x;
		}
		if (T[j].key == k) {
			x.status = j;
			return x;
		}
		else
			x.key++;
	}
	x.status = -1;
	return x;



	/**
	for (int i = 0; i < m; i++) {
		int h = hash_func(k, m, i);
		if (T[h].key == k) {
			return h;   /// este pe pozitia h
		}
	}
	return -1;   /// inseamna ca nu este in tabela
	*/
}




int main() {

	int n = 10007, nr_elem_inserate;
	Cell nr;
	//int v_alfa[5] = { 80, 85, 90, 95, 99 };
	float v_alfa[5] = { 0.8, 0.85, 0.9, 0.95, 0.99 };
	//int v1[20009];
	int de_cate_ori_gasit = 0, de_cate_ori_negasit = 0;
	double mediu_gasit = 0, mediu_negasit = 0;
	int maxim_gasit = 0, maxim_negasit = 0;

	cout << "Fact Umpl | Ef med gas | Ef max gas | ";
	cout << "Ef med negas | Ef max negas |" << endl;
	cout << "-------------------------------------------------------------------" << endl;

	for (int i = 0; i < 5; i++) {

		Cell T1[20009] = { 0 };
		//set_table_free(T1, 20009);
		int v1[20009];
		//nr_elem_inserate = n * v_alfa[i] / 100;
		nr_elem_inserate = n * v_alfa[i];
		FillRandomArray(v1, nr_elem_inserate + 3000, 10, 500000, false, 0);
		for (int j = 0; j < nr_elem_inserate; j++) {
			insert_key(v1[j], T1, n, quadratic_probing);
		}
		for (int contor = nr_elem_inserate - 1500; contor < nr_elem_inserate + 1500; contor++) {
			nr = search_key(v1[contor], T1, n, quadratic_probing);
			if (nr.status == -1) {
				mediu_negasit = nr.key + mediu_negasit;
				de_cate_ori_negasit++;
				if (nr.key > maxim_negasit)
					maxim_negasit = nr.key;
			}

			else {
				mediu_gasit = nr.key + mediu_gasit;
				de_cate_ori_gasit++;
				if (nr.key > maxim_gasit)
					maxim_gasit = nr.key;
			}
		}

		
		printf("%0.2f", v_alfa[i]);
		cout << "      |";
		//printf("%0.4f", mediu_gasit/gasit);
		cout << mediu_gasit/ de_cate_ori_gasit;
		cout << "     |";
		printf("%d", maxim_gasit);
		cout << "          |";
		//printf("%0.4f", mediu_negasit/negasit);
		cout << mediu_negasit/ de_cate_ori_negasit;
		cout << "       |";
		printf("%d", maxim_negasit);
		cout<<"            |"<< endl;
		cout << "-------------------------------------------------------------------" << endl;
		
	}
	cout << endl;




	/**
	int m = 7;
	Cell* T = (Cell*)malloc(m * sizeof(Cell));
	int v[] = { 19, 36, 5, 21, 4, 26, 14, 17 };

	///test quadratic probing
	set_table_free(T, m);
	for (int i = 0; i < sizeof(v) / sizeof(int); i++)
		insert_key(v[i], T, m, quadratic_probing);
	afisare_normala(T, m);
	//int gasire = search_key(36, T, m, quadratic_probing);
	Cell gasire = search_key(36, T, m, quadratic_probing);
	cout << endl;
	cout << gasire.status << endl;
	//k + i + i*i mod m: 5, 36, 21, 26, 4, 19, 14
	*/


	///test linear probing
	/*
	set_table_free(T, m);
	for(int i=0; i<sizeof(v)/sizeof(int); i++)
		insert_key(v[i], T, m, linear_probing);
	afisare_normala(T, m);
	//21, 36, 26, 14, 4, 19, 5
	*/


	/*
	///test double hashing
	set_table_free(T, m);
	for(int i=0; i<sizeof(v)/sizeof(int); i++)
		insert_key(v[i], T, m, double_hashing);
	afisare_normala(T, m);
	//k + i*2^k mod m: 21, 36, 5, 14, 4, 19, 26
	*/


	//free(T);

	return 0;
}
