package player;

import java.util.HashMap;
import java.util.Map;

public class PlayerMultiton {

    private static Map instances = new HashMap();
    public static enum keys { Instance1, Instance2 };

    private PlayerMultiton() {

        instances.put(keys, new Player ( "Pepe" ));
        instances.put(keys, new Player ( "Juan" ));
    }

    public static PlayerMultiton getInstance (Enum key) {
        PlayerMultiton instance = instances.get(key);

        if ( instance == null ) {
            instance = new PlayerMultiton ();
            instances.put (key,instance);
        }
        return instance;
    }
}

/*

public class PlayerMultiton {
    //private static Dictionary < String , PlayerMultiton > instances = new Dictionary<String, PlayerMultiton>();
    private static Map<Integer,PlayerMultiton> instances = new HashMap<Integer, PlayerMultiton> ();

    private PlayerMultiton() {

    }

    public static PlayerMultiton getInstance (int key) {
        PlayerMultiton instance = instances.get(key);

        if ( instance == null ) throw

        return instances[key];
    }
}

*/

