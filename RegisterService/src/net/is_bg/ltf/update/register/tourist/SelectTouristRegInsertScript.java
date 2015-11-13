package net.is_bg.ltf.update.register.tourist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import net.is_bg.ltf.update.register.services.SqlUtils;

public class SelectTouristRegInsertScript extends SelectPagingSqlStatement{
	
	
	private String touristRegScript = null;
	private static final long PAGE_SIZE_IN_ROWS = 1000; 
	private static final String PAGE_SQL_BEGIN = " select b.currow from (select a.*, ROW_NUMBER() OVER (ORDER BY 0) rnum from ( ";
	private static String TO_ARRAY_PROLOG = " select  array_to_string(array (	\n"; 
	private static String TO_ARRAY_EPILOG =     " \n"+ " ), '\n "+ " ') script;\n";
	
	private String PAGE_SQL_END;   
	private long pageNumber = -1;
	private boolean lastPage = false;
	private String municipalityList = "   99999999999 ";
	private Date maxInsertDate;
	
	private final static String sql = 		
			" select  \n"+
			" ' insert into turreg (" +
					
			//table fields 
			"   turreg_id, " +
			"   nomer, " +
			"   municipality, " +
			"   municipality_id, " +
			"   udostovereniesrok, " +
			"   kind, " +
			"   type1, " +
			"   subtype, " +
			"   name1, " +
			"   category, " +
			"   capacity, " +
			"   capacityindoor, " +
			"   capacityoutdoor, " +
			"   roomcnt, " +
			"   bedcnt, " +
			"   province, " +
			"   city," +
			"   address," +
			"   personincharge, " +
			"   personcontact," +
			"   tel," +
			"   fax," +
			"   email," +
			"   regno," +
			"   recordcreated, " +
			"   recordchanges, " +
			"   xls_id)  " +
			
			//values
			"   values ( ' \n"+
			" ||  'snextval('||  '''' || 's_turreg' || '''' || ')' || ',' "+
			" ||   case when ( treg.nomer) is null then ' null' else    treg.nomer::text  end || ',' \n"+
			" ||   case when ( treg.municipality ) is null then ' null' else   '''' || treg.municipality || ''''  end || ',' \n"+
			" ||   case when ( treg.municipality_id) is null then ' null' else  treg.municipality_id::text  end || ',' \n"+
			" ||   case when ( treg.udostovereniesrok ) is null then ' null ' else   '''' || treg.udostovereniesrok || ''''  end || ',' \n"+
			" ||   case when ( treg.kind ) is null then ' null ' else   '''' || treg.kind || ''''  end || ',' \n"+
			" ||   case when ( treg.type1 ) is null then ' null ' else   '''' || treg.type1 || ''''  end || ',' \n"+
			" ||   case when ( treg.subtype ) is null then ' null ' else   '''' || treg.subtype || ''''  end || ',' \n"+
			" ||   case when ( treg.name1 ) is null then ' null ' else   '''' || treg.name1 || ''''  end || ',' \n"+
			" ||   case when ( treg.category ) is null then ' null ' else   '''' || treg.category || ''''  end || ',' \n"+
			" ||   case when ( treg.capacity ) is null then ' null ' else   '''' || treg.capacity || ''''  end || ',' \n"+
			" ||   case when ( treg.capacityindoor ) is null then ' null ' else   '''' || treg.capacityindoor || ''''  end || ',' \n"+
			" ||   case when ( treg.capacityoutdoor ) is null then ' null ' else   '''' || treg.capacityoutdoor || ''''  end || ',' \n"+
			" ||   case when ( treg.roomcnt ) is null then ' null ' else   '''' || treg.roomcnt || ''''  end || ',' \n"+
			" ||   case when ( treg.bedcnt ) is null then ' null ' else   '''' || treg.bedcnt || ''''  end || ',' \n"+
			" ||   case when ( treg.province ) is null then ' null ' else   '''' || treg.province || ''''  end || ',' \n"+
			" ||   case when ( treg.city ) is null then ' null ' else   '''' || treg.city || ''''  end || ',' \n"+
			" ||   case when ( treg.address ) is null then ' null ' else   '''' || treg.address || ''''  end || ',' \n"+
			" ||   case when ( treg.personincharge ) is null then ' null ' else   '''' || treg.personincharge || ''''  end || ',' \n"+
			" ||   case when ( treg.personcontact ) is null then ' null ' else   '''' || treg.personcontact || ''''  end || ',' \n"+
			" ||   case when ( treg.tel ) is null then ' null ' else   '''' || treg.tel || ''''  end || ',' \n"+
			" ||   case when ( treg.fax ) is null then ' null ' else   '''' || treg.fax || ''''  end || ',' \n"+
			" ||   case when ( treg.email ) is null then ' null ' else   '''' || treg.email || ''''  end || ',' \n"+
			" ||   case when ( treg.regno ) is null then ' null ' else   '''' || treg.regno || ''''  end || ',' \n"+
			" ||   case  treg.recordcreated  when null then ' null ' else  ' timestamp  ' || '''' || to_char(treg.recordcreated, 'dd.mm.yyyy hh24:mm:ss')  ||  ''''    end || ',' \n"+
			" ||   case  treg.recordchanges  when null then ' null ' else  ' timestamp  ' || '''' || to_char(treg.recordchanges, 'dd.mm.yyyy hh24:mm:ss')  ||  ''''    end || ',' \n"+
			" ||   case when ( treg.xls_id ) is null then ' null ' else   '''' || treg.xls_id || ''''  end || ' );'  currow \n"+
			" from turreg treg \n" ;
	        
		    
	
	
	public SelectTouristRegInsertScript(long pageNum, String municipalityList, Date maxInsertDate){
		this.pageNumber = pageNum;
		this.municipalityList = municipalityList;
		this.maxInsertDate = maxInsertDate;
		
		if(pageNumber < 1) throw new RuntimeException("Invalid page number passed to SelectTouristRegScript");
		
		long pagebegin = ((pageNumber - 1)*PAGE_SIZE_IN_ROWS) + 1;
		long pageEnd  =  pageNumber*PAGE_SIZE_IN_ROWS;
		
		PAGE_SQL_END = "  ) a ) b where rnum  >= " + pagebegin  + " and rnum <= " + pageEnd+  " ";
	}
	

	@Override
	protected String getSqlString() {
		// TODO Auto-generated method stub
		String sqlret = addPaging(sql); /* +  createInsertConditions())*/;
		System.out.println(sqlret);
		return sqlret;
		
	}
	
	
	private String addPaging(String sql){
		return PAGE_SQL_BEGIN + sql + PAGE_SQL_END;
	}
	
	private String toArray(String sql){
		return TO_ARRAY_PROLOG +  sql +  TO_ARRAY_EPILOG;
	}
	
	
	@Override
	protected void retrieveResult(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		long cnt = 0;
		StringBuilder b = new StringBuilder();
		while(rs.next()){
			String res = rs.getString(1);
			b.append(res + "\n");
			if(res != null && !res.equals(""))cnt++;
		}
		if(cnt > 0) lastPage = false;
		else lastPage = true;
		
		touristRegScript = b.toString();
	}


	public String getTouristRegScript() {
		return touristRegScript;
	}


	public boolean isLastPage() {
		return lastPage;
	}
	
	
	public String createInsertConditions(){
		bindVarData.setDate(SqlUtils.toSQLDate(maxInsertDate));
		return   " where 1=1  " + /* and treg.municipality_id in (" + municipalityList + " ) "  + "*/  " and  treg.recordcreated >  ? ";
	}
	

}
