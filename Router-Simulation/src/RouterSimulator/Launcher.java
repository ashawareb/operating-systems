package RouterSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Launcher {

    private Scanner input;


    public Launcher() {
        input = new Scanner(System.in);
    }

    public void launch() {
        System.out.print("Enter number of WI-FI connections : ");
        int numOfConnections = input.nextInt();
        Router router = new Router(numOfConnections);
        System.out.print("Enter number of devices : ");
        int numOfDevices = input.nextInt();
        Device[] devices = new Device[numOfDevices];
        for (int i = 0; i < numOfDevices; i++) {
            System.out.print("Enter Device #" + (i + 1) + " name : ");
            String name = input.next();
            System.out.print("Enter Device #" + (i + 1) + " type : ");
            String type = input.next();
            devices[i] = new Device(name, type, router);
        }
        try {
            PrintStream FilePrint = new PrintStream(new File("log.txt"));
            System.setOut(FilePrint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Thread[] threads = new Thread[numOfDevices];
        for (int i = 0; i < numOfDevices; i++) {
            threads[i] = new Thread(devices[i]);
            threads[i].start();
        }
        for (int i = 0; i < numOfDevices; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
