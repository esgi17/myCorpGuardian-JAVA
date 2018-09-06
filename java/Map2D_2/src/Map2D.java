import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pa.Models.Api;
import pa.Models.ListDatas;
import pa.Models.NavHandler;
import pa.Models.Wall;
import pa.plugins.Map2DPlugins;
import pa.plugins.Plugins;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.SocketPermission;

public class Map2D implements Map2DPlugins {

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
    @FXML AnchorPane pane;

    private ObservableList<pa.Models.Wall> walls = FXCollections.observableArrayList();
    private ObservableList<String> wallsName = FXCollections.observableArrayList();

    private ObservableList<pa.Models.Device> devices = FXCollections.observableArrayList();
    private ObservableList<String> devicesName = FXCollections.observableArrayList();


    public void initialize () throws Exception {
        wallList.getItems().clear();
        deviceList.getItems().clear();
        devices.clear();
        devicesName.clear();
        walls.clear();
        wallsName.clear();
        fillDeviceList();
        drawWalls("");
        fillWallList();
    }

    public void openHomePage() throws Exception {
        NavHandler.openHomePage(pane);
    }

    public void fillWallList () throws Exception {
        pa.Models.Wall[] wallsLoad = ListDatas.getWalls();
        wallList.getItems().clear();
        for(int i=0 ; i<wallsLoad.length ; i++){
            System.out.println(i);
            walls.add(wallsLoad[i]);
            wallsName.add(wallsLoad[i].getName());
            drawExistingWall(wallsLoad[i]);
        }
        wallList.setItems(wallsName);
    }

    public void fillDeviceList () throws Exception {
        pa.Models.Device[] res = ListDatas.getDevicesXY();
        deviceList.getItems().clear();
        for(int i=0 ; i< res.length ; i++ ){
            switch(res[i].getDeviceTypeId()){
                case "1":
                    devices.add(res[i]);
                    devicesName.add("Door      : " + res[i].getName());
                    break;
                case "2":
                    devices.add(res[i]);
                    devicesName.add("Captor   : " + res[i].getName());
                    break;
                case "3":
                    devices.add(res[i]);
                    devicesName.add("Pass       : " + res[i].getName());
                    break;
                case "4":
                    devices.add(res[i]);
                    devicesName.add("Camera  : " + res[i].getName());
                    break;
                default:
                    break;
            }
        }
        deviceList.setItems(devicesName);
    }

    public void drawExistingWall(Wall wall){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeLine( Double.parseDouble(wall.getX1()), Double.parseDouble(wall.getY1()), Double.parseDouble(wall.getX2()), Double.parseDouble(wall.getY2()));
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
                            pa.Models.Wall wall = new pa.Models.Wall();
                            wall.Wall(nameWallField.getText(), Long.toString( beginX /5*5), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall );
                            try{
                                ListDatas.postWall(wall.getName(),wall.getX1(),wall.getY1(),wall.getX2(),wall.getY2());
                            }
                            catch (Exception e){
                                System.out.println(e);
                            }
                            wallsName.add(wall.getName());
                            nameWallField.setText("");

                        } else if (shape == 1) {
                            gc.strokeRect( beginX, beginY, endX - beginX, endY - beginY );
                            pa.Models.Wall wall1 = new pa.Models.Wall();
                            wall1.Wall(nameRoomField.getText() + "O", Long.toString( beginX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( beginX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall1 );
                            try{
                                ListDatas.postWall(wall1.getName(),wall1.getX1(),wall1.getY1(),wall1.getX2(),wall1.getY2());
                            }
                            catch (Exception e){

                            }
                            wallsName.add(wall1.getName());

                            pa.Models.Wall wall2 = new pa.Models.Wall();
                            wall2.Wall(nameRoomField.getText() + "N", Long.toString( beginX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( beginY/5*5 ) );
                            walls.add( wall2 );
                            try{
                                ListDatas.postWall(wall2.getName(),wall2.getX1(),wall2.getY1(),wall2.getX2(),wall2.getY2());
                            }
                            catch (Exception e){

                            }
                            wallsName.add(wall2.getName());

                            pa.Models.Wall wall3 = new pa.Models.Wall();
                            wall3.Wall(nameRoomField.getText() + "E", Long.toString( endX/5*5 ), Long.toString( beginY/5*5 ), Long.toString( endX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall3 );
                            try{
                                ListDatas.postWall(wall3.getName(),wall3.getX1(),wall3.getY1(),wall3.getX2(),wall3.getY2());
                            }
                            catch (Exception e){

                            }
                            wallsName.add(wall3.getName());

                            pa.Models.Wall wall4 = new pa.Models.Wall();
                            wall4.Wall(nameRoomField.getText() + "S", Long.toString( endX/5*5 ), Long.toString( endY/5*5 ), Long.toString( beginX/5*5 ), Long.toString( endY/5*5 ) );
                            walls.add( wall4 );
                            try{
                                ListDatas.postWall(wall4.getName(),wall4.getX1(),wall4.getY1(),wall4.getX2(),wall4.getY2());
                            }
                            catch (Exception e){

                            }
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

    public void deleteWall () throws Exception{
        int index = wallList.getSelectionModel().getSelectedIndex();
        if (index != -1){
            ListDatas.deleteWall(wallsName.get(index));
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
        if(index != -1 ) {

            drawWalls("");
            if(devices.get(index).getX()!=null && devices.get(index).getY()!=null) {
                gc.setLineWidth( 5 );
                gc.strokeRect( Double.parseDouble( devices.get( index ).getX() ) - 5, Double.parseDouble( devices.get( index ).getY() ) - 5, 10, 10 );
                gc.setLineWidth( 1 );
            }
            drawBox.setOnMouseClicked( new EventHandler<MouseEvent>() {
                // @Override
                public void handle(MouseEvent event) {
                    long X = Math.round( event.getX()-100 ) / 5 * 5;
                    long Y = Math.round( event.getY()-100 ) / 5 * 5;
                    gc.strokeRect(X-5,Y-5,10,10);
                    devices.get( index ).setX(Long.toString(X));
                    devices.get( index ).setY(Long.toString(Y));
                    try{
                        ListDatas.putDevice(devices.get(index).getName(),devices.get(index).getX(),devices.get(index).getY());
                    } catch (Exception e){
                        System.out.println(e);
                    }
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
            if(devices.get(i).getX()!=null && devices.get(i).getY()!=null) {
                gc.strokeRect( Long.parseLong( devices.get( i ).getX() ) - 5, Long.parseLong( devices.get( i ).getY() ) - 5, 10, 10 );
            }
        }
    }

    public void savePlan () throws IOException {
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


    public static void main(String[] args) {
        //launch(args);
    }

    public void print(String str) {

    }


    public void plug() {
        System.out.println("Map2D plugged");
    }

    public void unplug() {
        System.out.println("Map2D unPlugged");
    }

    public String getName() {
        return "Map2D";
    }
}


