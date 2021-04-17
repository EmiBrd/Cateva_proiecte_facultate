#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <cstdlib>
#include <time.h>
#include <queue>
#include <list>
#include "Profiler.h"
Profiler p("tema9");
#include "bfs.h"
using namespace std;
#define MAX 10000


void rev(Node* a[], int n, Node* b[]) {
	for (int i = 0; i < n; i++) {
		b[i] = a[n - i - 1];
	}
}

Node* alocate_r(int key) {
	Node* p = (Node*)malloc(sizeof(Node));
	p->parent = NULL;
	p->position.row = key;
	return p;
}

Node* alocate_c(int key) {
	Node* p = (Node*)malloc(sizeof(Node));
	p->parent = NULL;
	p->position.col = key;
	return p;
}

void insert_first_r(Node** first, int key) {
	Node* p = alocate_r(key);
	if ((*first) == NULL)
		(*first) = p;
	else {
		p->parent = (*first);
		(*first) = p;
	}
}

void insert_first_c(Node** first, int key) {
	Node* p = alocate_c(key);
	if ((*first) == NULL)
		(*first) = p;
	else {
		p->parent = (*first);
		(*first) = p;
	}
}



typedef struct mway {
	int key, length;
	mway* v_children[MAX];
}mway;


mway* NewNode(int givenkey) {
	mway* p = (mway*)malloc(sizeof(mway));
	p->key = givenkey;
	p->length = 0;
	return(p);
}


mway* t1(int n, int* v) {
	mway** root = (mway**)malloc(n * sizeof(mway));
	mway* v_index[MAX];
	int poz = -1;

	for (int i = 0; i < n; i++) {
		v_index[i] = NewNode(i);
	}

	for (int i = 0; i < n; i++) {
		root[i] = (mway*)malloc(sizeof(mway));
		if (v[i] != -1) {
			root[i] = v_index[v[i]];
			root[i]->v_children[root[i]->length] = v_index[i];
			root[i]->length++;
		}
		else {
			root[i] = v_index[i];
			poz = i;
		}
	}
	return root[poz];
}
// -------------------------------------------------------------------------------------

void pretty_print(mway* root, int k, Point* repr) {
	if (root) {
		for (int i = 0; i < k; i++) {
			cout << "        ";
		}
		cout << "(" << repr[root->key].row << ", " << repr[root->key].col << ")" << endl << endl;
		for (int i = 0; i < root->length; i++) {
			pretty_print(root->v_children[i], k + 1, repr);
		}
	}
}
// --------------------------------------------------------------------------------------


int get_neighbors(const Grid* grid, Point p, Point neighb[])
{
	// TODO: fill the array neighb with the neighbors of the point p and return the number of neighbors
	// the point p will have at most 4 neighbors (up, down, left, right)
	// avoid the neighbors that are outside the grid limits or fall into a wall
	// note: the size of the array neighb is guaranteed to be at least 4

	int count = 0;
	if (grid->mat[p.row - 1][p.col] == 0) {
		neighb[count].row = p.row - 1;
		neighb[count].col = p.col;
		count++;
	}
	if (grid->mat[p.row + 1][p.col] == 0) {
		neighb[count].row = p.row + 1;
		neighb[count].col = p.col;
		count++;
	}
	if (grid->mat[p.row][p.col - 1] == 0) {
		neighb[count].row = p.row;
		neighb[count].col = p.col - 1;
		count++;
	}
	if (grid->mat[p.row][p.col + 1] == 0) {
		neighb[count].row = p.row;
		neighb[count].col = p.col + 1;
		count++;
	}

	return count;

}

void grid_to_graph(const Grid* grid, Graph* graph)
{
	//we need to keep the nodes in a matrix, so we can easily refer to a position in the grid
	Node* nodes[MAX_ROWS][MAX_COLS];
	int i, j, k;
	Point neighb[4];

	//compute how many nodes we have and allocate each node
	graph->nrNodes = 0;
	for (i = 0; i < grid->rows; ++i) {
		for (j = 0; j < grid->cols; ++j) {
			if (grid->mat[i][j] == 0) {
				nodes[i][j] = (Node*)malloc(sizeof(Node));
				memset(nodes[i][j], 0, sizeof(Node)); //initialize all fields with 0/NULL
				nodes[i][j]->position.row = i;
				nodes[i][j]->position.col = j;
				++graph->nrNodes;
			}
			else {
				nodes[i][j] = NULL;
			}
		}
	}
	graph->v = (Node**)malloc(graph->nrNodes * sizeof(Node*));
	k = 0;
	for (i = 0; i < grid->rows; ++i) {
		for (j = 0; j < grid->cols; ++j) {
			if (nodes[i][j] != NULL) {
				graph->v[k++] = nodes[i][j];
			}
		}
	}

	//compute the adjacency list for each node
	for (i = 0; i < graph->nrNodes; ++i) {
		graph->v[i]->adjSize = get_neighbors(grid, graph->v[i]->position, neighb);
		if (graph->v[i]->adjSize != 0) {
			graph->v[i]->adj = (Node**)malloc(graph->v[i]->adjSize * sizeof(Node*));
			k = 0;
			for (j = 0; j < graph->v[i]->adjSize; ++j) {
				if (neighb[j].row >= 0 && neighb[j].row < grid->rows &&
					neighb[j].col >= 0 && neighb[j].col < grid->cols &&
					grid->mat[neighb[j].row][neighb[j].col] == 0) {
					graph->v[i]->adj[k++] = nodes[neighb[j].row][neighb[j].col];
				}
			}
			if (k < graph->v[i]->adjSize) {
				//get_neighbors returned some invalid neighbors
				graph->v[i]->adjSize = k;
				graph->v[i]->adj = (Node**)realloc(graph->v[i]->adj, k * sizeof(Node*));
			}
		}
	}
}

