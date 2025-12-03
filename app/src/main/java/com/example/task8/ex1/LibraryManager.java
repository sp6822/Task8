package com.example.task8.ex1;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class LibraryManager {

    public static void main(String[] args) {
        Map<UUID, LibraryItem> libraryCatalog = new HashMap<>();

        Book firstBook = new Book("Harry Potter and the Sorcerer's Stone", 1997, "J.K. Rowling", 309);
        Book secondBook = new Book("To Kill a Mockingbird", 1960, "Harper Lee", 281);

        Magazine magOne = new Magazine("Science Today", 2024, 15, "Emma Collins");
        Magazine magTwo = new Magazine("Tech Trends", 2023, 8, "Oliver Brown");
        Magazine magThree = new Magazine("Nature Monthly", 2022, 12, "Sophia Adams");

        libraryCatalog.put(firstBook.getItemId(), firstBook);
        libraryCatalog.put(secondBook.getItemId(), secondBook);

        libraryCatalog.put(magOne.getItemId(), magOne);
        libraryCatalog.put(magTwo.getItemId(), magTwo);
        libraryCatalog.put(magThree.getItemId(), magThree);

        UUID searchId = firstBook.getItemId();

        LibraryItem retrieved = libraryCatalog.get(searchId);
        if (retrieved != null) {
            System.out.println(retrieved);
        }

        UUID randomUUID = UUID.randomUUID();
        if (!libraryCatalog.containsKey(randomUUID)) {
            System.out.println("No item found for UUID: " + randomUUID);
        }

        firstBook.borrow();
        magTwo.borrow();

        System.out.println(" Late Fees (10 days) ");
        for (LibraryItem item : libraryCatalog.values()) {
            if (item.isBorrowed()==true) {
                double fee = item.calculateLateFee(10);
                System.out.println(item.getTitle() + " → Late fee: " + fee);
            }
        }

        String searchTerm = "Harry Potter";
        System.out.println("Search results for: " + searchTerm);
        for (LibraryItem item : libraryCatalog.values()) {
            if (item instanceof Searchable) {
                Searchable s = (Searchable) item;
                if (s.matches(searchTerm)) {
                    System.out.println("Match found: " + item.getTitle());
                }
            }
        }
    }
}
