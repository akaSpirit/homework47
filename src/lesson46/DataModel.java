package lesson46;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataModel {
    private final List<Book> books;
    private final Map<String, Employee> employees;

    public DataModel() {
        books = FileService.readBooks();
        employees = FileService.readEmployees();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }


    public static class Book {
        private int id;
        private String bookName;
        private String authorName;
        private BookState bookState;
        private String info;
        private List<String> currentReaders;
        private List<String> pastReaders;

        public String getBookName() {
            return bookName;
        }

        public String getAuthorName() {
            return authorName;
        }

        public BookState getBookState() {
            return bookState;
        }

        public void setBookState(BookState bookState) {
            this.bookState = bookState;
        }

        public String getInfo() {
            return info;
        }

        public int getId() {
            return id;
        }

        public List<String> getCurrentReaders() {
            return currentReaders;
        }

        public List<String> getPastReaders() {
            return pastReaders;
        }

        public String getCurrentReader() {
            Map<String, DataModel.Employee> employees = FileService.readEmployees();
            String fmt = "";
            if (bookState == BookState.NOT_AVAILABLE) {
                for (int i = 0; i < currentReaders.size(); i++) {
                    if (employees.containsKey(currentReaders.get(i))) {
                        fmt = String.format("%s", employees.get(currentReaders.get(i)).email);
                    }

                }
            } else fmt = "Book is free";
            return fmt;
        }
    }

    public static class Employee {
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private String password;
        private List<Integer> readNowID = new ArrayList<>();
        private List<Integer> readPastID = new ArrayList<>();

        public Employee(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Employee(String firstName, String lastName, String email, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public List<Integer> getReadNowID() {
            return readNowID;
        }

        public void setReadNowID(List<Integer> readNowID) {
            this.readNowID = readNowID;
        }

        public List<Integer> getReadPastID() {
            return readPastID;
        }

        public void setReadPastID(List<Integer> readPastID) {
            this.readPastID = readPastID;
        }

        public String getReadNowBooks() {
            List<DataModel.Book> books = FileService.readBooks();
            String fmt = "";
            for (int i = 0; i < readNowID.size(); i++) {
                fmt += String.format("'%s' by %s. ", books.get(readNowID.get(i)).bookName, books.get(readNowID.get(i)).authorName);
            }
            return fmt;
        }

        public String getReadPastBooks() {
            List<DataModel.Book> books = FileService.readBooks();
            String fmt = "";
            if(readPastID.size() != 0) {
                for (int i = 0; i < readPastID.size(); i++) {
                    fmt += String.format("'%s' by %s. ", books.get(readPastID.get(i)).bookName, books.get(readPastID.get(i)).authorName);
                }
            }
                return fmt;
        }

        public List<DataModel.Book> getListNowBooks() {
            List<DataModel.Book> books = FileService.readBooks();
            List<DataModel.Book> b = new ArrayList<>();
            for (int i = 0; i < readNowID.size(); i++) {
                b.add(books.get(readNowID.get(i)));
            }
            return b;
        }
    }
}

