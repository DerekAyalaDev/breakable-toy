package com.encora.searchflights.enums;

public enum Currency {
    USD, MXN, EUR;

    @Override
    public String toString() {
        return name(); // Returns "USD", "MXN", etc.
    }
}
