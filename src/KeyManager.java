
public class KeyManager {

	int _idx;
	int _idxMax;
	String _key;

	public KeyManager(String key) {
		_idx = 0;
		_idxMax = key.length() - 1;
		_key = key;
	}
	
	public char getNextChar(){
		if(_idx > _idxMax)
			_idx = 0;
		
		return _key.charAt(_idx++);
	}
}
