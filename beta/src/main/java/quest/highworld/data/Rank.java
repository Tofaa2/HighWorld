package quest.highworld.data;

public enum Rank {

    MEMBER(8),
    DONATOR(7),
    VIP(6),
    MVP(5),

    HELPER(4),
    MODERATOR(3),
    ADMINISTRATOR(2),
    OWNER(1);

    public final int weight;
    Rank(int weight) {this.weight=weight;}
}
