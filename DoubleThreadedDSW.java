public class DoubleThreadedDSW<T extends Comparable<? super T>> extends DoubleThreadedBST<T> {

    /*
	TODO: The DoubleThreadedDSW class inherits from the 
	DoubleThreadedBST class. A lot of the functionality 
	required for an DSW tree will be handled in your 
	DoubleThreadedBST class. You will have to override all 
	appropriate methods inherited from DoubleThreadedBST 
	in order to create a functional tree balanced by the
	DSW algorithm.
	
	You must add any additional methods or data fields which 
	you might need to accomplish your task.
     */
    DTNode<T> root;

    public DoubleThreadedDSW() {
        /*
		The default constructor.
         */
        //super();
        //root = dt.getRoot();
        //DoubleThreadedBST dt = clone();
        //root = dt.getRoot();
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!    " + dt.getRoot());
        root = null;
    }

    /*@Override
    public boolean insert(T element) {
        boolean done = false;
        done = super.insert(element);
        balance();
        return done;
    }*/

 /*@Override
    public boolean delete(T element) {
        boolean done = false;
        done = super.delete(element);
        balance();
        return done;
    }*/
    public void balance() {
        /*
		You must implement the DSW algorithm in order to
		balance this tree. Be sure to perform any updates
		that are required for any of the threads in the tree.
         */

        //return false;
        //if (root != null) {
        //DoubleThreadedBST dt = clone();
        root = getRoot();
        removeThreads(root);

        createBackbone();
        //makeThreadsDSW(root);
		
        createPerfectBST();

        makeThreadsDSW(root);
        //dt = new DoubleThreadedBST(dt);
        //}
        setRoot(root);
    }

    public void createBackbone() {
        DTNode gp = null;
        DTNode p = root;
        DTNode c;

        while (p != null) {
            c = p.left;
            if (c != null) {
                gp = rotateRight(gp, p, c);
                p = c;
            } else {
                gp = p;
                p = p.right;
            }
        }
    }

    public void createPerfectBST() {
        double y = Math.log(numNodes + 1) / Math.log(2);
        int t = (int) Math.floor(y);
        //t = t - 1;

        int m = (int) Math.pow(2, t);
        m--;
        rotations(numNodes - m);
        //m = m - (numNodes - m);
        while (m > 1) {
            rotations(m /= 2);
        }

        /*DTNode gp = null;
        DTNode p = root;
        DTNode c = root.right;
        for (int i = (numNodes - m); i > 0; i--) {
            if (c != null) {
                if (gp != null) {
                    gp.right = c;
                } else {
                    root = c;
                }
                p.right = c.left;
                c.left = p;

                gp = c;
                p = gp.right;
                c = p.right;
            } else {
                break;
            }
        }

        while (m > 1) {
            gp = null;
            p = root;
            c = root.right;
            for (int i = (m /= 2); i > 0; i--) {
                System.out.println(i);
                if (c != null) {
                    if (gp != null) {
                        gp.right = c;
                    } else {
                        root = c;
                    }
                    p.right = c.left;
                    c.left = p;

                    gp = c;
                    p = gp.right;
                    if (p == null) {
                        break;
                    }
                    c = p.right;
                } else {
                    break;
                }
            }
            break;
        }*/
    }

    public void rotations(int m) {
        DTNode gp = null;
        DTNode p = root;
        DTNode c = root.right;
        while (m > 0) {
            if (c != null) {
                rotateLeft(gp, p, c);
                gp = c;
                p = gp.right;
                if (p == null) {
                    break;
                }
                c = p.right;
            } else {
                break;
            }
            m--;
        }
    }

    public void rotateLeft(DTNode gp, DTNode p, DTNode c) {
        if (gp != null) {
            gp.right = c;
            c.parent = gp;
        } else {
            root = c;
            c.parent = null;
        }
        if (c.left != null) {
            c.left.parent = p;
        }
        p.right = c.left;
        c.left = p;
        p.parent = c;
    }

    public DTNode rotateRight(DTNode gp, DTNode p, DTNode c) {
        if (gp != null) {
            gp.right = c;
            c.parent = gp;
        } else {
            root = c;
            c.parent = null;
        }
        if (c.right != null) {
            c.right.parent = p;
        }
        p.left = c.right;
        c.right = p;
        p.parent = c;
        return gp;
    }

    public void removeThreads(DTNode node) {
        if (node == null) {
            return;
        }
        if (node.hasLeftThread == true) {
            node.left = null;
            node.hasLeftThread = false;
        }
        if (node.hasRightThread == true) {
            node.right = null;
            node.hasRightThread = false;
        }
        removeThreads(node.left);
        removeThreads(node.right);
    }

    public void makeThreadsDSW(DTNode node) {
        if (node == null) {
            return;
        }
        //if (node.hasLeftThread == false /*|| node.left != null*/) {
            makeThreadsDSW(node.left);
        //}
        //if (node.hasRightThread == false /*|| node.right != null*/) {
            makeThreadsDSW(node.right);
        //}
        if (node.hasLeftThread == false && node.left == null) {
            DTNode p = node.parent;
            while (true) {
                if (p == null) {
                    //node.hasLeftThread = false;
                    break;
                }

                if (p.data.compareTo(node.data) < 0) {
                    node.left = p;
                    node.hasLeftThread = true;
                    break;
                } else {
                    p = p.parent;
                }
            }
        }

        if (node.hasRightThread == false && node.right == null) {
            DTNode p = node.parent;
            while (true) {
                if (p == null) {
                    //node.hasRightThread = false;
                    break;
                }

                if (p.data.compareTo(node.data) > 0) {
                    node.right = p;
                    node.hasRightThread = true;
                    break;
                } else {
                    p = p.parent;
                }
            }
        }
    }
}
