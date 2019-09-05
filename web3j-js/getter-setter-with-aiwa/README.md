# Connect to Aiwa

This project showcases how to connect a website to the Aion blockchain using the [Aiwa browser wallet](https://chrome.google.com/webstore/detail/aiwa/objigohafkcoodmofgmifblmfidicehc). Since Aiwa is only available on Chromeum based browser, this application will only work on [Chrome](https://www.google.com/chrome/) and [Brave](https://brave.com/).

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

The Aiwa browser wallet gets called by using the `aionweb3` object that is injected by the extension. Clicking the **Set String** button prompts Aiwa to hand the transaction object, sign it with your accounts private key, and send the `signedTransaction` object it to the network.

## Demo

There's a [live demo of this app available](https://aion-examples.netlify.com/web3-js/getter-setter-with-aiwa), kindly hosted for free by [Netlify](https://www.netlify.com/).
