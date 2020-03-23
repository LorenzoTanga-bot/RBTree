package it.unicam.cs.asdl1920.mp2;

import java.util.ArrayList;
import java.util.List;

/**
 * Un RBTree, un albero rosso-nero, è un albero binario di ricerca con le
 * seguenti proprietà:
 * 
 * 1- Ciascun nodo è rosso o nero e la radice è sempre nera
 * 
 * 2- Ciascuna foglia NIL (cioè una foglia che non contiene una etichetta di
 * tipo E) è nera
 * 
 * 3- Se un nodo è rosso allora entrambi i figli sono neri
 * 
 * 4- Ogni cammino da un nodo ad una foglia sua discendente contiene lo stesso
 * numero di nodi neri (contando anche le foglie NIL)
 * 
 * Si può dimostrare che le operazioni di inserimento, ricerca e rimozione di un
 * valore in un RBTree hanno un costo O(lg n), dove n è il numeo di nodi
 * dell'albero. Ciò è dovuto al fatto che la cosiddetta altezza nera (cioè il
 * numero dei nodi neri incontrati in un cammino) viene mantenuta uguale per
 * tutti i cammini dalla radice alle foglie.
 * 
 * Per maggiori dettagli si veda il Cap. 13 di T.H. Cormen, C.E. Leiserson, R.L.
 * Rivest, C. Stein, Introduzione agli Algoritmi e Strutture Dati (terza
 * edizione), McGraw-Hill, 2010 -
 * https://www.mheducation.it/9788838665158-italy-introduzione-agli-algoritmi-e-strutture-dati-3ed
 * 
 * In questa implementazione degli RBTree è possibile inserire elementi
 * duplicati, ma non è possibile inserire elementi null.
 * 
 * @author Lorenzo Tanganelli 
 * 		   lorenzo.tanganelli@studenti.unicam.it
 *
 * @param <E> il tipo delle etichette dei nodi dell'albero. La classe E deve
 *            avere un ordinamento naturale implementato tramite il metodo
 *            compareTo. Tale ordinamento è quello usato nell'RBTree per
 *            confrontare le etichette dei nodi.
 * 
 * 
 */
public class RBTree<E extends Comparable<E>> {
	/*
	 * Costanti e metodi static.
	 */
	protected static final boolean RED = true;
	protected static final boolean BLACK = false;

	protected static boolean isRed(@SuppressWarnings("rawtypes") RBTree.RBTreeNode x) {
		if (isNil(x))
			return false;
		return x.color == RED;
	}

	protected static boolean isBlack(@SuppressWarnings("rawtypes") RBTree.RBTreeNode x) {
		if (isNil(x))
			return true;
		return x.color == BLACK;
	}

	protected static boolean isNil(@SuppressWarnings("rawtypes") RBTree.RBTreeNode n) {
		return (n == null ? false : n.el == null);
	}

	/*
	 * Variabili istanza e metodi non static.
	 */

	private RBTreeNode root;
	private RBTreeNode nil = new RBTreeNode();
	private int size = 0;
	private int numberOfNodes = 0;

	public RBTree() {
		root = nil;
	}

	public RBTree(E element) {
		if (element == null)
			throw new NullPointerException("è stato passato un parametro null");
		root = new RBTreeNode(element);
		root.setColor(BLACK);
		size++;
		numberOfNodes++;
	}

	public boolean contains(E el) {
		//cerso l'elemento all'interno dell'albero, ritorno true se è presente, false altrimenti. 
		return getNodeOf(el) == null ? false : true;
	}

	protected RBTreeNode getNodeOf(E el) {
		if (el == null)
			throw new NullPointerException("è stato passato un parametro null");
		//utilizzo il metodo search della radice per trovare l'elemento all'intenro dell'albero
		return root.search(el);
	}

	public int getCount(E el) {
		//cerco il nodo, se è presente torno il suo count altrimenti ritorno 0
		RBTreeNode x = getNodeOf(el);
		return x != null ? x.getCount() : 0;
	}

