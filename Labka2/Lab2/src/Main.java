import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

class ServerUnavailableException extends Exception {
    public ServerUnavailableException(String message) {
        super(message);
    }
}

public class Main {


    public static void sendEmail(String email, boolean isServerUp) throws ServerUnavailableException {
        if (!isServerUp) {
            throw new ServerUnavailableException("Помилка відправки: Сервер пошти недоступний для листа " + email);
        }
        System.out.println("Успіх: Лист на адресу " + email + " успішно надіслано!");
    }

    public static void main(String[] args) {
        System.out.println("=== Система відправки листів ===");

        Queue<String> emailQueue = new LinkedList<>();

        emailQueue.add("poroshenko.official@company.com");
        emailQueue.add("client@domain.com");
        emailQueue.add("friend@mail.com");

        boolean isServerUp = true;

        while (true) {
            try {
                String currentEmail = emailQueue.remove();
                System.out.println("\nВзято з черги: " + currentEmail);

                try {
                    sendEmail(currentEmail, isServerUp);
                } catch (ServerUnavailableException e) {
                    System.out.println(e.getMessage());
                }

            } catch (NoSuchElementException e) {
                System.out.println("\nУвага: Черга порожня! Спроба взяти лист з порожньої черги перехоплена.");
                break;
            }
        }
    }
}