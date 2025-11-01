package com.monolith.ecommerce.E_Commerce.controller;

import com.monolith.ecommerce.E_Commerce.DTO.payments.PaymentPayload;
import com.monolith.ecommerce.E_Commerce.service.RazorpayPaymentService;
import com.razorpay.RazorpayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pay")
public class PaymentController {

    @Autowired
    private RazorpayPaymentService paymentService;

    public Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam double amount, @RequestParam String currency) throws RazorpayException {
        return new ResponseEntity<>(paymentService.createOrder(amount,currency), HttpStatus.CREATED);
    }

    @PostMapping("/web-callback")
    public ResponseEntity<?> webHookCallbacks(@RequestBody(required = false) PaymentPayload data){
        logger.info("webhook called");
        logger.info(data.getEntity());
        logger.info(data.getPayload().getPayment().getEntity().getStatus());
        return new ResponseEntity<>(paymentService.getWebHookResponse(data),HttpStatus.CREATED);
    }

    @GetMapping("get/webhook-response")
    public ResponseEntity<?> sendWebHookResponse(){
        return new ResponseEntity<>(paymentService.sendWebHookResponse(),HttpStatus.ACCEPTED);
    }
}
