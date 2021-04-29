import java.util.Scanner;

public class BetterBully {
    Scanner sc;
    Node[] nodes;
    int noOfNodes;
    int messageComplexity=0;

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Highest node fails
        System.out.println("Node number " + nodes[getMax()].id + " fails");
        nodes[getMax()].active = false;

        // Random node notices that highest node has failed
        int InitiatorProcessId = 0;
        // int InitiatorProcessId = (int)Math.floor(Math.random()*(getMax()-0+1)+0);
        System.out.println("Node " + InitiatorProcessId + " notices the coordinator has failed");
        
        boolean notOver = true;
        while (notOver) {

            for (int i = InitiatorProcessId + 1; i < noOfNodes; i++) {
                System.out.println("Node " + InitiatorProcessId + " Passes Election(" + InitiatorProcessId
                            + ") message to process " + i);
                messageComplexity++;
            }

            for (int i = InitiatorProcessId +1; i < noOfNodes; i++) {
                if (nodes[i].active) {
                    System.out.println("Node " + i + " Passes Ok(" + i + ") message to node " + InitiatorProcessId);
                    messageComplexity++;
                } else {
                    System.out.println("Node " + i + " does not respond to the message from node " + InitiatorProcessId);
                    messageComplexity++;

                }
            }
            
            int coordinator = nodes[getMax()].id;
            System.out.println("Finally Node " + coordinator + " Becomes Coordinator");
            for (int i = coordinator - 1; i >= 0; i--) {
                if (nodes[i].active) {
                    System.out.println("Node " + coordinator + " Passes Coordinator(" + coordinator
                            + ") message to node " + i);
                    messageComplexity++;
                }
            }

            System.out.println("End of Election");
            System.out.println("Message complexity: "+messageComplexity);
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
        BetterBully b = new BetterBully();
        b.initialiseRing();
        b.performElection();

    }

}

 // Source: https://pocketstudyblog.wordpress.com/2019/04/14/bully-algorithm-java-implementation/