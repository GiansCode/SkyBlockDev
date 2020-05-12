package dev.skyblock.command.exception;

import javax.annotation.concurrent.Immutable;
import java.util.Arrays;

@Immutable
public final class IllegalCommandArgException extends Exception {

    private final String argumentName;
    private final Class<?> requiredType;

    public IllegalCommandArgException(String argumentName, Class<?> requiredType) throws NoSuchFieldException, IllegalAccessException {
        super("Incorrect argument type for, " + argumentName + ". Required type: " +
          (requiredType.isEnum() ? Arrays.toString((Enum[]) requiredType.getDeclaredField("$VALUES").get(null)) : requiredType.getSimpleName()));

        this.argumentName = argumentName;
        this.requiredType = requiredType;
    }

    public String getArgumentName() {
        return this.argumentName;
    }

    public Class<?> getRequiredType() {
        return this.requiredType;
    }
}
