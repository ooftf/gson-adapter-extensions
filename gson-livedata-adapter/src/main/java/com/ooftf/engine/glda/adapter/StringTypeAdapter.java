package com.ooftf.engine.glda.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/11/5
 */
public class StringTypeAdapter extends TypeAdapter<String> {
    @Override
    public String read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return "";
        }
        /* coerce booleans to strings for backwards compatibility */
        if (peek == JsonToken.BOOLEAN) {
            return Boolean.toString(in.nextBoolean());
        }
        return in.nextString();
    }

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        out.value(value);
    }

    public static TypeAdapterFactory FACTORY = getFactory();
    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(String.class,new StringTypeAdapter());
    }
}