void free_graph(Graph* graph)
{
	if (graph->v != NULL) {
		for (int i = 0; i < graph->nrNodes; ++i) {
			if (graph->v[i] != NULL) {
				if (graph->v[i]->adj != NULL) {
					free(graph->v[i]->adj);
					graph->v[i]->adj = NULL;
				}
				graph->v[i]->adjSize = 0;
				free(graph->v[i]);
				graph->v[i] = NULL;
			}
		}
		free(graph->v);
		graph->v = NULL;
	}
	graph->nrNodes = 0;
}

void bfs(Graph* graph, Node* s, Operation* op)
{
	// TOOD: implement the BFS algorithm on the graph, starting from the node s
	// at the end of the algorithm, every node reachable from s should have the color BLACK
	// for all the visited nodes, the minimum distance from s (dist) and the parent in the BFS tree should be set
	// for counting the number of operations, the optional op parameter is received
	// since op can be NULL (when we are calling the bfs for display purposes), you should check it before counting:
	// if(op != NULL) op->count();


	//if (op != NULL) op->count();
	for (int w = 0; w < graph->nrNodes; w++) {
		if (op != NULL) op->count();
		if (graph->v[w] == s) {
			graph->v[w]->color = COLOR_BLACK;
			if (op != NULL) op->count();
			break;
		}
		//if (op != NULL) op->count();
	}

	list<Node*> coada;
	coada.push_back(s);

	if (op != NULL) op->count();
	while (!coada.empty()) {
		if (op != NULL) op->count();
		Node* u = coada.front();
		coada.pop_front();

		for (int w = 0; w < u->adjSize; w++) {
			if (op != NULL) op->count();
			if (u->adj[w]->color == COLOR_WHITE) {
				u->adj[w]->color = COLOR_GRAY;
				if (op != NULL) op->count();
				//graph->v[w]->color = COLOR_GRAY;
				u->adj[w]->dist = u->dist + 1;
				if (op != NULL) op->count();
				u->adj[w]->parent = u;
				if (op != NULL) op->count();
				coada.push_back(u->adj[w]);
			}
		}

		u->color = COLOR_BLACK;
		if (op != NULL) op->count();
	}

}


void bfss(Graph* graph, Node* s){
	
	for (int w = 0; w < graph->nrNodes; w++) {
		if (graph->v[w] == s) {
			graph->v[w]->color = COLOR_BLACK;
			break;
		}
	}

	list<Node*> coada;
	coada.push_back(s);

	while (!coada.empty()) {
		Node* u = coada.front();
		coada.pop_front();

		for (int w = 0; w < u->adjSize; w++) {
			if (u->adj[w]->color == COLOR_WHITE) {
				u->adj[w]->color = COLOR_GRAY;
				//graph->v[w]->color = COLOR_GRAY;
				u->adj[w]->dist = u->dist + 1;
				u->adj[w]->parent = u;
				coada.push_back(u->adj[w]);
			}
		}
		u->color = COLOR_BLACK;
	}

}


void print_bfs_tree(Graph* graph)
{
	//first, we will represent the BFS tree as a parent array
	int n = 0; //the number of nodes
	int* p = NULL; //the parent array
	Point* repr = NULL; //the representation for each element in p

	//some of the nodes in graph->v may not have been reached by BFS
	//p and repr will contain only the reachable nodes
	int* transf = (int*)malloc(graph->nrNodes * sizeof(int));
	for (int i = 0; i < graph->nrNodes; ++i) {
		if (graph->v[i]->color == COLOR_BLACK) {
			transf[i] = n;
			++n;
		}
		else {
			transf[i] = -1;
		}
	}
	if (n == 0) {
		//no BFS tree
		free(transf);
		return;
	}

	int err = 0;
	p = (int*)malloc(n * sizeof(int));
	repr = (Point*)malloc(n * sizeof(Node));
	for (int i = 0; i < graph->nrNodes && !err; ++i) {
		if (graph->v[i]->color == COLOR_BLACK) {
			if (transf[i] < 0 || transf[i] >= n) {
				err = 1;
			}
			else {
				repr[transf[i]] = graph->v[i]->position;
				if (graph->v[i]->parent == NULL) {
					p[transf[i]] = -1;
				}
				else {
					err = 1;
					for (int j = 0; j < graph->nrNodes; ++j) {
						if (graph->v[i]->parent == graph->v[j]) {
							if (transf[j] >= 0 && transf[j] < n) {
								p[transf[i]] = transf[j];
								err = 0;
							}
							break;
						}
					}
				}
			}
		}
	}
	free(transf);
	transf = NULL;

	if (!err) {
		// TODO: pretty print the BFS tree
		// the parrent array is p (p[k] is the parent for node k or -1 if k is the root)
		// when printing the node k, print repr[k] (it contains the row and column for that point)
		// you can adapt the code for transforming and printing multi-way trees from the previous labs

		mway* a = t1(n, p);
		pretty_print(a, 0, repr);
	}



	if (p != NULL) {
		free(p);
		p = NULL;
	}
	if (repr != NULL) {
		free(repr);
		repr = NULL;
	}
}

