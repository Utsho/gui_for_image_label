import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class guiController {
    public Label main_image_folder_label;
    public Label good_image_folder_label;
    public Label bad_image_folder_label;
    public ImageView image_view;
    public Button start_slide_show_bnt;
    public Label end;


    String main_image_directory_path;
    String good_image_directory_path="Good";
    String bad_image_directory_path="Bad";

    File main_image_directory;
    File good_image_directory;
    File bad_image_directory;

    Window window;

    int index=0;


    File[] image_files;

    Image current_image;

    public void setWindow(Window window) {
        this.window = window;
    }

    @FXML
    void initialize(){
        good_image_directory=new File(good_image_directory_path);
        if(!good_image_directory.isDirectory()) good_image_directory.mkdir();

        good_image_directory_path=good_image_directory.getAbsolutePath();

        bad_image_directory=new File(bad_image_directory_path);
        if(!bad_image_directory.isDirectory()) bad_image_directory.mkdir();

        bad_image_directory_path=bad_image_directory.getAbsolutePath();


        good_image_folder_label.setText(good_image_directory.getAbsolutePath());
        bad_image_folder_label.setText(bad_image_directory.getAbsolutePath());

        start_slide_show_bnt.setDisable(true);
        image_view.setVisible(false);

        end.setVisible(false);

    }

    public void select_main_image_folder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        main_image_directory=directoryChooser.showDialog(window);
        main_image_folder_label.setText(main_image_directory.getAbsolutePath());
        start_slide_show_bnt.setDisable(false);



    }

    public void select_good_image_folder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        good_image_directory=directoryChooser.showDialog(window);
        good_image_directory_path=good_image_directory.getAbsolutePath();
    }

    public void select_bad_image_folder(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        bad_image_directory=directoryChooser.showDialog(window);
        bad_image_directory_path=bad_image_directory.getAbsolutePath();
    }

    public void start_slide_show(ActionEvent actionEvent) {
        index=0;
        image_files=main_image_directory.listFiles();
        for(File f:image_files){
            System.out.println(f.getAbsolutePath());
        }
        start_slide_show_bnt.setVisible(false);
        image_view.setVisible(true);
        loadNextImage();
    }

    public void select_good(ActionEvent actionEvent) {
        File image_file=image_files[index];
        String image_file_name=image_file.getName();
        String format=image_file_name.substring(image_file_name.lastIndexOf('.')+1);
        System.out.println(image_file_name);
        System.out.println(format);
        File new_image_file=new File(good_image_directory.getAbsolutePath()+"/"+image_file_name);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(current_image,null),format,new_image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        index++;
        loadNextImage();


    }

    public void select_bad(ActionEvent actionEvent) {
        File image_file=image_files[index];
        String image_file_name=image_file.getName();
        String format=image_file_name.substring(image_file_name.lastIndexOf('.')+1);
        File new_image_file=new File(bad_image_directory.getAbsolutePath()+"/"+image_file_name);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(current_image,null),format,new_image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        index++;
        loadNextImage();
    }


    void loadNextImage(){
        if(index>=image_files.length){
            image_view.setVisible(false);
            end.setVisible(true);
        }
        else{
            current_image=new Image("file:/"+image_files[index].getAbsolutePath());
            image_view.setImage(current_image);
        }
    }
}
