package com.ikinsure.hyperskill.hard.budget;

import java.util.ArrayList;
import java.util.Collections;

public class PurchaseManager {

    private final ArrayList<Purchase> purchases;
    private double sum;

    public PurchaseManager() {
        this.purchases = new ArrayList<>();
        this.sum = 0.0;
    }

    public void add(Purchase p) {
        purchases.add(p);
    }

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public double getCurrentSum() {
        return sum;
    }

    public void clear() {
        purchases.clear();
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public String getList(Categories category) {
        sum = 0.0;
        StringBuilder result = new StringBuilder();
        if (category == null) {
            for (Purchase p : purchases) {
                result.append(p).append("\n");
                sum += p.getPrice();
            }
        } else {
            for (Purchase p : purchases) {
                if (p.getCategory() == category) {
                    result.append(p).append("\n");
                    sum += p.getPrice();
                }
            }
        }
        return result.length() == 0 ? "Purchase list is empty!" : result.toString();
    }

    public String getSortedList(Categories category) {
        sum = 0.0;
        ArrayList<Purchase> copy = new ArrayList<>(purchases);
        Collections.sort(copy);
        StringBuilder result = new StringBuilder();
        if (category == null) {
            for (Purchase p : copy) {
                result.append(p).append("\n");
                sum += p.getPrice();
            }
        } else {
            for (Purchase p : copy) {
                if (p.getCategory() == category) {
                    result.append(p).append("\n");
                    sum += p.getPrice();
                }
            }
        }
        return result.length() == 0 ? "Purchase list is empty!" : result.toString();
    }
}
