package com.library.repository;

import com.library.model.documents.*;
import com.library.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    /**
     * #1.
     * @param doc Document
     */
    public static void addDocument(Document doc) {
        String sql = "INSERT INTO Documents(type, title, author, quantity," +
                " genre, issueNumber, topic) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, doc.getType());
            pstmt.setString(2, doc.getTitle());
            pstmt.setString(3, doc.getAuthor());
            pstmt.setInt(4, doc.getQuantity());
            if (doc.getType().equals("Book")) {
                pstmt.setString(5, ((Book) doc).getGenre());
                pstmt.setInt(6, 0);
                pstmt.setString(7, "NULL");
            } else if (doc.getType().equals("Magazine")) {
                pstmt.setString(5, "NULL");
                pstmt.setInt(6, ((Magazine) doc).getIssueNumber());
                pstmt.setString(7, "NULL");
            } else {
                pstmt.setString(5, "NULL");
                pstmt.setInt(6, 0);
                pstmt.setString(7, ((Thesis) doc).getTopic());
            }
            pstmt.executeUpdate();
            System.out.println("Document added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #2.
     * @param docId ID
     */
    public static void removeDocument(int docId) {
        String sql = "DELETE FROM Documents WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, docId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Document removed successfully!");
            } else {
                System.out.println("Document not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #3.
     * @param docId ID
     * @param type Type
     * @param title Title
     * @param author Author
     * @param quantity Quantity
     * @param genre Genre
     * @param issueNumber IssueNumber
     * @param topic Topic
     */
    public static void updateDocument(int docId, String type, String title, String author, int quantity,
                                      String genre, int issueNumber, String topic) {
        String sql = "UPDATE Documents SET type = ?, title = ?, author = ?, quantity = ?," +
                " genre = ?, issueNumber = ?, topic = ? WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, docId);
            pstmt.setString(2, type);
            pstmt.setString(3, title);
            pstmt.setString(4, author);
            pstmt.setInt(5, quantity);
            if (type.equals("Book")) {
                pstmt.setString(6, genre);
                pstmt.setInt(7, 0);
                pstmt.setString(8, "NULL");
            } else if (type.equals("Magazine")) {
                pstmt.setString(6, "NULL");
                pstmt.setInt(7, issueNumber);
                pstmt.setString(8, "NULL");
            } else {
                pstmt.setString(6, "NULL");
                pstmt.setInt(7, 0);
                pstmt.setString(8, topic);
            }
            pstmt.executeUpdate();
            System.out.println("Document updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #4.
     * @param keyword word to find out
     * @return List
     */
    public static List<Document> findDocument(String keyword) {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE title LIKE ? OR author LIKE ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                String genre = rs.getString("genre");
                int issueNumber = rs.getInt("issueNumber");
                String topic = rs.getString("topic");
                Document doc = null;
                if (type.equals("Book")) {
                    doc = new Book(type, title, author, quantity, genre);
                } else if (type.equals("Magazine")) {
                    doc = new Magazine(type, title, author, quantity, issueNumber);
                } else {
                    doc = new Thesis(type, title, author, quantity, topic);
                }
                results.add(doc);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    /**
     * #5.
     */
    public static List<Document> getDocuments() {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                String genre = rs.getString("genre");
                int issueNumber = rs.getInt("issueNumber");
                String topic = rs.getString("topic");
                Document doc = null;
                if (type.equals("Book")) {
                    doc = new Book(type, title, author, quantity, genre);
                } else if (type.equals("Magazine")) {
                    doc = new Magazine(type, title, author, quantity, issueNumber);
                } else {
                    doc = new Thesis(type, title, author, quantity, topic);
                }
                results.add(doc);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
