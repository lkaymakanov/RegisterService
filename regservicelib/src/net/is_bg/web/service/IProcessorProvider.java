package net.is_bg.web.service;

import net.is_bg.web.base.processors.IProcessObject;

public interface IProcessorProvider {
	IProcessObject<Object> getProcessObject(long type);
}
