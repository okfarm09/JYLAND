package project.jyland.helper;

public class UploadPath {
	
	private static final String KIM_PATH="C:\\Users\\Jermy\\git\\JYLAND\\project0515\\WebContent\\upload";
	private static final String NAM_PATH="/home/namo/ho2/git/JYLAND/project0515/WebContent/upload";
	private static final String LEE_PATH="C:\\Users\\JY\\git\\JYLAND\\project0515\\WebContent\\upload";
	private static final String CHOI_PATH="F:\\git\\JYLAND\\project0515\\WebContent\\upload";

	public static String getPath(int pathNum) {
		if(pathNum==1) {
			return KIM_PATH;
		}else if(pathNum==2) {
			return NAM_PATH;
		}else if(pathNum==3) {
			return LEE_PATH;
		}else if(pathNum==4) {
			return CHOI_PATH;
		}else {
			return "";
		}
	}
	
}
