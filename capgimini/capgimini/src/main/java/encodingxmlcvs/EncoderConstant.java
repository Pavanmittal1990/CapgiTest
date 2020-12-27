package encodingxmlcvs;

public interface EncoderConstant {
	  String PASS_FIRST_ARGUMENT="Please pass file name as first argument";
      Short  IN_ACTIVE_SHORT=0;
      Short  ACTIVE_SHORT=1;
      String SPECIFY_FILE_NAME="Please provide the file name ";
      String WRONG_FILE_PATH="Please provide the file path proper ";
     // String SENTENCE_FIRSTPATTERN="(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s+";
      String TEXT="text";
      String SPACE="\\s";
      String SPACE_PLUS="\\s+";
      String SENTENCE_SPLIT_PATTERN="(,)|(-)|(\\s+)|(,[\\s+])|(-[\\s+])|([\\s+],)|([\\s+]-)|(:)|\\)|\\(";
      String CURRENT_DIRECTORY="user.dir";
      String COLON=":";
      String XML_CVS_ENCODED_FILES="XML_CVS_Encoded_Files";
      String SPACE_COMMA=" ,";
      String NEW_LINE="\r\n";
      String SENTENCE_SPACE="sentence ";
      String COMMA=",";
      String WORD_SPACE="word ";
      String WRITTEN_FILE_IS="written file is ";
      String DOT="\\.";
      String WORKING_DIRECTORY="current working directory";
      String DOT_XML=".xml";
      String DOT_CSV=".csv";
      String ONLY_DOT=".";
     // String SENTENCE_CHECK_PATTERN="((\\.)|(-)|(!)|(\\?))";
     // String SENTENCE_CHECK_PATTERN_TWO="(!|\\?|-)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)((?<=\\.|\\?)\\s+))";
     // String SENTENCE_CHECK_PATTERN_THREE="(!|\\?|-)|(/\\.(?=[A-Z])/)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)((?<=\\.|\\?)\\s+))|\\.(?=[A-Z])";
     // String SENTENCE_CHECK_PATTERN_FOUR="(!|\\?|-)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s+)|\\.(?=[A-Z])";
      
     // String SENTENCE_LOOKAHEAD="\\.(?=[A-Z])";
     // String SENTENCE_DOT_SPACE="\\.(?=\\s+)";
      //String SENTENCE_CHECK_PATTERN_FIFTH="(!|\\?|-)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)((?<=\\.|\\?)\\s+|(\\.(?=\\s+))))|\\.(?=[A-Z])";
      //String SENTENCE_CHECK_PATTERN_SIXTH="(!|\\?|-)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(\\.(?=\\s+)))|\\.(?=[A-Z])";
      
      String SENTENCE_END_PATTERN="(!|\\?)|((?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s+)|(\\.(?=[A-Z]))";
}
