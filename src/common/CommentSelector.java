package common;


import java.util.Arrays;

public class CommentSelector {

    public static final int npos = -1;

    public static int getCommentPosition(String line, boolean isMulti) {
        char triggerToken = isMulti ? '*' : '/';

        int idx = 0;

        boolean inLiteral = false;
        boolean escapedClosing = false;
        char[] charArray = line.toCharArray();
        for(char ch : charArray){
            if(++idx >= charArray.length)
                break;

            if(!inLiteral){
                if(ch == '/' && charArray[idx] == triggerToken)
                    return idx + 1;
            }

            if(!inLiteral && ch == Config.Java.stringLiteralOpening){
                inLiteral = true;           // turn into string literal
                escapedClosing = false;     // by default closing dropped in general
            }
            else if(escapedClosing){
                escapedClosing = false;     // reset escaped closing
            }
            else if(ch == Config.Java.escapeSymbol
                    && charArray[idx] == Config.Java.stringLiteralClosing) {
                escapedClosing = true;      // set escaped closing flag
            }
            else if(ch == Config.Java.stringLiteralClosing) {
                inLiteral = false;          // turn out string literal
            }
        }

        return CommentSelector.npos;
    }

    public static int getMultiCommentEnding(String line) {
        if(line.contains(Config.Java.multiCommentClosing))
            return line.indexOf(Config.Java.multiCommentClosing);

        return CommentSelector.npos;
    }

    public static int getMultiCommentOpening(String line) {
        return CommentSelector.getCommentPosition(line, true);
    }

    public static boolean hasMultiCommentOpening(String line) {
        return CommentSelector.getMultiCommentOpening(line) != CommentSelector.npos;
    }

    public static boolean hasMultiCommentEnding(String line) {
        return CommentSelector.getMultiCommentEnding(line) != CommentSelector.npos;
    }

    public static String[] getMultiComment(String[] lines) {
        boolean inComment = false;
        int startLine = CommentSelector.npos;
        int endLine = CommentSelector.npos;

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

        if (startLine == CommentSelector.npos || endLine == CommentSelector.npos)
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
        return CommentSelector.getSingleCommentPosition(line) != CommentSelector.npos;
    }

    /**
     * Single-line comment is a string as <b>singleCommentOpening + ".+"</b>.<br>
     * <i>Look at common.Config.Java</i>.
     * @param line java source code line
     * @return single lined comment
     */
    public static String getSingleComment(String line) {
        int commentPosition = CommentSelector.getSingleCommentPosition(line);

        if(commentPosition == CommentSelector.npos)
            return "";

        return line.substring(commentPosition);
    }

}
