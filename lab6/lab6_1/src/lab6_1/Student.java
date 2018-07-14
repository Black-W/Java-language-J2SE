package lab6_1;

public class Student {
	private String name;
	private String address;
	public void setName(String name) throws IllegalNameException {
		if(name.length()<1) {
			throw new IllegalNameException("姓名长度小于1");
		}
		else if(name.length()>5) {
			throw new IllegalNameException("姓名长度大于5");
		}
		this.name = name;
	}
	public void setAddress(String address) throws IllegalAddressException{
		if(!address.contains("省")) {
			throw new IllegalAddressException("地址中不含有“省”关键字");
		}
		else if(!address.contains("市")) {
			throw new IllegalAddressException("地址中不含有“市”关键字");
		}
		this.address = address;
	}
}
