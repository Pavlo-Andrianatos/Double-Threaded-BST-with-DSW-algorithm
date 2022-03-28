public class Tester 
{
	public static void main(String[] args) throws Exception
	{          
            DoubleThreadedBST dt = new DoubleThreadedBST();
            
            dt.insert(9);
            dt.insert(6);
            dt.insert(12);
            dt.insert(4);
            dt.insert(8);
            dt.insert(10);
            dt.insert(16);
            dt.insert(2);
            dt.insert(5);
            dt.insert(11);
            dt.insert(14);
            System.out.println("!!!DONE INSERT!!!");
            System.out.println(dt.inorderReverse());
            System.out.println(dt.inorderReverseDetailed());
            System.out.println(dt.preOrder());
            System.out.println(dt.preorderDetailed());
            
            System.out.println(dt.contains(14));
            System.out.println(dt.getNumberOfNodes());
            System.out.println(dt.getHeight());
            
            dt.delete(100);
            System.out.println("DONE Deleting");
            System.out.println(dt.inorderReverse());
            System.out.println(dt.inorderReverseDetailed());
            System.out.println(dt.preOrder());
            System.out.println(dt.preorderDetailed());
            
            System.out.println(dt.contains(14));
            System.out.println(dt.getNumberOfNodes());
            System.out.println(dt.getHeight());
            
            /*System.out.println("");
            System.out.println("!!!BALANCING!!!");
            DoubleThreadedDSW ds = new DoubleThreadedDSW();
            ds.balance();
            
            
            System.out.println(dt.inorderReverse());
            System.out.println(dt.inorderReverseDetailed());
            System.out.println(dt.preOrder());
            System.out.println(dt.preorderDetailed());*/
            
            System.out.println("STARTING CLONE");
           /* DoubleThreadedBST dt2 = dt.clone();
            System.out.println(dt.preorderDetailed());
            System.out.println(dt2.preorderDetailed());
            
            dt.delete(14);
            System.out.println(dt.preorderDetailed());
            System.out.println(dt2.preorderDetailed());*/
            
            DoubleThreadedBST dt3;
            
            dt3 = new DoubleThreadedBST(dt);
            //System.out.println(dt2.preorderDetailed());
            
            System.out.println(dt.preorderDetailed());
            System.out.println(dt3.preorderDetailed());
            
            dt.delete(14);
            
            System.out.println(dt.preorderDetailed());
            System.out.println(dt3.preorderDetailed());
	}
}
