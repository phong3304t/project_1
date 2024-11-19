package com.repository;

import com.model.documents.*;
import com.model.exceptions.DocumentNotFoundException;
import com.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * Add Document.
     *
     * @param doc Document
     */
    public static void addDocument(Document doc) {
        String sql = "INSERT INTO "
                + "Documents(ISBN, type, title, author, quantity,"
                + " publisher, categories, description, pageNumber, batch)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, doc.getISBN());
            pstmt.setString(2, doc.getType());
            pstmt.setString(3, doc.getTitle());
            pstmt.setString(4, doc.getAuthor());
            pstmt.setInt(5, doc.getQuantity());
            if (doc.getType().equals("Book")) {
                pstmt.setString(6, ((Book) doc).getPublisher());
                pstmt.setString(7, ((Book) doc).getCategories());
                pstmt.setString(8, ((Book) doc).getDescription());
                pstmt.setInt(9, ((Book) doc).getPageNumber());
                pstmt.setNull(10, java.sql.Types.INTEGER);
            } else {
                pstmt.setString(6, ((Photo) doc).getPublisher());
                pstmt.setString(7, ((Photo) doc).getCategories());
                pstmt.setString(8, ((Photo) doc).getDescription());
                pstmt.setInt(9, ((Photo) doc).getPageNumber());
                pstmt.setInt(10, ((Photo) doc).getBatch());
            }
            pstmt.executeUpdate();
            System.out.println("Document added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove Document by ISBN.
     *
     * @param ISBN doc ISBN
     * @param type doc type
     * @param batch photo batch
     */
    public static void removeDocument(String ISBN, String type, int batch) {
        String sql = type.equals("Book")
                ? "DELETE FROM Documents WHERE ISBN = ? AND type = ?"
                : "DELETE FROM Documents WHERE ISBN = ? AND type = ? AND batch = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, ISBN);
            pstmt.setString(2, type);

            if (!type.equals("Book")) {
                pstmt.setInt(3, batch);
            }

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
     * Update Document.
     *
     * @param doc Document
     */
    public static void updateDocument(Document doc) {
        String sql = "UPDATE Documents SET type = ?, title = ?,author = ?, "
                + "quantity = ?, publisher = ?, categories = ?, "
                + "description = ?, pageNumber = ?, batch = ? WHERE ISBN = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, doc.getType());
            pstmt.setString(2, doc.getTitle());
            pstmt.setString(3, doc.getAuthor());
            pstmt.setInt(4, doc.getQuantity());
            if (doc.getType().equals("Book")) {
                pstmt.setString(5, ((Book) doc).getPublisher());
                pstmt.setString(6, ((Book) doc).getCategories());
                pstmt.setString(7, ((Book) doc).getDescription());
                pstmt.setInt(8, ((Book) doc).getPageNumber());
                pstmt.setNull(9, java.sql.Types.INTEGER);
            } else {
                pstmt.setString(5, ((Photo) doc).getPublisher());
                pstmt.setString(6, ((Photo) doc).getCategories());
                pstmt.setString(7, ((Photo) doc).getDescription());
                pstmt.setInt(8, ((Photo) doc).getPageNumber());
                pstmt.setInt(9, ((Photo) doc).getBatch());
            }
            pstmt.setString(10, doc.getISBN());
            pstmt.executeUpdate();
            System.out.println("Document updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Find document.
     *
     * @param keyword word to find out
     * @return List
     */
    public static List<Document> findDocument(String keyword)
            throws DocumentNotFoundException {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents " +
                "WHERE title LIKE ? OR ISBN LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String ISBN = rs.getString("ISBN");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                String publisher = rs.getString("publisher");
                String categories = rs.getString("categories");
                String description = rs.getString("description");
                int pageNumber = rs.getInt("pageNumber");
                Document doc = switch (type) {
                    case "Book" -> new Book(ISBN, title, author, quantity,
                            publisher, categories, description, pageNumber);
                    case "Photo" -> {
                        int batch = rs.getInt("batch");
                        yield new Photo(ISBN, title, author, quantity,
                                publisher, categories, description, pageNumber, batch);
                    }
                    default -> null;
                };
                if (doc != null) {
                    results.add(doc);
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (results.isEmpty()) {
            throw new DocumentNotFoundException("No documents found "
                    + "for keyword: " + keyword);
        }
        return results;
    }

    /**
     * View all document.
     */
    public static List<Document> viewAllDocuments() {
        List<Document> results = new ArrayList<>();
        String sql = "SELECT * FROM documents";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String ISBN = rs.getString("ISBN");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                String publisher = rs.getString("publisher");
                String categories = rs.getString("categories");
                String description = rs.getString("description");
                int pageNumber = rs.getInt("pageNumber");
                Document doc = switch (type) {
                    case "Book" -> new Book(ISBN, title, author, quantity,
                            publisher, categories, description, pageNumber);
                    case "Photo" -> {
                        int batch = rs.getInt("batch");
                        yield new Photo(ISBN, title, author, quantity,
                                publisher, categories, description, pageNumber, batch);
                    }
                    default -> null;
                };
                if (doc != null) {
                    results.add(doc);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
