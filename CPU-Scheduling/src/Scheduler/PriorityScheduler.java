package Scheduler;

import Process.Process;
import Comparator.PriorityComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityScheduler extends Scheduler {
    private List<Process> order;
    private PriorityQueue<Process> priority;

    public PriorityScheduler(List<Process> processes) {
        super(processes.size(), 0, processes);
        order = new ArrayList<Process>();
        priority = new PriorityQueue<>(numOfProcesses, new PriorityComparator());
    }

    @Override
    public void schedule() {
        Process running = null;
        //List<Process> order = new ArrayList<Process>();
        //PriorityQueue<Process> priority = new PriorityQueue<>(numOfProcesses, new PriorityComparator());
        for (Process ob : processes) {
            ob.setOriginalPriority(ob.getUpdatedPriority());
            priority.add(ob);
        }
        for (int i = 0; i < 250000; i++) {
            for (Process ob : priority) {
                if (ob.getArrivalTime() <= i) {
                    ob.setAliveTime((i - ob.getArrivalTime()) / 10);    //Process age
                    ob.setUpdatedPriority(Math.max(0, ob.getOriginalPriority() - ob.getAliveTime()));
                }
            }
            for (Process ob : priority) {
                if (ob.getArrivalTime() <= i) {
                    running = priority.poll();
                    break;
                }
            }
            if (running != null) {
                order.add(running);
                running.setTurnaroundTime(i + running.getBurstTime());
                running.setWaitingTime(running.getTurnaroundTime() - running.getArrivalTime());
                i = i + running.getBurstTime() - 1;
                running = null;
            }
        }
        out();
    }

    private void out() {
        Double avaregeWaitingTime = 0.0;
        Double avaregeTurnaroundTime = 0.0;
        System.out.println("+-------------------------------------------------+");
        for (Process ob : order) {
            System.out.println("| Process name:               |   " + ob.getProcessName() + "               |");
            System.out.println("| Process ID:                 |   " + ob.getProcessID() + "               |");
            System.out.println("| Process waiting time:       |   " + ob.getWaitingTime() + "               |");
            System.out.println("| Process turnaround time:    |   " + ob.getTurnaroundTime() + "               |");
            System.out.println("+-------------------------------------------------+");
            avaregeWaitingTime += ob.getWaitingTime() * 1.0 / order.size();
            avaregeTurnaroundTime += ob.getTurnaroundTime() * 1.0 / order.size();
        }
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Avarege Waiting time:       |   " + avaregeWaitingTime + "             |");
        System.out.println("| Avarege Turnaround time:    |   " + avaregeTurnaroundTime + "             |");
        System.out.println("+-------------------------------------------------+");
    }
}
