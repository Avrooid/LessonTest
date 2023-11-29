package example.bot;

import java.util.ArrayList;
import java.util.List;

public class FakeBot implements Bot {

    private final List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }
}
