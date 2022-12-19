package com.logicore.rest.services.serviceprocessor3;

import com.logicore.rest.services.serviceprocessor3.payment.PaymentMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="service-processor4")
public interface ServiceProcessor4Proxy {
    @PostMapping("/service-process4/")
    public ServiceProcessed3 retreiveProcessingStatus(PaymentMessage paymentMessage);

}
