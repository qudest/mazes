package backend.academy.mazes.utils;

import java.security.SecureRandom;

public final class SecureRandomSingleton {

    private SecureRandomSingleton() {
    }

    public static SecureRandom getInstance() {
        return Holder.INSTANCE;
    }

    private final static class Holder {
        private static final SecureRandom INSTANCE = new SecureRandom();
    }

}
