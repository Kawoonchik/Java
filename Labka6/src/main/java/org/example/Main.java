import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 16. wait(), notifyAll() demonstration
class StoreManager {
    private boolean isStoreOpen = false;

    public synchronized void waitForStoreToOpen() throws InterruptedException {
        while (!isStoreOpen) {
            wait();
        }
    }

    public synchronized void openStore() {
        isStoreOpen = true;
        System.out.println("STORE IS NOW OPEN");
        notifyAll();
    }
}

class OnlineStore {
    private final Map<String, Integer> inventory = new HashMap<>();

    // 12. ReentrantReadWriteLock
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    // 15. Condition variable
    private final Condition itemRestocked = writeLock.newCondition();

    // 14. Semaphore (Max 2
    private final Semaphore storeCapacity = new Semaphore(2);

    // 6. Atomic operations
    private final AtomicInteger totalItemsSold = new AtomicInteger(0);

    public OnlineStore() {
        inventory.put("Laptop", 1);
        inventory.put("Smartphone", 2);
    }

    // 14. Semaphore & 11. tryLock equivalent (tryAcquire)
    public boolean enterStore() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is waiting at the entrance...");

        if (storeCapacity.tryAcquire(3, TimeUnit.SECONDS)) {
            return true;
        }
        return false;
    }

    public void leaveStore() {
        System.out.println(Thread.currentThread().getName() + " left the store.");
        storeCapacity.release();
    }

    // 12. ReentrantReadWriteLock (Read Lock)
    public void viewCatalog() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is viewing catalog: " + inventory);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
    }

    // 10. LockInterruptibly & 15. Condition Variable
    public boolean takeItem(String itemName) throws InterruptedException {
        writeLock.lockInterruptibly();
        try {
            while (inventory.getOrDefault(itemName, 0) == 0) {
                System.out.println(Thread.currentThread().getName() + " is waiting for " + itemName + " to restock...");
                // 15. Condition variable await
                itemRestocked.await();
            }
            inventory.put(itemName, inventory.get(itemName) - 1);
            totalItemsSold.incrementAndGet();
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    public void returnItem(String itemName) {
        writeLock.lock();
        try {
            inventory.put(itemName, inventory.getOrDefault(itemName, 0) + 1);
            totalItemsSold.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " returned " + itemName);
            // 15. Condition variable signal
            itemRestocked.signalAll();
        } finally {
            writeLock.unlock();
        }
    }
}

class Customer implements Runnable {
    private final OnlineStore store;
    private final StoreManager manager;
    private final String itemToBuy;

    public Customer(OnlineStore store, StoreManager manager, String itemToBuy) {
        this.store = store;
        this.manager = manager;
        this.itemToBuy = itemToBuy;
    }

    @Override
    public void run() {
        try {
            manager.waitForStoreToOpen(); // Waits  notifyAll()

            if (store.enterStore()) {
                store.viewCatalog();

                System.out.println(Thread.currentThread().getName() + " attempts to buy " + itemToBuy);
                store.takeItem(itemToBuy);
                System.out.println(Thread.currentThread().getName() + " successfully bought " + itemToBuy);


                Thread.sleep(5000);

                store.leaveStore();
            } else {
                System.out.println(Thread.currentThread().getName() + " gave up waiting at the entrance.");
            }
        } catch (InterruptedException e) {
            // 2. Forced thread stop
            System.out.println(Thread.currentThread().getName() + " WAS INTERRUPTED AND FORCED TO LEAVE!");
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OnlineStore store = new OnlineStore();
        StoreManager manager = new StoreManager();

        Thread user1 = new Thread(new Customer(store, manager, "Laptop"), "Stepan");
        Thread user2 = new Thread(new Customer(store, manager, "Laptop"), "Impatient-Oleg");
        Thread user3 = new Thread(new Customer(store, manager, "Smartphone"), "Sanya");
        Thread user4 = new Thread(new Customer(store, manager, "Laptop"), "Yegor");

        user1.start();
        user2.start();
        user3.start();
        user4.start();

        System.out.println("Main: Customers are waiting for the store to open...");
        Thread.sleep(2000);
        manager.openStore(); // Triggers notifyAll()

        Thread.sleep(500);
        // 2. Forced stop of a thread
        System.out.println("Main: Oleg is taking too long, kicking him out!");
        user2.interrupt();

        user1.join();
        user2.join();
        user3.join();
        user4.join();

        System.out.println("Simulation finished.");
    }
}