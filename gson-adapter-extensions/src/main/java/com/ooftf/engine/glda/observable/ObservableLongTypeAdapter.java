package com.ooftf.engine.glda.observable;

import androidx.databinding.ObservableLong;

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
public class ObservableLongTypeAdapter extends TypeAdapter<ObservableLong> {
    @Override
    public ObservableLong read(JsonReader in) throws IOException {
        ObservableLong result = new ObservableLong();
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            result.set(0L);
            return result;
        }
        try {
            result.set(in.nextLong());
            return result;
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public void write(JsonWriter out, ObservableLong value) throws IOException {
        out.value(value.get());
    }

    public static TypeAdapterFactory FACTORY = getFactory();

    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(ObservableLong.class, new ObservableLongTypeAdapter());
    }
}
