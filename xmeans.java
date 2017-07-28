package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import weka.clusterers.XMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;

public class xmeans {
	public static void main(String[] args){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("Q:\\�����ʼ���201604\\������100-200���ڵ�\\��AA9315_6����Ľ��.csv")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Instances ins = null;
		XMeans x_means = null;
		try {
			CSVLoader loader = new CSVLoader();
			loader.setSource(new File("Q:\\�����ʼ���201604\\������100-200���ڵ�\\������100-200���ڵĳ�����Ϣ_������\\��AA9315_6.csv"));
			ins = loader.getDataSet();
			// ��ʼ�������� �������㷨��
			x_means = new XMeans();
			x_means.setMinNumClusters(1); 		//���þ���Ҫ�õ������������СֵΪ2�����ֵΪ4
			x_means.setMaxNumClusters(10);
			x_means.buildClusterer(ins);		//��ʼ���о���
			System.out.print(x_means);
			int[] sum = new int[x_means.numberOfClusters()];
			String[] output = new String[x_means.numberOfClusters()];
			for (int j = 0; j < ins.numInstances(); j++)
				output[x_means.clusterInstance(ins.instance(j))] = "";
			try {
				for (int i = 0; i < ins.numInstances(); i++) {
					output[x_means.clusterInstance(ins.instance(i))] += ins.instance(i) + "\n";
//					System.out.println(ins.instance(i));
					sum[x_means.clusterInstance(ins.instance(i))]++;
				}
				for(int i = 0; i < x_means.numberOfClusters(); i++){
					bw.write("��"+(i+1)+"�������:\n");
					bw.write(output[i]);
				}
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			double[] fre = new double[sum.length];
			for (int i = 0; i < sum.length; i++) {
				fre[i] = (double)sum[i] / (double)ins.numInstances();
			}
			for(int i=0;i<fre.length;i++)
				System.out.println(fre[i]);
			//			// ��ӡ������
			//			String output = x_means.getClusterCenters().toString();
			//			String[] data;
			//			data = output.split("\n");
			//			for(int i=0;i<data.length;i++){
			//				System.out.println(data[i]);
			//			}
			System.out.print(x_means.getClusterCenters().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
