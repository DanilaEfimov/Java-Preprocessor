package exceptions;

public class MissedMultiCommentOpeningException extends MissedTokenException {
    protected int pos;
    protected String sourceLine;

    public MissedMultiCommentOpeningException(int pos, String sourceLine) {
        super("Missed multi-line comment opening at position " + pos + " in line: " + sourceLine);
        this.pos = pos;
        this.sourceLine = sourceLine;
    }

    @Override
    public String toString() {
        String context = sourceLine + "\n" +
                "~".repeat(Math.max(0, pos)) +
                "^";
        return "MissedMultiCommentOpeningException: Position: " + pos + ", Context:\n" + context;
    }
}
