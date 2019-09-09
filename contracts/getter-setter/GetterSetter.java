package docsexample;

import org.aion.avm.tooling.abi.Callable;

public class GetterSetter
{
    private static String myStr = "Hello AVM";

    @Callable
    public static String getString() {
        return myStr;
    }

    @Callable
    public static void setString(String newStr) {
        myStr = newStr;
    }
}
