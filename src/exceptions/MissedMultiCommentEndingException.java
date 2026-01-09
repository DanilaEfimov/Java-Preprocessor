package exceptions;

public class MissedMultiCommentEndingException extends MissedTokenException {
    protected int pos;
    protected String sourceLine;

    public MissedMultiCommentEndingException(int pos, String sourceLine) {
        super("Missed multi-line comment ending at position " + pos + " in line: " + sourceLine);
        this.pos = pos;
        this.sourceLine = sourceLine;
    }

    @Override
    public String toString() {
        String context = sourceLine + "\n" +
                "~".repeat(Math.max(0, pos)) +
                "^";
        return "MissedMultiCommentEndingException: Position: " + pos + ", Context:\n" + context;
    }
}
