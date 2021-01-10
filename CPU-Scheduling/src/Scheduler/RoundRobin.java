package Scheduler;

import Comparator.ArrivalTimeComparator;
import Comparator.BurstTimeComparator;
import Process.Process;
import javafx.util.Pair;

import java.util.*;

public class RoundRobin extends Scheduler {
    private ArrayList<Integer> burst;
    private ArrayList<Integer> arrival;
    private ArrayList<Integer> waiting;
    private List<Pair<Integer, String>> executionOrder;
    private Integer time;
    private Integer quantum;


    public RoundRobin(Integer quantum, Integer contextSwitchDuration, List<Process> processes) {
        super(processes.size(), contextSwitchDuration, processes);
        burst = new ArrayList<>();
        arrival = new ArrayList<>();
        waiting = new ArrayList<>();
        executionOrder = new ArrayList<>();
        this.quantum = quantum;
        this.time = 0;
        for (Process ob : processes) {
            burst.add(ob.getBurstTime());
            arrival.add(ob.getArrivalTime());
        }
    }

    public void waitingTimer(List<Process> processes, int quantum, int compeletiontime[]) {
        int remainingTime[] = new int[processes.size()];
        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
        }
        //time=0;
        int arrivaltime = 0;
        while (true) {
            boolean finished = true;
            for (int i = 0; i < processes.size(); i++) {
                if (remainingTime[i] > 0) {
                    finished = false;
                    if (remainingTime[i] > quantum && processes.get(i).getArrivalTime() <= arrivaltime) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                        arrivaltime++;
                        executionOrder.add(new Pair<>(time, processes.get(i).getProcessID()));
                    } else {
                        if (processes.get(i).getArrivalTime() <= arrivaltime) {
                            arrivaltime++;
                            time += remainingTime[i];
                            remainingTime[i] = 0;
                            compeletiontime[i] = time;
                            executionOrder.add(new Pair<>(time, processes.get(i).getProcessID()));
                        }
                    }
                }
            }
            if (finished == true) {
                break;
            }
        }

    }

    public void turnaroundTimer(List<Process> processes, int completiontime[]) {
        for (int i = 0; i < processes.size(); i++) {
            int tat = completiontime[i] - processes.get(i).getArrivalTime();
            processes.get(i).setTurnaroundTime(tat);
            int wt = processes.get(i).getTurnaroundTime() - processes.get(i).getBurstTime();
            processes.get(i).setWaitingTime(wt);
        }
    }

    public void avaregeTimer(List<Process> processes, int quantum) {
        int completionTime[] = new int[processes.size()];
        waitingTimer(processes, quantum, completionTime);
        turnaroundTimer(processes, completionTime);
        out(processes);
    }

    @Override
    public void schedule() {
        avaregeTimer(processes, quantum);
    }

    private void out(List<Process> information) {
        Double avaregeWaitingTime = 0.0;
        Double avaregeTurnaroundTime = 0.0;
        System.out.println("Time \t Process processing");
        for (Pair<Integer, String> e : executionOrder) {
            System.out.println(e.getKey() + " \t\t " + e.getValue());
        }
        System.out.println("+-------------------------------------------------+");
        for (Process ob : information) {
            System.out.println("| Process name:               |   " + ob.getProcessName() + "               |");
            System.out.println("| Process ID:                 |   " + ob.getProcessID() + "               |");
            System.out.println("| Process waiting time:       |   " + ob.getWaitingTime() + "               |");
            System.out.println("| Process turnaround time:    |   " + ob.getTurnaroundTime() + "               |");
            System.out.println("+-------------------------------------------------+");
            avaregeWaitingTime += ob.getWaitingTime() * 1.0;
            avaregeTurnaroundTime += ob.getTurnaroundTime() * 1.0;
        }
        avaregeTurnaroundTime /= information.size();
        avaregeWaitingTime /= information.size();
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Avarege Waiting time:       |   " + avaregeWaitingTime + "             |");
        System.out.println("| Avarege Turnaround time:    |   " + avaregeTurnaroundTime + "             |");
        System.out.println("+-------------------------------------------------+");
    }
}
