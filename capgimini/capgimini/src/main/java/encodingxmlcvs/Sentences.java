package encodingxmlcvs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name=EncoderConstant.TEXT)
@XmlAccessorType(XmlAccessType.FIELD)
public class Sentences {
    
	@XmlTransient
	public Map<Sentence,Boolean> sentenceMap=new LinkedHashMap<>();
    
    List<Sentence> sentence ;
    
    public Sentences(){
    	
    }
	
	public List<Sentence> getSentences() {
		return sentence;
	}

	public void setSentences(Map<Sentence,Boolean> sentenceMap) {
		if(Objects.nonNull(sentenceMap)) {
			Set<Sentence> sentencesAsKey=new LinkedHashSet<>();
			sentenceMap.forEach((sentence,value)->{
				if(Objects.nonNull(value) && value.equals(true))
					sentencesAsKey.add(sentence);
			});
			this.sentence = new ArrayList<>(sentencesAsKey);
		}
		
	}
	
    
}
