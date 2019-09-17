package com.uptc.prg3.dinner.server.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JsonManager {
    private ObjectInputStream mInput;
    private ObjectOutputStream mOutput;
    private Gson mGson;

    public JsonManager(ObjectInputStream input, ObjectOutputStream output) {
        this.mInput = input;
        this.mOutput = output;
        this.mGson = new Gson();
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
     * Gets a value from the input stream given the key from the parameter. If the key is null
     * or the object retrieved does not have that key, it throws an {@link IOException}.
     *
     * @param key The key to get the information.
     * @return The information paired with the key.
     * @throws IOException            If the key does not corresponds with anything.
     * @throws ClassNotFoundException If the object read through the {@link ObjectInputStream} can't
     *                                be converted to a {@link JsonObject}.
     * @see ObjectInputStream
     */
    public String getValue(String key) throws IOException, ClassNotFoundException {
        JsonObject object = this.mGson.fromJson((String) this.mInput.readObject(), JsonObject.class);
        return object.get(key).getAsString();
    }

    public void close() throws IOException {
        this.mInput.close();
        this.mOutput.close();
    }
}
