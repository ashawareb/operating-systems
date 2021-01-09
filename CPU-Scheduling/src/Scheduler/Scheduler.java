package Scheduler;

import java.util.List;

public abstract class Scheduler {
    protected int numOfProcesses;
    protected int contextSwitchDuration;
    protected List<Process> processes;

    public Scheduler(int numOfProcesses, int contextSwitchDuration, List<Process> processes) {
        this.numOfProcesses = numOfProcesses;
        this.contextSwitchDuration = contextSwitchDuration;
        this.processes = processes;
    }

    public abstract void schedule();
}
