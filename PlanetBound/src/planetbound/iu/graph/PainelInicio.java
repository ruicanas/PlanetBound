/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUINICIAL;
import planetbound.logica.estados.MaqJogoObservavel;


/**
 *
 * @author Rui
 */
public class PainelInicio extends BorderPane implements IConstantes_GUI{
    private MaqJogoObservavel maq;
    
    private Text titulo;
    private VBox boxTitulo;
    private VBox boxBt;
    private VBox box;
    private Button btNovoJogo;
    private Button btCarregarJogo;
    private Button btSaiJogo;
    
    
    public PainelInicio(MaqJogoObservavel maq){
        this.maq = maq;
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
        
        setPrefSize(SIZEPANEL_X, SIZEPANEL_Y);
    }

    private void createObjects() {
        //Título e respetiva caixa
        titulo = new Titulo("PLANET BOUND");
        boxTitulo = new VBox(titulo);
        boxTitulo.setAlignment(Pos.CENTER);
        
        //Botões e respetiva caixa
        btNovoJogo = new BotoesJogo("Iniciar Novo Jogo");
        btCarregarJogo = new BotoesJogo("Carregar Jogo");
        btSaiJogo = new BotoesJogo("Sair do Jogo");
        
        boxBt = new VBox();
        boxBt.getChildren().addAll(btNovoJogo, btCarregarJogo, btSaiJogo);
        boxBt.setSpacing(SPACING_BT_V);                               //Aumentar o espaço entres os botões
        boxBt.setAlignment(Pos.CENTER);
        
        //Caixa que junta todos os complementos
        box = new VBox(boxTitulo, boxBt);
        box.setAlignment(Pos.CENTER);                       //Posiciona os botões no centro
        box.setSpacing(ESPACO_TITULO_BT);
        
        //Background deste painel        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUINICIAL),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setCenter(box);
    }

    private void setListener() {
        btNovoJogo.setOnAction((ActionEvent t) -> {
            maq.comecaJogo();
        });
        
        btCarregarJogo.setOnAction((ActionEvent t) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extF = new FileChooser.ExtensionFilter("OBJ (*.obj)", "*.obj");
            fileChooser.getExtensionFilters().add(extF);
            File file = fileChooser.showOpenDialog(new Stage());
            if(file != null){
                maq.load(file);
            }
        });
        
        btSaiJogo.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
    }
}
