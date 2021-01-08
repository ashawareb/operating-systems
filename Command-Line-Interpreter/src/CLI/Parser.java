package CLI;

public class Parser {

    private String[] args; // Will be filled by arguments extracted by parse method
    private String cmd; // Will be filled by the command extracted by parse method

    public boolean parse(String input) {
        this.args = input.split("\\s+");
        if (!checkRedirect(this.args).equals("")) {
            this.cmd = checkRedirect(args);
            return true;
        } else {
            this.cmd = args[0];
            if (checkCommand(cmd)) {
                return true;
            } else {
                cmd = "";
                return false;
            }
        }
    }

    private String checkRedirect(String[] args) {
        String re = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(">") || args[i].equals(">>")) {
                re = args[i];
                return re;
            }
        }
        return re;
    }

    private boolean checkCommand(String command) {
        if (command.equals("cd") || command.equals("ls") || command.equals("pwd") || command.equals("cp")
                || command.equals("rm") || command.equals("mv") || command.equals("cat") || command.equals("mkdir")
                || command.equals("rmdir") || command.equals("more") || command.equals("help") || command.equals("clear")
                || command.equals("date") || command.equals("args") || command.equals("exit")) {
            return true;
        }
        return false;
    }

    public String getCmd() {
        return this.cmd;
    }

    public String[] getArguments() {
        return this.args;
    }
}
