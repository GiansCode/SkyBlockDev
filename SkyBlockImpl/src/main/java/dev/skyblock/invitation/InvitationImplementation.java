package dev.skyblock.invitation;

import com.google.common.collect.Sets;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import dev.skyblock.storage.InvitationStorage;

import java.util.Set;

public class InvitationImplementation implements InvitationAPI {

    private final InvitationStorage storage;

    public InvitationImplementation(InvitationStorage storage) {
        this.storage = storage;
    }

    @Override
    public Set<IslandInvitation> getPendingInvites(Island island) {
        return this.storage.getInvitations().getOrDefault(island.getId(), Sets.newHashSet());
    }

    @Override
    public void sendInvitation(Island island, Islander islander) {

    }

    @Override
    public void cancelInvitation(Island island, Islander islander) {

    }

    @Override
    public void acceptInvitation(Island island, Islander islander) {

    }

    @Override
    public void declineInvitation(Island island, Islander islander) {

    }
}
