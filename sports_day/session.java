package sports_day;

import java.sql.* ;

/**
 * @author Alex Lockwood
 * @purpose Used to access the MySQL database
 */

public class session
{
    //class variables
    //JDBC driver name and database URL
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/lockwood_sportsday";

    //credentials
    final String USER = "root";
    final String PASS = "4$spdV&gp&kSMfQ";
    Connection conn = null;
    boolean bConnected;

    //constructor
    public session()
    {
        reset();
        return;
    }

    //setters
    private void reset()
    {
        this.bConnected = false;
        return;
    }

    public void connect()
    {
        try
        {
            System.err.println( this.getClass().getName() + ":: Connecting to database...");
            DriverManager.getDriver( DB_URL ) ;
            conn = DriverManager.getConnection( DB_URL , USER , PASS ) ;
            if (conn != null)
            {
                this.setIfConnected(true);
            }
            System.err.println(this.getClass().getName() + ":: successful connection to DB.");
        }
        catch(SQLException se)
        {
            System.err.println(this.getClass().getName() + ":: SQL error:: " + se);
            se.printStackTrace();
        }
        catch(Exception e)
        {
            System.err.println(this.getClass().getName() + ":: Error:: " + e);
            e.printStackTrace();
        }
        finally
        {
        }
        return ;
    }

    public void disconnect()
    {
        try
        {
            this.conn.close();
            this.setIfConnected(false);
            System.err.println(this.getClass().getName() + ":: disconnected.");
        }
        catch (SQLException se)
        {
            System.err.println(this.getClass().getName() + ":: SQL error " + se);
            se.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println(this.getClass().getName() + ":: error " + e);
            e.printStackTrace();
        }
        finally
        {
        }
        return ;
    }

    public void setIfConnected(boolean b) { this.bConnected = b; }

    //getters
    public boolean isConnected() { return this.bConnected; }

    public Connection getConnection() { return this.conn; }


}

