/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package commonutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author kingshuk
 */
public interface BaseConnection
{
    public static final String connectionDriver="oracle.jdbc.driver.OracleDriver";
    public static final String url="jdbc:oracle:thin:@198.162.0.11:1521:";
    public static final String dbname="mydatabase";
    public static final String username="spring_hibernate";
    public static final String password="Iofdtiger#16";

    //list of methods
    public  Connection getMyConnection() throws Exception;
    public void closeConnection(Connection con);
    public void closeStatement();
    public void closePreparedStatement(PreparedStatement ps);
    public void closeCallableStatement();
    public void closeResultset(ResultSet rs);
}
