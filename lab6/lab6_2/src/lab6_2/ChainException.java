package lab6_2;

public class ChainException {

	public static void main(String[] args) {
		try {
			m1();
		}catch(Exception e) {
			System.err.printf("错误信息:%s\n\n",e.getMessage());
			e.printStackTrace();
			
			StackTraceElement [] traceElements=e.getStackTrace();
			
			System.out.println("\n错误发生的层次结构:");
			System.out.println("Class\t\t\tFile\t\t\tLinet\tMethod");
			
			for(StackTraceElement element:traceElements) {
				System.out.printf("%s\t",element.getClassName());
				System.out.printf("%s\t",element.getFileName());
				System.out.printf("%s\t",element.getLineNumber());
				System.out.printf("%s\n",element.getMethodName());
				
			}
		}
	}
	
	
	public static void m1() throws Exception {
		try {
			m2();
		}catch(Exception e) {
			throw new Exception("m2抛出的异常");
		}
	}
	
	public static void m2() throws Exception {
		throw new Exception("错误的处理");
	}

}
