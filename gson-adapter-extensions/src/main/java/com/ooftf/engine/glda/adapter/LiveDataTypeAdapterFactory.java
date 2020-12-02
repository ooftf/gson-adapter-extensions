package com.ooftf.engine.glda.adapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
public final class LiveDataTypeAdapterFactory implements TypeAdapterFactory {

    public LiveDataTypeAdapterFactory() {

    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();

        Class<? super T> rawType = typeToken.getRawType();
        if (!LiveData.class.isAssignableFrom(rawType)) {
            return null;
        }

        Type elementType = getLiveDataElementType(type, rawType);
        TypeAdapter<?> elementTypeAdapter = gson.getAdapter(TypeToken.get(elementType));

        return new LiveDataTypeAdapterFactory.Adapter(gson, elementType, elementTypeAdapter);
    }

    /**
     * Returns the element type of this collection type.
     *
     * @throws IllegalArgumentException if this type is not a collection.
     */
    public static Type getLiveDataElementType(Type context, Class<?> contextRawType) {
        Type collectionType = GsonTypes.getSupertype(context, contextRawType, LiveData.class);

        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType) collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    private static final class Adapter<E> extends TypeAdapter<LiveData<E>> {
        private final TypeAdapter<E> elementTypeAdapter;

        public Adapter(Gson context, Type elementType,
                       TypeAdapter<E> elementTypeAdapter) {
            this.elementTypeAdapter =
                    new TypeAdapterRuntimeTypeWrapper<E>(context, elementTypeAdapter, elementType);
        }

        @Override
        public LiveData<E> read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return new MutableLiveData<E>();
            }

            MutableLiveData<E> result = new MutableLiveData<E>();
            E instance = elementTypeAdapter.read(in);
            result.setValue(instance);
            return result;
        }

        @Override
        public void write(JsonWriter out, LiveData<E> collection) throws IOException {
            if (collection == null) {
                out.nullValue();
                return;
            }
            elementTypeAdapter.write(out, collection.getValue());
        }
    }
}
