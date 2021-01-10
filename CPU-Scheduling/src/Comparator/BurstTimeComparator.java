/**
 * @author: AbdUlRahman Shawareb
 */
package Comparator;

import java.util.Comparator;

import Process.Process;


public class BurstTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process process1, Process process2) {
        if (process1.getBurstTime().equals(process2.getBurstTime())) { // .equals ==> "==" may cause an error
            return (process1.getArrivalTime() - process2.getArrivalTime());
        } else {
            return (process1.getBurstTime() - process2.getBurstTime());
        }
    }
}
