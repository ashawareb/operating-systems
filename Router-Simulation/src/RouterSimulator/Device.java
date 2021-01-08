package RouterSimulator;

public class Device implements Runnable {

    private String name;
    private String type;
    private Router router;

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Router getRouter() {
        return router;
    }

    private int connect() throws InterruptedException { //device to take place in the router
        return router.connect(this);
    }

    private void disconnect(int idx) {
        router.disconnect(idx);
    }

    private void activity(int idx) {
        router.activity(idx);
    }

    private void sleep() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 5000.0));
    }

    @Override
    public void run() {
        try {
            int idx = this.connect();
            this.sleep();
            this.activity(idx);
            this.sleep();
            this.disconnect(idx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String re = "";
        re += this.name + " (" + this.type + " )";
        return re;
    }
}
