package encodingxmlcvs;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * this class has the methods which convert sentences to words and words are
 * also in sorted format
 * 
 * @author Pavan Mittal
 *
 */

public class ParagraphToSentence {

	public static void main(String arg[]) {
		//String ar[] = {"small.in"};
		//String ar[] = {"large.in"};
		final String path = System.getProperty(EncoderConstant.CURRENT_DIRECTORY);
        System.out.println(EncoderConstant.WORKING_DIRECTORY+path);
		if (Objects.nonNull(arg) && arg.length != EncoderConstant.IN_ACTIVE_SHORT) {
			final String fileName = arg[EncoderConstant.IN_ACTIVE_SHORT];
			if (Objects.nonNull(fileName) && !fileName.isBlank()) {
				final String filePath=path+ File.separator+fileName;
				final String filePrefix= fileName.split(EncoderConstant.DOT)
						[EncoderConstant.IN_ACTIVE_SHORT];
				System.out.println(filePrefix);
				ParagraphToSentence paragraphToSentence = new ParagraphToSentence();
			    paragraphToSentence.processFile(filePath,filePrefix);
			} else {
				System.out.println(EncoderConstant.SPECIFY_FILE_NAME);
			}
		} else {
			System.out.println(EncoderConstant.PASS_FIRST_ARGUMENT);
		}
	}

	/**
	 * this function is used to process the file which is specified with fileName
	 * 
	 * @param fileName
	 */
	private void processFile(final String fileName,final String filePrefix) {
		try {
			if (Objects.nonNull(fileName) && !fileName.isBlank()) {
				Path path = Paths.get(fileName);
				if (Files.isRegularFile(path)) {
					this.readFile(path,filePrefix);
				} else {
					System.out.println(EncoderConstant.WRONG_FILE_PATH);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to read a file
	 * 
	 * @param path
	 */
	private void readFile(final Path path,final String filePrefix) {
		try {
			if (Objects.nonNull(path)) {
				final List<String> allLines = Files.readAllLines(path);
				final StringBuilder makeParagraph = new StringBuilder();
				for (final String line : allLines) {
						makeParagraph.append(line);
				}
				if (Objects.nonNull(makeParagraph) && !makeParagraph.isEmpty()) {
					this.paragraphToSentences(makeParagraph.toString(),filePrefix);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to convert paragraph to sentences
	 * 
	 * @param string
	 */
	private void paragraphToSentences(final String paragraph,final String filePrefix) {
		try {
			if (Objects.nonNull(paragraph) && !paragraph.isBlank()) {
				final Pattern pt = Pattern.compile(EncoderConstant.SENTENCE_END_PATTERN);
				final String[] values = pt.split(paragraph);
				final List<String> sentencesAsList = Arrays.asList(values);
				this.setSentenceWithWordsInMap(sentencesAsList,filePrefix);

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to set sentences with sorted words as dictionary and
	 * also remove special character in map
	 * 
	 * @param sentencesAsList
	 */
	private void setSentenceWithWordsInMap(final List<String> sentencesAsList,final String filePrefix) {
		Sentences sentences = null;
		try {
			if (Objects.nonNull(sentencesAsList) && !sentencesAsList.isEmpty()) {
				sentences = new Sentences();
				final Map<Sentence, Boolean> sentencesMap = Collections.synchronizedMap(sentences.sentenceMap);
				sentencesAsList.stream().forEach(sentence -> {
					try {
						if (Objects.nonNull(sentence) && !sentence.isBlank()) {
							// sorted words as dictionary
							final Comparator<String> dictonaryOrder = (final String wordPrevious,
									final String wordNew) -> wordPrevious.toLowerCase()
											.compareTo(wordNew.toLowerCase());
							sentence = this.setSentenceOtherOperation(sentence);
							final Pattern pt = Pattern.compile(EncoderConstant.SENTENCE_SPLIT_PATTERN);
							final String[] wordSplited = pt.split(sentence);
							final List<String> unsortedWords = this.removeEmptyString(wordSplited);
							final List<String> words = unsortedWords.stream().sorted(dictonaryOrder)
									.collect(Collectors.toList());
							System.out.println(words);
							sentencesMap.put(new Sentence(words), true);
						}
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						ex.printStackTrace();
					}
				});
				sentences.setSentences(sentencesMap);
				this.writeSentencesInXmlCsv(sentences,filePrefix);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to remove empty string after split sentences in words
	 * 
	 * @param wordSplited
	 * @return
	 */
	private List<String> removeEmptyString(final String[] wordSplited) {
		final List<String> wordList = new ArrayList<>();
		try {
			Arrays.stream(wordSplited).forEach((word) -> {
				if (Objects.nonNull(word) && !word.isBlank()) {
					wordList.add(word);
				}
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return wordList;
	}

	/**
	 * this function is used to perform other operation like trim string and remove
	 * dot if exist as last character
	 * 
	 * @param sentence
	 * @return
	 */
	private String setSentenceOtherOperation(String sentence) {
		try {
			if (Objects.nonNull(sentence) && !sentence.isBlank()) {
				sentence = sentence.trim();
				if (sentence.endsWith(EncoderConstant.ONLY_DOT)) {
					StringBuffer sb = new StringBuffer(sentence);
					sb.deleteCharAt(sb.length() - EncoderConstant.ACTIVE_SHORT);
					sentence = sb.toString();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return sentence;
	}

	/**
	 * this function is use used to start the process to write sentences in XML and
	 * CSv
	 * 
	 * @param sentences
	 */
	private void writeSentencesInXmlCsv(final Sentences sentences,final String filePrefix) {
		try {
			if (Objects.nonNull(sentences)) {
				final Encoder encoder = new Encoder();
				encoder.writeSentencesAsXml(sentences, filePrefix+EncoderConstant.DOT_XML);
				encoder.writeSentencesAsCsv(sentences, filePrefix+EncoderConstant.DOT_CSV);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
