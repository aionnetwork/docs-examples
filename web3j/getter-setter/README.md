# Getter Setter

This project is a standard Java application that allows you to interact with a simple _getter-setter_ blockchain application.

## Setup

You can import the project directly into IntelliJ using the `GetTheString.iml` file. Use the [Import a Project](https://docs.aion.network/docs/tools-intellij-plugin-import-a-project) guide.

## Explanation

This project contains two major directories, `lib` and `src`. The `lib` directory contains the Aion Virtual Machine `jar` file. The `src` directory contains two files, `GetTheString.java` and `GetterSetter.java`.

The `GetterSetter.java` file is the wrapped version of a _getter-setter_ contract deployed to the test network. The `GetTheString.java` file is a class for a standard Java application that gets a string from the Aion test network and displays it in the terminal. Here is the Java blockchain application used, for reference:

```java
package getset;
import org.aion.avm.tooling.abi.Callable;

public class HelloWorld
{
    private static String myString = "Hello World";

    @Callable
    public static String getString() {
        return myString;
    }

    @Callable
    public static void setString(String newStr) {
        myString = newStr;
    }
}
```
