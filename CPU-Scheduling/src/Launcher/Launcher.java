/**
 * @author: AbdUlRahman Shawareb
 */
package Launcher;

import Process.Process;
import Scheduler.SRTF;
import Scheduler.FCFS;
import Scheduler.RoundRobin;
import Scheduler.PriorityScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {
    private List<Process> processes;
    Scanner input;

    public Launcher() {
        processes = new ArrayList<Process>();
        input = new Scanner(System.in);
    }

    public void FCFSandSRTFLauncher(int numOfProcesses, int algorithmType) {
        Process process = new Process();
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("Enter Process name: ");
            process.setProcessName(input.next());
            System.out.print("Enter Process ID: ");
            process.setProcessID(input.next());
            System.out.print("Enter Process arrival time: ");
            process.setArrivalTime(input.nextInt());
            System.out.print("Enter Process burst time: ");
            process.setBurstTime(input.nextInt());
            processes.add(process);
        }
        System.out.print("Enter Context switching duration: ");
        int contextSwitchingDuration = input.nextInt();
        if (algorithmType == 1) {
            FCFS ob = new FCFS(processes, contextSwitchingDuration);
            ob.schedule();
        } else {
            SRTF ob = new SRTF(processes, contextSwitchingDuration);
            ob.schedule();
        }
    }

    public void RoundRobinLauncher(int numOfProcesses) {
        Process process = new Process();
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("Enter Process name: ");
            process.setProcessName(input.next());
            System.out.print("Enter Process ID: ");
            process.setProcessID(input.next());
            System.out.print("Enter Process arrival time: ");
            process.setArrivalTime(input.nextInt());
            System.out.print("Enter Process burst time: ");
            process.setBurstTime(input.nextInt());
            processes.add(process);
        }
        int quantum = input.nextInt();
        RoundRobin ob = new RoundRobin(quantum, processes);
        ob.schedule();
    }

    public void PriorityLauncher(int numOfProcesses) {
        Process process = new Process();
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("Enter Process name: ");
            process.setProcessName(input.next());
            System.out.print("Enter Process ID: ");
            process.setProcessID(input.next());
            System.out.print("Enter Process arrival time: ");
            process.setArrivalTime(input.nextInt());
            System.out.print("Enter Process burst time: ");
            process.setBurstTime(input.nextInt());
            System.out.print("Enter Process priority: ");
            process.setUpdatedPriority(input.nextInt());
            processes.add(process);
        }
        PriorityScheduler ob = new PriorityScheduler(processes);
        ob.schedule();
    }
}
