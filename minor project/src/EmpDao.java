import java.util.*;
import java.sql.*;
public class EmpDao {
	public static Connection getConnection()   //Connection interface (return type)
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tcs","root","shashee");  //mysql localhost & getConnection  user define
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	public static int save(Emp e) {
		int status=0;
		try {
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into emp values(?,?,?,?,?)");
			ps.setInt(1,e.getId());
			ps.setString(2,e.getName());
			ps.setString(3,e.getPassword());
			ps.setString(4,e.getEmail());
			ps.setString(5,e.getCountry());
			
			status=ps.executeUpdate();
			
			con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		return status;
	}
public static List<Emp>getAllEmployees()
{
	List<Emp>list=new ArrayList<Emp>();
	
	try {
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from emp");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Emp e=new Emp();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setPassword(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setCountry(rs.getString(5));
			list.add(e);
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return list;
}
public static Emp getEmployeeById(int id)
{
	Emp e=new Emp();

try
{
	Connection con=EmpDao.getConnection();
	PreparedStatement ps=con.prepareStatement("select * from emp where emp_id=?");
	ps.setInt(1,id);
	ResultSet rs=ps.executeQuery();  
	if(rs.next())
	{
		e.setId(rs.getInt(1));   //0
		e.setName(rs.getString(2));
		e.setPassword(rs.getString(3));
		e.setEmail(rs.getString(4));
		e.setCountry(rs.getString(5));
	}
	con.close();
}
catch(Exception exx)
{
	exx.printStackTrace();
}
return e;
}

public static int update(Emp e)
{
	int status=0;

try {
	Connection con=EmpDao.getConnection();
	PreparedStatement ps=con.prepareStatement("update emp set name=?,password=?,email=?,country=? where emp_id=?");
	ps.setString(1,e.getName());
	ps.setString(2,e.getPassword());
	ps.setString(3,e.getEmail());
	ps.setString(4,e.getCountry());
	ps.setInt(5,e.getId());
	
	status=ps.executeUpdate();
	con.close();
	
}
catch(Exception ex)
{
	ex.printStackTrace();
}
return status;

}
public static int delete(int id) {
	int status=0;
	try {
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from emp where emp_id=?");
		ps.setInt(1,id);
		status=ps.executeUpdate();
		
		con.close();
	}
  catch(Exception exxx)
	{
		exxx.printStackTrace();
	}
	return status;
	
}
}