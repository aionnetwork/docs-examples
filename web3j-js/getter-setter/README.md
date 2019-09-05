# Getter Setter

This is a basic frontend for a simple _getter-setter_ blockchain application built on the Aion network.

## Setup

All you need to get things up and running are:

1. A node URL.
2. A private key.

If you choose to not use the contract address listed in `assets/js/script.js` then you'll need to supply a new contract address by deploying the contract (listed below) again.

## Explanation

The script (`script.js`) looks for two methods:

- `getString`
- `setString`

By clicking on either of the buttons on the frontend, you'll be able to retrieve and set the string in a particular contract, assuming these two methods exist. This frontend was built using the following contract:

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

If you want to use a copy of the above contract that's already been deployed, uncomment line 14 in `assets/js/script.js`.

## Demo

There's a [live demo of this app available](https://aion-examples.netlify.com/web3-js/getter-setter), kindly hosted for free by [Netlify](https://www.netlify.com/).
