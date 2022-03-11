package com.amore.cash.amoredb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Category {

    @Id
    @GeneratedValue
    Long categoryNo;

    String categoryName;

    Integer parent_no;

}
