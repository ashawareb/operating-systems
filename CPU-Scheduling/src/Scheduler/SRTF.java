package Scheduler;

import java.util.*;

import Process.Process;
import Comparator.ArrivalTimeComparator;
import Comparator.BurstTimeComparator;
import javafx.util.Pair;


public class SRTF extends Scheduler {
    //Shortest Remaining Time First
    private PriorityQueue<Process> burst;
    private PriorityQueue<Process> arrival;
    private List<Pair<Integer, String>> executionOrder;
    private List<Process> information;
    private int stop;
    private Map<String, Integer> actual;

    public SRTF(List<Process> processes, int contextSwitchDuration) {
        super(processes.size(), contextSwitchDuration, processes);
        stop = processes.size();
        executionOrder = new ArrayList<>();
        information = new ArrayList<>();
        this.contextSwitchDuration = contextSwitchDuration;
        burst = new PriorityQueue<>(new BurstTimeComparator());
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
            if (p != null) {
                if (!p.getProcessID().equals(burst.peek().getProcessID()) && p.getBurstTime() > 0) {
                    i += contextSwitchDuration;
                }
            }
            p = burst.poll();
            int pBurstTime = p.getBurstTime();
            pBurstTime--;
            p.setBurstTime(pBurstTime);
            executionOrder.add(new Pair<>(i, p.getProcessID()));
            if (p.getBurstTime() > 0) {
                burst.add(p); //Add to queue if not finishid
            } else {
                stop--;
                int pWaitingTime = i - p.getArrivalTime() - actual.get(p.getProcessID());
                int pTurnaroundTime = i - p.getArrivalTime() + 1;
                p.setWaitingTime(pWaitingTime);
                p.setTurnaroundTime(pTurnaroundTime);
                information.add(p);
                i += contextSwitchDuration;
                if (stop == 0) {
                    break;
                }
            }
        }
        //out();
    }

    private void out() {

    }
}
