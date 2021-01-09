package Process;

public class Process {
    private String processName;
    private String processID;
    private Integer arrivalTime;
    private Integer burstTime;
    private Integer waitingTime;
    private Integer turnaroundTime;
    private Integer aliveTime;
    private Integer originalPriority;
    private Integer updatedPriority;

    public Process(Process process) {
        this.processName = process.processName;
        this.processID = process.processID;
        this.arrivalTime = process.arrivalTime;
        this.burstTime = process.burstTime;
        this.waitingTime = process.waitingTime;
        this.turnaroundTime = process.turnaroundTime;
        this.aliveTime = process.aliveTime;
        this.originalPriority = process.originalPriority;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Integer getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public Integer getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(int aliveTime) {
        this.aliveTime = aliveTime;
    }

    public Integer getOriginalPriority() {
        return originalPriority;
    }

    public void setOriginalPriority(int originalPriority) {
        this.originalPriority = originalPriority;
    }

    public Integer getUpdatedPriority() {
        return updatedPriority;
    }

    public void setUpdatedPriority(int updatedPriority) {
        this.updatedPriority = updatedPriority;
    }

    @Override
    public String toString() {
        String re = "";
        re += "Process information:\n";
        re += "Process name: " + processName + "\n";
        re += "Process ID: " + processID + "\n";
        re += "Process priority: " + updatedPriority + "\n";
        re += "Process arrival time: " + arrivalTime + "\n";
        re += "Process burst time: " + burstTime + "\n";
        return re;
    }

}
