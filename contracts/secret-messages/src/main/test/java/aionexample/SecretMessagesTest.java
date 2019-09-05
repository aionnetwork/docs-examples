package aionexample;
import avm.Address;
import avm.Blockchain;
import avm.Result;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.tooling.abi.Initializable;
import org.aion.avm.userlib.abi.ABIDecoder;

import java.math.BigInteger;

public class SecretMessagesTest
{
   @Initializable
   private static String secretMessage;
   @Initializable
   private static int secretKey;

   static {
       owner = Blockchain.getCaller();
   }

   @Callable
   public static String getMessage(int keyInput) {
       if (keyInput == secretKey) {
           return secretMessage;
       } else {
           return "Wrong secret key.";
       }
   }

   @Callable
   public static void setMessage(String newMessage, int newKey) {
       onlyOwner();

       secretMessage = newMessage;
       secretKey = newKey;
   }

   private static void onlyOwner() {
       Blockchain.require(Blockchain.getCaller().equals(owner));
   }
}