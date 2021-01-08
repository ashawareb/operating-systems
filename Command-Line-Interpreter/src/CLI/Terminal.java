package CLI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Terminal {

    private String currentPath = "/home";

    public String getCurrentPath() {
        return this.currentPath;
    }

    public void cd(String[] args) {
        if (args.length > 2) {
            System.out.print("Too many arguments");
        } else if (args.length == 1) {
            this.currentPath = "/home";
        } else {
            Path path = Path.of(args[1]);
            if (Files.exists(path)) {
                this.currentPath = args[1];
            } else {
                System.out.println("No such file or directory");
            }
        }

    }

    public void ls(String[] args) {
        String directoryPath = "";
        if (args.length == 1) {
            directoryPath = this.currentPath;
        } else if (args.length == 2) {
            directoryPath = args[1];
        } else {
            System.out.println("Too many arguments");
            return; //It may cause an unexpected error
        }
        Path path = Path.of(directoryPath);
        if (Files.exists(path)) {
            File directory = new File(directoryPath);
            if (directory.isDirectory()) {
                File[] listOfFiles = directory.listFiles();
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        System.out.println("File - " + listOfFiles[i].getName());
                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory - " + listOfFiles[i].getName());
                    }
                }
            } else {
                System.out.println(directoryPath + " is not a directory");
            }
        } else {
            System.out.println("No such directory");
        }
    }

    public void pwd(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many arguments");
            return;
        }
        System.out.println(this.currentPath);
    }

    public void cp(String[] args) {
        if (args.length < 3) {
            System.out.println("Command not found");
            return;
        } else if (args.length > 3) {
            System.out.println("Too many arguments");
            return;
        } else {
            String src = args[1];
            String dest = args[2];
            Path srcPath = Path.of(src);
            Path destPath = Path.of(dest);
            if (Files.exists(srcPath) && Files.exists(destPath)) { //Check if files
                if (src.equals(dest)) {
                    System.out.println("File is already in directory");
                } else {
                    File file = new File(src);
                    File chFile = new File(dest); //used to check if the given path is directory or file
                    if (chFile.isFile()) {
                        File file2 = new File(dest);
                        copyContent(file, file2, false);
                    } else {
                        String srcFileName = file.getName();
                        String destFileName = dest + '/' + srcFileName;
                        File file2 = new File(destFileName);
                        copyContent(file, file2, false);
                    }

                }
            } else {
                System.out.println("No such file or directory");
            }

        }

    }

    private void copyContent(File src, File dest, boolean delete) {
        FileInputStream instream = null;
        FileOutputStream outstream = null;
        try {
            instream = new FileInputStream(src);
            outstream = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();
            if (delete) {
                src.delete();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void mv(String[] args) {
        if (args.length < 3) {
            System.out.println("Command not found");
            return;
        } else if (args.length > 3) {
            System.out.println("Too many arguments");
            return;
        } else {
            String src = args[1];
            String dest = args[2];
            Path srcPath = Path.of(src);
            Path destPath = Path.of(dest);
            if (Files.exists(srcPath) && Files.exists(destPath)) {
                if (src.equals(dest)) {
                    System.out.println("File is already in directory");
                } else {
                    File file = new File(src);  //File I want to copy;
                    File chFile = new File(dest); //used to check if the given path is directory or file
                    if (chFile.isFile()) {
                        File file2 = new File(dest);
                        copyContent(file, file2, true);
                    } else {
                        String srcFileName = file.getName();
                        String destFileName = dest + '/' + srcFileName;
                        File file2 = new File(destFileName);
                        copyContent(file, file2, true);
                    }

                }
            } else {
                System.out.println("No such file or directory");
            }
        }
    }

    public void rm(String[] args) {
        if (args.length > 2) {
            System.out.println("Too many arguments");
            return;
        }
        String src = args[1];
        Path srcPath = Path.of(src);
        if (Files.exists(srcPath)) {
            File file = new File(src);
            if (file.isFile()) {
                file.delete();
            } else {
                System.out.println("File isn't exist");
            }
        } else {
            System.out.println("No such file");
        }

    }

    public void cat(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            System.out.println("Missing operand");
        } else if (args.length == 2) {
            String src = args[1];
            File file = new File(src);
            if (file.exists() && file.isFile()) {
                Scanner sc = new Scanner(file); //MAY CAUSE AN ERROR
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
            } else {
                System.out.println("No such file");
            }

        } else if (args.length == 3) {
            String src1 = args[1];
            String src2 = args[2];
            File file1 = new File(src1);
            File file2 = new File(src2);
            if (file1.exists() && file2.exists() && file1.isFile() && file2.isFile()) {
                Scanner sc1 = new Scanner(file1);
                Scanner sc2 = new Scanner(file2);
                while (sc1.hasNextLine()) {
                    System.out.println(sc1.nextLine());
                }
                while (sc2.hasNextLine()) {
                    System.out.println(sc2.nextLine());
                }
            } else {
                System.out.println("No such file");
            }
        } else {
            System.out.println("Too many arguments");
        }
    }

    public void mkdir(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing operand");
            return;
        } else if (args.length > 2) {
            System.out.println("Too many arguments");
            return;
        }
        String src = args[1];
        Path srcPath = Path.of(src);
        if (Files.exists(srcPath)) {
            System.out.println("Directory already exist");
        } else {
            File file = new File(src);
            if (!file.mkdir()) {
                System.out.println("Cannot create file: No such file or directory");
            }
        }
    }

    public void rmdir(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing operand");
            return;
        } else if (args.length > 2) {
            System.out.println("Too many arguments");
            return;
        }
        String src = args[1];
        Path srcPath = Path.of(src);
        if (Files.exists(srcPath)) {
            File directory = new File(src);
            if (directory.isDirectory()) {
                File[] listOfFiles = directory.listFiles();
                if (listOfFiles.length > 0) {
                    System.out.println("Cannot delete directory: Directory is not empty");
                } else {
                    directory.delete();
                }
            } else {
                System.out.println(src + " is not a directory");
            }
        } else {
            System.out.println("No such directory");
        }
    }

    public void more(String[] args) throws FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Missing operand");
            return;
        } else if (args.length > 2) {
            System.out.println("Too many arguments");
            return;
        } else {
            String src = args[1];
            Path srcPath = Path.of(src);
            if (Files.exists(srcPath)) {
                File file = new File(src);
                if (file.isFile()) {
                    int cnt = 1;
                    Scanner sc = new Scanner(file); //MAY CAUSE AN ERROR
                    Scanner input = new Scanner(System.in);
                    String userInput = "";
                    while (sc.hasNextLine()) {
                        if (cnt % 10 == 0) {
                            System.out.println("More?(y/n)");
                            userInput = input.next();
                            if (userInput.equalsIgnoreCase("n")) {
                                break;
                            }
                        }
                        System.out.println(sc.nextLine());
                        cnt++;
                    }
                } else {
                    System.out.println(src + " is not a file");
                }
            } else {
                System.out.println("No such file");
            }
        }
    }

    public void clear(String[] args) {
        for (int i = 0; i < 150; i++) {
            System.out.println("\n");
        }
    }

    public void help(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many arguments");
            return;
        }
        System.out.println("cd : cd [directory] -> change directory");
        System.out.println("ls : ls || ls [Directory] -> list all files in directory");
        System.out.println("pwd : pwd -> print the working directory");
        System.out.println("cp : cp [file] [file] -> copy file");
        System.out.println("mv : mv [file] [file] -> move file");
        System.out.println("rm : rm [file] -> remove file");
        System.out.println("mkdir : mkdir [directory] -> create directory");
        System.out.println("rmdir : rmdir [directory] -> remove directory");
        System.out.println("cat : cat [file] [file] -> concatenate files and print on the output");
        System.out.println("date : date -> print the current date");
        System.out.println("more : more [file] -> display and scroll down the output in one direction");
        System.out.println("clear : clear -> clear the terminal");
        System.out.println("help : help -> print all commands and their usage");
        System.out.println("args : args [command] -> list all commands arguments");
        System.out.println("> : command > [file name] -> overwrite the output to the file");
        System.out.println(">> : command >> [file name] -> append the out put to the file ");
    } //Complete > and >>

    public void date(String[] args) {
        if (args.length > 1) {
            System.out.println("Too many arguments");
            return;
        }
        System.out.println(java.time.LocalDateTime.now());
    }

    public void args(String[] args) {
        if (args.length > 2) {
            System.out.println("Too many arguments");
            return;
        } else if (args.length < 2) {
            System.out.println("Missing operand");
            return;
        }
        switch (args[1]) {
            case "cd" -> System.out.println("cd : cd [directory]");
            case "ls" -> System.out.println("ls : ls || ls [Directory]");
            case "pwd" -> System.out.println("pwd : pwd");
            case "cp" -> System.out.println("cp : cp [file] [file]");
            case "mv" -> System.out.println("mv : mv [file] [file]");
            case "rm" -> System.out.println("rm : rm [file]");
            case "mkdir" -> System.out.println("mkdir : mkdir [directory]y");
            case "rmdir" -> System.out.println("rmdir : rmdir [directory]");
            case "cat" -> System.out.println("cat : cat [file] [file]");
            case "date" -> System.out.println("date : date");
            case "more" -> System.out.println("more : more [file]");
            case "clear" -> System.out.println("clear : clear");
            case "help" -> System.out.println("help : help");
            case "args" -> System.out.println("args : args [command]");
            default -> System.out.println("Command not found");
        }
    }

    public void exit(String[] args) {
        if (args.length > 1) {
            System.out.print("Too many arguments");
        } else {
            System.exit(0);
        }
    }

    public void redirectOperator(String[] args) throws IOException {
        String command = args[0];
        if (command.equals("pwd")) {
            if (args.length > 3) {
                System.out.println("Too many arguments");
            } else if (args.length < 3) {
                System.out.println("Missing operand");
            } else {
                String redirect = args[1];
                String src = args[2];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(src);
                if (file.exists() && file.isFile()) {
                    operatorPwd(file, append);
                } else {
                    System.out.println("No such file");
                    return;
                }
            }
        } else if (command.equals("date")) {
            if (args.length > 3) {
                System.out.println("Too many arguments");
            } else if (args.length < 3) {
                System.out.println("Missing operand");
            } else {
                String redirect = args[1];
                String src = args[2];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(src);
                if (file.exists() && file.isFile()) {
                    operatorDate(file, append);
                } else {
                    System.out.println("No such file");
                }
            }
        } else if (command.equals("ls")) {
            if (args.length == 3) {
                String redirect = args[1];
                String src = args[2];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(this.currentPath);
                File file2 = new File(src);
                if (file2.exists() && file2.isFile()) {
                    String str = "";
                    File[] listOfFiles = file.listFiles();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        str += listOfFiles[i].getName() + "\n";
                    }
                    FileWriter writer = new FileWriter(file2, append);
                    writer.write(str);
                    writer.close();
                } else {
                    System.out.println("No such file");
                }
            } else if (args.length == 4) {
                String redirect = args[2];
                String src = args[3];
                String directory = args[1];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(src);
                File dir = new File(directory);
                if (dir.exists() && dir.isDirectory()) {
                    if (file.exists() && file.isFile()) {
                        String str = "";
                        File[] listOfFiles = dir.listFiles();
                        for (int i = 0; i < listOfFiles.length; i++) {
                            str += listOfFiles[i].getName() + "\n";
                        }
                        FileWriter writer = new FileWriter(file, append);
                        writer.write(str);
                        writer.close();
                    } else {
                        System.out.println("No such file");
                    }
                } else {
                    System.out.println("No such directory");
                }
            } else {
                System.out.println("Missing operand or Too many arguments");
            }
        } else if (command.equals("cat")) {
            if (args.length == 4) {
                String fileSrc = args[1];
                String redirect = args[2];
                String fileDest = args[3];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(fileSrc);
                File file2 = new File(fileDest);
                if (file.exists() && file2.exists() && file.isFile() && file2.isFile()) {
                    Scanner sc = new Scanner(file);
                    String str = "";
                    while (sc.hasNextLine()) {
                        str += sc.nextLine();
                    }
                    FileWriter writer = new FileWriter(file2, append);
                    writer.write(str);
                    writer.close();
                } else {
                    System.out.println("No such file");
                }
            } else if (args.length == 5) {
                String srcFile1 = args[1];
                String srcFile2 = args[2];
                String redirect = args[3];
                String destFile = args[4];
                boolean append = false;
                if (redirect.equals(">>")) {
                    append = true;
                }
                File file = new File(srcFile1);
                File file2 = new File(srcFile2);
                File file3 = new File(destFile);
                if (file.isFile() && file.exists() && file2.exists() && file2.isFile() && file3.isFile() && file3.exists()) {
                    Scanner sc1 = new Scanner(file);
                    Scanner sc2 = new Scanner(file2);
                    String str = "";
                    while (sc1.hasNextLine()) {
                        str += sc1.nextLine();
                    }
                    while (sc2.hasNextLine()) {
                        str += sc2.nextLine();
                    }
                    FileWriter writer = new FileWriter(file3, append);
                    writer.write(str);
                    writer.close();
                }
            } else {
                System.out.println("Missing operand or Too many arguments");
            }
        } else {
            System.out.println("No such command");
        }
    }

    private void operatorPwd(File file, boolean append) throws IOException {
        FileWriter writer = new FileWriter(file, append);
        String str = this.currentPath;
        writer.write(str);
        writer.close();
    }

    private void operatorDate(File file, boolean append) throws IOException {
        FileWriter writer = new FileWriter(file, append);
        String str = java.time.LocalDateTime.now().toString();
        writer.write(str);
        writer.close();
    }

}
