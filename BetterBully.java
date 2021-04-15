import java.util.Scanner;

public class BetterBully {
    Scanner sc;
    Node[] nodes;
    int noOfNodes;

    public BetterBully() {
        sc = new Scanner(System.in);
    }

    public void initialiseRing() {
        System.out.println("Enter number of processes:");
        noOfNodes = sc.nextInt();
        nodes = new Node[noOfNodes];
        for (int i = 0; i < noOfNodes; i++) {
            nodes[i] = new Node(i);
        }
    }

    public void performElection() {
        // Test
        System.out.println("Christian is tyk");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Highest node fails
        System.out.println("Node number " + nodes[getMax()].id + " fails");
        nodes[getMax()].active = false;

        // Random node notices that highest node has failed
        int InitiatorProcessId = (int)Math.floor(Math.random()*(getMax()-0+1)+0); 
        
        boolean notOver = true;
        while (notOver) {

            for (int i = InitiatorProcessId + 1; i < noOfNodes; i++) {
                if (nodes[i].active) {
                    System.out.println("Node " + InitiatorProcessId + " Passes Election(" + InitiatorProcessId
                            + ") message to process " + i);
                }
            }

            int coordinator = nodes[getMax()].id;
            System.out.println("Finally Node " + coordinator + " Becomes Coordinator");
            for (int i = coordinator - 1; i >= 0; i--) {
                if (nodes[i].active) {
                    System.out.println("Node " + coordinator + "Passes Coordinator(" + coordinator
                            + ") message to node " + i);
                }
            }

            System.out.println("End of Election");
            notOver = false;
            break;
            
        }

    }

    public int getMax() {
        int maxId = -99;
        int maxIdIndex = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].active && nodes[i].id > maxId) {
                maxId = nodes[i].id;
                maxIdIndex = i;
            }
        }
        return maxIdIndex;
    }

    public static void main(String[] args) {
        Bully b = new Bully();
        b.initialiseRing();
        b.performElection();

    }

}

/*
 * OP
 * 
 * C:\Users\Garry\Desktop\CLIX\Bully>java Bully Enter No of Processes 5 Process
 * no 4 fails Process 0Passes Election(0) message to process 1 Process 0Passes
 * Election(0) message to process 2 Process 0Passes Election(0) message to
 * process 3 Process 1Passes Ok(1) message to process 0 Process 2Passes Ok(2)
 * message to process 0 Process 3Passes Ok(3) message to process 0 Process
 * 1Passes Election(1) message to process 2 Process 1Passes Election(1) message
 * to process 3 Process 2Passes Ok(2) message to process 1 Process 3Passes Ok(3)
 * message to process 1 Process 2Passes Election(2) message to process 3 Process
 * 3Passes Ok(3) message to process 2 Finally Process 3 Becomes Coordinator
 * Process 3Passes Coordinator(3) message to process 2 Process 3Passes
 * Coordinator(3) message to process 1 Process 3Passes Coordinator(3) message to
 * process 0 End of Election
 * 
 */

 // Code found at: https://pocketstudyblog.wordpress.com/2019/04/14/bully-algorithm-java-implementation/