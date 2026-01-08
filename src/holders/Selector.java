package holders;

import java.util.regex.*;
import java.util.ArrayList;
import common.Config;

public class Selector {

    public static String select(String line) {
        Pattern commentPattern = Pattern.compile(Config.directivePattern);
        Matcher matcher = commentPattern.matcher(line);

        if (matcher.find()) {
            String comment = matcher.group();
            int index = comment.indexOf(Config.jppHolder);
            if (index != -1 && index + Config.jppHolder.length() < comment.length()) {
                return comment.substring(index + Config.jppHolder.length()).trim();
            }
        }

        return "";
    }

    public static String getComment(String line) {
        Pattern pattern = Pattern.compile(Config.commentPattern);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static String beforeHolder(String line) {
        Pattern pattern = Pattern.compile(Config.directivePattern);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String comment = matcher.group();
            int index = comment.indexOf(Config.jppHolder);
            if (index != -1) {
                return comment.substring(0, index).trim();
            }
        }
        return "";
    }

    public static ArrayList<String> getLiteral(String line) {
        ArrayList<String> literals = new ArrayList<>();
        Pattern pattern = Pattern.compile(Config.stringLiteral);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            literals.add(matcher.group(0));
        }

        return literals;
    }

}
