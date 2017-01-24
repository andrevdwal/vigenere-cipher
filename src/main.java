
public class main {

	public static void main(String[] args) {
		// validate and parse args
		if (args.length != 3) {
			displayHelp();
			return;
		}

		// encrypt or decrypt
		boolean encrypt = false;
		if (args[0].equals("-e")) {
			encrypt = true;
		} else if (!args[0].equals("-d")) {
			displayHelp();
			return;
		}

		// key and input text
		String key = args[1];
		String text = args[2];

		// encrypt/decrypt
		char[] result;
		if (encrypt)
			result = encrypt(key.toLowerCase(), text.toLowerCase()).toCharArray();
		else
			result = decrypt(key.toLowerCase(), text.toLowerCase()).toCharArray();

		// fix casing
		for (int i = 0; i < text.length(); i++) {
			char origChar = text.charAt(i);

			// if uppercase
			if ((int)origChar >= 65 && (int)origChar <= 90) {
				result[i] = String.valueOf(result[i]).toUpperCase().charAt(0);
			}
		}

		String resultText = new String(result);
		System.out.println(resultText);
	}

	static String encrypt(String key, String text) {
		KeyManager keyMngr = new KeyManager(key);
		AlphabetMap alphMap = new AlphabetMap();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			builder.append(alphMap.getByText(keyMngr, text.charAt(i)));
		}

		return builder.toString();
	}

	static String decrypt(String key, String text) {
		KeyManager keyMngr = new KeyManager(key);
		AlphabetMap alphMap = new AlphabetMap();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			builder.append(alphMap.getByCipher(keyMngr, text.charAt(i)));
		}

		return builder.toString();
	}

	static void displayHelp() {
		System.out.println("Vigenere Cipher");
		System.out.println("usage: vici -d|-e key text");
		System.out.println("\t-d\tDecrypt text.");
		System.out.println("\t-e\tEncrypt text.");
		System.out.println("\tkey\tThe key for encrypting and decrypting.");
		System.out.println("\ttext\tThe text to perform the action on. Use \"\" for text with spaces.");
	}
}
