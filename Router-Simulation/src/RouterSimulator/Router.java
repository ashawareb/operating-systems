package RouterSimulator;

public class Router {

    private Semaphore semaphore;
    private int numOfConnections; //buffer
    private String[] connected;
    private Object[] locks;

    public Router(int numOfConnections) {
        this.numOfConnections = numOfConnections;
        semaphore = new Semaphore(numOfConnections);
        connected = new String[numOfConnections];
        locks = new Object[numOfConnections];
        for (int i = 0; i < numOfConnections; i++) {
            locks[i] = new Object();
        }
    }

    public int connect(Device device) throws InterruptedException {
        semaphore.Wait(device);
        for (int i = 0; i < numOfConnections; i++) {
            synchronized (locks[i]) {
                if (connected[i] == null) { //there is no device in this place
                    System.out.println("Connection #" + (i + 1) + " : " + device.getName() + " is connected");
                    connected[i] = device.getName();
                    return i;
                }
            }
        }
        assert (false);
        return -1;
    }

    public void activity(int idx) {
        System.out.println("Connection #" + (idx + 1) + " : " + connected[idx] + " performs online activity");
    }

    public void disconnect(int idx) {
        System.out.println("Connection #" + (idx + 1) + " : " + connected[idx] + " is disconnected");
        connected[idx] = null;
        semaphore.Signal();
    }

}
