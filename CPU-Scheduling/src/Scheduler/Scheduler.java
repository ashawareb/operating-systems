/**
 * @author: AbdUlRahman Shawareb
 */
package Scheduler;

import Process.Process;

import java.util.List;

public abstract class Scheduler {
    protected Integer numOfProcesses;
    protected Integer contextSwitchDuration;
    protected List<Process> processes;

    public Scheduler(Integer numOfProcesses, Integer contextSwitchDuration, List<Process> processes) {
        this.numOfProcesses = numOfProcesses;
        this.contextSwitchDuration = contextSwitchDuration;
        this.processes = processes;
    }

    public abstract void schedule();
}
