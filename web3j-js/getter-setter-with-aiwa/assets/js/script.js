// Create a web3 object.
const web3 = new Web3(new Web3.providers.HttpProvider('https://aion.api.nodesmith.io/v1/mastery/jsonrpc?apiKey=ab40c8f567874400a69c1e80a1399350'));

// Set the address we want to call.
let contractAddress = "0xa003cd11951f9a58f81df851e83cf7b5eca4b2ca5d6429dadb49021c13603357";

// Call the getString method to get the myString variable from the network.
async function getString() {
    let data = web3.avm.contract.method("getString").encode();
    const transactionObject = {
        to: contractAddress,
        data: data,
        type: "0x1"
    };

    let initialResponse = await web3.eth.call(transactionObject);
    let avmResponse = await web3.avm.contract.decode("string", initialResponse);
    document.querySelector('#string_output').innerHTML = avmResponse;

    document.querySelector(
        "#last_updated_code"
    ).innerHTML = new Date().getTime();
}

// Set the string input field as the new string.
async function setString() {
    document.querySelector('#set_string_button').innerHTML = 'Loading...';
    document.querySelector('#set_string_button').disabled = true;

    let stringInput = document.querySelector("#string_input").value;

    let data = web3.avm.contract
        .method("setString")
        .inputs(["string"], [stringInput])
        .encode();

    const transactionObject = {
        to: contractAddress,
        data: data
    };

    // Call the aionweb3 object injected by Aiwa.
    let transactionHash = await aionweb3.sendTransaction(transactionObject);
    
    // Wait for the receipt to be returned from the network.
    let timer = setInterval(
        async function() {
            if(await web3.eth.getTransactionReceipt(transactionHash)){
                console.log("getTransactionReceipt", transactionHash);
                console.log("onTxComplete");
                document.querySelector('#set_string_button').innerHTML = 'Set String';
                document.querySelector('#set_string_button').disabled = false;
                getString();
                clearInterval(timer);
            } else {
                console.log("Transaction Pending", transactionHash);
            }
        },
        1000
    );

    // Reload the string variable on the page.
    getString();
}

// Call the getString function as soon as the page has loaded.
window.onload = function() {
    getString();
}