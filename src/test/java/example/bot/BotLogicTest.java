package example.bot;

import example.bot.*;
import org.junit.Before;
import org.junit.Test;
import static example.bot.Constants.*;
import static org.junit.Assert.assertEquals;

public class BotLogicTest {
    private TestBot bot;
    private BotLogic botLogic;
    private User user;

    @Before
    public void setUp() {
        bot = new TestBot();
        botLogic = new BotLogic(bot);
        user = new User(123L);
    }

    @Test
    public void testProcessTest() {
        botLogic.processCommand(user, COMMAND_TEST);
        assertEquals(State.TEST, user.getState());
    }

    @Test
    public void testProcessRepeatNoQuestionsToRepeat() {
        botLogic.processCommand(user, COMMAND_REPEAT);
        assertEquals(State.INIT, user.getState());
        assertEquals("Нет вопросов для повторения", bot.lastMessage);
    }

    @Test
    public void testProcessNotify() {
        botLogic.processCommand(user, COMMAND_NOTIFY);
        assertEquals(State.SET_NOTIFY_TEXT, user.getState());
        assertEquals("Введите текст напоминания", bot.lastMessage);
    }

    private static class TestBot implements Bot {

        private String lastMessage;

        @Override
        public void sendMessage(Long chatId, String message) {
            lastMessage = message;
        }
    }
}