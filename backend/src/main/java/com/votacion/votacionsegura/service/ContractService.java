package com.votacion.votacionsegura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final String contractAddress;

    @Autowired
    public ContractService(Web3j web3j) {
        this.web3j = web3j;
        String privateKey = "0x223abb4054e778dfc4e80f979998631a50e324090a0ff2a61ea878ec1fcb8cde"; // Reemplaza con tu clave privada
        this.credentials = Credentials.create(privateKey);
        this.contractAddress = "0xf84D486f0372004b1261b9c2c8ED5FC037ca5257"; // Reemplaza con tu dirección del contrato desplegado
    }

    public TransactionReceipt createElection(String name, String startDate, String endDate) throws Exception {
        Function function = new Function(
                "createElection",
                Arrays.asList(new Utf8String(name), new Utf8String(startDate), new Utf8String(endDate)),
                Collections.emptyList()
        );

        return executeTransaction(function);
    }

    public TransactionReceipt addCandidate(int electionId, String name) throws Exception {
        Function function = new Function(
                "addCandidate",
                Arrays.asList(new Uint256(electionId), new Utf8String(name)),
                Collections.emptyList()
        );

        return executeTransaction(function);
    }

    public TransactionReceipt vote(int electionId, int candidateId) throws Exception {
        Function function = new Function(
                "vote",
                Arrays.asList(new Uint256(electionId), new Uint256(candidateId)),
                Collections.emptyList()
        );

        return executeTransaction(function);
    }

    public List<Object> getCandidate(int electionId, int candidateId) throws Exception {
        Function function = new Function(
                "getCandidate",
                Arrays.asList(new Uint256(electionId), new Uint256(candidateId)),
                Arrays.asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {})
        );

        List<Type> result = executeCall(function);
        return result.stream().map(this::convertTypeToObject).collect(Collectors.toList());
    }

    private TransactionReceipt executeTransaction(Function function) throws Exception {
        String encodedFunction = FunctionEncoder.encode(function);

        TransactionManager txManager = new RawTransactionManager(web3j, credentials);
        BigInteger gasPrice = BigInteger.valueOf(20000000000L);
        BigInteger gasLimit = BigInteger.valueOf(6721975);

        EthSendTransaction ethSendTransaction = txManager.sendTransaction(
                gasPrice,
                gasLimit,
                contractAddress,
                encodedFunction,
                BigInteger.ZERO
        );

        if (ethSendTransaction.hasError()) {
            throw new RuntimeException("Error sending transaction: " + ethSendTransaction.getError().getMessage());
        }

        String transactionHash = ethSendTransaction.getTransactionHash();
        TransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(
                web3j,
                TransactionManager.DEFAULT_POLLING_FREQUENCY,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH
        );

        return receiptProcessor.waitForTransactionReceipt(transactionHash);
    }

    private List<Type> executeCall(Function function) throws Exception {
        String encodedFunction = FunctionEncoder.encode(function);

        EthCall ethCall = web3j.ethCall(
                Transaction.createEthCallTransaction(credentials.getAddress(), contractAddress, encodedFunction), 
                org.web3j.protocol.core.DefaultBlockParameterName.LATEST
        ).send();

        if (ethCall.isReverted()) {
            throw new RuntimeException("Error calling function: " + ethCall.getRevertReason());
        }

        return FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
    }

    private Object convertTypeToObject(Type type) {
        if (type instanceof Uint256) {
            return ((Uint256) type).getValue();
        } else if (type instanceof Utf8String) {
            return ((Utf8String) type).getValue();
        } else {
            throw new IllegalArgumentException("Unsupported Type: " + type.getClass().getName());
        }
    }
}
