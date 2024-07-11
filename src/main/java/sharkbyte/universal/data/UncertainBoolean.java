package sharkbyte.universal.data;

/**
 * This class intends to be used similarly to a boolean, but provides an uncertain status for when something isn't
 * necessarily known to be true or false. This was created to deal with Minecraft's desyncs but may be useful elsewhere
 * too.
 *
 * @Authors: am noah
 * @Since: 1.0.0
 * @Updated: 1.0.0
 */
public enum UncertainBoolean {
    FALSE,
    TRUE,
    UNCERTAIN;

    /**
     * Converts a regular boolean to an UncertainBoolean.
     */
    public static UncertainBoolean convertBoolean(boolean bool) {
        if (bool) return TRUE;
        return FALSE;
    }

    /**
     * Return whether the UncertainBoolean is in a false state.
     */
    public boolean isFalse() {
        return equals(FALSE);
    }

    /**
     * Return whether the UncertainBoolean is in a true state.
     */
    public boolean isTrue() {
        return equals(TRUE);
    }

    /**
     * Return whether the UncertainBoolean is in an uncertain state.
     */
    public boolean isUncertain() {
        return equals(UNCERTAIN);
    }
}