	public int getBlackHeight() {
		if (isEmpty())
			return -1;
		//utilizzo il metodo getBlackHeight della radice
		return root.getBlackHeight();
	}

	public E getMinimum() {
		if (isEmpty())
			return null;
		//utilizzo il metodo getMinimum della radice e resituisco l'elemento del nodo ritornoto 
		return root.getMinimum().getEl();
	}

	public E getMaximum() {
		if (isEmpty())
			return null;
		//simile a getMinimum
		return root.getMaximum().getEl();
	}

	public int getNumberOfNodes() {
		return this.numberOfNodes;
	}

	public E getPredecessor(E el) {
		if (el == null)
			throw new NullPointerException("è stato passato un parametro null");
		if (!contains(el))
			throw new IllegalArgumentException("è stata passata un elemento non presente in questo albero");
		//cerco l'elemento nell'albero e restituisco l'elemento del suo predecessore 
		RBTreeNode x = getNodeOf(el);
		return x.getPredecessor().getEl();
	}

	protected RBTreeNode getRoot() {
		return this.root;
	}

	public int getSize() {
		return this.size;
	}

	public E getSuccessor(E el) {
		if (el == null)
			throw new NullPointerException("è stato passato un parametro null");
		if (!contains(el))
			throw new IllegalArgumentException("è stata passata un elemento non presente in questo albero");
		//simile a getPredecessor
		RBTreeNode x = getNodeOf(el);
		return x.getSuccessor().getEl();
	}

	public List<E> inOrderVisit() {
		//creo una lista e la passo al metodo inOrderVisti della root, dopo di che ritorno la lista
		List<E> r = new ArrayList<E>();
		root.inOrderVisit(r);
		return r;
	}

	public boolean isEmpty() {
		return isNil(this.root);
	}

	@Override
	public String toString() {
		String descr = "RBTree [root=" + root.el.toString() + ", size=" + size + ", numberOfNodes=" + numberOfNodes
				+ "]\n";
		return descr + this.root.toString();
	}

	public int insert(E el) {
		if (el == null)
			throw new NullPointerException("è stato passato un parametro null");
		//utilizzo l'insert della root per inserire il nuovo elemento
		return root.insert(el);
	}

	public boolean remove(E el) {
		if (el == null)
			throw new NullPointerException("è stato passato un parametro null");
		//utilizzo il remove della root per eliminare l'elemento dall'albero
		return root.remove(el);
	}

	protected class RBTreeNode {

		protected final E el;
		protected RBTreeNode left;
		protected RBTreeNode right;
		protected RBTreeNode parent;
		protected boolean color;
		protected int count;

		protected RBTreeNode(E el) {
			this.el = el;
			left = nil;
			right = nil;
			parent = nil;
			color = RED;
			count = 1;
		}

		protected RBTreeNode() {
			// NIL
			this.el = null;
			left = null;
			right = null;
			parent = null;
			color = BLACK;
			count = 1;
		}

		protected void inOrderVisit(List<E> r) {
			//se sono una foglia NIL ritorno,
			if (isNil(this))
				return;
			//visito prima i nodi alla mia sinistra, aggiungo me alla lista e poi visito i nodi alla mia destra
			getLeft().inOrderVisit(r);
			
			for (int i = 0; i < getCount(); i++)
				r.add(getEl());
			
			getRight().inOrderVisit(r);
		}

		protected RBTreeNode getSuccessor() {
			//se a destra ho dei figli ritorno il minimo numero a destra
			if (!isNil(getRight()))
				return getRight().getMinimum();

			RBTreeNode x = this;
			RBTreeNode y = getParent();
			
			//scorro l'albero dal nodo alla radice
			while (!isNil(y) && x == y.getRight()) {
				x = y;
				y = y.getParent();
			}

			return y;
		}

		protected RBTreeNode getPredecessor() {
			//analogo a getSuccessor con getMin sostituito con getMax e left con right
			if (!isNil(getLeft()))
				return getLeft().getMaximum();

			RBTreeNode x = this;
			RBTreeNode y = getParent();

			while (!isNil(y) && x == y.getLeft()) {
				x = y;
				y = y.getParent();
			}

			return y;
		}

