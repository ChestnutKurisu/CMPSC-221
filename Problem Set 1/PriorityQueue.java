import java.util.ArrayList;

public class PriorityQueue {
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<Integer> priorities = new ArrayList<Integer>();

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        System.out.println("Enqueuing the following ordered pairs in order: (V, 10), (W, 1), (X, 13)," +
                " (U, 13), (T, 5), (Y, 4), (Z, 7), (S, 1), (R, 13)");
        pq.enqueue("V", 10);
        pq.enqueue("W", 1);
        pq.enqueue("X", 13);
        pq.enqueue("U", 13);
        pq.enqueue("T", 5);
        pq.enqueue("Y", 4);
        pq.enqueue("Z", 7);
        pq.enqueue("S", 1);
        pq.enqueue("R", 13);
        System.out.print("After enqueueing, the queue looks like: ");
        pq.printPQueue();
        //TAIL->(S, 1)->(W, 1)->(Y, 4)->(T, 5)->(Z, 7)->(V, 10)->(R, 13)->(U, 13)->(X, 13)->HEAD");
        System.out.println("Dequeuing 4 elements and adding (A, 5), (B, 6), and (C, 1) yields");
        for(int i=0; i<4; i++)
            System.out.println(pq.dequeue());
        pq.enqueue("A", 5);
        pq.enqueue("B", 6);
        pq.enqueue("C", 1);
        pq.printPQueue();
        System.out.println("Dequeuing the priority queue until it is empty:");
        while (!pq.isEmpty()) {
            System.out.println(pq.dequeue());
        }
        System.out.println("The empty queue looks like:");
        pq.printPQueue();
    }

    public void printPQueue() {
        System.out.print("TAIL->");
        for (int i = 0; i < items.size(); i++) {
            System.out.print("(" + items.get(i) + ", " + priorities.get(i) + ")->");
        }
        System.out.println("HEAD");
    }

    public boolean isEmpty() {
        if (items.size() == 0 || priorities.size() == 0)
            return true;
        return false;
    }

    public void enqueue(String s, int priority) {
        //Adding elements to an empty priority queue
        if (items.size() == 0) {
            items.add(s);
            priorities.add(priority);
            return;
        }
        //Adding elements to a priority queue. Elements with equal priority are arranged and dequeued in the order that they were added.
        for (int i = 0; i < items.size(); i++) {
            if (priority <= priorities.get(i)) {
                items.add(i, s);
                priorities.add(i, priority);
                break;
            }
            if (i == items.size() - 1) {
                items.add(s);
                priorities.add(priority);
                break;
            }
        }
    }

    public String dequeue() {
        //Return null if the queue is empty
        if (items.size() == 0 || priorities.size() == 0)
            return null;
        String s = items.get(items.size() - 1);
        items.remove(items.size() - 1);
        priorities.remove(priorities.size() - 1);
        return s;
    }
}
