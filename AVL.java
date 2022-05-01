package hw4;


public class AVL<T> {
	private AVLNode root;
public AVL(){
	this.root = null;
}	
	
	
	public void insert(int key, T data){
	AVLNode Node = new AVLNode(key, data);
	if(this.root == null) {
		this.root = Node;
	}
	else {
		this.insertRec(this.root, Node);
	}
	}
	public void RotationRight(AVLNode node) {
		if (node.getLeftChild().getLeftChild() == null) {
			AVLNode temp = node.getLeftChild().getLeftChild();
			HeightUpdate(temp);
			AVLNode temp2 = node;
			AVLNode temp1 = node.getLeftChild();
			node = temp1;
			node.right = temp2;
			node.left = temp;
			HeightUpdate(node);
			HeightUpdate(node.right);
			HeightUpdate(node.left);
			HeightUpdateTree(node);
			
		}
		else {
		AVLNode temp = node.getLeftChild().getRightChild();
		AVLNode temp1 = node.getLeftChild().getLeftChild();
		AVLNode temp2= node;
		node=node.getLeftChild();
		node.right=temp2;
		temp2.left=temp;
		node.left=temp1;
		HeightUpdate(node);
		HeightUpdate(node.right);
		HeightUpdate(node.left);
		HeightUpdateTree(node);
		}
	}
	public void RotationLeft(AVLNode node) {
		if (node.getRightChild().getLeftChild() == null) {
			AVLNode temp = node.getRightChild().getRightChild();
			AVLNode temp2 = node;
			AVLNode temp1 = node.getRightChild();
			node = temp1;
			node.left = temp2;
			node.right = temp;
			HeightUpdate(node);
			HeightUpdate(node.right);
			HeightUpdate(node.left);
			HeightUpdateTree(node);
		}
		else {
		AVLNode temp = node.getRightChild().getLeftChild();
		AVLNode temp1 = node.getRightChild().getRightChild();
		AVLNode temp2= node;
		node=node.getRightChild();
		node.right=temp1;
		node.left=temp2;
		node.left.right=temp;	
		HeightUpdate(node);
		HeightUpdate(node.right);
		HeightUpdate(node.left);
		HeightUpdateTree(node);
		}
		
	}
	public void insertRec(AVLNode root ,AVLNode node)	{
		if (node.getKey() > root.getKey()) {
			if (root.getRightChild() == null) {
				root.right = node;
				node.parent = root;
				if (root.getLeftChild() == null) {
					this.HeightUpdate(root);
					this.HeightUpdate(this.root);
					this.BalanceTree(this.root, node);
				}
			}
			this.insertRec(root.getRightChild(), node);
		}
		if (node.getKey() < root.getKey()) {
			if (root.getLeftChild() == null) {
				root.left = node;
				node.parent = root;
			if (root.getRightChild() == null) {
				this.HeightUpdate(root);
				this.BalanceTree(this.root, node);
			}
			}
			this.insertRec(root.left, node);
		}
		this.BalanceTree(this.root, node);
	}
	public void HeightUpdate(AVLNode node) {
		if (node.getLeftChild() == null) {
			node.SetHeight();
			return;
		}
		if (node.getRightChild() == null) {
			node.SetHeight();
			return;
		}
		else {
		node.height = Math.max(node.getLeftChild().GetHeight(), node.getRightChild().GetHeight()) + 1;
		}
	}
	public void HeightUpdateTree(AVLNode node) {
		AVLNode temp = node;
		while (temp.parent != null) {
			HeightUpdate(temp);
			temp.parent = temp;
		}
	}
	public int Balanced(AVLNode root) {
		if (root.getLeftChild() == null && root.GetHeight() > 1) {
			 return root.GetHeight();
		}
		if (root.getRightChild() == null && root.GetHeight() > 1) {
			return -(root.GetHeight());
		}
		if(root == null) {
			return 0;
		}
		if (root.getLeftChild() == null) {
			return 1;
		}
		if (root.getRightChild() == null) {
			return -1;
		}
		return root.getRightChild().height - root.getLeftChild().height;
	}
	public void BalanceTree(AVLNode root, AVLNode newnode) {
		if (Balanced(root) == 0 || Balanced(root) == 1 || Balanced(root) == -1) {
			return;
		}
		AVLNode temp = root;
		while (this.Balanced(temp) != 0 || this.Balanced(temp) != 1 || this.Balanced(temp) != -1) {
			if (this.Balanced(temp) > 1) {
				temp = temp.getRightChild();
				continue;
			}
			if (this.Balanced(temp) < -1) {
				temp = temp.getLeftChild();
				continue;
			}
		}
		AVLNode Unbalanced = temp.getFather();
		AVLNode nodefather = newnode.getFather();
		if (Unbalanced.getLeftChild().getLeftChild() == nodefather) {
			this.RotationRight(Unbalanced);
			return;}
		if (Unbalanced.getRightChild().getRightChild() == nodefather) {
			this.RotationLeft(Unbalanced);
			return;
		}
		if (Unbalanced.getRightChild().getLeftChild() == nodefather) {
			this.RotationRight(Unbalanced.getRightChild());
			this.RotationLeft(Unbalanced);
			return;
			
		}
		if (Unbalanced.getLeftChild().getRightChild() == nodefather) {
			this.RotationLeft(Unbalanced.getLeftChild());
			this.RotationRight(Unbalanced);
			return;
		}
		}
		
	
	public T searchHelper(int key, AVLNode node){
	if (node.getKey() == key) {
		return (T) node.getData();
	}	
	
	if (key > node.getKey()) {
		return searchHelper( key, node.getRightChild());	
	}
	if (key < node.getKey()) {
		return searchHelper(key, node.getLeftChild());
	}
	
	return null;
	}
	public T search(int key){
		return this.searchHelper(key, this.root);
		
		
	}

	public AVLNode<T> getRoot(){
		
		return this.root;
	}
}

