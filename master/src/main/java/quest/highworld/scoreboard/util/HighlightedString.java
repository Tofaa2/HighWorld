package quest.highworld.scoreboard.util;

public class HighlightedString extends FrameAnimatedString {

    protected String context;
    protected String highlightFormat;
    protected String prefix = "";
    protected String suffix = "";

    public HighlightedString(String context,  String highlightFormat) {
        super();
        this.context = context;
        this.highlightFormat = highlightFormat;
        generateFrames();
    }

    public HighlightedString(String context, String highlightFormat, String prefix, String suffix) {
        super();
        this.context = context;
        this.highlightFormat = highlightFormat;
        this.prefix = prefix;
        this.suffix = suffix;
        generateFrames();
    }

    protected void generateFrames() {
        int index = 0;
        String color = "§c";
        while (index < context.length()) {
            if (context.charAt(index) == '§') {
                color = context.substring(index, index+2);
                index += 2;
                continue;
            }
            if (context.charAt(index) != ' ') {
                String highlighted = color + context.substring(0, index) + highlightFormat + context.charAt(index) + color + context.substring(index + 1);
                String whole = prefix + highlighted + suffix;
                addFrame(whole);
            } else {
                addFrame(context);
            }
            index++;
        }
    }

    public String getContext() {
        return context;
    }

    public String getHighlightColor() {
        return highlightFormat;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

}