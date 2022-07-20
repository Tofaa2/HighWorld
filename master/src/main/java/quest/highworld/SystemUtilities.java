package quest.highworld;

import lombok.Getter;

public class SystemUtilities {

    @Getter private int systemCurrentTimeDays;
    @Getter private int systemCurrentTimeHours;
    @Getter private int systemCurrentTimeMinutes;
    @Getter private int systemCurrentTimeSeconds;

    private final HighWorld hw = HighWorld.getInstance();

    public SystemUtilities(){
        this.systemCurrentTimeDays = 0;
        this.systemCurrentTimeHours = 0;
        this.systemCurrentTimeMinutes = 0;
        this.systemCurrentTimeSeconds = 0;

        this.hw.getServer().getScheduler().scheduleSyncRepeatingTask(hw, () -> {
            systemCurrentTimeSeconds++;

            if (this.systemCurrentTimeSeconds > 60) {
                this.systemCurrentTimeSeconds = 0;
                this.systemCurrentTimeMinutes++;
            }

            if (this.systemCurrentTimeMinutes > 60) {
                this.systemCurrentTimeMinutes = 0;
                this.systemCurrentTimeHours++;
            }

            if (this.systemCurrentTimeHours > 24) {
                this.systemCurrentTimeHours = 0;
                this.systemCurrentTimeDays++;
            }

        }, 0L, 20L);

    }

    public int getSecondsSince(int seconds){
        return (this.systemCurrentTimeMinutes * 60 + this.systemCurrentTimeSeconds) - seconds;
    }

    public int getFromHoursInSeconds(){
        return this.systemCurrentTimeHours * 3600 + this.systemCurrentTimeMinutes * 60 + this.systemCurrentTimeSeconds;
    }



}
