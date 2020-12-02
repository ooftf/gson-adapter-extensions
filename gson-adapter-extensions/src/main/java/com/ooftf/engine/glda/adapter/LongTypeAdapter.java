package com.ooftf.engine.glda.adapter;

import com.google.gson.JsonSyntaxException;
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
public class LongTypeAdapter extends TypeAdapter<Number> {
    @Override
    public Number read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return 0L;
        }
        try {
            return in.nextLong();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public void write(JsonWriter out, Number value) throws IOException {
        out.value(value);
    }

    public static TypeAdapterFactory FACTORY = getFactory();

    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(long.class, Long.class, new LongTypeAdapter());
    }
}
