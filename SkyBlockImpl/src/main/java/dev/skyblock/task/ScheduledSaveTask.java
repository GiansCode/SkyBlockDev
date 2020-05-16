package dev.skyblock.task;

import dev.skyblock.SkyBlock;

public class ScheduledSaveTask implements Runnable {

    private final SkyBlock instance;

    public ScheduledSaveTask(SkyBlock instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        this.instance.getChallengeStorage().save();
        this.instance.getGridStorage().save();
        this.instance.getInvitationStorage().save();
        this.instance.getIslanderStorage().save();
        this.instance.getIslandStorage().save();
        this.instance.getWarpStorage().save();
    }
}
