package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.fail;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimilarityFinderTest {

    @Test
    void testEmptySequences() {
        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcher() {
            @Override
            public SearchResult search(int elem, int[] sequence) {
                return SearchResult.builder().build();
            }
        });
        int tab[] = new int[0];
        int tab2[] = new int[0];
        double result = similarityFinder.calculateJackardSimilarity(tab, tab2);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testCorrectResultWithSequencesOfEqualLength() {
        SimilarityFinder similarityFinder = new SimilarityFinder(new SequenceSearcher() {
            @Override
            public SearchResult search(int elem, int[] sequence) {
                SearchResult searchResult = null;
                if (elem == 1) {
                    searchResult = SearchResult.builder().withPosition(0).withFound(true)
                            .build();
                } else if (elem == 2) {
                    searchResult = SearchResult.builder().withPosition(0).withFound(false)
                            .build();
                }
                return searchResult;
            }
        });
        int tab[] = {1, 2};
        int tab2[] = {1, 3};
        double result = similarityFinder.calculateJackardSimilarity(tab, tab2);
        Assertions.assertEquals(1 / 3.0, result);
    }
}
