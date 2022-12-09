// Christian Alexander, 12/9/2022
package kakkoiichris.nazonoshiro.json;

public interface JSONConvertible<X> {
    X load(Node.Object object);
    
    Node.Object save();
}
