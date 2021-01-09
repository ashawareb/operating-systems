package Scheduler;

import java.util.List;

public class SRTF extends Scheduler {
    //Shortest Remaining Time First

    public SRTF(int numOfProcesses, int contextSwitchDuration, List<Process> processes) {
        super(numOfProcesses, contextSwitchDuration, processes);
    }

    @Override
    public void schedule() {

    }
}
