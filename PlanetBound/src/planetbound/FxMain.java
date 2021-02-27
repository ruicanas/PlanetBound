/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static planetbound.iu.graph.IConstantes_GUI.ICON;
import planetbound.iu.graph.Imagens;
import planetbound.iu.graph.PaneOrganizer;
import planetbound.logica.estados.MaqJogo;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */


public class FxMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MaqJogoObservavel maq = new MaqJogoObservavel(new MaqJogo());
        
        Scene scene = new Scene(new PaneOrganizer(maq).getRoot(), 1250, 700);
        
        primaryStage.getIcons().add(Imagens.getImagem(ICON));
        primaryStage.setTitle("Planet Bound");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
