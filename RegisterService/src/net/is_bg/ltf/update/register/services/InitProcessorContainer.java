package net.is_bg.ltf.update.register.services;



import net.is_bg.ltf.update.register.common.results.delduplicateaddress.ClientDelDuplicateResult;
import net.is_bg.ltf.update.register.common.sql.registertourist.v1.RegisterTouristInsertV1;
import net.is_bg.ltf.update.register.tourist.GenerateScriptsUtils;
import net.is_bg.web.base.processors.IProcessObject;
import net.is_bg.web.base.processors.ProcessorContainer;





public class InitProcessorContainer {

	private static InitProcessorContainer ref = null;
	private InitProcessorContainer(){}
	
	
	public static final String SCRIPT_PROLOG = "do \n  $$declare \n begin \n";
	public static final String SCRIPT_EPILOG = " end$$; \n";
	public static final String EMPTY_SQL =  SCRIPT_PROLOG + SCRIPT_EPILOG;

	
	public void initContainer(ProcessorContainer processor) {
		
		ProcessorContainer container = (ProcessorContainer)processor;
		container.addProcessor(RegisterTouristInsertV1.class, new IProcessObject<RegisterTouristInsertV1>() {
			
			@Override
			public RegisterTouristInsertV1 processObject(RegisterTouristInsertV1 object) {
				return GenerateScriptsUtils.processObject(object);
			}
		});
		
		
		container.addProcessor(ClientDelDuplicateResult.class, new IProcessObject<ClientDelDuplicateResult>() {
			@Override
			public ClientDelDuplicateResult processObject(
					ClientDelDuplicateResult arg0) {
				// TODO Auto-generated method stub
				if(arg0 != null) {
					if (arg0.getClientInfo() != null  && arg0.getClientInfo().getClientAddress() != null){
						System.out.println("Client Address is:" +  arg0.getClientInfo().getClientAddress().toString());
					}
					
					if(arg0.getFields().get(0) != null){
						System.out.println(arg0.getFields().get(0));
					}
					
					if(arg0.getMsg() != null ){
						System.out.println(arg0.getMsg());
					}
				}
				return arg0;
			}
		});
		
		System.out.println("===============================InitProcessorContainer========================================");
	}
			
	
	public static void initProcessContainer(){
		if(ref == null) {
			ref = new InitProcessorContainer();
			ref.initContainer(ProcessorContainer.getProcessContainer());
		}
	}
	
}
