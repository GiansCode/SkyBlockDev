package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.JsonConfig;
import dev.skyblock.invitation.IslandInvitation;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InvitationStorage extends JsonConfig<InvitationStorage> {

    private static final InvitationStorage DEFAULT_STORAGE = new InvitationStorage(new HashMap<>());

    private Map<Integer, Set<IslandInvitation>> invitations;

    /**
     * Represents a configuration file.
     */
    public InvitationStorage() {
        super(InvitationStorage.class);
    }

    /**
     * Represents a configuration file.
     */
    public InvitationStorage(Map<Integer, Set<IslandInvitation>> invitations) {
        super(InvitationStorage.class);

        this.invitations = invitations;
    }

    public Map<Integer, Set<IslandInvitation>> getInvitations() {
        return this.invitations;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("invitations.json");
    }

    @Override
    public InvitationStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
