package com.library.entity;

import lombok.Data;

@Data
public class Book {
    int bid;
    final String title;
    final String description;
    final double price;
}
