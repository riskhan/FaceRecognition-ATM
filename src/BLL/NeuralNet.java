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
import javax.imageio.ImageIO;
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
    
    static File dir = new File(".\\trainingset");
  
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
    
    /**
     * This method creates the training set
     * @param outputSize the size of the output(the number of customers)
     */
    public static void createTrainingset(int outputSize)
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
                    img=ImageIO.read(f);
                    imgName=f.getName();//get the name of the image
                    imgId=imgName.substring(0, 1);//get the id of the image by getting the first letter of the image
                    id=Integer.parseInt(imgId);
                    //to create the output array
                    double[]output=new double[outputSize];
                    Arrays.fill(output,0);
                    output[id-1]=1;
                    //to create the input array
                    double []input=new double[80];
                    /*do the gabor filtering and extract the features and put to dataset*/
                    //to add new input row to the network
                    trainingSet.addRow(new DataSetRow(input,new double[]{2,35,4,5,6}));
                }
                catch(IOException e)
                {
                    //logger
                }
            }
        }
        Normalizer norm=new MaxMinNormalizer();
        norm.normalize(trainingSet);
        trainingSet.saveAsTxt("trainingdataSet.txt",",");
        
    }
    
    
    public void trainNetwork(int outputSize,int hidden,double lrate,double momentum)
    {
        File trainingSet=new File(".\\trainingset\\trainingdataSet.txt");
        DataSet training = null;
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,80,hidden ,outputSize);
        MomentumBackpropagation backProp = new MomentumBackpropagation() ;
        backProp.setLearningRate(lrate);
        backProp.setMomentum(momentum);
        backProp.setMaxError(0.001);
        backProp.setMaxIterations(10000);
        
        if(trainingSet.exists())
        {
            training=DataSet.load(".\\trainingset\\trainingdataSet.txt");
        }
        else
        {
            createTrainingset(outputSize);
        }
        neuralNet.setLearningRule(backProp);
        neuralNet.learn(training);
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
        createTrainingset(5);
    }

}
