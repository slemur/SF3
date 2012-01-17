package serp.bytecode;

/**
 * @author Rick van Biljouw
 *         Date: 17-1-12
 *         Time: 18:34
 */
public class UnknownType {
    public String signature;
    public boolean selfReference;

    public UnknownType(String signature, boolean selfReference) {
        this.signature = signature;
        this.selfReference = selfReference;
    }

}
