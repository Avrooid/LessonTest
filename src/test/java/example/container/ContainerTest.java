package example.container;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тесты для класса {@link Container}
 */
public class ContainerTest {

    private Container container;

    @Before
    public void setUp() {
        container = new Container();
    }

    /**
     * Тест метода {@link Container#add(Item)}
     */
    @Test
    public void testAdd() {
        Item testItem = new Item(10);
        boolean result = container.add(testItem);
        assertTrue(result);
        assertEquals(1, container.size());
        assertTrue(container.contains(testItem));
    }

    /**
     * Тест метода {@link Container#remove(Item)}
     */
    @Test
    public void testRemove() {
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        container.add(item1);
        container.add(item2);
        boolean result = container.remove(item1);
        assertTrue(result);
        assertEquals(1, container.size());
        assertTrue(container.contains(item2));
    }
}