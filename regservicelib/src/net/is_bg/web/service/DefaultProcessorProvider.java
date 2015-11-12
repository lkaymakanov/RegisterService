package net.is_bg.web.service;

import net.is_bg.web.base.processors.IProcessObject;
import net.is_bg.web.base.processors.ProcessorContainer;

public class DefaultProcessorProvider implements IProcessorProvider {

	@Override
	public IProcessObject<Object> getProcessObject(long type) {
		// TODO Auto-generated method stub
		return ProcessorContainer.getProcessContainer();
	}
}