		protected RBTreeNode search(E el) {
			RBTreeNode x = this;
			//scorro l'albero dal nodo fino alla foglia e faccio dei confronti
			while (!isNil(x)) {

				switch (x.getEl().compareTo(el)) {
					case -1:
						//se l'elemento el è più grande dell'elemento del nodo x vado a destra
						x = x.getRight();
						break;
					case 1:
						//se l'elemento el è più piccolo dell'elemento del nodo x vado a sinistra
						x = x.getLeft();
						break;
					case 0:
						//se l'elemento el è uguale dell'elemento del nodo x ritorno x
						return x;
				}
			}
			//ritorno null se non trovo il nodo
			return null;
		}

		protected RBTreeNode getMinimum() {
			RBTreeNode x = this;
			
			//ritorno il figlio più a sinistra dell'albero
			while (!isNil(x.getLeft()))
				x = x.getLeft();

			return x;
		}

		protected RBTreeNode getMaximum() {
			RBTreeNode x = this;
			
			//ritorno il figlio più a sinistra dell'albero
			while (!isNil(x.getRight()))
				x = x.getRight();

			return x;
		}

		protected E getEl() {
			return el;
		}

		protected RBTreeNode getLeft() {
			return left;
		}

		protected RBTreeNode getRight() {
			return right;
		}

		protected RBTreeNode getParent() {
			return parent;
		}

		protected boolean getColor() {
			return color;
		}

		protected int getCount() {
			return count;
		}

		protected boolean isRed() {

			return this.color == RED;
		}

		protected boolean isBlack() {

			return this.color == BLACK;
		}

		private void setLeft(RBTreeNode left) {
			//il nodo passato diventa il nuovo left, al quale aggiorno il parent
			left.parent = this;
			this.left = left;
		}

		private void setRight(RBTreeNode right) {
			//il nodo passato diventa il nuovo right, al quale aggiorno il parent
			right.parent = this;
			this.right = right;

		}

		private void setParent(RBTreeNode parent) {
			//il nodo passato diventa il nuovo parent,e se divento figlio sinistro allora aggiorno il parent.left altrimenti parent.right
			this.parent = parent.getParent();
			if (parent == parent.getParent().getLeft())
				parent.getParent().left = this;
			else
				parent.getParent().right = this;
		}

		private void setColor(boolean color) {
			this.color = color;
		}

		private void setCount(int i) {
			count += i;
		}

		protected int getBlackHeight() {
			RBTreeNode x = this;
			if (isNil(x))
				return 0;
			//controllo ricorsivamente l'altezza nera a desta e a sinistra
			int iLeft = x.left.getBlackHeight();
			int iRight = x.right.getBlackHeight();
			
			//se le altezze sono diverse ritorno -1
			if (iLeft != iRight)
				return -1;
			//altrimenti se sono un nodo nero ritorno l'altezza dei miei figli+1, altrimenti ritorno l'altezza dei miei figli
			return x.isRed() ? iLeft : iLeft + 1;
		}

		protected int insert(E el) {
			int cCmp = 0;

			RBTreeNode x = this;
			RBTreeNode y = nil;
			
			//cerco dove aggiungere il nuovo nodo 
			while (!isNil(x)) {
				y = x;
				cCmp++;
				switch (x.getEl().compareTo(el)) {
				case 1:
					//se l'elemento passato è più grande di x.el vado a sinistra
					x = x.getLeft();
					break;
				case -1:
					//altrimenti vado a destra
					x = x.getRight();
					break;
				case 0:
					//se il l'elemento è già presente incremento la sua molteplicità e il size dell'albero
					x.setCount(1);
					size++;
					return cCmp;
				}
			}
			
			//se la foglia trovata è NIL, vuol dire che l'albero è vuoto e quindi aggiorno la root con il nuvoo nodo
			if (isNil(y)) {
				root = new RBTreeNode(el);
				root.setColor(BLACK);
			} else {
				cCmp++;
				//decido se il nuovo nodo deve essere un figlio destro o sinistro e faccio il fix sul nuovo nodo
				if (y.getEl().compareTo(el) < 0) {
					y.setRight(new RBTreeNode(el));
					fixInsert(y.getRight());
				} else {
					y.setLeft(new RBTreeNode(el));
					fixInsert(y.getLeft());
				}
			}
			
			//se y.parent==NIL vuol dire che è la nuova root e la coloro di nero;
			if (isNil(y.parent)) 
				root = y;
			root.setColor(BLACK);

			
			
			size++;
			numberOfNodes++;
			return cCmp;
		}

