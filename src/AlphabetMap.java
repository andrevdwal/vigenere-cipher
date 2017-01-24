
public class AlphabetMap {

	final int LOWER_OFFSET = 97;

	int[][] _map = new int[26][26];

	public AlphabetMap() {
		// fill map
		for (int i = 0; i < _map[0].length; i++) {
			for (int j = 0; j < _map[1].length; j++) {
				int total = i + j;
				while (true) {
					if (total > 25)
						total -= 26;
					else
						break;
				}

				_map[i][j] = LOWER_OFFSET + total;
			}
		}
	}

	public char getByText(KeyManager keyMngr, char textChar) {
		int textIdx = ((int) textChar) - LOWER_OFFSET;
		if (textIdx < 0 || textIdx > 25)
			return textChar;	

		int keyIdx = ((int) keyMngr.getNextChar()) - LOWER_OFFSET;
		if (keyIdx < 0 || keyIdx > 25)
			throw new IllegalArgumentException("Invalid key. It must be a value between a-z or A-Z.");

		return (char) _map[keyIdx][textIdx];
	}

	public char getByCipher(KeyManager keyMngr, char cipherChar) {
		int cipherIdx = ((int) cipherChar) - LOWER_OFFSET;
		if (cipherIdx < 0 || cipherIdx > 25)
			return cipherChar;	

		int keyIdx = ((int) keyMngr.getNextChar()) - LOWER_OFFSET;
		if (keyIdx < 0 || keyIdx > 25)
			throw new IllegalArgumentException("Invalid key. It must be a value between a-z or A-Z.");

		int[] alphabet = _map[keyIdx];
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == (cipherIdx+LOWER_OFFSET))
				return (char) (i+LOWER_OFFSET);
		}

		throw new RuntimeException("An unexpected error occured.");
	}
}
