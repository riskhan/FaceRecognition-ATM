/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;
import org.neuroph.core.*;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
//import org.neuroph.util.TransferFunctionType;
/**
 *
 * @author User
 */

public class NeuralNetwork {
    
    public NeuralNetwork createNetwork()//create the neural network
    {
        MultiLayerPerceptron neural=new MultiLayerPerceptron(100,15,5);
        DataSet training=new DataSet(500);
        return null;
    }

}
