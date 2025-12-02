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
        if (str.length() < 2) {
             return false;
        }

        if (str.length() == 2) {
            return str.charAt(0) == str.charAt(1);
        }

        //System.out.println("testing " + this.id);
        if (true) {
            int k = 0;
            char first = str.charAt(k);
            for (int i = 1; i < str.length(); i++) {
                k = 0;

                // look for first appearance of this number
                char second = str.charAt(i);
                if (first != second) {
                    //System.out.println("\tcontinue1 str.length():" + str.length() + " i:" + i);
                    continue;
                }

                if (str.length() % i != 0) {
                    //System.out.println("\tcontinue2 str.length():" + str.length() + " i:" + i);
                    continue;
                }

                // now we have a pattern here, we need to iterate both.
                int j;
                for (j = i; j < str.length(); j++) {
                    if (str.charAt(k) != str.charAt(j)) {
                        //System.out.println("\tbreak");
                        break;
                    }
                    k++;
                }

                if (j != str.length()) {
                    //System.out.println("\tcontinue3");
                    continue;
                }

                //System.out.println("\tinvalid: " + this.id);
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

        //System.out.println("\tinvalid: " + this.id);
        return true;
    }

    public long value() {
        return id;
    }
}
