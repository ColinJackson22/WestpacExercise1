package helpers;

public class Method {

    /*
    index is the expected location in the stack trace, with Method.class (this) being at index 0
    The calling class is at index 1, but the actual root calling class is liable to be
    a stepdef method which will generally lie at index 2. Hence, getCurrentMethodName() assumes
    index 2.
     */

    static String getCurrentMethodName() {
        return getCurrentMethodName(2);
    }

    static String getCurrentMethodName(int index) {
        return Thread.currentThread().getStackTrace()[index].getMethodName();
    }

    static String getCurrentClassName() {
        return getCurrentClassName(2);
    }

    static String getCurrentClassName(int index) {
        return Thread.currentThread().getStackTrace()[index].getClassName();
    }
}
