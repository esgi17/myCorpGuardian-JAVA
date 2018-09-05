package Controllers;

import Models.Device;
import Models.Wall;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class SampleController {
    @FXML VBox drawBox;
    @FXML Canvas canvas;
    @FXML Label labelX1;
    @FXML Label labelY1;
    @FXML Label labelX2;
    @FXML Label labelY2;
    @FXML Label nameWall;
    @FXML Label nameRoom;
    @FXML TextField nameRoomField;
    @FXML TextField nameWallField;
    @FXML javafx.scene.control.ListView wallList;
    @FXML javafx.scene.control.ListView deviceList;

    private ObservableList<Wall> walls = FXCollections.observableArrayList();
    private ObservableList<String> wallsName = FXCollections.observableArrayList();

    private ObservableList<Device> devices = FXCollections.observableArrayList();
    private ObservableList<String> devicesName = FXCollections.observableArrayList();


    public void initialize () {
        Device device = new Device();
        device.Device("Cam1","-10","-10");
        Device device2 = new Device();
        device2.Device("capt1","-10","-10");
        Device device3 = new Device();
        device3.Device("Cam2","-10","-10");
        devices.addAll(device,device2,device3);
        devicesName.addAll("Cam1","capt1","Cam2");
        deviceList.setItems(devicesName);
        drawWalls("");
        fillWallList();
        fillDeviceList();
    }

    public void fillWallList () {

    }

    public void fillDeviceList () {

    }

    public void draw (int shape) {
        setLabelPos(0);
        labelX1.setText("");
        labelY1.setText("");
        labelX2.setText("");
        labelY2.setText("");
        if(shape ==0 && verifyName(nameWall,nameWallField) || shape == 1 && verifyName(nameRoom,nameRoomField)) {
            drawBox.setOnMouseClicked( new EventHandler<MouseEvent>() {
                Long beginX;
                Long beginY;
                Long endX;
                Long endY;
                int i = 0;

                @Override
                public void handle(MouseEvent event) {
                    i++;
                    if (i == 1) {
                        setLabelPos( 1 );
                        labelX1.setText( "X1 : " + Double.toString( Math.round( event.getX()-100 ) ) );
                        labelY1.setText( "Y1 : " + Double.toString( Math.round( event.getY()-100 ) ) );
                        beginX = Math.round( event.getX()-100 ) / 5 * 5;
                        beginY = Math.round( event.getY()-100 ) / 5 * 5;
                    } else if (i == 2) {
                        drawBox.setOnMouseMoved( null );
                        labelX2.setText( "X2 : " + Double.toString( Math.round( event.getX()-100 ) ) );
                        labelY2.setText( "Y2 : " + Double.toString( Math.round( event.getY()-100 ) ) );
                        endX = Math.round( event.getX()-100 ) / 5 * 5;
                        endY = Math.round( event.getY()-100 ) / 5 * 5;
                        GraphicsContext gc = canvas.getGraphicsContext2D();
                        if (shape == 0) {
                            gc.strokeLine( beginX, beginY, endX, endY );
                            Wall wall = new Wall();
                            wall.Wall(nameWallField.getText(), Long.toString( beginX /5*5), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall );
                            wallsName.add(wall.getName());
                            nameWallField.setText("");

                        } else if (shape == 1) {
                            gc.strokeRect( beginX, beginY, endX - beginX, endY - beginY );
                            Wall wall1 = new Wall();
                            wall1.Wall(nameRoomField.getText() + "O", Long.toString( beginX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( beginX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall1 );
                            wallsName.add(wall1.getName());

                            Wall wall2 = new Wall();
                            wall2.Wall(nameRoomField.getText() + "N", Long.toString( beginX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( beginY/5*5 ) );
                            walls.add( wall2 );
                            wallsName.add(wall2.getName());

                            Wall wall3 = new Wall();
                            wall3.Wall(nameRoomField.getText() + "E", Long.toString( endX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall3 );
                            wallsName.add(wall3.getName());

                            Wall wall4 = new Wall();
                            wall4.Wall(nameRoomField.getText() + "S", Long.toString( endX/5*5 ), Long.toString( endY/5*5 ), Long.toString( beginX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall4 );
                            wallsName.add(wall4.getName());
                            nameRoomField.setText("");
                        }
                        wallList.setItems( wallsName );
                        drawBox.setOnMouseClicked( null );
                    } else {
                        drawBox.setOnMouseClicked( null );
                    }
                }
            } );
        }
    }

    public void setLabelPos (int xy) {
        drawBox.setOnMouseMoved( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(xy ==0) {
                    labelX1.setText( "X1 : " + Double.toString( Math.round(event.getX()-100)/5*5));
                    labelY1.setText( "Y1 : " + Double.toString( Math.round(event.getY()-100)/5*5));
                }
                else{
                    labelX2.setText("X2 : " + Double.toString(Math.round(event.getX()-100)/5*5));
                    labelY2.setText("Y2 : " + Double.toString(Math.round(event.getY()-100)/5*5));
                }
            }
        } );
    }

    public void newRoom () {
        draw(1);
    }
    public void newWall () {
        draw(0);
    }

    public boolean verifyName (Label label, TextField tf) {
        boolean res = true;
        if(tf.getText().equalsIgnoreCase("")){
            res = false;
            label.setText("Empty Name");
        }
        else{
            for(int i=0 ; i< walls.size() ; i++ ){
                if(walls.get(i).getName().equalsIgnoreCase(tf.getText())
                        || walls.get(i).getName().equalsIgnoreCase(tf.getText()+"N")
                        || walls.get(i).getName().equalsIgnoreCase(tf.getText()+"S")
                        || walls.get(i).getName().equalsIgnoreCase(tf.getText()+"E")
                        || walls.get(i).getName().equalsIgnoreCase(tf.getText()+"O")){
                    res = false;
                    label.setText("Already exist");
                }
            }
        }
        if(res){
            label.setText("Name");
            return true;
        }
        else{
            return false;
        }
    }

    public void drawWalls (String name) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,600,600);
        drawDevices();
        for(int i = 0 ; i < walls.size() ; i++){
            if(walls.get(i).getName() != name) {
                gc.strokeLine(
                        Double.parseDouble( walls.get( i ).getX1() ),
                        Double.parseDouble( walls.get( i ).getY1() ),
                        Double.parseDouble( walls.get( i ).getX2() ),
                        Double.parseDouble( walls.get( i ).getY2() ) );
            }
        }
    }

    public void deleteWall () {
        int index = wallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            drawWalls(wallsName.get(index));
            wallsName.remove(index);
            walls.remove(index);
        }
    }

    public void surline () {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
        drawWalls( "");
        int index = wallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            gc.setLineWidth(5);
            gc.strokeLine(
                    Double.parseDouble( walls.get( index ).getX1() ),
                    Double.parseDouble( walls.get( index ).getY1() ),
                    Double.parseDouble( walls.get( index ).getX2() ),
                    Double.parseDouble( walls.get( index ).getY2() ) );
            gc.setLineWidth(1);
            labelX1.setText("X1 : " + walls.get( index ).getX1());
            labelY1.setText("Y1 : " + walls.get( index ).getY1());
            labelX2.setText("X2 : " + walls.get( index ).getX2());
            labelY2.setText("Y2 : " + walls.get( index ).getY2());
        }
    }

    public void moveDevice () {
        int index = deviceList.getSelectionModel().getSelectedIndex();
        setLabelPos(0);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(index != -1) {
            drawWalls("");
            gc.setLineWidth(5);
            gc.strokeRect(Double.parseDouble(devices.get( index ).getX())-5,Double.parseDouble(devices.get( index ).getY())-5,10,10);
            gc.setLineWidth(1);
            drawBox.setOnMouseClicked( new EventHandler<MouseEvent>() {
              // @Override
                public void handle(MouseEvent event) {
                    long X = Math.round( event.getX()-100 ) / 5 * 5;
                    long Y = Math.round( event.getY()-100 ) / 5 * 5;
                    gc.strokeRect(X-5,Y-5,10,10);
                    devices.get( index ).setX(Long.toString(X));
                    devices.get( index ).setY(Long.toString(Y));
                    drawWalls("");
                    drawBox.setOnMouseClicked(null);
                    drawBox.setOnMouseMoved(null);
                }
            } );
        }
    }

    public void drawDevices () {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0 ; i<devices.size() ; i++){
            gc.strokeRect(Long.parseLong(devices.get(i).getX())-5,Long.parseLong(devices.get(i).getY())-5,10,10);
        }
    }

    public void savePlan () throws IOException{
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            WritableImage writableImage = new WritableImage(400,400);
            canvas.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        }
    }
}
