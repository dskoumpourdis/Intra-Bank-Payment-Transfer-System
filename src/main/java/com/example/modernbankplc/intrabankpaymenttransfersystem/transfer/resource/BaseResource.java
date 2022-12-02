package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 *  Simple DTO with an id property. Used as a base class for objects
 *  that need this property.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class BaseResource implements Serializable {
    protected Long id;
}
