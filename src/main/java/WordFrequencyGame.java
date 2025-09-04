import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr){
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<Input>  frequencies = countFrequencies(words);

                frequencies.sort((w1, w2) -> w2.wordCount() - w1.wordCount());
                return composeOutput(frequencies);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String composeOutput(List<Input> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : frequencies) {
            String s = w.value() + " " + w.wordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, Integer> wordCountMap = java.util.Arrays.stream(words)
            .collect(java.util.stream.Collectors.toMap(
                word -> word,
                word -> 1,
                Integer::sum
            ));
        return wordCountMap.entrySet().stream()
            .map(entry -> new Input(entry.getKey(), entry.getValue()))
            .collect(java.util.stream.Collectors.toList());
    }


}
