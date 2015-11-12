package net.is_bg.ltf.update.register.common.providers;

import net.is_bg.ltf.update.register.common.objectprocessor.ProcessObjectFactory;
import net.is_bg.ltf.update.register.common.objectprocessor.ProcessObjectFactory.OBJ_PROCESSOR;
import net.is_bg.web.base.processors.IProcessObject;
import net.is_bg.web.service.IProcessorProvider;

public class MyProcessorProvider implements IProcessorProvider{

	@Override
	public IProcessObject<Object> getProcessObject(long arg0) {
		// TODO Auto-generated method stub
		return ProcessObjectFactory.getProcessObject(OBJ_PROCESSOR.valToObjProcessor(arg0));
	}

}
