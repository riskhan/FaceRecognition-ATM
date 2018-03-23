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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.neuroph.core.*;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.data.norm.MaxMinNormalizer;
import org.neuroph.core.data.norm.Normalizer;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;
/**
 *
 * @author User
 */

/**
 *
 * @author User
 */
public class NeuralNet {
    
     //Logger logger = Logger.getLogger(NeuralNet.class.getName());
     File dir = new File(".\\trainingset");
  
     final String[] extensions = new String[]{//the file extensions
        "gif", "png", "bmp","jpg"
    };
    
    final FilenameFilter imgFilter = new FilenameFilter() {//to filter the directory with images

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
    
    /**
     * This method creates the training set
     * @param outputSize the size of the output(the number of customers)
     */
    public void createTrainingset(int outputSize)
    {
        String imgName,imgId;
        int id,x;
        DataSet trainingSet=new DataSet(80, outputSize);//initialize the dataset
        BufferedImage img=null;
        if(dir.isDirectory())
        {
            for(final File f: dir.listFiles(imgFilter))
            {
                try
                {
                    img = ImageIO.read(f);
                    imgName = f.getName();//name of the image
                    imgId = imgName.substring(0, 1);//id of the image by getting the first letter of the image
                    id = Integer.parseInt(imgId);
                    
                    double[]output=new double[outputSize];
                    Arrays.fill(output,0);
                    output[id-1]=1;
                    
                    double []input=new double[80];
                    /*do the gabor filtering and extract the features and put to dataset*/

                    trainingSet.addRow(new DataSetRow(input,output));
                    System.out.println((output.length));
                }
                catch(IOException e)
                {
                    //logger.log(Level.SEVERE,"Cannot create the dataset");//remove all the static
                }
            }
        }
        trainingSet.normalize();
        trainingSet.saveAsTxt("trainingdataSet.txt",",");
        
    }
    
    
    public static void trainNetwork(int outputSize,int hidden,double lrate,double momentum)
    {
        File trainingFile=new File(".\\trainingdataSet.txt");
        DataSet trainingSet = null;
        if(trainingFile.exists())
        {
            trainingSet=DataSet.load(".\\trainingdataSet.txt");
            if(trainingSet.getOutputSize()!=outputSize)
            {
                JOptionPane.showMessageDialog(null,"Output sizes dont match create/load dataset first","ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No dataset found","ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        //int outputSize=0;
        //outputSize=training.getOutputSize();
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,81,hidden ,outputSize);
        MomentumBackpropagation backProp = new MomentumBackpropagation() ;
        backProp.setLearningRate(lrate);
        backProp.setMomentum(momentum);
        backProp.setMaxError(0.001);
        backProp.setMaxIterations(10000);
        
        
        neuralNet.setLearningRule(backProp);
        neuralNet.learn(trainingSet);
    }
    
    /**
     * Method to recognize input faces
     * @param testImage the features of the image to be recognized
     * @returns the highest output of the neural network
     */
    public int recognizeFaces(double[] testImage)
    {
        NeuralNetwork neural=NeuralNetwork.load(".\\faceRec.nnet");
        neural.setInput(testImage);
        neural.calculate();
        double[] output=neural.getOutput();//get the output of the neural network
        
        //method to get the highest id from the output 
        int high=0,id;
        for(int i=1;i<output.length;i++)
        {
            if(output[i]>=output[high])
            {
                high=i;
            }     
        }
        id=high+1;
        return id;
    }

    public static void main(String args[])
    {
        trainNetwork(3,3,0.2,0.7);
    }
}
