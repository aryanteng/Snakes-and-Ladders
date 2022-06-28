module com.example.snakesandladders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
//    requires javafx.media;



    opens com.example.snakesandladders to javafx.fxml;
    exports com.example.snakesandladders;
}