package com.example.modernbankplc.intrabankpaymenttransfersystem.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class that can be extended by other classes to obtain a logger.
 */

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());
}
