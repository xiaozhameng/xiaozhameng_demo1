package com.xiaozhameng.base.validation;

import com.xiaozhameng.common.exception.PlatformException;
import com.xiaozhameng.common.message.CommonMessage;
import com.xiaozhameng.common.utils.MessageHelper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;


public class DefaultValidationService {
	
	private Validator baseValidator;
	
	public DefaultValidationService() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.baseValidator = factory.getValidator();
	}
	
	/**
	 * 注解验证
	 * @throws PlatformException 
	 * @throws ClassNotFoundException 
	 */
	protected <T> void validate(T request, Class<?>... groups) throws PlatformException, ClassNotFoundException {
		Set<ConstraintViolation<T>> violations = baseValidator.validate(request, Default.class);
		if(violations.size() == 0) {
			violations.addAll(baseValidator.validate(request, groups));
		}
		
		for(ConstraintViolation violation : violations) {
			StringBuilder builder = new StringBuilder();
			MessageHelper msgHelp = MessageHelper.getInstance();
			builder.append(msgHelp.getMessageByCode(CommonMessage.PARAM_VALIDATE_FAIL));
			builder.append(":");
			String msg = violation.getMessage();
			if(MessageHelper.isErrorCode(msg)) {
				builder.append(msgHelp.getMessageByCode(msg));
			} else {
				builder.append(violation.getPropertyPath()).append(msg);
			}
			throw new PlatformException(CommonMessage.PARAM_VALIDATE_FAIL, builder.toString());
		}
	}
}