		private void fixInsert(RBTreeNode x) {
			RBTreeNode y = null;
			
			while (x.getParent().isRed()) {
				//se x.parent è figlio destro
				if (x.getParent() == x.getParent().getParent().getRight()) {
					//y è uguale allo zio di x
					y = x.getParent().getParent().getLeft();
					if (y.isRed()) {
						//se y è rosso aggiorno i colori del padre e del nonno di x
						y.setColor(BLACK);
						x.getParent().setColor(BLACK);
						x.getParent().getParent().setColor(RED);
						//aggiorno x con il nonno
						x = x.getParent().getParent();
					} else {
						//se x è figlio sinistro
						if (x == x.getParent().getLeft()) {
							//aggiorno x con il padre e faccio una rotazione a destra su x
							x = x.getParent();
							rightRotate(x);
						} else {
							//aggiorno i colori del padre e del nonno di x e faccio una rotazione a sinistra sul nonno di x
							x.getParent().setColor(BLACK);
							x.getParent().getParent().setColor(RED);
							leftRotate(x.getParent().getParent());
						}
					}
				} else {
					//analogo al ramo if con right e left scambiati 
					y = x.getParent().getParent().getRight();
					if (y.isRed()) {
						y.setColor(BLACK);
						x.getParent().setColor(BLACK);
						x.getParent().getParent().setColor(RED);
						x = x.getParent().getParent();
					} else {
						if (x == x.getParent().getRight()) {
							x = x.getParent();
							leftRotate(x);
						} else {
							x.getParent().setColor(BLACK);
							x.getParent().getParent().setColor(RED);
							rightRotate(x.getParent().getParent());
						}
					}
				}
				//quando x diventa la root esco dal while
				if (x == root)
					break;
			}

		}

		protected void leftRotate(RBTreeNode x) {
			if (isNil(x.getRight()))
				throw new IllegalArgumentException("è stato passato un nodo con figlio desto NIL");
			
			RBTreeNode y = x.getRight();
			x.right = y.getLeft();
			//controllo che y.left non sia NIL e aggiorno il suo parent
			if (!isNil(y.getLeft()))
				y.getLeft().parent = x;
			y.parent = x.getParent();
			
			//se il padre di x è NIL aggiorno la root
			if (isNil(x.getParent())) {
				root = y;
				//se x è figlio sinistro aggiorno lo aggiorno, altrimenti aggiorno il figlio destro
			} else if (x == x.getParent().getLeft()) {
				x.getParent().left = y;
			} else {
				x.parent.right = y;
			}
			y.left = x;
			x.parent = y;

		}

		protected void rightRotate(RBTreeNode x) {
			//analogo a leftRotate con right e left scambiati 
			if (isNil(x.getLeft()))
				throw new IllegalArgumentException("è stato passato un nodo con figlio sinistro NIL");
			RBTreeNode y = x.left;
			x.left = y.right;
			if (!isNil(y.getRight()))
				y.getRight().parent = x;
			y.parent = x.parent;
			if (isNil(x.getParent())) {
				root = y;
			} else if (x == x.getParent().getRight()) {
				x.parent.right = y;
			} else {
				x.parent.left = y;
			}
			y.right = x;
			x.parent = y;
		}
		
