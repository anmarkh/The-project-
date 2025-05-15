import java.io.File;
import java.io.IOException;
public class ShellCommandHandler {

        // المتغير الذي يمثل المجلد الحالي للعمل
        private File currentDirectory;

        // المُنشئ: يحدد المجلد الافتراضي للعمل عند بدء البرنامج
        public ShellCommandHandler() {
            currentDirectory = new File(System.getProperty("user.dir"));
        }

        // عرض المسار الحالي (pwd)
        public void printWorkingDirectory() {
            System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
        }

        // عرض محتويات المجلد (ls)
        public void listDirectory() {
            File[] contents = currentDirectory.listFiles();
            if (contents != null && contents.length > 0) {
                for (File file : contents) {
                    if (file.isDirectory()) {
                        System.out.println("[DIR] " + file.getName());
                    } else {
                        System.out.println("[FILE] " + file.getName());
                    }
                }
            } else {
                System.out.println("File is empety");
            }
        }

        // تغيير المسار (cd)
        public void changeDirectory(String name) {
            if (name == null) {
                System.out.println(" USE :cd [directory_name]");
                return;
            }

            if (name.equals("..")) {
                File parent = currentDirectory.getParentFile();
                if (parent != null) {
                    currentDirectory = parent;
                } else {
                    System.out.println("You are now in the directory.");
                }
                return;
            }

            File targetDir = new File(currentDirectory, name);
            if (targetDir.exists() && targetDir.isDirectory()) {
                currentDirectory = targetDir;
            } else {
                System.out.println("Directory does not exist.: " + name);
            }
        }

        // إنشاء مجلد جديد (mkdir)
        public void makeDirectory(String name) {
            if (name == null) {
                System.out.println(" USE: mkdir [directory_name] ");
                return;
            }

            File newDir = new File(currentDirectory, name);
            if (newDir.exists()) {
                System.out.println("File already exists.");
            } else if (newDir.mkdir()) {
                System.out.println("File created: " + name);
            } else {
                System.out.println("Failed to create directory.");
            }
        }

        // إنشاء ملف جديد (touch)
        public void createFile(String name) {
            if (name == null) {
                System.out.println("USE :touch [file_name]");
                return;
            }

            File newFile = new File(currentDirectory, name);
            try {
                if (newFile.exists()) {
                    System.out.println("File already exists..");
                } else if (newFile.createNewFile()) {
                    System.out.println("File created.");
                } else {
                    System.out.println("Failed to create file");
                }
            } catch (IOException e) {
                System.out.println("ERROR 404: " + e.getMessage());
            }
        }

        // عرض تعليمات الاستخدام (help)
        public void printHelp() {
            System.out.println("\nالأوامر المتاحة:");
            System.out.println("pwd - عرض المسار الحالي");
            System.out.println("ls - عرض محتويات المجلد");
            System.out.println("cd [dir] - تغيير المسار");
            System.out.println("mkdir [name] - إنشاء مجلد جديد");
            System.out.println("touch [name] - إنشاء ملف جديد");
            System.out.println("help - عرض قائمة الأوامر");
            System.out.println("exit - إنهاء البرنامج\n");
        }

        // إرجاع المسار الحالي
        public File getCurrentDirectory() {
            return currentDirectory;
        }
    }


