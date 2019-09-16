package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import server.entity.Message;
import server.model.Philosopher;

public class FileManager {
	public static ArrayList<Message> loadPhi() throws IOException,
			ParseException {
		JsonParser jsonParser = new JsonParser();
		FileReader fileReader = new FileReader("data/message.json");
		JsonElement jsonElement = jsonParser.parse(fileReader);
		if (jsonElement.isJsonNull()) {
			return new ArrayList<Message>();
		} else {
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			ArrayList<Message> messageList = new ArrayList<>();
			java.util.Iterator<JsonElement> iterator = jsonArray.iterator();
			Gson gson = new Gson();
			while (iterator.hasNext()) {
				JsonElement jsonElement2 = iterator.next();
				Message message= new Message();
				message = gson.fromJson(jsonElement2, Message.class);
				messageList.add(message);
			}
			return messageList;
		}
	}

	public static String getGsonFrom(Philosopher[] messageList)
			throws IOException {
//		FileWriter fileWriter = new FileWriter("data/message.json");
		Gson gson = new Gson();
//		fileWriter.write(gson.toJson(messageList, messageList.getClass()));
		return gson.toJson(messageList, messageList.getClass());
//		fileWriter.flush();
//		fileWriter.close();
	}

	public static Philosopher[] toArray(String arrayInGson) {
		Gson gson = new Gson();
		
		return gson.fromJson(arrayInGson, Philosopher[].class);
	}

}
