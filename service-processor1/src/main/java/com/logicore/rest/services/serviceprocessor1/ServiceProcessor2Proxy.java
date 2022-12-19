package com.logicore.rest.services.serviceprocessor1;

import com.logicore.rest.services.serviceprocessor1.payment.PaymentMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="service-processor2")
public interface ServiceProcessor2Proxy {
    @PostMapping("/service-process2/")
    public ServiceProcessed1 retrieveProcessingStatus(PaymentMessage paymentMessage);

}
