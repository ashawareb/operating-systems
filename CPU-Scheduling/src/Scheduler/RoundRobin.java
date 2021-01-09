package Scheduler;

import Process.Process;

import java.util.List;

public class RoundRobin extends Scheduler {
    private Integer quantum;

    public RoundRobin(Integer numOfProcesses, Integer contextSwitchDuration, List<Process> processes) {
        super(numOfProcesses, contextSwitchDuration, processes);
    }

    @Override
    public void schedule() {

    }
}
