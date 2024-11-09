package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> products = List.of(
                new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics),
                new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном…", smartphones),
                new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories)
        );

        Cart cart = new Cart();
        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути перелік товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар із кошика");
            System.out.println("5 - Замовити");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару на ім'я");
            System.out.println("8 - Пошук товару за категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> displayProducts(products);
                case 2 -> addProductToCart(scanner, products, cart);
                case 3 -> System.out.println(cart);
                case 4 -> removeProductFromCart(scanner, products, cart);
                case 5 -> placeOrder(cart);
                case 6 -> displayOrderHistory();
                case 7 -> searchProductByName(scanner, products);
                case 8 -> searchProductByCategory(scanner, products);
                case 0 -> {
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                }
                default -> System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }

    private static void displayProducts(List<Product> products) {
        System.out.println("Список товарів:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void addProductToCart(Scanner scanner, List<Product> products, Cart cart) {
        System.out.println("Введіть ID товару для додавання до кошика:");
        int id = scanner.nextInt();
        products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        cart::addProduct,
                        () -> System.out.println("Товар з таким ID не знайдено")
                );
    }

    private static void removeProductFromCart(Scanner scanner, List<Product> products, Cart cart) {
        System.out.println("Введіть ID товару для видалення з кошика:");
        int id = scanner.nextInt();
        products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        cart::removeProduct,
                        () -> System.out.println("Товар з таким ID не знайдено")
                );
    }

    private static void placeOrder(Cart cart) {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
        } else {
            Order order = new Order(cart);
            System.out.println("Замовлення оформлено:");
            System.out.println(order);
            cart.clear();
        }
    }

    private static void displayOrderHistory() {
        System.out.println("Історія замовлень:");
        for (Order order : Order.getOrderHistory()) {
            System.out.println(order);
        }
    }

    private static void searchProductByName(Scanner scanner, List<Product> products) {
        System.out.println("Введіть назву товару для пошуку:");
        String name = scanner.next();
        List<Product> foundByName = Product.searchByName(products, name);
        System.out.println("Знайдені товари: " + foundByName);
    }

    private static void searchProductByCategory(Scanner scanner, List<Product> products) {
        System.out.println("Введіть категорію для пошуку:");
        String category = scanner.next();
        List<Product> foundByCategory = Product.searchByCategory(products, category);
        System.out.println("Знайдені товари: " + foundByCategory);
    }
}
