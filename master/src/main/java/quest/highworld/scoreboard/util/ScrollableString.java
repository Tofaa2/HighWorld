package quest.highworld.scoreboard.util;

public class ScrollableString extends FrameAnimatedString {
    private int position;
    private final String context;
    private final int width;
    private String currentColor;
    public ScrollableString(String message, int w) {
        // String is too short for window?
        message = new String(new char[w]).replace('\0', ' ') + message;
        if (message.length() < w) {
            StringBuilder sb = new StringBuilder(message);
            while (sb.length() < w)
                sb.append(" ");
            message = sb.toString();
        }
        context = message;
        position = 0;
        currentColor = "§r";
        width = w;
    }

    @Override
    public String next() {

        if (context.charAt(position) == '§') {
            currentColor = context.substring(position, position + 2);

            position += 2;
            return this.next();
        }
        int end = Math.min(position + this.width, context.length());
        if (position != 0 && context.charAt((position-1)) == '§') {
            position++; // skip it if it is a colors number/letter
            return this.next();
        }
        int last;
        do {
            last = end;
            end = (int) (Math.min(context.length(), position + this.width + 2 * context.substring(position, end).codePoints().filter(ch -> ch == '§').count()));
        } while (last != end);
        String text = context.substring(position, end);
        position++;
        position %= context.length();
        return currentColor + text;
    }

}