package common;

public class CommentSelector {

    public static int npos = -1;

    public static int getSingleCommentPosition(String line) {
        int idx = 0;

        boolean inLiteral = false;
        boolean escapedClosing = false;
        char[] charArray = line.toCharArray();
        for(char ch : charArray){
            if(++idx >= charArray.length)
                break;

            if(!inLiteral){
                if(ch == '/' && charArray[idx] == '/')
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

    public static boolean hasSingleComment(String line) {
        return CommentSelector.getSingleCommentPosition(line) != CommentSelector.npos;
    }

    public static String getSingleComment(String line) {
        int commentPosition = CommentSelector.getSingleCommentPosition(line);

        if(commentPosition == CommentSelector.npos)
            return "";

        return line.substring(commentPosition);
    }

}
