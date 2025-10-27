package android.open.keyboard.utils.shift;

/**
 * Shift State
 * <p/>
 * Simple way of handling the virtual
 * keyboard shift state
 */
public enum ShiftState {
    /**
     * OFF State
     */
    OFF,
    /**
     * ON State
     */
    ON,
    /**
     * Shift capslock
     */
    CAPSLOCK;



    /**
     * Tries to turn off shift
     * <p/>
     * If the state is <code>ON</code> then this will return <code>OFF</code>
     * otherwise it will return the old state.
     *
     * @return New shift state
     */
    public ShiftState off() {
        if(this == ON) return OFF;
        return this;
    }

    /**
     * Gets the next shift state
     * <p/>
     * This will read the states as a list of states,
     * then by incrementing the index it will get
     * the next state in the list or the first object
     * if the index becomes bigger than the amount of
     * states.
     *
     * @return The next shift state
     */
    public ShiftState next() {
        int next = this.ordinal() + 1;
        int max = ShiftState.values().length - 1;

        if(next > max) next = 0;
        return ShiftState.values()[next];
    }
}