// -------------------------------------------------------------------------------
// -------------------------------------------------------------------------------


int shortest_path(Graph* graph, Node* start, Node* end, Node* path[])
{
	// TODO: compute the shortest path between the nodes start and end in the given graph
	// the nodes from the path, should be filled, in order, in the array path
	// the number of nodes filled in the path array should be returned
	// if end is not reachable from start, return -1
	// note: the size of the array path is guaranteed to be at least 1000


	int counter = 0, i = 0, copie = 0, copie2 = 0;
	bfss(graph, start);
	Node** pathh = (Node**)calloc(graph->nrNodes, sizeof(Node));
	Node* q = end;
	while (q != start) {
		q = q->parent;
		path[counter] = q;
		pathh[counter] = path[counter];
		counter++;
	}
	copie = counter, copie2 = counter;
	//reverse(path, path+counter);
	rev(pathh, copie2, path);

	free(pathh);
	return counter;

}

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------



void performance()
{
	int n, i;
	//Profiler p("bfs");
	srand(time(NULL));
	int vv = 0, uu = 0, vvv = 0, uuu = 0, vv1, uu1;
	int g = 0, h = 0, f = 0, e = 0;
	int v_vv[4500], v_uu[4500], v_vvv[4500], v_uuu[4500];
	FillRandomArray(v_vv, 4500, 0, 4500, false, 0);
	FillRandomArray(v_uu, 4500, 0, 4500, false, 0);
	FillRandomArray(v_vvv, 4500, 0, 4500, false, 0);
	FillRandomArray(v_uuu, 4500, 0, 4500, false, 0);


	// vary the number of edges
	for (n = 1000; n <= 4500; n+= 100) {
		Operation op = p.createOperation("bfs-edges", n);
		Graph graph;
		graph.nrNodes = 100;
		//initialize the nodes of the graph
		graph.v = (Node**)malloc(graph.nrNodes * sizeof(Node*));
		for (i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
			memset(graph.v[i], 0, sizeof(Node));
		}

		// TODO: generate n random edges
		// make sure the generated graph is connected
		for (e = 0; e < 100; e++) {
			graph.v[e]->adjSize = n / 50;
			graph.v[e]->adj = (Node**)malloc(graph.v[e]->adjSize * sizeof(Node*));
			for (f = 0; f < graph.v[e]->adjSize; f++) {
				graph.v[e]->adj[f] = graph.v[rand() % 100];
			}
		}
		bfs(&graph, graph.v[0], &op);
		

		/*
		for (int g = 0; g < graph.nrNodes; g++) {
			vv = rand() % n;
			uu = rand() % n;
			insert_first_r(&graph.v[vv], vv);
			insert_first_c(&graph.v[uu], uu);
		}
		bfs(&graph, graph.v[0], &op);
		*/
		free_graph(&graph);
	}




	// vary the number of vertices
	for (n = 100; n <= 200; n += 10) {
		Operation op = p.createOperation("bfs-vertices", n);
		Graph graph;
		graph.nrNodes = n;
		//initialize the nodes of the graph
		graph.v = (Node**)malloc(graph.nrNodes * sizeof(Node*));
		for (i = 0; i < graph.nrNodes; ++i) {
			graph.v[i] = (Node*)malloc(sizeof(Node));
			memset(graph.v[i], 0, sizeof(Node));
		}
		// TODO: generate 4500 random edges
		// make sure the generated graph is connected

		for (g = 0; g < graph.nrNodes; g++) {
			graph.v[g]->adjSize = 4499;
			graph.v[g]->adj = (Node**)malloc(graph.v[g]->adjSize * sizeof(Node*));
			for (h = 0; h < graph.v[g]->adjSize; h++) {
				graph.v[g]->adj[h] = graph.v[rand() % n];
			}
		}
		bfs(&graph, graph.v[0], &op);
		

		/*
		for (int g = 0; g < 4500; g++) {
			vvv = rand() % n;
			uuu = rand() % n;
			insert_first_r(&graph.v[vvv], uuu);
			insert_first_c(&graph.v[uuu], vvv);
			bfs(&graph, graph.v[0], &op);
		}
		bfs(&graph, graph.v[0], &op);
		*/
		
		free_graph(&graph);
	}


	p.showReport();
}
