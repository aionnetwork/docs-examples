package docsexample;

import avm.Address;
import org.aion.avm.embed.AvmRule;
import org.aion.avm.tooling.ABIUtil;
import org.aion.types.TransactionStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.math.BigInteger;

public class HelloAvmRuleTest {
    @ClassRule
    public static AvmRule avmRule = new AvmRule(true);
    private static Address from = avmRule.getPreminedAccount();
    private static Address dappAddr;

    @BeforeClass
    public static void deployDapp() {
        byte[] dapp = avmRule.getDappBytes(docsexample.HelloAvm.class, null);
        dappAddr = avmRule.deploy(from, BigInteger.ZERO, dapp).getDappAddress();
    }

    @Test
    public void testSetString() {
        byte[] txData = ABIUtil.encodeMethodArguments("setString","Hello Alice");
        AvmRule.ResultWrapper result = avmRule.call(from, dappAddr, BigInteger.ZERO, txData);
        TransactionStatus status = result.getReceiptStatus();
        Assert.assertTrue(status.isSuccess());
    }

    @Test
    public void testGetString() {
        byte[] txData = ABIUtil.encodeMethodArguments("getString");
        AvmRule.ResultWrapper result = avmRule.call(from, dappAddr, BigInteger.ZERO, txData);
        TransactionStatus status = result.getReceiptStatus();
        Assert.assertTrue(status.isSuccess());
    }
}

