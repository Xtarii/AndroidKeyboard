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
}
