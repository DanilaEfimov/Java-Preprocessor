package common;


import java.util.Arrays;

public class CommentSelector {

    public static int getCommentPosition(String line, boolean isMulti) {
        char token = isMulti ? '*' : '/';
        char trigger = '/';

        return StringLiteralFreeCharSelector.getPosition(line, trigger, token);
    }

    public static int getMultiCommentEnding(String line) {
        if(line.contains(Config.Java.multiCommentClosing))
            return line.indexOf(Config.Java.multiCommentClosing);

        return Config.npos;
    }

    public static int getMultiCommentOpening(String line) {
        return CommentSelector.getCommentPosition(line, true);
    }

    public static boolean hasMultiCommentOpening(String line) {
        return CommentSelector.getMultiCommentOpening(line) != Config.npos;
    }

    public static boolean hasMultiCommentEnding(String line) {
        return CommentSelector.getMultiCommentEnding(line) != Config.npos;
    }

    public static String[] getMultiComment(String[] lines) {
        boolean inComment = false;
        int startLine = Config.npos;
        int endLine = Config.npos;

        for(int i = 0; i < lines.length; i++) {
            if(!inComment && CommentSelector.hasMultiCommentOpening(lines[i])){
                inComment = true;
                startLine = i;
            }
            if(inComment && CommentSelector.hasMultiCommentEnding((lines[i]))){
                endLine = i;
                break;
            }
        }

        if (startLine == Config.npos || endLine == Config.npos)
            return new String[]{};

        String[] slice = Arrays.copyOfRange(lines, startLine, endLine + 1);
        slice[0] = slice[0].substring(CommentSelector.getMultiCommentOpening(slice[0]));
        slice[slice.length - 1] = slice[slice.length - 1].substring(0,
                CommentSelector.getMultiCommentEnding(slice[slice.length - 1])
                - Config.Java.multiCommentClosing.length() + 1);

        return slice;
    }

    public static int getSingleCommentPosition(String line) {
        return CommentSelector.getCommentPosition(line, false);
    }

    public static boolean hasSingleComment(String line) {
        return CommentSelector.getSingleCommentPosition(line) != Config.npos;
    }

    /**
     * Single-line comment is a string as <b>singleCommentOpening + ".+"</b>.<br>
     * <i>Look at common.Config.Java</i>.
     * @param line java source code line
     * @return single lined comment
     */
    public static String getSingleComment(String line) {
        int commentPosition = CommentSelector.getSingleCommentPosition(line);

        if(commentPosition == Config.npos)
            return "";

        return line.substring(commentPosition);
    }

}
