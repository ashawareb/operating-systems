package Scheduler;

import java.util.List;

public class ShortestJobFirst extends Scheduler {


    public ShortestJobFirst(int numOfProcesses, int contextSwitchDuration, List<Process> processes) {
        super(numOfProcesses, contextSwitchDuration, processes);
    }

    @Override
    public void schedule() {

    }
}
