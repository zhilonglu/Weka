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
        File inputFile = new File("D://Program Files//Weka-3-6//data//cpu.with.vendor.arff");//ѵ�������ļ�
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
		} // ����ѵ���ļ�    
        inputFile = new File("D:\\Program Files\\Weka-3-6\\data\\cpu.with.vendor.arff");//���������ļ�
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
		} // ��������ļ�
        instancesTest.setClassIndex(0); //���÷������������кţ���һ��Ϊ0�ţ���instancesTest.numAttributes()����ȡ����������
        double sum = instancesTest.numInstances(),//��������ʵ����
        right = 0.0f;
        instancesTrain.setClassIndex(0);
        try {
			m_classifier.buildClassifier(instancesTrain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //ѵ��            
        for(int  i = 0;i<sum;i++)//���Է�����
        {
            try {
				if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())//���Ԥ��ֵ�ʹ�ֵ��ȣ����������еķ������ṩ����Ϊ��ȷ�𰸣�����������壩
				{
				  right++;//��ȷֵ��1
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        System.out.println("J48 classification precision:"+(right/sum));
	}
}
