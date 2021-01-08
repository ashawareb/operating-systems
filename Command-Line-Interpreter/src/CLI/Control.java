package CLI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Control {
    private Scanner input;
    private Parser parser;
    private Terminal terminal;

    public Control() {
        input = new Scanner(System.in);
        parser = new Parser();
        terminal = new Terminal();

    }

    public void start() throws IOException {
        String command;
        while (true) {
            System.out.print("user@device-name:~$");
            command = input.nextLine();
            if (parser.parse(command)) {
                switch (parser.getCmd()) {
                    case "cd" -> terminal.cd(parser.getArguments());
                    case "ls" -> terminal.ls(parser.getArguments());
                    case "cp" -> terminal.cp(parser.getArguments());
                    case "pwd" -> terminal.pwd(parser.getArguments());
                    case "rm" -> terminal.rm(parser.getArguments());
                    case "mv" -> terminal.mv(parser.getArguments());
                    case "cat" -> terminal.cat(parser.getArguments());
                    case "mkdir" -> terminal.mkdir(parser.getArguments());
                    case "rmdir" -> terminal.rmdir(parser.getArguments());
                    case "more" -> terminal.more(parser.getArguments());
                    case "help" -> terminal.help(parser.getArguments());
                    case "clear" -> terminal.clear(parser.getArguments());
                    case "date" -> terminal.date(parser.getArguments());
                    case "args" -> terminal.args(parser.getArguments());
                    case "exit" -> terminal.exit(parser.getArguments());
                    case ">", ">>" -> terminal.redirectOperator(parser.getArguments());
                    default -> System.out.println("Command not found");
                }
            } else {
                System.out.println("command not found");
            }
        }

    }
}
