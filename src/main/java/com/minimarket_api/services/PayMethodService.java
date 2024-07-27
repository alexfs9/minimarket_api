package com.minimarket_api.services;

import com.minimarket_api.persistence.entities.PayMethodEntity;
import com.minimarket_api.persistence.enums.PayMethodEnum;
import com.minimarket_api.persistence.repositories.PayMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayMethodService {

    @Autowired
    private PayMethodRepository payMethodRepository;

    public Optional<PayMethodEntity> findByPayMethod(PayMethodEnum payMethod) {
        return this.payMethodRepository.findByPayMethod(payMethod);
    }

    public PayMethodEntity save(PayMethodEntity payMethod) {
        return payMethodRepository.save(payMethod);
    }
}
