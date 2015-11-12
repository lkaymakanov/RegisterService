package net.is_bg.ltf.update.register.common.utils;

import java.io.Serializable;

public interface IBindVariableInfo extends Serializable {
	 Object 	getValue();
	 int		getType();
	 int 	    getPosition();
	 boolean    IsOutputParam();
}
