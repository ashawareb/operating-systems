/**
 * @author: AbdUlRahman Shawareb
 */
package Main;

import Scheduler.PriorityScheduler;
import Scheduler.RoundRobin;
import Scheduler.FCFS;
import Scheduler.SRTF;
import Launcher.Launcher;
import Process.Process;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Launcher ob = new Launcher();
        System.out.println("Welcome to CPU-Scheduler");
        System.out.print("Enter number of processes: ");
        int numOfProcesses = input.nextInt();
        while (numOfProcesses < 1) {
            System.out.print("Enter valid value: ");
            numOfProcesses = input.nextInt();
        }
        System.out.println("Enter which Schduling algorithm to use: ");
        System.out.println("\t 1 - FCFS algorithm");
        System.out.println("\t 2 - SRTF algorithm");
        System.out.println("\t 3 - Round Robin algorithm");
        System.out.println("\t 4 - Priority algorithm");
        System.out.println("\t 5 - Exit");
        System.out.print("Algorithm type: ");
        int algorithmType = input.nextInt();
        while (algorithmType < 1 || algorithmType > 5) {
            System.out.println("Enter valid value: ");
            algorithmType = input.nextInt();
        }
        switch (algorithmType) {
            case 1 -> ob.FCFSandSRTFLauncher(numOfProcesses, algorithmType);
            case 2 -> ob.FCFSandSRTFLauncher(numOfProcesses, algorithmType);
            case 3 -> ob.RoundRobinLauncher(numOfProcesses);
            case 4 -> ob.PriorityLauncher(numOfProcesses);
            case 5 -> System.exit(0);
        }
    }
}
