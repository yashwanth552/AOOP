import java.util.ArrayList;
import java.util.List;

// Book.java
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }
}

// Member.java
class Member {
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }
}

// NotificationService.java
interface NotificationService {
    void notify(Member member, String message);
}

// EmailNotificationService.java
class EmailNotificationService implements NotificationService {
    @Override
    public void notify(Member member, String message) {
        System.out.println("Email to " + member.getEmail() + ": " + message);
    }
}

// SMSNotificationService.java
class SMSNotificationService implements NotificationService {
    @Override
    public void notify(Member member, String message) {
        // Here, just simulate SMS sending
        System.out.println("SMS to " + member.getName() + ": " + message);
    }
}

// Borrowing.java
class Borrowing {
    private Member member;
    private Book book;

    public Borrowing(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public void borrowBook() {
        if (!book.isBorrowed()) {
            member.borrowBook(book);
            System.out.println(member.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is already borrowed.");
        }
    }

    public void returnBook() {
        member.returnBook(book);
        System.out.println(member.getName() + " returned " + book.getTitle());
    }
}

// Library.java
class Library {
    private List<Book> books;
    private List<Member> members;
    private NotificationService notificationService;

    public Library(NotificationService notificationService) {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void borrowBook(Member member, String bookTitle) {
        Book book = findBook(bookTitle);
        if (book != null && !book.isBorrowed()) {
            Borrowing borrowing = new Borrowing(member, book);
            borrowing.borrowBook();
            notificationService.notify(member, "You borrowed " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Member member, String bookTitle) {
        Book book = findBook(bookTitle);
        if (book != null && book.isBorrowed()) {
            Borrowing borrowing = new Borrowing(member, book);
            borrowing.returnBook();
            notificationService.notify(member, "You returned " + book.getTitle());
        } else {
            System.out.println("Book is not currently borrowed.");
        }
    }

    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        // Create a notification service (Email or SMS)
        NotificationService notificationService = new EmailNotificationService();
        // Or use SMSNotificationService
        // NotificationService notificationService = new SMSNotificationService();

        // Create library
        Library library = new Library(notificationService);

        // Add books to library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));

        // Register members
        Member member1 = new Member("Alice", "alice@example.com");
        Member member2 = new Member("Bob", "bob@example.com");
        library.registerMember(member1);
        library.registerMember(member2);

        // Borrow books
        library.borrowBook(member1, "1984");
        library.borrowBook(member2, "The Great Gatsby");

        // Return books
        library.returnBook(member1, "1984");
    }
}