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
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {
    @Override
    public Boolean read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return false;
        } else if (peek == JsonToken.STRING) {
            return Boolean.parseBoolean(in.nextString());
        }
        return in.nextBoolean();

    }

    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        out.value(value);
    }

    public static TypeAdapterFactory FACTORY = getFactory();

    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(boolean.class, Boolean.class, new BooleanTypeAdapter());
    }
}
