package com.ooftf.engine.glda.observable;

import androidx.databinding.ObservableDouble;

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
public class ObservableDoubleTypeAdapter extends TypeAdapter<ObservableDouble> {
    @Override
    public ObservableDouble read(JsonReader in) throws IOException {
        ObservableDouble observableDouble = new ObservableDouble();
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            observableDouble.set(0.0d);
            return observableDouble;
        }
        try {
            observableDouble.set(in.nextDouble());
            return observableDouble;
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public void write(JsonWriter out, ObservableDouble value) throws IOException {
        out.value(value.get());
    }

    public static TypeAdapterFactory FACTORY = getFactory();

    private static TypeAdapterFactory getFactory() {
        return TypeAdapters.newFactory(ObservableDouble.class, new ObservableDoubleTypeAdapter());
    }
}
