package com.example.demo.service;

import com.example.demo.entity.Contract;
import java.util.List;

public interface ContractService {

    Contract saveContract(Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);
}
