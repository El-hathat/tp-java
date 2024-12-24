package com.dbconnect.Dao;

import com.dbconnect.database.SingletonConnexionDB;
import com.dbconnect.models.Equipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAOImpl implements EquipeDAO {



        Connection conn = SingletonConnexionDB.getConnexion();


    @Override
    public List<Equipe> findAll() {
        List<Equipe> equipes = new ArrayList<>();
        String query = "SELECT * FROM equipe";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getLong("id"));
                equipe.setNom(rs.getString("nom"));
                equipe.setVille(rs.getString("ville"));
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipes;
    }

    @Override
    public Equipe findById(Long id) {
        String query = "SELECT * FROM equipe WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getLong("id"));
                equipe.setNom(rs.getString("nom"));
                equipe.setVille(rs.getString("ville"));
                return equipe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Equipe equipe) {
        String query = "INSERT INTO equipe (nom, ville) VALUES (?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, equipe.getNom());
            stmt.setString(2, equipe.getVille());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     @Override
      public void deleteById(Long id) {
        String query = "DELETE FROM equipe WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Equipe equipe) {
        String query = "UPDATE equipe SET nom = ?, ville = ? WHERE id = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, equipe.getNom());
            stmt.setString(2, equipe.getVille());
            stmt.setLong(3, equipe.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Equipe> findByKeyword(String keyword) {
        List<Equipe> equipes = new ArrayList<>();
        String query = "SELECT * FROM equipe WHERE nom LIKE ? OR ville LIKE ?";

        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Utilisation de % pour rechercher des correspondances partielles
            String searchKeyword = "%" + keyword + "%";

            // Remplacement des paramètres dans la requête
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);

            // Exécution de la requête
            ResultSet rs = stmt.executeQuery();

            // Traitement des résultats
            while (rs.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(rs.getLong("id"));
                equipe.setNom(rs.getString("nom"));
                equipe.setVille(rs.getString("ville"));
                equipes.add(equipe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipes;
    }

}

