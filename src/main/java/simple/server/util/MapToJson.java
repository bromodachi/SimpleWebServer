package simple.server.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MapToJson {

    private MapToJson() {}

    private static final Set<Class<?>> WRAPPER_TYPES = getPrimitiveTypes();
    private static final Set<Class<?>> NUMBER_TYPES = getNumberType();

    private static Set<Class<?>> getPrimitiveTypes()
    {
        Set<Class<?>> set = new HashSet<>();
        set.add(Boolean.class);
        set.add(Character.class);
        set.add(String.class);
        set.addAll(getNumberType());
        return set;
    }

    private static Set<Class<?>> getNumberType() {
        Set<Class<?>> set = new HashSet<>();
        set.add(Byte.class);
        set.add(Short.class);
        set.add(Integer.class);
        set.add(Long.class);
        set.add(Float.class);
        set.add(Double.class);;
        return set;
    }
    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        else if (NUMBER_TYPES.contains(value.getClass()) ) {
            return "" +value;
        }
        else {
            return "\"" + value + "\"";
        }
    }
    public static String mapToJson(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean firstProcessed = false;
        for (Object key : map.keySet()) {
            if (firstProcessed) {
                sb.append(",");
            }
            if (WRAPPER_TYPES.contains(map.get(key).getClass())) {
                sb.append("\"").append(key.toString().trim()).append("\"").append(":").append(formatValue(map.get(key)));
            }
            else if (map.get(key) instanceof Map<?, ?>){
                sb.append(mapToJson((Map<?, ?>) map.get(key)));
            }
            else {
                //TODO: Will never implement as there are many libraries that do this already.
                System.out.println("handle classes");
            }
            firstProcessed = true;
        }
        sb.append('}');
        return sb.toString();
    }
}
