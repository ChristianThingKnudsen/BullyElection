import java.util.Scanner;

class Process {
    public int id;
    public boolean active;

    public Process(int id) {
        this.id = id;
        this.active = true;
    }
}

public class Bully {
    Scanner sc;
    Process[] processes;
    int noOfProcess;

    public Bully() {
        sc = new Scanner(System.in);
    }

    public void initialiseRing() {
        System.out.println("Enter number of processes:");
        noOfProcess = sc.nextInt();
        processes = new Process[noOfProcess];
        for (int i = 0; i < noOfProcess; i++) {
            processes[i] = new Process(i);
        }
    }

    public void performElection() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Process number " + processes[getMax()].id + " fails");
        processes[getMax()].active = false;

        int InitiatorProcessId = 0;
        boolean notOver = true;
        while (notOver) {

            boolean moreHigherProcesses = false;
            for (int i = InitiatorProcessId + 1; i < noOfProcess; i++) {
                if (processes[i].active) {
                    System.out.println("Process " + InitiatorProcessId + " Passes Election(" + InitiatorProcessId
                            + ") message to process " + i);
                    moreHigherProcesses = true;

                }
            }

            if (moreHigherProcesses) {

                for (int i = InitiatorProcessId + 1; i < noOfProcess; i++) {
                    if (processes[i].active) {
                        System.out.println(
                                "Process " + i + " Passes Ok(" + i + ") message to process " + InitiatorProcessId);
                    }

                }
                InitiatorProcessId++;

            }

            else {
                int coordinator = processes[getMax()].id;
                System.out.println("Finally Process " + coordinator + " Becomes Coordinator");
                for (int i = coordinator - 1; i >= 0; i--) {
                    if (processes[i].active) {
                        System.out.println("Process " + coordinator + "Passes Coordinator(" + coordinator
                                + ") message to process " + i);
                    }
                }

                System.out.println("End of Election");
                notOver = false;
                break;
            }
        }

    }

    public int getMax() {
        int maxId = -99;
        int maxIdIndex = 0;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].active && processes[i].id > maxId) {
                maxId = processes[i].id;
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