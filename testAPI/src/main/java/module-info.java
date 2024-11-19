module com.testapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.testapi to javafx.fxml;
    exports com.testapi;
}