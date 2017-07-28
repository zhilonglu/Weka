package test;
import java.io.File;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
public class test {
	public static void main(String[] args)
	{
		Classifier m_classifier = new J48();
        File inputFile = new File("D://Program Files//Weka-3-6//data//cpu.with.vendor.arff");//训练语料文件
        ArffLoader atf = new ArffLoader(); 
        try {
			atf.setFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Instances instancesTrain = null;
		try {
			instancesTrain = atf.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 读入训练文件    
        inputFile = new File("D:\\Program Files\\Weka-3-6\\data\\cpu.with.vendor.arff");//测试语料文件
        try {
			atf.setFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
        Instances instancesTest = null;
		try {
			instancesTest = atf.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 读入测试文件
        instancesTest.setClassIndex(0); //设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
        double sum = instancesTest.numInstances(),//测试语料实例数
        right = 0.0f;
        instancesTrain.setClassIndex(0);
        try {
			m_classifier.buildClassifier(instancesTrain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //训练            
        for(int  i = 0;i<sum;i++)//测试分类结果
        {
            try {
				if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
				{
				  right++;//正确值加1
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("J48 classification precision:"+(right/sum));
	}
}
