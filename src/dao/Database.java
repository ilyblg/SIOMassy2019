package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repr�sente la base de donn�es. Fournit une connexion � cette base (via
 * <code>getConnection()</code>.
 *
 * ATTENTION : ajouter dans cette classe tous les codes erreur pr�vus par les
 * d�clencheurs �crits par nous dans MySQL (apr�s FOREIGN_KEY_NOT_FOUND).
 *
 * @author plasse
 */
public class Database {

    /**
     * Code erreur MySQL quand le serveur est inaccessible
     */
    public static final int SERVER_OFF = 0;

    /**
     * Code erreur MySQL pour "duplicate entry" (doublons)
     */
    public static final int DOUBLON = 1062;

    /**
     * Code erreur MySQL pour "Cannot delete or update a parent row: a foreign
     * key constraint fails "
     */
    public static final int ROW_IS_REFERENCED = 1451;

    /**
     * Code erreur MySQL pour "Cannot add or update a child row: a foreign key
     * constraint fails"
     */
    public static final int FOREIGN_KEY_NOT_FOUND = 1452;

    /**
     * Pilote MySQL (biblioth�que contenant les impl�mentations de jdbc)
     */
    protected static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    protected static final String DB_NAME = "agriotes2019";
    protected static final String USER = "root";
    protected static final String PASSWORD = "philaemafillejetaime";

    /**
     * Chaine de connexion (adresse TCP/IP de la base de donn�es
     */
    protected static String URL = "jdbc:mysql://localhost/" + DB_NAME;
    // La chaine de connexion diff�re d'un SGBD � l'autre.
    // Pour Oracle : "jdbc:oracle:oci8:@localhost:1521:XE/" + DB_NAME
    // Pour Derby (BD en m�moire en Java) : "jdbc:derby://localhost:1527/" + DB_NAME
    // Pour MySQL : "jdbc:mysql://localhost/" + DB_NAME;

    // Bloc d'initialisation pour les variables static, ne s'ex�cute qu'une fois
    static {
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.printf("*** Driver %s loaded.\n", DRIVER_NAME);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
            // Depuis java 1.7, on peut rassembler ainsi les exceptions
            exc.printStackTrace();
            throw new RuntimeException("Pilote pas charg�");
        }
    }

    public enum SortOrder {
        ASC, DESC;
    }

    /**
     * Fournit une connexion � la base de donn�es. Ne fait pas appel � un pool
     * de connexion, m�me si cela est envisageable plus tard (ne changerait rien
     * � l'appel de la m�thode)
     * <br><strong>Requiert</strong> que le pilote soir charg�
     *
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Réinitialise la base à la date passée en paramètre. Si ce paramètre vaut
     * null, prend l'instant courant.
     */
    public static void reset(LocalDateTime date) throws SQLException {
        Connection con = Database.getConnection();
        CallableStatement stmt = con.prepareCall("CALL agriotes2018_reset(?)");
        Timestamp ts = null;
        if (date != null) {
            ts = Timestamp.valueOf(date);
        }
        stmt.setTimestamp(1, ts);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    /** Recupère sous forme de liste de tableaux associatifs (HashMap) un ResultSet
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List<HashMap<String, Object>> getAsList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
        while (rs.next()) {
           HashMap<String, Object> row = new HashMap<String, Object>(columns);
           for (int i = 1; i <= columns; ++i) {
              row.put(md.getColumnName(i), rs.getObject(i));
           }
           result.add(row);
        }
        return result;
     }


}
