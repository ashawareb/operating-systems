package Scheduler;

import Comparator.ArrivalTimeComparator;
import Comparator.BurstTimeComparator;
import Process.Process;
import javafx.util.Pair;

import java.util.*;

public class RoundRobin extends Scheduler {
    private Integer quantum;
    private int stop;
    private PriorityQueue<Process> burst;
    private PriorityQueue<Process> arrival;
    private List<Pair<Integer, String>> executionOrder;
    private List<Process> information;
    private Map<String, Integer> actual;

    public RoundRobin(Integer quantum, Integer contextSwitchDuration, List<Process> processes) {
        super(processes.size(), contextSwitchDuration, processes);
        this.quantum = quantum;
        stop = processes.size();
        executionOrder = new ArrayList<>();
        information = new ArrayList<>();
        burst = new PriorityQueue<>(/*new BurstTimeComparator()*/new ArrivalTimeComparator());
        arrival = new PriorityQueue<>(new ArrivalTimeComparator());
        arrival.addAll(processes);
        actual = new HashMap<>();
        for (Process ob : processes) {
            actual.put(ob.getProcessID(), ob.getBurstTime());
        }
    }

    @Override
    public void schedule() {
        Process p = null;
        for (int i = 0; i <= 250000; i++) {
            while (!arrival.isEmpty() && arrival.peek().getArrivalTime() <= i) {
                Process arrived = arrival.poll(); //get the process in the turn and remove it from the arrival
                burst.add(arrived);
            }
            if (burst.isEmpty()) {
                continue;
            }
            /*if (p != null) {
                if (!p.getProcessID().equals(burst.peek().getProcessID()) && p.getBurstTime() > 0) {
                    i += contextSwitchDuration;
                }
            }*/
            p = burst.poll();
            executionOrder.add(new Pair<>(i, p.getProcessID()));
            //p.setBurstTime(p.getBurstTime() - quantum);
            if (p.getBurstTime() > quantum) {
                int pBurstTime = p.getBurstTime();
                pBurstTime -= quantum;
                p.setBurstTime(pBurstTime);
                i += quantum + contextSwitchDuration;
                burst.add(p);
            } else {
                stop--;
                i += (quantum - p.getBurstTime()) + contextSwitchDuration;
                p.setTurnaroundTime(i - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - actual.get(p.getProcessID()));
                information.add(p);
                if (stop == 0) {
                    break;
                }
            }
            /*if (p.getBurstTime() > 0) {
                burst.add(p);
            } else {
                stop--;
                int pWaitingTime = i - p.getArrivalTime() - actual.get(p.getProcessID()) + 1;
                int pTurnaroundTime = i - p.getArrivalTime() + 1;
                p.setWaitingTime(pWaitingTime);
                p.setTurnaroundTime(pTurnaroundTime);
                information.add(p);
                i += contextSwitchDuration;
                if (stop == 0) {
                    break;
                }
            }*/
        }
        out();
    }

    private void out() {
        Double avaregeWaitingTime = 0.0;
        Double avaregeTurnaroundTime = 0.0;
        System.out.println("Tim, \t Process processing");
        for (Pair<Integer, String> e : executionOrder) {
            System.out.println(e.getKey() + " \t\t " + e.getValue());
        }
        System.out.println("+-------------------------------------------------+");
        for (Process ob : information) {
            System.out.println("| Process name:               |   " + ob.getProcessName() + "              |");
            System.out.println("| Process ID:                 |   " + ob.getProcessID() + "              |");
            System.out.println("| Process waiting time:       |   " + ob.getWaitingTime() + "               |");
            System.out.println("| Process turnaround time:    |   " + ob.getTurnaroundTime() + "               |");
            System.out.println("+-------------------------------------------------+");
            avaregeWaitingTime += ob.getWaitingTime() * 1.0 / information.size();
            avaregeTurnaroundTime += ob.getTurnaroundTime() * 1.0 / information.size();
        }
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Avarege Waiting time:       |   " + avaregeWaitingTime + "             |");
        System.out.println("| Avarege Turnaround time:    |   " + avaregeTurnaroundTime + "             |");
        System.out.println("+-------------------------------------------------+");
    }
}
