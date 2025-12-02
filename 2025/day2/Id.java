package day2;

public class Id {
    private final long id;
    private final String str;

    private Id(long id, String str) {
        this.id = id;
        this.str = str;
    }

    static Id from(long id) {
        return new Id(id, String.valueOf(id));
    }

    boolean isInvalid() {
        if (true) {
            int k = 0;
            char first = str.charAt(k);
            for (int i = 1; i < str.length() / 2; i++) {
                // look for first appearance of this number
                char second = str.charAt(i);
                if (first != second) {
                    continue;
                }

                // now we have a pattern here, we need to iterate both.
                for (int j = i; j < str.length(); j++) {
                    if (str.charAt(k) != str.charAt(j)) {
                        return false;
                    }
                    k++;
                }

                System.out.println("invalid: " + this.id);
                return true;
            }
            return false;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + (str.length() / 2));
            if (c1 != c2) {
                return false;
            }
        }

        return true;
    }

    public long value() {
        return id;
    }
}