		protected boolean remove(E el) {
			RBTreeNode x = getNodeOf(el);
			RBTreeNode y = nil;
			RBTreeNode z = nil;

			if (x == null)
				return false;

			if (x.getCount() > 1) {
				x.setCount(-1);
				size--;
				return true;
			}

			if (size == 1) {
				root = nil;
				size--;
				numberOfNodes--;
				return true;
			}

			// se uno dei figli di x è NIL, dobbiamo rimuovere x altrimenti il successore di x
			if (isNil(x.getLeft()) || isNil(x.getRight()))
				y = x;
			else
				y = x.getSuccessor();
			
			// si assegna ad z il figlio desto o sinistro non NIL di y oppure NIL se y non ha figli
			if (!isNil(y.getLeft()))
				z = y.getLeft();
			else
				z = y.getRight();

			z.parent = y.getParent();

			// se il padre di y è NIL allora z è la nuova root
			if (isNil(y.getParent()))
				root = z;
			else {
				// se y è il figlio sinistro allora sostituisco il puntatore al figlio sinistro del padre di y con z
				if (y == y.getParent().getLeft())
					y.getParent().left = z;
				// altrimenti se y è il figlio destro sostitusci il puntatore al figlio destro del padre di y con z
				else if (y == y.getParent().getRight())
					y.getParent().right = z;

			}

			// se y != x, sostituisco y ad x;.
			if (y != x) {
				y.setLeft(x.getLeft());
				y.setRight(x.getRight());
				y.setParent(x);
				y.setColor(x.getColor());
			}
			
			//se il padre di y è nil aggiorno la root
			if (isNil(y.parent)) {
				root = y;
				root.setColor(BLACK);
			}
			
			//se l'altezza dell'albero è errata dopo il remove faccio il removeFIX
			if (root.getBlackHeight() == -1)
				removeFix(z);

			size--;
			numberOfNodes--;
			return true;
		}
		private void removeFix(RBTreeNode x) {
			RBTreeNode y = nil;
			//finche x non è la root e x è nero
				while (x != root && x.isBlack()) {
					//se x è figlio sinistro
					if (x == x.getParent().getLeft()) {
						//y = fratello di x
						y = x.getParent().getRight();
						//se y è rosso aggiorno i coloro di y e del padre di x, dopo di che faccio una ruotazione a sinistra sul padre di x e aggiorno y con il nuovo fratello di x 
						if (y.isRed()) {
							y.setColor(BLACK);
							x.getParent().setColor(RED);
							leftRotate(x.getParent());
							y = x.getParent().getRight();
						}
						//se entrambi i figli di y sono neri faccio diventare y rossa e aggiorno x con suo padre
						if (y.getLeft().isBlack() && y.getRight().isBlack()) {
							y.setColor(RED);
							x = x.getParent();
						} else {
							//se y.parent è nero aggiorno i coloro e faccio una rotazionedestra su y
							if (y.getRight().isBlack()) {
								y.getLeft().setColor(BLACK);
								y.setColor(RED);
								rightRotate(y);
								y = x.getParent().getRight();
							}
							//aggiorno i colori di y, y.right e x.parent
							y.setColor(x.getParent().getColor());
							x.getParent().setColor(BLACK);
							y.getRight().setColor(BLACK);
							leftRotate(x.getParent());
							x = root;
						}
					} else {
						//analogo al ramo if con left e right invertiti 
						y = x.getParent().getLeft();
						if (y.isRed()) {
							y.setColor(BLACK);
							x.getParent().setColor(RED);
							rightRotate(x.getParent());
							y = x.getParent().getLeft();
						}
						if (y.getRight().isBlack() && y.getLeft().isBlack()) {
							y.setColor(RED);
							x = x.getParent();
						} else {
							if (y.getLeft().isBlack()) {
								y.getRight().setColor(BLACK);
								y.setColor(RED);
								leftRotate(y);
								y = x.getParent().getLeft();
							}
							y.setColor(x.getParent().getColor());
							x.getParent().setColor(BLACK);
							y.getLeft().setColor(BLACK);
							rightRotate(x.getParent());
							x = root;
						}
					}
				}
			x.setColor(BLACK);
		}
	}
}
