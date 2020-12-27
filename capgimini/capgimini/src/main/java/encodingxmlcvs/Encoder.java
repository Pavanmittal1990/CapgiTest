package encodingxmlcvs;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *  this class has function to write sentences in XML and csv file 
 *  as as desired format which is represented by 
 *  // XML
 *  <text>
 *     <sentence>
 *      <word>xyz</word>
 *     </sentence>
 *     <sentence>
 *       <word>xyz</word>
 *     </sentence>
 *  </text>
 *  
 *  CSV format
 *             word 1 ,word 2,word 3...
 * sentence 1  hey
 * sentence 2  how , are, you  
 * @author Pavan Mittal
 *
 */
@Service
public class Encoder {

	/**
	 * this function is used to write XML file
	 * 
	 * @param sentences
	 * @throws JAXBException
	 * @throws IOException
	 */

	public void writeSentencesAsXml(final Sentences sentences,final String fileName) throws JAXBException, IOException {
		try {
			if (Objects.nonNull(sentences)) {
				final StringWriter writer = new StringWriter();
				final JAXBContext context = JAXBContext.newInstance(Sentences.class);
				final Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(sentences, writer);
				this.createFolder(writer.toString(), fileName);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * this function is used to write sentences with words as a CSV file
	 * 
	 * @param sentencesList
	 */
	public void writeSentencesAsCsv(final Sentences sentencesList,final String fileName) {
		try {
			if (Objects.nonNull(sentencesList)) {
				final List<Sentence> sentences = sentencesList.getSentences();
				if (Objects.nonNull(sentences) && !sentences.isEmpty()) {
					final StringBuffer buffer = new StringBuffer(EncoderConstant.SPACE_COMMA);
					// for header need max count to define Word maxcount
					final int maxWordCount = this.getSentenceMaxWordCount(sentences);
					this.setHeaders(maxWordCount, buffer);
					buffer.append(EncoderConstant.NEW_LINE);
					final AtomicInteger sentenceCount = new AtomicInteger(EncoderConstant.ACTIVE_SHORT);
					sentences.stream().forEach(sentence -> {
						try {
							final List<String> words = sentence.getWord();
							if (Objects.nonNull(words) && !words.isEmpty()) {
								final String wordsAsString = String.join(EncoderConstant.COMMA, words);
								// add sentence 1 like
								buffer.append(EncoderConstant.SENTENCE_SPACE)
										.append(sentenceCount.getAndAdd(EncoderConstant.ACTIVE_SHORT))
										.append(EncoderConstant.COMMA);
								// add words like word1, word2,
								buffer.append(wordsAsString);
							}
						} catch (Exception ex) {
							System.out.println(ex.getMessage());
							ex.printStackTrace();
						}
						buffer.append(EncoderConstant.NEW_LINE);
					});
					this.createFolder(buffer.toString(), fileName);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to write the content in a file in a folder
	 * XML_CVS_Encoded_Files
	 * 
	 * @param content
	 */

	private void createFolder(final String content, final String fileName) {
		try {
			if (Objects.nonNull(content) && !content.isBlank() && Objects.nonNull(fileName) && !fileName.isBlank()) {
				final String path = System.getProperty(EncoderConstant.CURRENT_DIRECTORY);
				final File directory=new File(path);
				if (directory.isDirectory()) {
					final String XML_CVS_Encoded_Files = EncoderConstant.XML_CVS_ENCODED_FILES;
					final File folder = new File(directory + File.separator + XML_CVS_Encoded_Files);
					if (!folder.exists()) {
						folder.mkdir();
					}
					this.createFileAndWriteContent(directory, fileName, content);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * this function is used to
	 * 
	 * @param maxWordCount
	 * @param buffer
	 * @return
	 */
	private StringBuffer setHeaders(final int maxWordCount, final StringBuffer buffer) {
		try {
			for (int i = EncoderConstant.ACTIVE_SHORT; i <= maxWordCount; i++) {
				buffer.append(EncoderConstant.WORD_SPACE + i + EncoderConstant.COMMA);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return buffer;
	}

	/**
	 * this function is used to get max word count in all the sentences
	 * 
	 * @param sentences
	 * @return
	 */
	private int getSentenceMaxWordCount(final List<Sentence> sentences) {
		int maxCount = EncoderConstant.IN_ACTIVE_SHORT;
		try {
			if (Objects.nonNull(sentences) && !sentences.isEmpty()) {
				final Comparator<Sentence> comparator = Comparator.comparing(sentence -> sentence.getWord().size());
				final Sentence maxCountSentence = sentences.stream().max(comparator).get();
				maxCount = maxCountSentence.getWord().size();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return maxCount;
	}


	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param content
	 * @throws Exception
	 */
	private void createFileAndWriteContent(final File directory, final String fileName, final String content)
			throws Exception {
		File file = null;
		try {
			if (Objects.nonNull(directory) && Objects.nonNull(fileName) && Objects.nonNull(content)
					&& !fileName.isBlank() && !content.isBlank()) {
				final String XML_CVS_Encoded_Files = EncoderConstant.XML_CVS_ENCODED_FILES;
				file = new File(directory + File.separator + XML_CVS_Encoded_Files + File.separator + fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
				Files.write(Paths.get(directory + File.separator + XML_CVS_Encoded_Files + File.separator + fileName),
						content.getBytes());
				System.out.println(EncoderConstant.WRITTEN_FILE_IS+file.getPath());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
}
