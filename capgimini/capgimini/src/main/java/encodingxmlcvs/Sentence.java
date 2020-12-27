package encodingxmlcvs;

import java.util.List;

public class Sentence {
	
    List<String> word;
    
    public Sentence(){
    	
    }
    public Sentence( List<String> word) {
    	this.word =word;
    }
	public List<String> getWord() {
		return word;
	}
	public void setWord(List<String> word) {
		this.word = word;
	}
}
