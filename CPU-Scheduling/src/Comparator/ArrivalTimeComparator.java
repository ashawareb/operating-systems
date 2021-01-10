/**
 * @author: AbdUlRahman Shawareb
 */
package Comparator;

import java.util.Comparator;

import Process.Process;

public class ArrivalTimeComparator implements Comparator<Process> {
    @Override
    public int compare(Process process1, Process process2) {
        return (process1.getArrivalTime() - process2.getArrivalTime());
    }
}
