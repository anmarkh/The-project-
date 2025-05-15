import java.util.Scanner;

public class MiniShell {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ShellCommandHandler handler = new ShellCommandHandler();

            System.out.println("Welcome to MiniShelle , type HELP to see the command list");

            while (true) {
                System.out.print(handler.getCurrentDirectory().getAbsolutePath() + " > ");
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String argument = parts.length > 1 ? parts[1] : null;

                switch (command.toLowerCase()) {
                    case "pwd" -> handler.printWorkingDirectory();
                    case "ls" -> handler.listDirectory();
                    case "cd" -> handler.changeDirectory(argument);
                    case "mkdir" -> handler.makeDirectory(argument);
                    case "touch" -> handler.createFile(argument);
                    case "help" -> handler.printHelp();
                    case "exit" -> {
                        System.out.println("Goodbye");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("أمر غير معروف. اكتب 'help' لرؤية قائمة الأوامر.");
                }
            }
        }

    }
