/**
 * @author Rick van Biljouw
 *         Date: 15-1-12
 *         Time: 10:21
 */
public class ByteCode {
    public static Object[] anObjectArray = new Object[256];

    public void getObject() {
        Object object = anObjectArray[0];
    }

    public void getObjectArray() {
        Object[] objects = (Object[])anObjectArray[0];
    }

    public void setObject() {
        anObjectArray[0] = null;
    }

    public void setObjectArray() {
        anObjectArray[0] = new Object[20];
    }


}
