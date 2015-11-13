package net.is_bg.ltf.update.register.tourist;

import net.is_bg.ltf.update.register.common.sql.registertourist.v1.RegisterTouristInsertV1;
import net.is_bg.ltf.update.register.services.InitProcessorContainer;
import net.is_bg.ltf.update.register.services.ServiceProvider;

public class GenerateScriptsUtils {
	
	private final static String CREATE_TABLES = " CREATE TABLE if not exists turreg\n"+
			" (\n"+
			"   turreg_id numeric,\n"+
			"   nomer bigint,  \n"+
			"   municipality character varying,\n"+
			"   municipality_id numeric,\n"+
			"   udostovereniesrok character varying,\n"+
			"   kind character varying,\n"+
			"   type1 character varying,\n"+
			"   subtype character varying, \n"+
			"   name1 character varying,\n"+
			"   category character varying,\n"+
			"   capacity character varying,\n"+
			"   capacityindoor character varying,\n"+
			"   capacityoutdoor character varying,\n"+
			"   roomcnt character varying,\n"+
			"   bedcnt character varying,\n"+
			"   province character varying,\n"+
			"   city character varying,  \n"+
			"   address character varying,\n"+
			"   personincharge character varying,\n"+
			"   personcontact character varying,\n"+
			"   tel character varying,\n"+
			"   fax character varying,\n"+
			"   email character varying,\n"+
			"   regno character varying,\n"+
			"   recordcreated timestamp,\n"+
			"   recordchanges timestamp,\n"+
			"   xls_id character varying NOT NULL,   -- the unique identifier\n"+
			"   unique (xls_id),\n"+
			"   CONSTRAINT pk_turreg PRIMARY KEY (turreg_id)\n"+
			" );\n";


	
	public static RegisterTouristInsertV1 processObject(RegisterTouristInsertV1 object) {
		if(object == null) return null;
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		SelectTouristRegInsertScript sel  = ServiceProvider.getServiceWrapper().getDao().getTouristInsertScript(object.getCurrentPage(), /* object.getMunicipalityList()*/ "", object.getMaxInsertDate());
		
		//get script
		String script = sel.getTouristRegScript();
		
		if(script == null)  script = " \n";
		
		builder.append(InitProcessorContainer.SCRIPT_PROLOG);
		if(object.getCurrentPage() == 1)builder.append(CREATE_TABLES);
		//builder.append("delete from turregxls;\n");
		builder.append(script);
		builder.append(InitProcessorContainer.SCRIPT_EPILOG);
		script = null;
		
		object.setSql(builder.toString());
		builder = null;
		
		object.setLastPage(sel.isLastPage());
		
		return object;
	}
	
	
	/*
	 * 
	 *  select * from turreg tr 
		where  1=1
		--update conditions 
		and tr.recordchanges > to_date('12.12.2011', 'dd.mm.yyyy')
		and
		not 
		exists 
		(
		select xls_id from turreg t 
		--insert conditions
		where 1=1
		and tr.recordcreated > to_date('01.01.2012', 'dd.mm.yyyy')
		and tr.xls_id = t.xls_id 
		) 
	 */

}
