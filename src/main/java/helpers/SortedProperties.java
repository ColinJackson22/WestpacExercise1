package helpers;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class SortedProperties extends Properties {

    @Override
    public void store(Writer writer, String comments) throws IOException {
        this.keySet().stream().map(k -> (String)k).sorted().forEach(k -> {
            try {
                writer.append(String.format("%s=%s\n", k, get(k)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Enumeration<Object> keys() {
        Enumeration<Object> keysEnum = super.keys();
        Vector<Object> keyList = new Vector<Object>();

        while (keysEnum.hasMoreElements()) {
            keyList.add(keysEnum.nextElement());
        }

        Collections.sort(keyList, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        return keyList.elements();
    }
}
