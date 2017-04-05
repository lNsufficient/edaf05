public class CP {
    public static void main(String args[]) {
        String fileName = "../data/"+args[0]+"txt";
        File file = new File(fileName);
		Scanner scan;

		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				String currentLine = scan.nextLine();
                
                
            }
        } catch (FileNotFoundException e) {

        }
    }
}
