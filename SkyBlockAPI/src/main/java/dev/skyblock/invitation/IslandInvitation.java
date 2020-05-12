package dev.skyblock.invitation;

import dev.skyblock.islander.Islander;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public final class IslandInvitation {

    private final Islander from;
    private final Islander to;

    private final long invitedOn;

    public IslandInvitation(Islander from, Islander to) {
        this.from = from;
        this.to = to;

        this.invitedOn = System.currentTimeMillis();
    }

    public Islander getFrom() {
        return this.from;
    }

    public Islander getTo() {
        return this.to;
    }

    public long getInvitedOn() {
        return this.invitedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IslandInvitation that = (IslandInvitation) o;
        return invitedOn == that.invitedOn &&
          Objects.equals(from, that.from) &&
          Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, invitedOn);
    }

    @Override
    public String toString() {
        return "IslandInvitation{" +
          "from=" + from +
          ", to=" + to +
          ", invitedOn=" + invitedOn +
          '}';
    }
}
