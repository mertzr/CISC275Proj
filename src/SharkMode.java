public enum SharkMode {

    DEFAULT("default"), EAT("eat");

	private String name = null;

  /**
   * Constructor of SharkMode from the string that tells if its eating or not
   * @param s String that is shark's mode (eating or not)
   */
	private SharkMode(String s){
		name = s;
	}

  /**
   * Getter for name of shark's mode
   * @return String that is the name of Shark's mode
   */
	public String getName() {
		return name;
	}
};
