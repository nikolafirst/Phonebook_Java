import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public static void main (String[]args){
    Book phoneBook = new Book();
    phoneBook.addContact("John", "1234567890");
    phoneBook.addContact("John", "09876554321");
    phoneBook.addContact("Bob", "55555555555");
    phoneBook.addContact("Roy", "99999999999");
    phoneBook.printBook(); // Вывод всех записей книги

    TreeSet<String> johnPhones = phoneBook.getPhones("John");
    System.out.println("John phones: " + johnPhones); // вывод телефонов для John

    phoneBook.removeContact("John", "0987654321");
    TreeSet<String> johnPhonesAfterRemove = phoneBook.getPhones("John");
    System.out.println("John phones after remove: " + johnPhonesAfterRemove); // Вывод телефонов для John после удаления

    List<String> sortedContacts = phoneBook.sortContactsByPhones();
    System.out.println("Sorted contacts by phones: ");
    for (String contact : sortedContacts){ // Вывод отсортированных записей книги
        System.out.println(contact);
    }    

}


public class Book {
    private HashMap<String, TreeSet<String>> phoneBook;

    public Book() {
        phoneBook = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber) {
        TreeSet<String> phones = phoneBook.getOrDefault(name, new TreeSet<>());
        phones.remove(phoneNumber);
        phoneBook.put(name, phones);
    }

    public void removeContact(String name, String phoneNumber) {
        TreeSet<String> phones = phoneBook.getOrDefault(name, new TreeSet<>());
        phones.remove(phoneNumber);
        if (phones.isEmpty()) {
            phoneBook.remove(name);
        } else {
            phoneBook.put(name, phones);
        }
    }

    public TreeSet<String> getPhones(String name) {
        return phoneBook.get(name);
    }

    public HashMap<String, TreeSet<String>> getAllContacts() {
        return phoneBook;
    }

    public void printBook() {
        for (String name : phoneBook.keySet()) {
            TreeSet<String> phones = phoneBook.get(name);
            System.out.println(name + ":" + phones);
        }
    }

    public List<String> sortContactsByPhones() {
        List<Map.Entry<String, TreeSet<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().size() - e1.getValue().size());
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, TreeSet<String>> entry : sortedEntries) {
            result.add(entry.getKey() + ":" + entry.getValue());
        }
        return result;
    }

}
