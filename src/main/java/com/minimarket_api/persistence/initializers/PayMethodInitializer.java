package com.minimarket_api.persistence.initializers;

import com.minimarket_api.persistence.entities.PayMethodEntity;
import com.minimarket_api.persistence.enums.PayMethodEnum;
import com.minimarket_api.services.PayMethodService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PayMethodInitializer {

    @Autowired
    private PayMethodService payMethodService;

    @PostConstruct
    public void initializePayMethods() {
        for (PayMethodEnum payMethod : PayMethodEnum.values()) {
            Optional<PayMethodEntity> registeredPayMethod = this.payMethodService.findByPayMethod(payMethod);
            if (registeredPayMethod.isEmpty()) {
                PayMethodEntity payMethodToRegister = new PayMethodEntity();
                payMethodToRegister.setPayMethod(payMethod);
                this.payMethodService.save(payMethodToRegister);
            }
        }
    }
}
