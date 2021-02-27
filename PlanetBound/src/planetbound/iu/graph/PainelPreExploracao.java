/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.graph;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static planetbound.iu.graph.IConstantes_GUI.BCKGND_MENUJOGO;
import planetbound.logica.estados.MaqJogoObservavel;

/**
 *
 * @author Rui
 */
public class PainelPreExploracao extends BorderPane implements IConstantes_GUI, PropertyChangeListener{
    private MaqJogoObservavel maq;
    
    private Text tTitulo;
    private Text tLog;
    private Text stInfoDrone;
    private Text stInfoPlaneta;
    private TextArea taLog;
    
    private Button btGuardarRec;
    private Button btExplorar;
    private Button btVoltar;
    
    private VBox boxTexto;
    private VBox boxBt;
    private VBox boxLog;
    private VBox box;
    
    public PainelPreExploracao(MaqJogoObservavel maq) {
        this.maq = maq;
        this.maq.addPropertyChangeListener("ATUALIZA_INFOPRE_EXP", this);
        
        //Criar objetos
        createObjects();
        //Colocar no cenário
        setOnView();
        //Definir as ações / listeners
        setListener();
    }
    
    private void createObjects() {
        criaTitulo();
        criaLog();
        criaBotoes();
        
        box = new VBox(boxLog, boxBt);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(SPACING_BT_V);
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BCKGND_MENUJOGO),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void setOnView() {
        setTop(boxTexto);
        setCenter(box);
    }

    private void setListener(){
        btExplorar.setOnAction((ActionEvent t) -> {
            maq.fazExploracao();
        });
        
        btGuardarRec.setOnAction((ActionEvent t) -> {
            maq.addRecursos();
        });
        
        btVoltar.setOnAction((ActionEvent t) -> {
            maq.voltaAoMenuAnterior();
        });
    }
    
    private void criaTitulo() {
        tTitulo = new Titulo("No planeta", Color.RED);
        stInfoDrone = new Subtitulo("");
        stInfoPlaneta = new Subtitulo("");
        
        boxTexto = new VBox(tTitulo, stInfoDrone, stInfoPlaneta);
        boxTexto.setAlignment(Pos.CENTER);
        boxTexto.setPadding(new Insets(30));
    }
    
    private void criaLog(){
        tLog = new Subtitulo("Acontecimentos do jogo", Color.RED);
        taLog = new MessageLog();
        
        boxLog = new VBox(tLog, taLog);
        boxLog.setAlignment(Pos.CENTER);
    }
    
    private void criaBotoes() {
        btExplorar = new BotoesJogo("Enviar\no drone!");
        btGuardarRec = new BotoesJogo("Guardar\nRecursos");
        btVoltar = new BotoesJogo("Voltar");
        
        boxBt = new VBox(btExplorar, btGuardarRec, btVoltar);
        boxBt.setAlignment(Pos.CENTER);
        boxBt.setSpacing(SPACING_BT_V);
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        infoTxt();
        taLog.setText(maq.mostraLog());
    }
    
    private void infoTxt(){
        if(maq.droneComRecursos()){
            stInfoDrone.setText("ATENÇÃO: Existem recursos para serem adicionados à carga!");
            btExplorar.setDisable(true);
            btVoltar.setDisable(true);
            btGuardarRec.setDisable(false);
        }
        else{
            stInfoDrone.setText("");
            btExplorar.setDisable(false);
            btVoltar.setDisable(false);
            btGuardarRec.setDisable(true);
        }
    }
}
