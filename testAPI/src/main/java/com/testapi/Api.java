package com.testapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Api {
    @FXML
    private TextField searchField;
    @FXML
    private TextArea resultArea;
    @FXML
    private ListView<String> suggestionsList;

    private final ObservableList<String> suggestions = FXCollections.observableArrayList();

    public void initialize() {
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSearch();
            } else {
                handleSuggestions();
            }
        });

        suggestionsList.setOnMouseClicked(event -> {
            String selected = suggestionsList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                searchField.setText(selected);
                handleSearch();
            }
        });
    }

    public void handleSuggestions() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            suggestionsList.setVisible(false);
            return;
        }

        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);

        Task<Void> suggestionTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                try (CloseableHttpClient client = HttpClients.createDefault()) {
                    HttpGet request = new HttpGet(apiUrl);
                    CloseableHttpResponse response = client.execute(request);

                    if (response.getStatusLine().getStatusCode() == 200) {
                        String jsonResponse = EntityUtils.toString(response.getEntity());
                        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                        if (jsonObject.has("items")) {
                            JsonArray items = jsonObject.getAsJsonArray("items");
                            suggestions.clear();  // Làm sạch danh sách gợi ý trước khi thêm mới

                            for (int i = 0; i < Math.min(3, items.size()); i++) {
                                JsonObject bookInfo = items.get(i).getAsJsonObject().getAsJsonObject("volumeInfo");
                                String title = bookInfo.get("title").getAsString();
                                suggestions.add(title);  // Thêm tiêu đề vào danh sách gợi ý
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        suggestionTask.setOnSucceeded(event -> {
            // Đảm bảo cập nhật UI trên FX Application Thread
            Platform.runLater(() -> {
                suggestionsList.setItems(suggestions);  // Cập nhật ListView
                suggestionsList.setVisible(!suggestions.isEmpty());  // Hiển thị hoặc ẩn danh sách
            });
        });

        suggestionTask.setOnFailed(event -> {
            // Cập nhật UI nếu có lỗi trong việc thực hiện API
            Platform.runLater(() -> {
                suggestionsList.setVisible(false);  // Ẩn danh sách nếu có lỗi
            });
        });

        // Chạy task trong một thread riêng biệt
        new Thread(suggestionTask).start();
    }



    private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            Platform.runLater(() -> resultArea.setText("Please enter ISBN or Title to search."));
            return;
        }

        String apiUrl;
        if (query.matches("\\d+")) {
            apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + query;
        } else {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            apiUrl = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + encodedQuery;
        }

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            CloseableHttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                if (jsonObject.has("items") && jsonObject.getAsJsonArray("items").size() > 0) {
                    JsonObject bookInfo = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject().getAsJsonObject("volumeInfo");

                    String isbn = query.matches("\\d+") ? query : "Unavailable";
                    String type = "Book"; // Default type
                    String title = bookInfo.get("title").getAsString();
                    String author = bookInfo.has("authors") ? bookInfo.get("authors").toString() : "No authors available";
                    String publisher = bookInfo.has("publisher") ? bookInfo.get("publisher").getAsString() : "No publisher available";
                    String categories = bookInfo.has("categories") ? bookInfo.get("categories").toString() : "No categories available";
                    String description = bookInfo.has("description") ? bookInfo.get("description").getAsString() : "No description available";
                    String pageNumber = bookInfo.has("pageCount") ? bookInfo.get("pageCount").getAsString() : "Unknown";
                    String quantity = "1"; // Placeholder for DB integration

                    String resultText = "ISBN: " + isbn +
                            "\nType: " + type +
                            "\nTitle: " + title +
                            "\nAuthor: " + author +
                            "\nPublisher: " + publisher +
                            "\nCategories: " + categories +
                            "\nDescription: " + description +
                            "\nPage Number: " + pageNumber +
                            "\nQuantity: " + quantity;

                    Platform.runLater(() -> resultArea.setText(resultText));
                } else {
                    Platform.runLater(() -> resultArea.setText("No books found for the given search criteria."));
                }
            } else {
                Platform.runLater(() -> resultArea.setText("Error: Unable to fetch data."));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Platform.runLater(() -> resultArea.setText("Error: " + e.getMessage()));
        }
    }


    public void handleSearch() {
        Task<Void> searchTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                performSearch();
                return null;
            }
        };

        searchTask.setOnFailed(event -> resultArea.setText("Search failed"));

        new Thread(searchTask).start();
    }
}
