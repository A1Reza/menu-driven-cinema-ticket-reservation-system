package com.cinema.model;

public class WaitingQueue {
    private Customer[] queue;
    private int customerCount;
    private final int WAITING_QUEUE_LENGTH = 20;

    public WaitingQueue() {
        this.customerCount = 0;
        this.queue = new Customer[WAITING_QUEUE_LENGTH];
    }

    public boolean isFull() {
        return customerCount >= WAITING_QUEUE_LENGTH;
    }

    public boolean add(Customer customer) {
        if (isFull()) return false;
        for (int i = 0; i < customerCount; i++) {
            if (queue[i].equals(customer))
                return false;
        }
        queue[customerCount++] = customer;
        return true;
    }

    public boolean isEmpty() {
        return customerCount <= 0;
    }

    public Customer poll() {
        if (isEmpty()) return null;
        Customer first = queue[0];
        for (int i = 0; i < customerCount - 1; i++) {
            queue[i] = queue[i + 1];
        }
        queue[--customerCount] = null;
        return first;
    }

    public int size() {
        return customerCount;
    }

    public void display() {
        System.out.println("All customers in the waiting queue with indices: ");
        for (int i = 0; i < customerCount; i++)
            System.out.println((i + 1) + "." + queue[i]);
    }
}

