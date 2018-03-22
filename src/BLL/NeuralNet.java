/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.neuroph.core.*;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
//import org.neuroph.util.TransferFunctionType;
/**
 *
 * @author User
 */

/**
 *
 * @author User
 */
public class NeuralNet {
    
    File dir = new File(".\\trainingset");
    
    
    static final String[] extensions = new String[]{//the file extensions
        "gif", "png", "bmp","jpg"
    };
    
    static final FilenameFilter imgFilter = new FilenameFilter() {//to filter the directory with images

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : extensions) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    
    public void createNetwork(int outputSize)//create the neural network
    {
        String name="";
        double id;
        DataSet trainingSet=new DataSet(80, outputSize);//initialize the dataset
        BufferedImage img=null;
        if(dir.isDirectory())
        {
            for(final File f: dir.listFiles(imgFilter))
            {
                try
                {
                    img=ImageIO.read(f);
                    double[]output=new double[outputSize];
                    
                    /*do the gabor filtering and extract the features and put to dataset*/
                }
                catch(IOException e)
                {
                    //logger
                }
            }
        }
    }
    
    public double[] recognizeFaces(double[] testImage)
    {
        NeuralNetwork neural=NeuralNetwork.load(".\\neuralNet");
        neural.setInput(testImage);
        neural.calculate();
        double[] output=neural.getOutput();
        return output;
    }

}
