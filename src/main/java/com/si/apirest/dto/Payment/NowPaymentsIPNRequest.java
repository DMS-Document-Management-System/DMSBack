package com.si.apirest.dto.Payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NowPaymentsIPNRequest {
     @JsonProperty("payment_id")
    private String paymentId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("ipn_secret")
    private String ipnSecret;

    @JsonProperty("price_amount")
    private double priceAmount;

    @JsonProperty("price_currency")
    private String priceCurrency;

    @JsonProperty("order_description")
    private String orderDescription;
}
