package com.chase.demo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private Long buyerId;
    private Long itemId;
    private Integer quantity;
    private Timestamp purchaseDate;
}
