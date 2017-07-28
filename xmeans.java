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
			bw = new BufferedWriter(new FileWriter(new File("Q:\\空载率计算201604\\车次在100-200以内的\\川AA9315_6聚类的结果.csv")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Instances ins = null;
		XMeans x_means = null;
		try {
			CSVLoader loader = new CSVLoader();
			loader.setSource(new File("Q:\\空载率计算201604\\车次在100-200以内的\\车次在100-200以内的车辆信息_带车型\\川AA9315_6.csv"));
			ins = loader.getDataSet();
			// 初始化聚类器 （加载算法）
			x_means = new XMeans();
			x_means.setMinNumClusters(1); 		//设置聚类要得到的类别数量最小值为2，最大值为4
			x_means.setMaxNumClusters(10);
			x_means.buildClusterer(ins);		//开始进行聚类
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
					bw.write("第"+(i+1)+"类的数据:\n");
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
			//			// 打印聚类结果
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
