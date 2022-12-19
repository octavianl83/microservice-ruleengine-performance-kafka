package com.logicore.rest.services.serviceprocessor1;

import com.logicore.rest.services.serviceprocessor1.model.Product;
import com.logicore.rest.services.serviceprocessor1.payment.PaymentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ServiceProcessor1Controller {

    @Autowired
    private RuleEngineProxy ruleEngineProxy;

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/service-process1")
    public ServiceProcessed1 processService1(@RequestBody PaymentMessage paymentMessage) throws Exception {

        Product product = ruleEngineProxy.getDiscount("gold", 32);
        ServiceProcessed1 serviceProcessed1 = (ServiceProcessed1) kafkaService.kafkaRequestReply(paymentMessage);

        return new ServiceProcessed1(serviceProcessed1.getId(), serviceProcessed1.isProcessed());
    }

}
