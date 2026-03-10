package webcaisse.dao;

import webcaisse.models.Conso;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GrcDAO {

    private static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webcaisse",
                "root",
                ""
        );
    }

    public static ArrayList<Conso> listeConsoAFideliser(int seuil, LocalDate dateDeb, LocalDate dateFin) {

        ArrayList<Conso> lesConsos = new ArrayList<>();

        String requete =
                "SELECT c.nom, c.prenom, c.mail, c.tel, COUNT(v.id) AS nbVentes " +
                        "FROM Conso c " +
                        "JOIN Vente v ON c.id = v.idConso " +
                        "WHERE c.id NOT IN (SELECT id FROM ConsoFidele) " +
                        "AND v.dateVente BETWEEN ? AND ? " +
                        "GROUP BY c.id, c.nom, c.prenom, c.mail, c.tel " +
                        "HAVING COUNT(v.id) >= ?";

        try (
                Connection conn = getConnexion();
                PreparedStatement pstmt = conn.prepareStatement(requete)
        ) {

            pstmt.setDate(1, Date.valueOf(dateDeb));
            pstmt.setDate(2, Date.valueOf(dateFin));
            pstmt.setInt(3, seuil);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Conso c = new Conso(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("mail"),
                        rs.getString("tel"),
                        rs.getInt("nbVentes")
                );

                lesConsos.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesConsos;
    }
}