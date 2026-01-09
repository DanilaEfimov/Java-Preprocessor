import common.CommentSelector;

import java.util.Arrays;

import static common.CommentSelector.getMultiComment;

public class Main {
    public static void main(String[] args) {

        String[] lines = {
                "int x = 5;",
                "/* This is a multi-line",
                "   comment that spans",
                "   multiple lines*/",
                "int y = 10;"
        };

        String[] comment = getMultiComment(lines);

        System.out.println(Arrays.toString(comment));
    }
}
