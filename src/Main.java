import common.CommentSelector;

public class Main {
    public static void main(String[] args) {
        String line1 = "String s = \"This is a string // not a comment here\";";
        System.out.println(CommentSelector.getSingleComment(line1));  // ""

        String line2 = "String s = \"This is a string with \\\"escaped\\\" quotes // but this is a real comment\";";
        System.out.println(CommentSelector.getSingleComment(line2));  // "// but this is a real comment"

        String line3 = "// This is a comment before any code";
        System.out.println(CommentSelector.getSingleComment(line3));  // "// This is a comment before any code"

        String line4 = "int x = 10; // This is a comment in the middle";
        System.out.println(CommentSelector.getSingleComment(line4));  // "// This is a comment in the middle"

        String line5 = "";
        System.out.println(CommentSelector.getSingleComment(line5));  // ""

        String line6 = "int a = 5;";
        System.out.println(CommentSelector.getSingleComment(line6));  // ""

        String line7 = "String s = \"Hello world\"; // This is a comment after string literal";
        System.out.println(CommentSelector.getSingleComment(line7));  // "// This is a comment after string literal"

        String line8 = "String s = \"This is a string with a \\\"quote\\\" inside it\"; // Comment should be outside";
        System.out.println(CommentSelector.getSingleComment(line8));  // "// Comment should be outside"

        String line9 = "int a = 5; // First comment int b = 10; // Second comment";
        System.out.println(CommentSelector.getSingleComment(line9));  // "// First comment"
    }
}
