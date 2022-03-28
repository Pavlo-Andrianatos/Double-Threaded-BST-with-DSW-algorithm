public class DoubleThreadedBST<T extends Comparable<? super T>> {

    private DTNode<T> root; // the root node of the tree
    public int numNodes = 1;

    /*
	TODO: You must complete each of the methods in this class to create
	your own double threaded BST. You may add any additional methods
	or data fields which you might need to accomplish your task. You
	must NOT change the signatures of any methods given to you with this
	class.
     */
    public DoubleThreadedBST() {
        /*
		The default constructor
         */

        root = null;
    }

    public DoubleThreadedBST(DoubleThreadedBST<T> other) {
        /*
		The copy constructor
         */
        root = null;
        numNodes = other.numNodes;

        root = copyTree(root, other.getRoot());
        makeThreads(root);

        /*DTNode r = other.getRoot();
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";
        boolean thread = false;

        boolean in = false;

        while (r != null && index < numNodes) {
            for (int i = 0; i < index; i++) {
                if (visited[i].data == r.data) {
                    in = true;
                    break;
                }
            }
            if (in == false) {
                if (thread == true) {
                    out += r.data;
                    thread = false;
                } else {
                    out += r.data + ",";
                }
                visited[index] = new DTNode(r.data);
                index++;
            } else {
                /*if (r.hasRightThread == true) {
                    out += "|";
                }*/
 /*}
            in = false;

            if (r.left != null && r.hasLeftThread == false) {
                if (out.contains(r.left.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.left.data) {
                            in = true;
                            break;
                        }
                    }
                }
                if (in == false) {
                    r = r.left;
                } else {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;
                }

            } else if (r.hasRightThread == false) {
                r = r.right;
            } else {
                out = out.substring(0, out.length() - 1);
                //out += "|";
                while (r != null && r.right != null && r.hasRightThread == true) {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;

                    in = false;
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.data) {
                            in = true;
                            break;
                        }
                    }

                    if (in == false) {
                        out += r.data + ",";
                        visited[index] = new DTNode(r.data);
                        index++;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                    }
                    in = false;

                    if (r.right == null) {
                        break;
                    } else {
                        /*if (r.hasRightThread == true) {
                            out += "|";
                        }*/
 /*r = r.right;
                    }
                    if (out.contains(r.data.toString()) == true && index > 0) {
                        for (int i = 0; i < index; i++) {
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                    }
                    if (in == false) {
                        //System.out.println(r.data);
                        break;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                        r = r.right;
                        in = false;
                        if (r == null) {
                            break;
                        }
                        if (out.contains(r.data.toString()) == true && index > 0) {
                            for (int i = 0; i < index; i++) {
                                if (visited[i].data == r.data) {
                                    in = true;
                                    break;
                                }
                            }
                        }

                        while (in == true) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                            r = r.right;
                            in = false;
                            if (r == null) {
                                break;
                            }
                            if (out.contains(r.data.toString()) == true && index > 0) {
                                for (int i = 0; i < index; i++) {
                                    if (visited[i].data == r.data) {
                                        in = true;
                                        break;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < index; i++) {
                            if (r == null) {
                                break;
                            }
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                        if (in == false && r != null) {
                            out += r.data + ",";
                            visited[index] = new DTNode(r.data);
                            index++;
                            break;
                        } else if (in == true && r != null) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                        }
                        in = false;
                    }
                }
                /*if (r != null && r.hasRightThread == true) {
                    out += "|";
                }*/
 /*}
            in = false;
        }
        numNodes = 1;
        if (visited[0] != null) {
            for (int i = 0; i < visited.length; i++) {
                //System.out.println(i);
                if(visited[i] != null){
                insert((T) visited[i].data);
                }
            }
            //System.out.println(root.data);
        }*/
    }

    public DTNode copyTree(DTNode node, DTNode otherNode) {

        if (otherNode == null) {
            return null;
        } else {
            DTNode newNode = new DTNode(otherNode.data);
            newNode.parent = node;
            newNode.hasLeftThread = otherNode.hasLeftThread;
            newNode.hasRightThread = otherNode.hasRightThread;

            if (otherNode.hasLeftThread == false) {
                newNode.left = copyTree(newNode, otherNode.left);
            }
            if (otherNode.hasRightThread == false) {
                newNode.right = copyTree(newNode, otherNode.right);
            }

            return newNode;
        }
    }

    public DoubleThreadedBST<T> clone() {
        /*
		The clone method should return a copy/clone
		of this tree.
         */
        DoubleThreadedBST dt = new DoubleThreadedBST();

        DTNode tempRoot = null;

        tempRoot = copyTree(tempRoot, getRoot());
        makeThreads(tempRoot);

        dt.setRoot(tempRoot);
        dt.numNodes = numNodes;

        /*if (root == null) {
            return dt;
        }

        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";
        boolean thread = false;

        boolean in = false;

        while (r != null && index < numNodes) {
            for (int i = 0; i < index; i++) {
                if (visited[i].data == r.data) {
                    in = true;
                    break;
                }
            }
            if (in == false) {
                if (thread == true) {
                    out += r.data;
                    thread = false;
                } else {
                    out += r.data + ",";
                }
                visited[index] = new DTNode(r.data);
                index++;
            } else {
                /*if (r.hasRightThread == true) {
                    out += "|";
                }*/
 /*}
            in = false;

            if (r.left != null && r.hasLeftThread == false) {
                if (out.contains(r.left.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.left.data) {
                            in = true;
                            break;
                        }
                    }
                }
                if (in == false) {
                    r = r.left;
                } else {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;
                }

            } else if (r.hasRightThread == false) {
                r = r.right;
            } else {
                out = out.substring(0, out.length() - 1);
                //out += "|";
                while (r != null && r.right != null && r.hasRightThread == true) {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;

                    in = false;
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.data) {
                            in = true;
                            break;
                        }
                    }

                    if (in == false) {
                        out += r.data + ",";
                        visited[index] = new DTNode(r.data);
                        index++;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                    }
                    in = false;

                    if (r.right == null) {
                        break;
                    } else {
                        /*if (r.hasRightThread == true) {
                            out += "|";
                        }*/
 /*r = r.right;
                    }
                    if (out.contains(r.data.toString()) == true && index > 0) {
                        for (int i = 0; i < index; i++) {
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                    }
                    if (in == false) {
                        //System.out.println(r.data);
                        break;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                        r = r.right;
                        in = false;
                        if (r == null) {
                            break;
                        }
                        if (out.contains(r.data.toString()) == true && index > 0) {
                            for (int i = 0; i < index; i++) {
                                if (visited[i].data == r.data) {
                                    in = true;
                                    break;
                                }
                            }
                        }

                        while (in == true) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                            r = r.right;
                            in = false;
                            if (r == null) {
                                break;
                            }
                            if (out.contains(r.data.toString()) == true && index > 0) {
                                for (int i = 0; i < index; i++) {
                                    if (visited[i].data == r.data) {
                                        in = true;
                                        break;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < index; i++) {
                            if (r == null) {
                                break;
                            }
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                        if (in == false && r != null) {
                            out += r.data + ",";
                            visited[index] = new DTNode(r.data);
                            index++;
                            break;
                        } else if (in == true && r != null) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                        }
                        in = false;
                    }
                }
                /*if (r != null && r.hasRightThread == true) {
                    out += "|";
                }*/
 /*}
            in = false;
        }

        if (visited[0] != null) {
            for (int i = 0; i < visited.length; i++) {
                dt.insert(visited[i].data);
            }
        }*/
        return dt;
    }

    public void setRoot(DTNode r) {
        root = r;
    }

    public DTNode<T> getRoot() {
        /*
		Return the root of the tree.
         */

        return root;
    }

    public boolean insert(T element) {
        /*
		The element passed in as a parameter should be
		inserted into this tree. Duplicates are not allowed.
		Left and right threads in the corresponding branch 
		must be updated accordingly, as necessary. 
		If the insert was successfull, the method should 
		return true. If the operation was unsuccessfull, 
		the method should return false.
		
		NB: Do not throw an exception.
         */

        if (root == null) {
            root = new DTNode(element);
        } else if (contains(element) == false) {
            DTNode newNode = new DTNode(element);
            DTNode r = root;
            while (r != null) {
                if (newNode.data.compareTo(r.data) < 0) {
                    if (r.hasLeftThread == false) {
                        if (r.left == null) {
                            break;
                        }
                        r = r.left;
                    } else {
                        break;
                    }
                } else {
                    if (r.hasRightThread == false) {
                        if (r.right == null) {
                            break;
                        }
                        r = r.right;
                    } else {
                        break;
                    }
                }
            }
			
            if (r.hasRightThread || r.hasLeftThread) {
                if (r.hasRightThread == true && r.hasLeftThread == false) {
                    /*newNode.hasRightThread = true;
                    r.hasRightThread = false;
                    newNode.right = r.right;
                    r.right = newNode;
                    newNode.parent = r;
                    newNode.left = r;
                    newNode.hasLeftThread = true;*/

                    if (newNode.data.compareTo(r.data) < 0) {
                        newNode.hasLeftThread = true;
                        r.hasLeftThread = false;
                        newNode.left = r.left;
                        r.left = newNode;
                        newNode.parent = r;
                        newNode.right = r;
                        newNode.hasRightThread = true;
                    } else {
                        newNode.hasRightThread = true;
                        r.hasRightThread = false;
                        newNode.right = r.right;
                        r.right = newNode;
                        newNode.parent = r;
                        newNode.left = r;
                        newNode.hasLeftThread = true;
                    }

                } else if (r.hasRightThread == false && r.hasLeftThread == true) {
                    /*newNode.hasLeftThread = true;
                    r.hasLeftThread = false;
                    newNode.left = r.left;
                    r.left = newNode;
                    newNode.parent = r;
                    newNode.right = r;
                    newNode.hasRightThread = true;*/

                    if (newNode.data.compareTo(r.data) < 0) {
                        newNode.hasLeftThread = true;
                        r.hasLeftThread = false;
                        newNode.left = r.left;
                        r.left = newNode;
                        newNode.parent = r;
                        newNode.right = r;
                        newNode.hasRightThread = true;
                    } else {
                        newNode.hasRightThread = true;
                        r.hasRightThread = false;
                        newNode.right = r.right;
                        r.right = newNode;
                        newNode.parent = r;
                        newNode.left = r;
                        newNode.hasLeftThread = true;
                    }
                } else {
                    if (newNode.data.compareTo(r.data) < 0) {
                        newNode.hasLeftThread = true;
                        r.hasLeftThread = false;
                        newNode.left = r.left;
                        r.left = newNode;
                        newNode.parent = r;
                        newNode.right = r;
                        newNode.hasRightThread = true;
                    } else {
                        newNode.hasRightThread = true;
                        r.hasRightThread = false;
                        newNode.right = r.right;
                        r.right = newNode;
                        newNode.parent = r;
                        newNode.left = r;
                        newNode.hasLeftThread = true;
                    }
                }
            } else if (newNode.data.compareTo(r.data) < 0) {
                r.left = newNode;
                newNode.hasRightThread = true;
                newNode.right = r;
                newNode.parent = r;
            } else {
                r.right = newNode;
                newNode.hasLeftThread = true;
                newNode.left = r;
                newNode.parent = r;
            }

            numNodes++;

            return true;
        }

        return false;
    }

    public boolean delete(T element) {
        /*
		The element passed in as a parameter should be
		deleted from this tree. If the delete was successfull,
		the method should return true. If the operation was
		unsuccessfull, the method should return false. Eg, if
		the requested element is not found, return false.
		
		You have to implement the mirror case of delete by merging 
		as discussed in the textbook. That is, for a deleted node,
		its right child should replace it in the tree and not its
		left child as in the textbook examples. Relevant left and
		right threads must be updated accordingly.
		
		NB: Do not throw an exception.
         */

        if (root == null) {
            return false;
        } else if (contains(element) == false) {
            return false;
        } else {
            DTNode nodeToBeDeleted = root;
            DTNode temp = new DTNode(element);

            while (nodeToBeDeleted.data != element) {
                if (temp.data.compareTo(nodeToBeDeleted.data) < 0) {
                    nodeToBeDeleted = nodeToBeDeleted.left;
                } else {
                    nodeToBeDeleted = nodeToBeDeleted.right;
                }
            }

            if (nodeToBeDeleted.left == null || nodeToBeDeleted.right == null) {
                DTNode p = nodeToBeDeleted.parent;

                if (p == null) {
                    if (nodeToBeDeleted.right == null) {
                        DTNode leftOfNodeToBeDeleted = nodeToBeDeleted.left;

                        root = leftOfNodeToBeDeleted;
                        root.parent = null;

                        if (leftOfNodeToBeDeleted.hasRightThread == true) {
                            leftOfNodeToBeDeleted.hasRightThread = false;
                            leftOfNodeToBeDeleted.right = null;
                        } else {
                            DTNode g = leftOfNodeToBeDeleted;
                            while (g.hasRightThread == false) {
                                g = g.right;
                            }

                            g.hasRightThread = false;
                            g.right = null;
                        }

                        makeThreads(root);
                    } else {
                        DTNode nodeToBeMoved = nodeToBeDeleted.right;
                        DTNode leftOfNodeToBeDeleted = nodeToBeDeleted.left;
                        DTNode rightOfNodeToBeDeleted = nodeToBeMoved;

                        while (nodeToBeMoved.hasLeftThread == false) {
                            nodeToBeMoved = nodeToBeMoved.left;
                        }
                        //p.right = rightOfNodeToBeDeleted;
                        //rightOfNodeToBeDeleted.parent = p;

                        root = rightOfNodeToBeDeleted;
                        root.parent = null;

                        nodeToBeMoved.left = leftOfNodeToBeDeleted;
                        leftOfNodeToBeDeleted.parent = nodeToBeMoved;

                        nodeToBeMoved.hasLeftThread = false;

                        makeThreads(root);
                    }
                } else if (nodeToBeDeleted.left == null) {
                    if (nodeToBeDeleted.hasRightThread == false) {
                        DTNode d = nodeToBeDeleted.right;
                        p.left = d;
                        d.parent = p;

                        DTNode t = p.left;

                        while (t.hasLeftThread == false) {
                            t = t.left;
                        }

                        t.left = null;

                        makeThreads(p.left);
                    } else {
                        p.left = null;
                    }
                } else if (nodeToBeDeleted.right == null) {
                    if (nodeToBeDeleted.hasLeftThread == false) {
                        DTNode d = nodeToBeDeleted.left;
                        p.right = d;
                        d.parent = p;

                        DTNode t = p.right;

                        while (t.hasRightThread == false) {
                            t = t.right;
                        }

                        t.right = null;

                        makeThreads(p.right);
                    } else {
                        p.right = null;
                    }
                }
            } else if (nodeToBeDeleted.hasLeftThread == true && nodeToBeDeleted.hasRightThread == true) {
                DTNode p = nodeToBeDeleted.parent;

                if (p.right == nodeToBeDeleted) {
                    p.right = nodeToBeDeleted.right;
                    p.hasRightThread = true;
                } else if (p.left == nodeToBeDeleted) {
                    p.left = nodeToBeDeleted.left;
                    p.hasLeftThread = true;
                }
            } else if (nodeToBeDeleted.hasLeftThread == false && nodeToBeDeleted.hasRightThread == true) {
                DTNode p = nodeToBeDeleted.parent;
                DTNode leftChild = nodeToBeDeleted.left;

                if (p.right == nodeToBeDeleted) {
                    p.right = leftChild;
                    leftChild.parent = p;
                    /*if(leftChild.hasRightThread == true){
                        leftChild.right = nodeToBeDeleted.right;
                    } else if(leftChild.hasRightThread == false){*/
                    makeThreads(leftChild);
                    //}
                } else if (p.left == nodeToBeDeleted) {
                    p.left = leftChild;
                    leftChild.parent = p;
                    /*if(leftChild.hasRightThread == true){
                        leftChild.right = nodeToBeDeleted.right;
                    } else if(leftChild.hasRightThread == false){*/
                    makeThreads(leftChild);
                    //}
                }
            } else if (nodeToBeDeleted.hasLeftThread == true && nodeToBeDeleted.hasRightThread == false) {
                DTNode p = nodeToBeDeleted.parent;
                DTNode rightChild = nodeToBeDeleted.right;

                if (p.right == nodeToBeDeleted) {
                    p.right = rightChild; // MAKE IT A THREAD
                    rightChild.parent = p;
                    makeThreads(rightChild);
                } else if (p.left == nodeToBeDeleted) {
                    p.left = rightChild; // MAKE IT A THREAD
                    rightChild.parent = p;
                    makeThreads(rightChild);
                }
            } else {
                if (nodeToBeDeleted.parent == null) {
                    DTNode nodeToBeMoved = nodeToBeDeleted.right;
                    DTNode leftOfNodeToBeDeleted = nodeToBeDeleted.left;
                    DTNode rightOfNodeToBeDeleted = nodeToBeMoved;

                    while (nodeToBeMoved.hasLeftThread != true) {
                        nodeToBeMoved = nodeToBeMoved.left;
                    }
                    //p.right = rightOfNodeToBeDeleted;
                    //rightOfNodeToBeDeleted.parent = p;

                    root = rightOfNodeToBeDeleted;
                    root.parent = null;

                    nodeToBeMoved.left = leftOfNodeToBeDeleted;
                    leftOfNodeToBeDeleted.parent = nodeToBeMoved;

                    nodeToBeMoved.hasLeftThread = false;

                    makeThreads(root);
                } else {
                    DTNode p = nodeToBeDeleted.parent;
                    if (p.right == nodeToBeDeleted) {
                        DTNode nodeToBeMoved = nodeToBeDeleted.right;
                        DTNode leftOfNodeToBeDeleted = nodeToBeDeleted.left;
                        DTNode rightOfNodeToBeDeleted = nodeToBeMoved;

                        while (nodeToBeMoved.hasLeftThread != true) {
                            nodeToBeMoved = nodeToBeMoved.left;
                        }
                        p.right = rightOfNodeToBeDeleted;
                        rightOfNodeToBeDeleted.parent = p;

                        nodeToBeMoved.left = leftOfNodeToBeDeleted;
                        leftOfNodeToBeDeleted.parent = nodeToBeMoved;

                        nodeToBeMoved.hasLeftThread = false;

                        makeThreads(p.right);
                    } else if (p.left == nodeToBeDeleted) {
                        DTNode nodeToBeMoved = nodeToBeDeleted.right;
                        DTNode leftOfNodeToBeDeleted = nodeToBeDeleted.left;
                        DTNode rightOfNodeToBeDeleted = nodeToBeMoved;

                        while (nodeToBeMoved.hasLeftThread != true) {
                            nodeToBeMoved = nodeToBeMoved.left;
                        }

                        p.left = rightOfNodeToBeDeleted;
                        rightOfNodeToBeDeleted.parent = p;

                        nodeToBeMoved.left = leftOfNodeToBeDeleted;
                        leftOfNodeToBeDeleted.parent = nodeToBeMoved;

                        nodeToBeMoved.hasLeftThread = false;

                        makeThreads(p.left);
                    }
                }
            }
            numNodes--;
        }

        return true;
    }

    public void makeThreads(DTNode node) {
        if (node == null) {
            return;
        }
        if (node.hasLeftThread == false /*|| node.left != null*/) {
            makeThreads(node.left);
        }
        if (node.hasRightThread == false /*|| node.right != null*/) {
            makeThreads(node.right);
        }
        if (node.hasLeftThread == true) {
            DTNode p = node.parent;
            while (true) {
                if (p == null) {
                    node.hasLeftThread = false;
                    break;
                }
                if (p.data.compareTo(node.data) < 0) {
                    node.left = p;
                    break;
                } else {
                    p = p.parent;
                }

            }
        }

        if (node.hasRightThread == true) {
            DTNode p = node.parent;
            while (true) {
                if (p == null) {
                    node.hasRightThread = false;
                    break;
                }
                if (p.data.compareTo(node.data) > 0) {
                    node.right = p;
                    break;
                } else {
                    p = p.parent;
                }

            }
        }
    }

    public boolean contains(T element) {
        /*
		This method searches for the element passed in as a
		parameter. If the element is found, true should be 
		returned. If the element is not in the tree, the
		method should return false.
         */
        if (root == null) {
            return false;
        }
        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";

        while (r.right != null) {
            r = r.right;
        }

        boolean in = true;

        while (true) {
            while (r.right != null && r.hasRightThread == false) {
                if (out.contains(r.right.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.right.data) {
                            in = true;
                            break;
                        }
                    }
                    if (in == true) {
                        break;
                    }
                }
                r = r.right;
            }
            in = false;
            visited[index] = new DTNode(r.data);
            index++;
            out += r.data + ",";
            r = r.left;
            if (r == null) {
                break;
            }
        }

        for (int i = 0; i < index; i++) {
            if (visited[i].data == element) {
                return true;
            }
        }

        /*if (root.left == null && root.right == null) {
            if (root.data == element) {
                return true;
            } else {
                return false;
            }
        }
        DTNode r = root;
        DTNode temp = new DTNode(element);

        while (r.data != element) {
            if (temp.data.compareTo(r.data) < 0) {
                if (r.left == null) {
                    break;
                }
                r = r.left;
            } else {
                if (r.right == null) {
                    break;
                }
                r = r.right;
            }

            if (r == root) {
                break;
            }
        }

        if (r == root && r.data != element) {
            return false;
        } else if (r == root && r.data == element) {
            return true;
        } else if (r.data == element) {
            return true;
        }*/
        return false;
    }

    public String inorderReverse() {
        /*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		E,D,C,B,A
         */

        if (root == null) {
            return "";
        }

        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";
        /*while (r != null) {
            while (r != null) {
                //System.out.println(r.data);
                if (r.right != null && r.hasRightThread == false) {
                    arr[0] = r.right;
                    index++;
                    for (int i = index; i > 0; i--) {
                        arr[i] = arr[i - 1];
                    }
                }*/

 /*arr[0] = r;

                if (r.left != null) {
                    index++;
                    for (int i = index; i > 0; i--) {
                        arr[i] = arr[i - 1];
                    }
                } else{
                    index++;
                }

                r = r.left;*/

 /*boolean inArray = false;
                for (int i = 0; i < index; i++) {
                    if(r.data == arr[i].data){
                        inArray = true;
                    }
                }
                if(inArray == true){
                    break;
                }*/
        //}
        /*r = arr[0];
            index--;
            for (int i = 0; i < index; i++) {
                arr[i] = arr[i + 1];
            }
            while (index > 0 && r.right != null) {
                out += r.data + ",";
                r = arr[0];
                index--;
                for (int i = 0; i < index; i++) {
                    arr[i] = arr[i + 1];
                }
            }
            out += r.data + ",";
            if (index > 0) {
                r = arr[0];
                index--;
                for (int i = 0; i < index; i++) {
                    arr[i] = arr[i + 1];
                }
            } else {
                r = null;
            }
        }*/
 /*while (r != null) {
            if (r.right != null && inArray(r.right, visited, index) == false) {
                visited[index] = r.right;
                index++;
                r = r.right;
                if(index >= numNodes){
                    break;
                }
            } else {
                out += r.data + ",";
                if(r.left != null && r.hasLeftThread == false){
                    r = r.left;
                } else{
                    r = r.right;
                }
            }
        }*/

 /*while (r.right != null) {
            r = r.right;
        }

        String out = "";

        out += r.data + ",";

        while (r.left != null) {
            r = r.left;
            out += r.data + ",";
        }*/
        //System.out.println(""); && out.contains(r.right.data.toString()) == false
        boolean in = false;
        while (true) {
            while (r.right != null && r.hasRightThread == false) {
                if (out.contains(r.right.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.right.data) {
                            in = true;
                            break;
                        }
                    }
                    //int ind = out.indexOf(r.right.data.toString());
                    //int lc = out.indexOf(",", ind);
                    //int fc = out.lastIndexOf(",", ind);
                    /*if(fc < 0){
                        fc = 0;
                    }
                    String str = out.substring(fc, lc);
                    if(str.substring(0, 1).equals(",")){
                        str = str.substring(1);
                    }
                    for (int i = 0; i < r.right.data.toString().length(); i++) {
                        if(str.substring(0, i + 1).equals(r.right.data.toString().substring(0, i + 1))){
                            in = true;
                        } else{
                            in = false;
                        }
                    }*/
                    if (in == true) {
                        break;
                    }
                    /*if(str.equals(r.right.data.toString()) == true){
                        break;
                    }*/
                }
                /*
                for (int i = 0; i < out.length(); i++) {
                    if(r.right.data.toString().contains(out.substring(i, i + 1)) == true){
                        if(out.substring(i, i+2).contains(r.right.data.toString()) == true){
                            //System.out.println("T");
                            in = true;
                            break;
                        }
                    }
                }
                if(in == true){
                    break;
                }*/
                r = r.right;
            }
            in = false;
            visited[index] = new DTNode(r.data);
            index++;
            out += r.data + ",";
            r = r.left;
            if (r == null) {
                break;
            }
            /*if (r.right != null && r.hasRightThread == false && out.contains(r.right.data.toString()) == false) {
                r = r.right;
            } else if (r.left != null && r.hasLeftThread == false) {
                out += r.data + ",";
                r = r.left;
            } else {
                if (r.right != null && r.hasRightThread == false && out.contains(r.right.data.toString()) == false) {
                    r = r.right;
                } else if (r.left == null) {
                    out += r.data + ",";
                    break;
                }
                out += r.data + ",";
                r = r.left;
            }*/
        }

        out = out.substring(0, out.length() - 1);
        return out;
    }

    public String inorderReverseDetailed() {
        /*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the left
		branch of node E to node D, and a thread linking
		the left branch of node C to node B.
		
		This means the following string must be returned:
		
		E|D,C|B,A
         */

        if (root == null) {
            return "";
        }

        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";

        while (r.right != null) {
            r = r.right;
        }

        boolean in = true;

        while (true) {
            while (r.right != null && r.hasRightThread == false) {
                if (out.contains(r.right.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.right.data) {
                            in = true;
                            break;
                        }
                    }
                    if (in == true) {
                        break;
                    }
                }
                r = r.right;
            }
            in = false;
            visited[index] = new DTNode(r.data);
            index++;
            if (r.hasLeftThread == true) {
                out += r.data + "|";
            } else {
                out += r.data + ",";
            }
            r = r.left;
            if (r == null) {
                break;
            }
        }

        /*String out = "";

        out += r.data + "|";

        while (r.left != null) {
            r = r.left;
            if (r.hasLeftThread) {
                out += r.data + "|";
            } else {
                out += r.data + ",";
            }
        }*/
        out = out.substring(0, out.length() - 1);

        return out;
    }

    public String preOrder() {
        /*
		This method must return a string representation
		of the elements in the tree in preorder, left to right. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		B,A,D,C,E
         */

        if (root == null) {
            return "";
        }

        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";

        //out += r.data + ",";
        /*while (r.left != null) {
            r = r.left;
        }*/
        boolean in = false;
        while (r != null && index < numNodes) {
            for (int i = 0; i < index; i++) {
                if (visited[i].data == r.data) {
                    in = true;
                    break;
                }
            }
            if (in == false) {
                out += r.data + ",";
                visited[index] = new DTNode(r.data);
                index++;
            }
            in = false;
            /*if (r.left != null && out.contains(r.left.data.toString()) == true && index > 0) {
                for (int i = 0; i < index; i++) {
                    if (visited[i].data == r.left.data) {
                        in = true;
                        break;
                    }
                }
                if (in == false) {

                    //in = false;
                } else {
                    r = r.right;
                }
            }*/
			/*System.out.println(r.data);
            if(r.right != null){
                System.out.println("\t" + r.right.data);
            }*/

            if (r.left != null && r.hasLeftThread == false) {
                //System.out.println("QQQ");
                if (out.contains(r.left.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.left.data) {
                            in = true;
                            break;
                        }
                    }
                    /*if (in == false) {
                        r = r.left;
                    }*/
                }
                if (in == false) {
                    r = r.left;
                } else {
                    r = r.right;
                }
                //r = r.left;

            } else if (r.hasRightThread == false) {
                /*if(out.contains(r.left.data.toString()) == true && index > 0){
                    for (int i = 0; i < index; i++) {
                        if(visited[i].data == r.left.data){
                            in = true;
                            break;
                        }
                    }
                    if(in == true){
                        break;
                    }
                }*/
                r = r.right;
            } else {
                while (r != null && r.right != null && r.hasRightThread == true) {
                    r = r.right;
                    in = false;
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.data) {
                            in = true;
                            break;
                        }
                    }
                    if (in == false) {
                        out += r.data + ",";
                        visited[index] = new DTNode(r.data);
                        index++;
                    }
                    in = false;

                    if (r.right == null) {
                        break;
                    } else {
                        r = r.right;
                    }
                    /*for (int i = 0; i < index; i++) {
                        //System.out.println("VISITED AT: " + i + ": " + visited[i].data);
                        //System.out.println("R.DATA: " + r.data);
                        if (visited[i].data == r.data) {
                            //System.out.println("QQQ");
                            in = true;
                            break;
                        }
                    }
                    if (in == false) {
                        out += r.data + ",";
                        visited[index] = new DTNode(r.data);
                        index++;
                    }
                    in = false;*/
                    //System.out.println("R.DATA: " + r.data);
                    if (out.contains(r.data.toString()) == true && index > 0) {
                        for (int i = 0; i < index; i++) {
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                    }
                    if (in == false) {
                        break;
                    } else {
                        r = r.right;
                        in = false;
                        if (r == null) {
                            break;
                        }
                        if (out.contains(r.data.toString()) == true && index > 0) {
                            for (int i = 0; i < index; i++) {
                                if (visited[i].data == r.data) {
                                    in = true;
                                    break;
                                }
                            }
                        }
                        while (in == true) {
                            r = r.right;
                            in = false;
                            if (r == null) {
                                break;
                            }
                            if (out.contains(r.data.toString()) == true && index > 0) {
                                for (int i = 0; i < index; i++) {
                                    if (visited[i].data == r.data) {
                                        in = true;
                                        break;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < index; i++) {
                            if (r == null) {
                                break;
                            }
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                        if (in == false && r != null) {
                            out += r.data + ",";
                            visited[index] = new DTNode(r.data);
                            index++;
                        }
                        in = false;
                    }
                }
            }
            in = false;
            /*if (r == null) {
                break;
            }*/
            //r = r.right;

        }

        /*String out = "";

        out += r.data + "|";

        while (r.left != null) {
            r = r.left;
            if (r.hasLeftThread) {
                out += r.data + "|";
            } else {
                out += r.data + ",";
            }
        }*/

 /*DTNode r = root;

        String out = "";

        while (r.right != null) {
            out += r.data + ",";
            while (r.left != null) {
                r = r.left;
                out += r.data + ",";
            }
            r = r.right;
            r = r.right;
            out += r.data + ",";
        }*/
        out = out.substring(0, out.length() - 1);

        return out;
    }

    public String preorderDetailed() {
        /*
		This method must return a string representation
		of the elements in the tree in preorder, right to left. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal (see the last paragraph on page 240
		of the textbook for more detail on this procedure).
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the right
		branch of node A to node B, and a thread linking
		the right branch of node C to node D.
		
		This means the following string must be returned:
		
		B,A|D,C|E
         */

        if (root == null) {
            return "";
        }

        DTNode r = root;
        DTNode[] visited = new DTNode[numNodes];
        int index = 0;
        String out = "";
        boolean thread = false;

        boolean in = false;

        while (r != null && index < numNodes) {
            for (int i = 0; i < index; i++) {
                if (visited[i].data == r.data) {
                    in = true;
                    break;
                }
            }
            if (in == false) {
                if (thread == true) {
                    out += r.data;
                    thread = false;
                } else {
                    out += r.data + ",";
                }
                visited[index] = new DTNode(r.data);
                index++;
            } else {
                /*if (r.hasRightThread == true) {
                    out += "|";
                }*/
            }
            in = false;

            if (r.left != null && r.hasLeftThread == false) {
                if (out.contains(r.left.data.toString()) == true && index > 0) {
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.left.data) {
                            in = true;
                            break;
                        }
                    }
                }
                if (in == false) {
                    r = r.left;
                } else {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;
                }

            } else if (r.hasRightThread == false) {
                r = r.right;
            } else {
                out = out.substring(0, out.length() - 1);
                //out += "|";
                while (r != null && r.right != null && r.hasRightThread == true) {
                    if (r.hasRightThread == true) {
                        out += "|";
                    }
                    r = r.right;

                    in = false;
                    for (int i = 0; i < index; i++) {
                        if (visited[i].data == r.data) {
                            in = true;
                            break;
                        }
                    }

                    if (in == false) {
                        out += r.data + ",";
                        visited[index] = new DTNode(r.data);
                        index++;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                    }
                    in = false;

                    if (r.right == null) {
                        break;
                    } else {
                        /*if (r.hasRightThread == true) {
                            out += "|";
                        }*/
                        r = r.right;
                    }
                    if (out.contains(r.data.toString()) == true && index > 0) {
                        for (int i = 0; i < index; i++) {
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                    }
                    if (in == false) {
                        break;
                    } else {
                        if (r.hasRightThread == true) {
                            out += "|";
                        }
                        r = r.right;
                        in = false;
                        if (r == null) {
                            break;
                        }
                        if (out.contains(r.data.toString()) == true && index > 0) {
                            for (int i = 0; i < index; i++) {
                                if (visited[i].data == r.data) {
                                    in = true;
                                    break;
                                }
                            }
                        }

                        while (in == true) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                            r = r.right;
                            in = false;
                            if (r == null) {
                                break;
                            }
                            if (out.contains(r.data.toString()) == true && index > 0) {
                                for (int i = 0; i < index; i++) {
                                    if (visited[i].data == r.data) {
                                        in = true;
                                        break;
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < index; i++) {
                            if (r == null) {
                                break;
                            }
                            if (visited[i].data == r.data) {
                                in = true;
                                break;
                            }
                        }
                        if (in == false && r != null) {
                            out += r.data + ",";
                            visited[index] = new DTNode(r.data);
                            index++;
                            break;
                        } else if (in == true && r != null) {
                            if (r.hasRightThread == true) {
                                out += "|";
                            }
                        }
                        in = false;
                    }
                }
                /*if (r != null && r.hasRightThread == true) {
                    out += "|";
                }*/
            }
            in = false;
        }

        /*DTNode r = root;

        String out = "";

        while (r.right != null) {
            out += r.data + ",";
            while (r.left != null) {
                r = r.left;
                if (r.hasRightThread) {
                    out += r.data + "|";
                } else {
                    out += r.data + ",";
                }
            }
            r = r.right;
            r = r.right;
            out += r.data + ",";
        }*/
        if (/*out.substring(out.length() - 1).equals("|") ||*/out.substring(out.length() - 1).equals(",")) {
            out = out.substring(0, out.length() - 1);
        }

        return out;
    }

    public int getNumberOfNodes() {
        /*
		This method should count and return the number of nodes 
		currently in the tree.
         */

 /*if (root == null) {
            return 0;
        }

        DTNode r = root;
        int num = 0;

        while (r.right != null) {
            num++;
            r = r.right;
        }

        while (r.left != null) {
            num++;
            r = r.left;
        }

        return num;*/
        return numNodes;
    }

    public int getHeight() {
        /*
		This method should return the height of the tree. The height 
		of an empty tree is 0; the height of a tree with nothing but
		the root is 1.
         */

        if (root == null) {
            return 0;
        }

        return getRecursiveHeight(root);
    }

    public int getRecursiveHeight(DTNode node) {
        if (node == null) {
            return -1;
        }
        //System.out.println(node.data);
        if (node.hasLeftThread == true && node.right == null) {
            return 1;
        }
        if (node.hasRightThread == true && node.left == null) {
            return 1;
        }
        /*if (node.hasLeftThread == true || node.hasRightThread == true) {
            if (node.hasRightThread == false) {
                return getRecursiveHeight(node.right);
            } else if (node.hasLeftThread == false) {
                return getRecursiveHeight(node.left);
            }
            return 1;
        }*/
        int left = 0;
        int right = 0;
        if (node.hasLeftThread == false) {
            left = getRecursiveHeight(node.left);
        }
        if (node.hasRightThread == false) {
            right = getRecursiveHeight(node.right);
        }

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }

    }
}
