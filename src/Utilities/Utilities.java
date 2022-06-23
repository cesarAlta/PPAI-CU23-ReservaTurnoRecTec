package Utilities;

import java.util.Map;
import java.util.Objects;

public class Utilities {
    public static <K, V> K getKeyHasMap(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
