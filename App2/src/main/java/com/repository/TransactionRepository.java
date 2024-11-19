package com.repository;

import com.util.DatabaseConnection;
import com.model.transactions.BorrowTransaction;
import com.model.transactions.ReturnTransaction;
import com.model.exceptions.InsufficientQuantityException;

import java.sql.*;

public class TransactionRepository {
    private static final Connection connection = DatabaseConnection.getConnection();

    /**
     * Borrow document.
     *
     * @param transaction transaction
     */
    public static void borrowDocument(BorrowTransaction transaction)
            throws InsufficientQuantityException {
        String sql = "INSERT INTO "
                + "Transactions(ISBN, docType, batch, userId, type, borrowDate, dueDate) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String checkDocumentSql = "SELECT quantity FROM Documents "
                + "WHERE ISBN = ? AND quantity > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity - 1 "
                + "WHERE ISBN = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments + 1 "
                + "WHERE id = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmtCheck = connection.prepareStatement(checkDocumentSql);
                 PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
                 PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmtCheck.setString(1, transaction.getISBN());
                ResultSet rs = pstmtCheck.executeQuery();

                if (rs.next()) {
                    pstmt.setString(1, transaction.getISBN());
                    pstmt.setString(2, transaction.getDocType());
                    pstmt.setInt(3, transaction.getBatch());
                    pstmt.setInt(4, transaction.getUserId());
                    pstmt.setString(5, transaction.getType());
                    pstmt.setDate(6, Date.valueOf(transaction.getBorrowDate()));
                    pstmt.setDate(7, Date.valueOf(transaction.getDueDate()));
                    pstmt.executeUpdate();

                    pstmtDocument.setString(1, transaction.getISBN());
                    pstmtDocument.executeUpdate();

                    pstmtUser.setInt(1, transaction.getUserId());
                    pstmtUser.executeUpdate();

                    connection.commit();
                    System.out.println("Document borrowed successfully.");
                } else {
                    throw new InsufficientQuantityException("Document is not "
                            + "available for borrowing.");
                }
                rs.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }

    /**
     * Return document.
     *
     * @param transaction transaction
     */
    public static void returnDocument(ReturnTransaction transaction) {
        String sql = "INSERT INTO "
                + "Transactions(ISBN, docType, batch, userId, "
                + "type, borrowDate, returnDate, fine) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        String checkUsersSql = "SELECT borrowedDocuments FROM Users "
                + "WHERE ISBN = ? AND borrowedDocuments > 0";
        String updateDocumentSql = "UPDATE Documents SET quantity = quantity + 1 "
                + "WHERE ISBN = ?";
        String updateUserSql = "UPDATE Users SET borrowedDocuments = borrowedDocuments - 1 "
                + "WHERE id = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmtCheck = connection.prepareStatement(checkUsersSql);
                 PreparedStatement pstmtDocument = connection.prepareStatement(updateDocumentSql);
                 PreparedStatement pstmtUser = connection.prepareStatement(updateUserSql);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmtCheck.setInt(1, transaction.getUserId());
                ResultSet rs = pstmtCheck.executeQuery();

                if (rs.next()) {
                    pstmt.setString(1, transaction.getISBN());
                    pstmt.setString(2, transaction.getDocType());
                    pstmt.setInt(3, transaction.getBatch());
                    pstmt.setInt(4, transaction.getUserId());
                    pstmt.setString(5, transaction.getType());
                    pstmt.setDate(6, Date.valueOf(transaction.getBorrowDate()));
                    pstmt.setDate(7, Date.valueOf(transaction.getReturnDate()));
                    pstmt.setDouble(8, transaction.getFine());
                    pstmt.executeUpdate();

                    pstmtDocument.setString(1, transaction.getISBN());
                    pstmtDocument.executeUpdate();

                    pstmtUser.setInt(1, transaction.getUserId());
                    pstmtUser.executeUpdate();

                    connection.commit();
                    System.out.println("Document returned successfully.");
                } else {
                    System.out.println("User has no borrowed documents to return.");
                }
                rs.close();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }
}
