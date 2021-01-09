package Main;

import Process.*;
import Scheduler.*;
import Process.Process;
import Scheduler.SRTF;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        int contextSwitchingDuration;
        int numOfPeocesses;
        System.out.print("Enter number of process: ");
        numOfPeocesses = input.nextInt();
        for (int i = 0; i < numOfPeocesses; i++) {
            Process p = new Process();
            System.out.print("Enter Process name: ");
            p.setProcessName(input.next());
            System.out.print("Enter Process ID: ");
            p.setProcessID(input.next());
            System.out.print("Enter Process arrival time: ");
            p.setArrivalTime(input.nextInt());
            System.out.print("Enter Process burst time: ");
            p.setBurstTime(input.nextInt());
            processes.add(p);
        }
        System.out.print("Enter Context switching duration: ");
        contextSwitchingDuration = input.nextInt();
        SRTF ob = new SRTF(new LinkedList<>(processes), contextSwitchingDuration);
        ob.schedule();
    }
}
