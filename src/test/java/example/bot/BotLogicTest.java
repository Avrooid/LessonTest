package example.bot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BotLogicTest {
    private FakeBot bot;
    private BotLogic botLogic;
    private User user;

    @Before
    public void setUp() {
        bot = new FakeBot();
        botLogic = new BotLogic(bot);
        user = new User(123L);
    }

    /**
     * Тестирование /test, при корректных ответах
     */
    @Test
    public void testProcessTestCorrect() {
        botLogic.processCommand(user, "/test");
        assertEquals(State.TEST, user.getState());

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", bot.getMessages().get(1));

        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", bot.getMessages().get(3));

        assertEquals("Тест завершен", bot.getMessages().get(4));
    }

    /**
     * Тестирование /test, при некорректных ответах
     */
    @Test
    public void testProcessTestWrong() {
        botLogic.processCommand(user, "/test");
        assertEquals(State.TEST, user.getState());

        botLogic.processCommand(user, "1000");
        assertEquals("Вы ошиблись, верный ответ: 100", bot.getMessages().get(1));

        botLogic.processCommand(user, "9");
        assertEquals("Вы ошиблись, верный ответ: 6", bot.getMessages().get(3));

        assertEquals("Тест завершен", bot.getMessages().get(4));

        assertEquals(2, user.getWrongAnswerQuestions().size());
        assertEquals("Вычислите степень: 10^2", user.getWrongAnswerQuestions().get(0).getText());
        assertEquals("Сколько будет 2 + 2 * 2", user.getWrongAnswerQuestions().get(1).getText());
    }

    /**
     * Тестирование /repeat
     */
    @Test
    public void testProcessRepeat() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1000");
        botLogic.processCommand(user, "9");
        botLogic.processCommand(user, "/repeat");
        assertEquals(State.REPEAT, user.getState());

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", bot.getMessages().get(6));

        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", bot.getMessages().get(8));

        assertEquals(0, user.getWrongAnswerQuestions().size());
    }

    /**
     * Тестирование /notify
     */
    @Test
    public void testProcessNotify() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания", bot.getMessages().get(0));

        botLogic.processCommand(user, "Поставить будильник");
        assertEquals("Через сколько секунд напомнить?", bot.getMessages().get(1));

        botLogic.processCommand(user, "1");
        assertEquals("Напоминание установлено", bot.getMessages().get(2));
        assertEquals(3, bot.getMessages().size());

        Thread.sleep(1010);
        assertEquals("Сработало напоминание: 'Поставить будильник'", bot.getMessages().get(3));
    }

}