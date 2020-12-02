package com.ooftf.engine.glda.observable;

import androidx.databinding.ObservableBoolean;

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
public class ObservableBooleanTypeAdapter extends TypeAdapter<ObservableBoolean> {
    @Override
    public ObservableBoolean read(JsonReader in) throws IOException {
        ObservableBoolean result = new ObservableBoolean();
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            result.set(false);
            return result;
        } else if (peek == JsonToken.STRING) {
            result.set(Boolean.parseBoolean(in.nextString()));
            return result;
        }
        result.set(in.nextBoolean());
        return result;

    }

    @Override
    public void write(JsonWriter out, ObservableBoolean value) throws IOException {
        out.value(value.get());
    }

    public static TypeAdapterFactory FACTORY = getFactory();

    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(ObservableBoolean.class, new ObservableBooleanTypeAdapter());
    }
}
