package lab10_1;

public class BubbleSort {

	public static void main(String[] args) {
		int array[]= {8,5,32,13,5,78,6,2,1,44,4};
		//冒泡排序
		for(int i=array.length;i>1;i--) {
			boolean isSwaped=false;
			for(int j=1;j<i;j++) {
				if(array[j]<array[j-1]) {
					int temp=array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
					isSwaped=true;
				}
			}
			//如果本躺没有发生交换，说明已经排序完成
			if(!isSwaped) {
				break;
			}
		}
		for(int now:array) {
			System.out.print(now+" ");
		}
	}

}
