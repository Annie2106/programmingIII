package com.uptc.prg3.dinner.client.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

public class JsonManager implements Serializable {
    private ObjectOutputStream mOutput;
    private Gson mGson;

    public JsonManager(ObjectOutputStream output) {
        this.mGson = new Gson();
        this.mOutput = output;
    }

    /**
     * Returns a formatted string with a serialization of a {@link JsonObject} class, for easy
     * transport. The serialized object is from a key and a value, the parameters.
     *
     * @param key   The key for the JSON object.
     * @param value The value for the JSON object.
     * @return Serialized JsonObject containing the key and the value.
     * @see JsonObject
     */
    public String toJsonValue(String key, String value) {
        JsonObject object = new JsonObject();
        object.addProperty(key, value);
        return this.mGson.toJson(object, JsonObject.class);
    }

    /**
     * Close all the related or given input or output streams for this object.
     *
     * @throws IOException If the streams are already closed or they haven't been even opened.
     * @see ObjectInputStream
     * @see ObjectOutputStream
     */
    public void close() throws IOException {
        this.mOutput.close();
    }
}
