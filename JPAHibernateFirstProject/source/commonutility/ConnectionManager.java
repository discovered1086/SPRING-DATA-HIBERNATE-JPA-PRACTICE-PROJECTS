/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package commonutility;

import java.sql.*;
//import sun.security.util.Debug;


/**
 * @author kingshuk
 */
public class ConnectionManager implements BaseConnection {
    public ConnectionManager() {

    }

    public Connection getMyConnection() throws ClassNotFoundException, SQLException {
        Connection mmcon = null;
        try {
            Class.forName(connectionDriver);
            mmcon = DriverManager.getConnection(url + dbname, username, password);
            //Debug.println("<connection>", "connection created");
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("the drivermanager class cannot be found");
        } catch (SQLException sqe) {
            throw new SQLException("error while acquiring the connection");
        }

        return mmcon;                                         //return the connection object.

    }


    public void closeResultset(ResultSet rs) {
        try {
            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException sql) {
            throw new RuntimeException("Result set could not be closed");
        }
    }

    public void closeConnection(Connection con) {
        try {
            if (!con.isClosed()) {
                con.close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException("error occurred while closing the connection");
        }
    }

    public void closeStatement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void closePreparedStatement(PreparedStatement ps){
        try {
            if (!ps.isClosed()) {
                ps.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("error occurred while closing the connection");
        }
    }

    public void closeCallableStatement() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
