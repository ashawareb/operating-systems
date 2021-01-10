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
        int quantm;
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
            //System.out.print("Enter Process priority: ");
            //p.setUpdatedPriority(input.nextInt());
            processes.add(p);
        }
        System.out.print("Enter Context switching duration: ");
        contextSwitchingDuration = input.nextInt();
        System.out.print("Enter quantm number: ");
        quantm = input.nextInt();
        RoundRobin ob = new RoundRobin(quantm, contextSwitchingDuration, new LinkedList<>(processes));
        ob.schedule();
    }
}
