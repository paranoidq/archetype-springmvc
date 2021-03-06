package me.webapp.support.customPropertyEditor;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paranoidq
 * @since 1.0.0
 */
@EditorTargetType(Map.class)
public class KvPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] entries = text.split("&");

        Map<String, String> map = new HashMap<>(entries.length);
        for (String entry: entries) {
            String[] kv = entry.split("=");
            map.put(kv[0], kv[1]);
        }
        setValue(map);
    }
}
