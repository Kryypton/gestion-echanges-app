

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;


public class ihm extends Application {

  public void start(Stage stage) {
    VBox root = new VBox();
    Label msg = new Label("Hello JavaFX");
    root.getChildren().add(msg);

    Scene scene = new Scene(root, 500, 500);
    stage.setScene(scene);
    stage.initStyle(StageStyle.DECORATED); // 3.1
    //stage.initOwner(stage);
    stage.setTitle("Hello JavaFX");
    stage.show();
    stage.setOpacity(1);

    Stage stage2 = new Stage(); //3.2
    VBox root2 = new VBox();
    Scene scene2 = new Scene(root2, 200, 200);
    stage.setX(100); //3.3
    stage2.setScene(scene2);
    stage2.initOwner(stage);
    stage2.initModality(Modality.WINDOW_MODAL);
    stage2.show();

    FlowPane flow = new FlowPane();
    Stage stage3 = new Stage();

    flow.setVgap(8);
    flow.setHgap(4);
    flow.setPrefWrapLength(300); // preferred width = 300
    Button button1 = new Button("Button 1");
    Button button2 = new Button("Button 2");
    Button button3 = new Button("Button 3");
    Button button4 = new Button("Button 4");
    Button button5 = new Button("Button 5");
    Button button6 = new Button("Button 6");
    Button button7 = new Button("Button 7");
    Button button8 = new Button("Button 8");
    Button button9 = new Button("Button 9");
    Button button10 = new Button("Button 10");

    ObservableList<Node> list = flow.getChildren();
    list.addAll(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10);
    Scene scene3 = new Scene(flow, 200,200);
    stage3.setScene(scene3);
    stage3.show();

  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}