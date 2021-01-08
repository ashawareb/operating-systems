package RouterSimulator;

public class Semaphore {

    private int cnt;

    public Semaphore(int init) {
        this.cnt = init;
    }

    public synchronized void Wait(Device device) throws InterruptedException {
        cnt--;
        if (cnt < 0) {
            // the device arrived and waiting to take place
            System.out.println(device.toString() + " arrived and in waiting list");
            wait();
        } else {
            System.out.println(device.toString() + " arrived");
        }
    }

    public synchronized void Signal() {
        cnt++;
        if (cnt <= 0) {
            notify();
        }
    }
}
