import common.SymbolSelector;
import exceptions.MatchRequiredException;

public class Main {
    public static void main(String[] args) {
        String classDefinition = "public class MyClass {";
        try {
            String className = SymbolSelector.getClassName(classDefinition);
            System.out.println("Class name: " + className);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        String functionDefinition = "public void myMethod(int a, String b) {";
        try {
            String functionName = SymbolSelector.getMemberFunctionName(functionDefinition);
            System.out.println("Function name: " + functionName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        String incorrectDefinition = "public MyClass {";
        try {
            String name = SymbolSelector.getClassName(incorrectDefinition);
            System.out.println("Name: " + name);
        } catch (MatchRequiredException e) {
            System.out.println("Error: " + e);
        }
    }
}
