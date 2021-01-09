package Comparator;

import java.util.Comparator;

import Process.Process;

public class PriorityComparator implements Comparator<Process> {
    @Override
    public int compare(Process process1, Process process2) {
        if (process1.getArrivalTime().equals(process2.getArrivalTime())) {
            if (process1.getUpdatedPriority().equals(process2.getUpdatedPriority())) {
                return Integer.compare(process1.getBurstTime(), process2.getBurstTime());
            }
            return Integer.compare(process1.getUpdatedPriority(), process2.getUpdatedPriority());
        }
        return Integer.compare(process1.getArrivalTime(), process2.getArrivalTime());
    }
}
