package com.twa.reservations.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Objects;

public class PriceDTO {

    @Positive(message = "totalPrice must be a positive value")
    private BigDecimal totalPrice;

    @PositiveOrZero(message = "totalTax must be a positive value")
    private BigDecimal totalTax;

    @Positive(message = "basePrice must be a positive value")
    private BigDecimal basePrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return Objects.equals(totalPrice, priceDTO.totalPrice) && Objects.equals(totalTax, priceDTO.totalTax)
                && Objects.equals(basePrice, priceDTO.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice, totalTax, basePrice);
    }

    @Override
    public String toString() {
        return "PriceDTO{" + "totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", basePrice=" + basePrice + '}';
    }
}
