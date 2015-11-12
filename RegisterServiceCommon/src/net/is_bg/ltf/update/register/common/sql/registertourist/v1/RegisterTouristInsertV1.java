package net.is_bg.ltf.update.register.common.sql.registertourist.v1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.is_bg.ltf.update.register.common.sql.adapters.RegServiceSqlAdapter;



public class RegisterTouristInsertV1 extends RegServiceSqlAdapter {

    private static final long serialVersionUID = 3936079113192282773L;
    private String  municipalityList =  " 9999999999999999 ";
    private boolean lastPage= false;
    private long currentPage = 1;
    private Date maxInsertDate;
    private Date maxUpdateDate;
    
    //no - arg constructor
    public RegisterTouristInsertV1() {
    	lastPage = true;
    	currentPage = 1;
    }
    
    public RegisterTouristInsertV1(Long curentPage, ArrayList<Long> municipality_id, Date maxInsertDate, Date maxUpdateDate) {
    	this.currentPage = curentPage;
    	this.maxInsertDate = maxInsertDate;
    	this.maxUpdateDate = maxUpdateDate;
    	createMuniciaplityList(municipality_id);
    }

    
    public String toString() {
    	return " This is RegisterTouristInsertV1 instance \n currentPage = " + currentPage  + " municipality_id = " + municipalityList + "; lastUpdate = " + "\n" + getSql();
    }



	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}


	
	public Date getMaxInsertDate() {
		return maxInsertDate;
	}


	public Date getMaxUpdateDate() {
		return maxUpdateDate;
	}
	
	private void createMuniciaplityList(List<Long> municiapality){
		if(municiapality == null || municiapality.size() == 0) {
			return ;
		}
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < municiapality.size(); i++){
			if(i > 0) builder.append(",");
			builder.append(municiapality.get(i));
		}
		
		municipalityList = builder.toString();
	}

	public String getMunicipalityList() {
		return municipalityList;
	}
	
	
	

}
