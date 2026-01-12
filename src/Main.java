import common.*;
import holders.*;
import parsing.Parser;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        // Scanner to input the file path
        Scanner in = new Scanner(System.in);
        System.out.print("Input input file path: ");
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
        // @jpp define MACRO1 some line of text

        // @jpp define macro1 lower cased macro

        // @jpp undef MACRO1

        // Processing each line
        Parser parser = new Parser(lines);
        parser.process();

        in.close();  // Closing the Scanner
    }
}
