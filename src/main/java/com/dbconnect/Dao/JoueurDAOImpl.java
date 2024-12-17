package com.dbconnect.Dao;

import com.dbconnect.database.SingletonConnexionDB;
import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;

import java.sql.*;
import java.util.List;

public class JoueurDAOImpl implements JoueurDAO {

    @Override
    public List<Joueur> findAll() {
        return null;
    }

    // 1. Trouver un joueur par son ID
    @Override
    public Joueur findById(Long id) {
        String query = "SELECT * FROM joueur WHERE id = ?";
        try (Connection conn = SingletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("id"));
                joueur.setNom(rs.getString("nom"));
                joueur.setPosition(rs.getString("position"));
                joueur.setNumero(rs.getInt("numero"));
                // Récupérer l'équipe associée
                long equipeId = rs.getLong("equipe_id");
                Equipe equipe = new EquipeDAOImpl().findById(equipeId); // Trouver l'équipe associée
                joueur.setEquipe(equipe);
                return joueur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2. Ajouter un joueur dans la base de données
    @Override
    public void save(Joueur joueur) {
        String query = "INSERT INTO joueur (nom, position, numero, equipe_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = SingletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, joueur.getNom());
            stmt.setString(2, joueur.getPosition());
            stmt.setInt(3, joueur.getNumero());
            stmt.setLong(4, joueur.getEquipe().getId()); // Associer le joueur à une équipe
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Supprimer un joueur par son ID
    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM joueur WHERE id = ?";
        try (Connection conn = SingletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Mettre à jour les informations d'un joueur
    @Override
    public void update(Joueur joueur) {
        String query = "UPDATE joueur SET nom = ?, position = ?, numero = ?, equipe_id = ? WHERE id = ?";
        try (Connection conn = SingletonConnexionDB.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, joueur.getNom());
            stmt.setString(2, joueur.getPosition());
            stmt.setInt(3, joueur.getNumero());
            stmt.setLong(4, joueur.getEquipe().getId()); // Mettre à jour l'équipe associée
            stmt.setLong(5, joueur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Joueur> findByEquipe(Equipe equipe) {
        return null;
    }
}

