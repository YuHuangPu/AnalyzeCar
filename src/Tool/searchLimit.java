package Tool;

import Log.loginsert;

public class searchLimit {
	private static String SQL ;
	public searchLimit(){
		
	}
	static void setLimit(Double [] grade,String make,String moneyMAX,String moneyMIN,String CarSize,String CarPower,String CarDrive,String CarPeople,String CCMAX,String CCMIN){
		set(grade,make,moneyMAX,moneyMIN,CarSize,CarPower,CarDrive,CarPeople,CCMAX,CCMIN);
	}
	private static void set(Double [] grade,String make,String moneyMAX,String moneyMIN,String CarSize,String CarPower,String CarDrive,String CarPeople,String CCMAX,String CCMIN){
		SQL = " and "+"C.EASY >= "+(grade[0]-2)*0.01
				+ " and "+"C.SAFETY >= "+(grade[1]-2)*0.01
				+ " and "+"C.COZY >= "+ (grade[2]-2)*0.01
				+ " and "+"C.CONTROLLED >= "+(grade[3]-2)*0.01
				+ " and "+"C.MONEY >= "+(grade[4]-2)*0.01
				+ (make != null || moneyMAX != null ||CarSize !=null ||CarPower !=null ||CarDrive !=null ||CarPeople !=null
					?" and C.name in "
						+"(select concat(a.�~��, ' ', a.�~�P, ' ', a.�ڦ�) from allcar a "
						+ "where 1"
						+ (moneyMAX!=null?" and a.���� between "+moneyMIN+" and "+moneyMAX:"")
						+ (make !=null?" and a.�~�P = ("+make+") ":"")
						+ (CarSize!=null?" and a.�������� like '%"+CarSize+"%'":"")
						+ (CarPower!=null?" and a.�ʤO���� ='"+CarPower+"'":"")
						+ (CarDrive!=null?" and a.�X�ʫ��� ='"+CarDrive+"'":"")
						+ (CarPeople!=null?" and a.�y��� ='"+CarPeople+"'":"")
						+ (CCMAX!=null?" and substring_index(a.�Ʈ�q,'cc',1) between "+CCMIN+" and "+CCMAX:"")
						+ ")"
						:"")
				
				;
	}
	public static String getSQL(String Type) {
		String re = Type.equals("c")
				?("select count(*) from cargrade C where 1 "+SQL)
				:("select C.NAME,C.EASY,C.SAFETY,C.COZY,C.CONTROLLED,C.MONEY from cargrade C where 1 "+SQL);
		return re;
	}
}
