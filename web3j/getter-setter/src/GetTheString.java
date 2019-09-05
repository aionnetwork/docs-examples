import org.web3j.aion.VirtualMachine;
import org.web3j.aion.crypto.Ed25519KeyPair;
import org.web3j.aion.protocol.Aion;
import org.web3j.aion.tx.AionTransactionManager;
import org.web3j.aion.tx.gas.AionGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;

public class GetTheString {
    private static String NODE_ENDPOINT = "https://aion.api.nodesmith.io/v1/mastery/jsonrpc?apiKey=ab40c8f567874400a69c1e80a1399350";
    private static String PRIVATE_KEY = "269a1fd07297bd5e89ad1b55feaf809f90aaf7426c0d96579653939b5a468466cfb364bd76d41c6d322928a042b44c5e8e06bde11b2177f8888304f364e30f44";

    private static final Aion aion = Aion.build(new HttpService(NODE_ENDPOINT));
    private static final TransactionManager manager = new AionTransactionManager(aion, new Ed25519KeyPair(PRIVATE_KEY), VirtualMachine.AVM);

    public static void main(String [] args) throws Exception {

        // Deploy the contract.
        final GetterSetter getterSetterContract = GetterSetter.deploy(aion, manager, AionGasProvider.INSTANCE).send();
        System.out.println("Tx Hash:"+ getterSetterContract.getTransactionReceipt());
        System.out.println("Contract Address: " + getterSetterContract.getContractAddress());

        // Get the string from the contract.
        String firstResult = getterSetterContract.call_getString().send();
        System.out.println("Current string is: " + firstResult);

        // Set the string to "Hello World".
        TransactionReceipt transactionReceipt = getterSetterContract.send_setString("Hello World!").send();
        System.out.println("String Set: " + transactionReceipt.isStatusOK());

        // Get the string from the contract again.
        String secondResult = getterSetterContract.call_getString().send();
        System.out.println("Current string is: " + secondResult);
    }
}