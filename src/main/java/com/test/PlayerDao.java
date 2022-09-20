package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PlayerDao {
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/allstates","root","password");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Player e){
		int status=0;
		try{
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into cricket values (?,?,?,?,?,?,?)");
			ps.setInt(1,e.getId());
			ps.setString(2,e.getName());
			ps.setInt(3,e.getMatches());
			ps.setInt(4,e.getScore());
			ps.setInt(5,e.getWickets());
			ps.setInt(6,e.getDucks());
			ps.setString(7,e.getType());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Player e){
		int status=0;
		try{
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("update cricket set matches=?,score=?,wickets=?,ducks=? where id=?");
			ps.setInt(1,e.getMatches());
			ps.setInt(2,e.getScore());
			ps.setInt(3,e.getWickets());
			ps.setInt(4,e.getDucks());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from cricket where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Player getEmployeeById(int id){
		Player e=new Player();
		
		try{
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from cricket where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setMatches(rs.getInt(3));
				e.setScore(rs.getInt(4));
				e.setWickets(rs.getInt(5));
				e.setDucks(rs.getInt(6));
				e.setType(rs.getString(7));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Player> getAllEmployees(){
		List<Player> list=new ArrayList<Player>();
		
		try{
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from cricket");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Player e=new Player();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setMatches(rs.getInt(3));
				e.setScore(rs.getInt(4));
				e.setWickets(rs.getInt(5));
				e.setDucks(rs.getInt(6));
				e.setType(rs.getString(7));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
	public static List<Player> getSelectedTeam(){
		List<Player> list=new ArrayList<Player>();//store entire database
		List<Player> finalTeam=new ArrayList<>();
		try {
			Connection con=PlayerDao.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from cricket");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Player e=new Player();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setMatches(rs.getInt(3));
				e.setScore(rs.getInt(4));
				e.setWickets(rs.getInt(5));
				e.setDucks(rs.getInt(6));
				e.setType(rs.getString(7));
				list.add(e);
			}
			Map<Float, Player> mapBatsman=new TreeMap<>(Collections.reverseOrder());
	        Map<Float, Player> mapBowler=new TreeMap<>(Collections.reverseOrder());
	        Map<Float, Player> mapKeeper=new TreeMap<>(Collections.reverseOrder());
	        for(Player p:list){
	            float avg=p.getScore()/p.getMatches();
	            if(p.getType().equalsIgnoreCase("WicketKeeper"))
	                mapKeeper.put(avg,p);
	            else if (p.getType().equalsIgnoreCase("Bowler"))
	                mapBowler.put(avg,p);
	            else
	                mapBatsman.put(avg,p);
	        }
	        //Add one wicketKeeper
	        Iterator<Map.Entry<Float, Player>> itr1=mapKeeper.entrySet().iterator();
	        Map.Entry<Float, Player> entry=null;
	        while(itr1.hasNext()){
	            entry= itr1.next();
	            finalTeam.add(entry.getValue());
	            break;
	        }
	        //add n bowlers
	        Iterator<Map.Entry<Float, Player>> itr2=mapBowler.entrySet().iterator();
	        int n=3;
	        //validation of this input is required
	        int count =1;
	        while(itr2.hasNext()){
	            entry= itr2.next();
	            finalTeam.add(entry.getValue());
	            count++;
	            if(count==n+1)
	                break;

	        }
	        //add remaining batsman
	        Iterator<Map.Entry<Float, Player>> itr3=mapBatsman.entrySet().iterator();

	        while(itr3.hasNext()){
	            entry= itr3.next();
	            finalTeam.add(entry.getValue());
	            count++;
	            if(count==11)
	                break;
	        }
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return finalTeam;
	}
	public static boolean validateInitialTeam(List<Player> list){
        int countBowler=0;
        int countKeeper=0;
        for(Player p:list){
            if(p.getType().equalsIgnoreCase("Bowler"))
                countBowler++;
            else if(p.getType().equalsIgnoreCase("WicketKeeper"))
                countKeeper++;
        }
        if(countBowler>3 && countKeeper>1)
            return true;
        else
            return false;

    } 	

}
