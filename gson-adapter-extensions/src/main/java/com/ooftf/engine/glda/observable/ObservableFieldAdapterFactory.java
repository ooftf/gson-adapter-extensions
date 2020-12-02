package com.ooftf.engine.glda.observable;

import androidx.databinding.ObservableField;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ooftf.engine.glda.engine.GsonTypes;
import com.ooftf.engine.glda.engine.TypeAdapterRuntimeTypeWrapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * Adapt a homogeneous LiveData of objects.
 * @author 99474
 */
public final class ObservableFieldAdapterFactory implements TypeAdapterFactory {

    public ObservableFieldAdapterFactory() {

    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();

        Class<? super T> rawType = typeToken.getRawType();
        if (!ObservableField.class.isAssignableFrom(rawType)) {
            return null;
        }

        Type elementType = getObservableFieldElementType(type, rawType);
        TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));

        return new ObservableFieldAdapterFactory.Adapter(gson, elementType, elementTypeAdapter);
    }

    /**
     * Returns the element type of this collection type.
     *
     * @throws IllegalArgumentException if this type is not a collection.
     */
    public static Type getObservableFieldElementType(Type context, Class<?> contextRawType) {
        Type collectionType = GsonTypes.getSupertype(context, contextRawType, ObservableField.class);

        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType) collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    private static final class Adapter<E> extends TypeAdapter<ObservableField<E>> {
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(Gson context, Type elementType,
                       TypeAdapter<E> elementTypeAdapter) {
            this.elementTypeAdapter =
                    new TypeAdapterRuntimeTypeWrapper<E>(context, elementTypeAdapter, elementType);
        }

        @Override
        public ObservableField<E> read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return new ObservableField<E>();
            }

            ObservableField<E> result = new ObservableField<E>();
            E instance = elementTypeAdapter.read(in);
            result.set(instance);
            return result;
        }

        @Override
        public void write(JsonWriter out, ObservableField<E> collection) throws IOException {
            if (collection == null) {
                out.nullValue();
                return;
            }
            elementTypeAdapter.write(out, collection.get());
        }
    }
}
