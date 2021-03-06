import java.util.Scanner;

public class Bully {
    Scanner sc;
    Node[] nodes;
    int noOfNodes;
    int messageComplexity=0;

    public Bully() {
        sc = new Scanner(System.in);
    }

    public void initialiseRing() {
        noOfNodes = 6; 
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

        System.out.println("Node number " + nodes[getMax()].id + " fails");
        nodes[getMax()].active = false;

        // int InitiatorProcessId = 0;
        int InitiatorProcessId = (int)Math.floor(Math.random()*(getMax()-0+1)+0); 
        System.out.println("Node " + InitiatorProcessId + " notices the coordinator has failed");
        boolean notOver = true;
        while (notOver) {

            boolean moreHigherProcesses = false;
            for (int i = InitiatorProcessId + 1; i < noOfNodes; i++) {
                    System.out.println("Node " + InitiatorProcessId + " Passes Election(" + InitiatorProcessId
                            + ") message to process " + i);
                    messageComplexity++;
                    moreHigherProcesses = true;

            }

            if (moreHigherProcesses) {

                for (int i = InitiatorProcessId + 1; i < noOfNodes; i++) {
                    if (nodes[i].active) {
                        System.out.println(
                                "Node " + i + " Passes Ok(" + i + ") message to process " + InitiatorProcessId);
                        messageComplexity++;
                    }

                }
                InitiatorProcessId++;

            }

            else {
                int coordinator = nodes[getMax()].id;
                System.out.println("Finally Node " + coordinator + " Becomes Coordinator");
                for (int i = coordinator - 1; i >= 0; i--) {
                    if (nodes[i].active) {
                        System.out.println("Node " + coordinator + "Passes Coordinator(" + coordinator
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

 // Source: https://pocketstudyblog.wordpress.com/2019/04/14/bully-algorithm-java-implementation/