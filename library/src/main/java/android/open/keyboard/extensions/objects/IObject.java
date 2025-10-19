package android.open.keyboard.extensions.objects;

/**
 * Keyboard Extension Object
 */
public class IObject<A, T> {
    /**
     * Extension Metadata
     */
    public final A meta;
    /**
     * Extension Instance
     */
    public final T instance;



    /**
     * Creates Keyboard Extension Object
     *
     * @param meta Metadata
     * @param instance Extension Instance
     */
    public IObject(A meta, T instance) {
        this.meta = meta; this.instance = instance;
    }
}
