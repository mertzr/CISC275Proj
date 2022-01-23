public enum DiverMode {

    SWIMLEFT("swimleft"), SWIMRIGHT("swimright"), CARRYLEFT("carryleft"), CARRYRIGHT("carryright"), FLOAT("float");

	private String name = null;

	private DiverMode(String s){
		name = s;
	}
  /**
  * Diver name getter
  * @return string of name of diver 
  */
	public String getName() {
		return name;
	}
};
