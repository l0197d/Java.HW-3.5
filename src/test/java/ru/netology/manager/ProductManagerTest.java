package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Чтиво", 151, "Н.Жопов");
    Product second = new Book(2, "Тестирование для чайников", 83, "Н.Жопов");
    Product third = new Book(3, "Книга", 759, "А.Савин");
    Product fourth = new Smartphone(4, "Redmi 9", 32, "Xiaomi");
    Product fifth = new Smartphone(5, "Honor 10", 5, "Honor");


    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    void searchByName() {
        setUp();

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("Книга");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchMatchesNameBook() {
        setUp();

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("Книга");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchMatchesAuthor() {
        setUp();

        Product[] expected = new Product[]{third};
        Product[] actual = manager.searchBy("А.Савин");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchMatchesManufacture() {
        setUp();

        Product[] expected = new Product[]{fourth};
        Product[] actual = manager.searchBy("Xiaomi");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchMatchesNameSmartphone() {
        setUp();

        Product[] expected = new Product[]{fifth};
        Product[] actual = manager.searchBy("Honor 10");
        assertArrayEquals(expected, actual);
    }

    //    поиск всех книг одного автора
    @Test
    void searchAllByAuthor() {
        setUp();

        Product[] expected = new Product[]{first, second};
        Product[] actual = manager.searchBy("Н.Жопов");
        assertArrayEquals(expected, actual);
    }
    //    Запрос, на который нет ответа
    @Test
    void searchAll() {
        setUp();

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Lopata");
        assertArrayEquals(expected, actual);
    }

}