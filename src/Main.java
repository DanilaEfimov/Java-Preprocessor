import common.*;
import holders.*;
import macros.Macro;
import macros.Table;
import parsing.Parser;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        // Scanner to input the file path
        Scanner in = new Scanner(System.in);
        String path = "C:\\Users\\home\\IdeaProjects\\Preprocessor\\src\\Main.java"; //in.nextLine();  // Get the path from the user

        // Reading the file
        File inputFile = new File(path);

        // Check if the file exists
        if (!inputFile.exists()) {
            System.out.println("File does not exist!");
            return;
        }

        // Reading lines from the file
        ArrayList<String> lines = new ArrayList<>();
        try {
            lines = (ArrayList<String>) Files.readAllLines(inputFile.toPath());  // Read all lines
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Example preprocessor directives with @jpp trigger
        String a = "@jpp define PI 3.14";

        // @jpp define macro1   \
        // lower cased macro    \
        // sfsgdfgsdfg dfg      \
        // dfgdsf df gsdfg sdf  \

        // @jpp undef MACRO1

        // Processing each line
        Parser parser = new Parser(lines);
        parser.process();

        HashMap<String, Macro> table = Context.macros.get();
        for(Macro macro : table.values()){
            System.out.println(macro.getName() + " :: ");
            System.out.print(Arrays.toString(macro.getDefinition()));
        }

        in.close();  // Closing the Scanner
    }
}
