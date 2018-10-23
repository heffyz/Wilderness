/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.managment;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.util.Callback;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author esprit
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pagination pagination;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TabPane tabpane;
  private double xOffset = 0;
    private double yOffset = 0;
    private static final int DEFAULT_STARTING_X_POSITION = 0;
    private static final int DEFAULT_ENDING_X_POSITION = -120;
    @FXML
    private AnchorPane parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             makeStageDrageable();
         pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
           pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
       
//        AnchorPane.setTopAnchor(pagination, 10.0);
//        AnchorPane.setRightAnchor(pagination, 10.0);
//        AnchorPane.setBottomAnchor(pagination, 10.0);
//        AnchorPane.setLeftAnchor(pagination, 10.0);
//        anchor.getChildren().addAll(pagination);
    }    
        public int itemsPerPage() {
        return 8;
    }
 
    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
         element.setStyle("-fx-background-color: white;");
         
            Hyperlink link = new Hyperlink("Item " + (i+1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor "+ link.getText());
            element.getChildren().addAll(link, text, new Separator());
            box.getChildren().add(element);
        }
        return box;
    }
  public void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventManagment.stage.setX(event.getScreenX() - xOffset);
               EventManagment.stage.setY(event.getScreenY() - yOffset);
                EventManagment.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
           EventManagment.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            EventManagment.stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void close_app(MouseEvent event) {
             System.exit(0);
    }

 
}
