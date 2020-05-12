package dev.skyblock.invitation;

import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;

import java.util.Set;

public interface InvitationAPI {

    Set<IslandInvitation> getPendingInvites(Island island);

    void sendInvitation(Island island, Islander islander);

    void cancelInvitation(Island island, Islander islander);

    void acceptInvitation(Island island, Islander islander);

    void declineInvitation(Island island, Islander islander);
}
