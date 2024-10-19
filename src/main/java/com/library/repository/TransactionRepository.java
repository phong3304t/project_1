package com.library.repository;

import com.library.util.*;

import java.sql.*;
import java.time.LocalDate;

public class TransactionRepository {
    /**
     * #7.
     * @param docId Document ID
     * @param userId User ID
     */
    public static void borrowDocument(int docId, int userId) {
        String sql = "INSERT INTO Transactions(documentId, userId, type, date, dueDate," +
                " VALUES(?, ?, ?, ?, ?)";
        String checkDocumentSql = "SELECT quantity FROM Documents WHERE id = ? AND quantity > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity - 1 WHERE id = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments + 1 WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmtCheckDocument = connection.prepareStatement(checkDocumentSql);
             PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
             PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmtCheckDocument.setInt(1, docId);
            ResultSet rs = pstmtCheckDocument.executeQuery();

            if (rs.next()) {
                pstmt.setInt(1, docId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, "Borrow");
                pstmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                pstmt.setDate(5, java.sql.Date.valueOf(LocalDate.now().plusMonths(1)));
                pstmt.executeUpdate();

                pstmtDocument.setInt(1, docId);
                pstmtDocument.executeUpdate();

                pstmtUser.setInt(1, userId);
                int rowsAffectedUser = pstmtUser.executeUpdate();

                if (rowsAffectedUser > 0 ) {
                    System.out.println("Document borrowed successfully.");
                } else {
                    System.out.println("Failed to borrow document.");
                }
            } else {
                System.out.println("Document is not available for borrowing.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * #8.
     * @param docId Document ID
     * @param userId User ID
     */
    public static void returnDocument(int docId, int userId, boolean isLate, double fine) {
        String sql = "INSERT INTO Transactions(documentId, userId, type, date, isLate, fine)" +
                " VALUES(?, ?, ?, ?, ?, ?)";
        String checkUsersSql = "SELECT borrowedDocuments FROM Users WHERE id = ? AND borrowedDocuments > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity + 1 WHERE id = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments - 1 WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmtCheck = connection.prepareStatement(checkUsersSql);
             PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
             PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmtCheck.setInt(1, userId);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                pstmt.setInt(1, docId);
                pstmt.setInt(2, userId);
                pstmt.setString(3, "Return");
                pstmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                pstmt.setBoolean(5, isLate);
                pstmt.setDouble(6, fine);
                pstmt.executeUpdate();

                pstmtDocument.setInt(1, docId);
                pstmtDocument.executeUpdate();

                pstmtUser.setInt(1, userId);
                int rowsAffectedUser = pstmtUser.executeUpdate();

                if (rowsAffectedUser > 0) {
                    System.out.println("Document returned successfully.");
                } else {
                    System.out.println("Failed to return document.");
                }
            } else {
                System.out.println("User has no borrowed documents to return.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
