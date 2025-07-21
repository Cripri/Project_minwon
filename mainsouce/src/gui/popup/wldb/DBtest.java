package gui.popup.wldb;

import function.connector.Members;
import function.connector.QueryRequest;
import gui.mainframe.MainFrameState;

public class DBtest {
	
	private static void givemeinfo() {
		QueryRequest<Members> request = new QueryRequest<>(
    			"SELECT * FROM Members"
    			+ " WHERE member_name like ?",
    			"시험용",
    			Members.class,
				MainFrameState.civil        			
    		);
		System.out.println(request.getSingleResult());
	}
	
	public static void main(String[] args) {
		givemeinfo();
	}

}
