package holders;

import java.util.regex.*;
import common.Config;

public class Selector {

    public static String select(String line) {
        Pattern commentPattern = Pattern.compile(Config.Jpp.directivePattern);
        String comment = common.CommentSelector.getSingleComment(line);
        Matcher matcher = commentPattern.matcher(comment);

        if (matcher.find()) {
            String directive = matcher.group();
            int index = directive.indexOf(Config.Jpp.jppHolder);
            if (index != -1 && index + Config.Jpp.jppHolder.length() < directive.length()) {
                return directive.substring(index + Config.Jpp.jppHolder.length()).trim();
            }
        }

        return "";
    }

    public static String beforeHolder(String line) {


        return "";
    }

}
